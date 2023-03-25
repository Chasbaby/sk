package com.ruoyi.framework.config;

import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.sights.domain.SightsBase;
import com.ruoyi.sights.service.ISightsBaseService;
import com.ruoyi.sights.service.impl.SightsHotServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.ruoyi.common.constant.HotConstants.HOTLABLE;

/**
 * @author chas
 * @introduction 监听器
 * @data 2023-3
 */
@Component
public class RedisExpiredListener extends KeyExpirationEventMessageListener {
    private static final Logger log = LoggerFactory.getLogger(RedisExpiredListener.class);

//    @Autowired
//    private RedisProperties redisProperties;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private ISightsBaseService baseService;

    public RedisExpiredListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

//    @Override
//    protected void doRegister(RedisMessageListenerContainer listenerContainer) {
//        new PatternTopic()
//        super.doRegister(listenerContainer);
//    }
//
//    @Override
//    protected void doHandleMessage(Message message) {
//        super.doHandleMessage(message);
//    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println(pattern.toString());
        // 获取失效的key
        String expiredKey = message.toString();
//        System.out.println(expiredKey);

        if (expiredKey.startsWith(HOTLABLE)){
//            String s = expiredKey.split(HOTLABLE)[1];
//            Long sightsId = Long.parseLong(s);

            redisCache.lock(expiredKey);// 开启事务 乐观锁
            redisCache.deleteObject(expiredKey);// 删除 过期 key
            SightsBase base = redisCache.getCacheObject(expiredKey);

            baseService.updateSightsBase(base); // 数据更新回数据库

            log.info("过期信息{%s}处理完成",expiredKey);
        }

    }


}
