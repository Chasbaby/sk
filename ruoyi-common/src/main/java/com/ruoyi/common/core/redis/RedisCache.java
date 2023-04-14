package com.ruoyi.common.core.redis;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.annotation.Transient;
import org.springframework.data.crossstore.ChangeSetBackedTransactionSynchronization;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.connection.jedis.JedisConverters;
import org.springframework.data.redis.connection.jedis.JedisUtils;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * spring redis 工具类
 *
 * @author ruoyi chas
 **/
@SuppressWarnings(value = { "unchecked", "rawtypes" })
@Component
public class RedisCache
{

    @Autowired
    public RedisTemplate redisTemplate;


    /**
     * 给单个键加锁
     * @param key 缓存的键值
     */
    public void lock(String key){
        redisTemplate.multi();
        redisTemplate.watch(key);
    }

    /**
     * 给多个键加锁
     * @param keys 缓存的键值们
     * @param <K>
     */
    public <K> void lockManyKey(Collection<K> keys){
        redisTemplate.multi();
        redisTemplate.watch(keys);
    }

    /**
     * key 是否过期
     * false 没过期  true 过期
     * @param key
     * @return
     */
    public Boolean isExpire(String key){
       return  redisTemplate.opsForValue().getOperations().getExpire(key) > 0 ?false:true;
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key 缓存的键值
     * @param value 缓存的值
     */
    public <T> void setCacheObject(final String key, final T value)
    {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key 缓存的键值
     * @param value 缓存的值
     * @param timeout 时间
     * @param timeUnit 时间颗粒度
     */
    public <T> void setCacheObject(final String key, final T value, final Integer timeout, final TimeUnit timeUnit)
    {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * 设置有效时间
     *
     * @param key Redis键
     * @param timeout 超时时间
     * @return true=设置成功；false=设置失败
     */
    public boolean expire(final String key, final long timeout)
    {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置有效时间
     *
     * @param key Redis键
     * @param timeout 超时时间
     * @param unit 时间单位
     * @return true=设置成功；false=设置失败
     */
    public boolean expire(final String key, final long timeout, final TimeUnit unit)
    {
        return redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public <T> T getCacheObject(final String key)
    {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * 删除单个对象
     *
     * @param key
     */
    public boolean deleteObject(final String key)
    {
        return redisTemplate.delete(key);
    }

    /**
     * 删除集合对象
     *
     * @param collection 多个对象
     * @return
     */
    public long deleteObject(final Collection collection)
    {
        return redisTemplate.delete(collection);
    }

    /**
     * 缓存List数据
     *
     * @param key 缓存的键值
     * @param dataList 待缓存的List数据
     * @return 缓存的对象
     */
    public <T> long setCacheList(final String key, final List<T> dataList)
    {
        Long count = redisTemplate.opsForList().rightPushAll(key, dataList);
        return count == null ? 0 : count;
    }

    /**
     * 获得缓存的list对象
     *
     * @param key 缓存的键值
     * @return 缓存键值对应的数据
     */
    public <T> List<T> getCacheList(final String key)
    {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     *  获取缓存的list对象 前num个
     * @param key
     * @param num
     * @param <T>
     * @return
     */
    public <T> List<T> getCacheListRange(final String key,int begin,int end){
        return redisTemplate.opsForList().range(key,begin,end);
    }

    /**
     * 缓存Set
     *
     * @param key 缓存键值
     * @param dataSet 缓存的数据
     * @return 缓存数据的对象
     */
    public <T> BoundSetOperations<String, T> setCacheSet(final String key, final Set<T> dataSet)
    {
        BoundSetOperations<String, T> setOperation = redisTemplate.boundSetOps(key);
        Iterator<T> it = dataSet.iterator();
        while (it.hasNext())
        {
            setOperation.add(it.next());
        }
        return setOperation;
    }

    /**
     * 获得缓存的set
     *
     * @param key
     * @return
     */
    public <T> Set<T> getCacheSet(final String key)
    {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 缓存Map
     *
     * @param key
     * @param dataMap
     */
    public <T> void setCacheMap(final String key, final Map<String, T> dataMap)
    {
        if (dataMap != null) {
            redisTemplate.opsForHash().putAll(key, dataMap);
        }
    }

    /**
     * 缓存Map 更灵活
     * @param key
     * @param dataMap
     * @param <K>
     * @param <V>
     */
    public <K,V> void setCacheMapKV(final String key , final Map<K,V> dataMap ){
        if (dataMap !=null){
            redisTemplate.opsForHash().putAll(key,dataMap);
        }
    }
    /**
     * 获得缓存的Map
     *
     * @param key
     * @return
     */
    public <T> Map<String, T> getCacheMap(final String key)
    {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 获得缓存的Map 更灵活
     * @param key
     * @param <K>
     * @param <V>
     * @return
     */
    public <K,V> Map<K,V> getCacheMapKV(final String key){
        return redisTemplate.opsForHash().entries(key);
    }


    /**
     * 往Hash中存入数据
     *
     * @param key Redis键
     * @param hKey Hash键
     * @param value 值
     */
    public <T> void setCacheMapValue(final String key, final String hKey, final T value)
    {
        redisTemplate.opsForHash().put(key, hKey, value);
    }

    /**
     * 获取Hash中的数据
     *
     * @param key Redis键
     * @param hKey Hash键
     * @return Hash中的对象
     */
    public <T> T getCacheMapValue(final String key, final String hKey)
    {
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.get(key, hKey);
    }

    /**
     * 删除Hash中的数据
     * 
     * @param key
     * @param
     */
    public void delCacheMapValue(final String key, final String hkey)
    {
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.delete(key, hkey);
    }

    /**
     * 获取多个Hash中的数据
     *
     * @param key Redis键
     * @param hKeys Hash键集合
     * @return Hash对象集合
     */
    public <T> List<T> getMultiCacheMapValue(final String key, final Collection<Object> hKeys)
    {
        return redisTemplate.opsForHash().multiGet(key, hKeys);
    }

    /**
     * 获得缓存的基本对象列表
     *
     * @param pattern 字符串前缀
     * @return 对象列表
     */
    public Collection<String> keys(final String pattern)
    {
        return redisTemplate.keys(pattern);
    }

    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    public Long incr(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    public Long incrExpire(String key, long time) {
        Long count = redisTemplate.opsForValue().increment(key, 1);
        if (count != null && count == 1) {
            redisTemplate.expire(key, time, TimeUnit.SECONDS);
        }
        return count;
    }
    public Object hGet(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }
    public Long decr(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, -delta);
    }
    public Boolean hSet(String key, String hashKey, Object value, long time) {
        redisTemplate.opsForHash().put(key, hashKey, value);
        return expire(key, time);
    }
    public void hSet(String key, String hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }
    public Map hGetAll(String key) {
        return redisTemplate.opsForHash().entries(key);
    }
    public Boolean hSetAll(String key, Map<String, Object> map, long time) {
        redisTemplate.opsForHash().putAll(key, map);
        return expire(key, time);
    }
    public void hSetAll(String key, Map<String, ?> map) {
    redisTemplate.opsForHash().putAll(key, map);
    }
    public void hDel(String key, Object... hashKey) {
        redisTemplate.opsForHash().delete(key, hashKey);
    }
    public Boolean hHasKey(String key, String hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }


    public Long hIncr(String key, String hashKey, Long delta) {
        return redisTemplate.opsForHash().increment(key, hashKey, delta);
    }


    public Long hDecr(String key, String hashKey, Long delta) {
        return redisTemplate.opsForHash().increment(key, hashKey, -delta);
    }


    public Double zIncr(String key, Object value, Double score) {
        return redisTemplate.opsForZSet().incrementScore(key, value, score);
    }


    public Double zDecr(String key, Object value, Double score) {
        return redisTemplate.opsForZSet().incrementScore(key, value, -score);
    }


//    public Map<Object, Double> zReverseRangeWithScore(String key, long start, long end) {
//        return redisTemplate.opsForZSet().reverseRangeWithScores(key, start, end)
//                .stream()
//                .collect(Collectors.toMap(ZSetOperations.TypedTuple::getValue, ZSetOperations.TypedTuple::getScore));
//    }


    public Double zScore(String key, Object value) {
        return redisTemplate.opsForZSet().score(key, value);
    }


//    public Map<Object, Double> zAllScore(String key) {
//        return Objects.requireNonNull(redisTemplate.opsForZSet().rangeWithScores(key, 0, -1))
//                .stream()
//                .collect(Collectors.toMap(ZSetOperations.TypedTuple::getValue, ZSetOperations.TypedTuple::getScore));
//    }


    public Set<Object> sMembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }


    public Long sAdd(String key, Object... values) {
        return redisTemplate.opsForSet().add(key, values);
    }


    public Long sAddExpire(String key, long time, Object... values) {
        Long count = redisTemplate.opsForSet().add(key, values);
        expire(key, time);
        return count;
    }


    public Boolean sIsMember(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }


    public Long sSize(String key) {
        return redisTemplate.opsForSet().size(key);
    }


    public Long sRemove(String key, Object... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }


    public List<Object> lRange(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }


    public Long lSize(String key) {
        return redisTemplate.opsForList().size(key);
    }


    public Object lIndex(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }


    public Long lPush(String key, Object value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }


    public Long lPush(String key, Object value, long time) {
        Long index = redisTemplate.opsForList().rightPush(key, value);
        expire(key, time);
        return index;
    }


    public Long lPushAll(String key, Object... values) {
        return redisTemplate.opsForList().rightPushAll(key, values);
    }


    public Long lPushAll(String key, Long time, Object... values) {
        Long count = redisTemplate.opsForList().rightPushAll(key, values);
        expire(key, time);
        return count;
    }


    public Long lRemove(String key, long count, Object value) {
        return redisTemplate.opsForList().remove(key, count, value);
    }


    public Boolean bitAdd(String key, int offset, boolean b) {
        return redisTemplate.opsForValue().setBit(key, offset, b);
    }


    public Boolean bitGet(String key, int offset) {
        return redisTemplate.opsForValue().getBit(key, offset);
    }


    public Long bitCount(String key) {
        return (Long) redisTemplate.execute((RedisCallback<Long>) con -> con.bitCount(key.getBytes()));
    }


    public List<Long> bitField(String key, int limit, int offset) {
        return (List<Long>) redisTemplate.execute((RedisCallback<List<Long>>) con ->
                con.bitField(key.getBytes(),
                        BitFieldSubCommands.create().get(BitFieldSubCommands.BitFieldType.unsigned(limit)).valueAt(offset)));
    }


    public byte[] bitGetAll(String key) {
        return (byte[]) redisTemplate.execute((RedisCallback<byte[]>) con -> con.get(key.getBytes()));
    }


    public Long hyperAdd(String key, Object... value) {
        return redisTemplate.opsForHyperLogLog().add(key, value);
    }


    public Long hyperGet(String... key) {
        return redisTemplate.opsForHyperLogLog().size(key);
    }


    public void hyperDel(String key) {
        redisTemplate.opsForHyperLogLog().delete(key);
    }


    public Long geoAdd(String key, Double x, Double y, String name) {
        return redisTemplate.opsForGeo().add(key, new Point(x, y), name);
    }

    public List<Point> geoGetPointList(String key, Object... place) {
        return redisTemplate.opsForGeo().position(key, place);
    }


    public Distance geoCalculationDistance(String key, String placeOne, String placeTow) {
        return redisTemplate.opsForGeo()
                .distance(key, placeOne, placeTow, RedisGeoCommands.DistanceUnit.KILOMETERS);
    }


    public GeoResults<RedisGeoCommands.GeoLocation<Object>> geoNearByPlace(String key, String place, Distance distance, long limit, Sort.Direction sort) {
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance().includeCoordinates();
        // 判断排序方式
        if (Sort.Direction.ASC == sort) {
            args.sortAscending();
        } else {
            args.sortDescending();
        }
        args.limit(limit);
        return redisTemplate.opsForGeo()
                .radius(key, place, distance, args);
    }

    public List<String> geoGetHash(String key, String... place) {
        return redisTemplate.opsForGeo()
                .hash(key, place);
    }



}
