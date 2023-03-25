package com.ruoyi.system.service.impl;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysConfig;
import com.ruoyi.system.mapper.SysConfigMapper;
import com.ruoyi.system.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;

/**
 * 参数配置 服务层实现
 * 
 * @author ruoyi
 */
@Service
public class SysConfigServiceImpl implements ISysConfigService
{

    @Autowired
    private SysConfigMapper configMapper;

    @Autowired
    private RedisCache redisCache;

    /**
     * 项目启动时，初始化参数到缓存
     */
    @PostConstruct
    public void init()
    {
        loadingConfigCache();
    }

    /**
     * 查询参数配置信息
     * 
     * @param configId 参数配置ID
     * @return 参数配置信息
     */
    @Override
    @DataSource(DataSourceType.MASTER) // 主库
    public SysConfig selectConfigById(Long configId)
    {
        SysConfig config = new SysConfig();//获得参数配置
        config.setConfigId(configId);//配置 设置为 id
        return configMapper.selectConfig(config);//根据此配置查询
    }

    /**
     * 根据键名查询参数配置信息
     *
     * 在这里前后呼应
     * 1. 先在缓存中查找  有则返回值 无则继续
     * 2. 后在 数据库中查找 找到则将 该配置 加入 缓存
     *
     *  综合 :  两大关  都不通过 那就 返回 空
     *
     * @param configKey 参数key
     * @return 参数键值
     */
    @Override
    public String selectConfigByKey(String configKey)
    {
        //1.从缓存中查找
        String configValue = Convert.toStr(redisCache.getCacheObject(getCacheKey(configKey)));
        //从redisCache 中 获得对应键名的值 转为 字符串  因为 这里 本身就只是 一个数据 并不是 对象什么的
        if (StringUtils.isNotEmpty(configValue))
        {
            return configValue;
        }
        SysConfig config = new SysConfig();
        config.setConfigKey(configKey);//配置中 设置这个key
        //2.从数据库中查找   所以说 实体类本身中没有数据  我们给她设置了 数据 根据这个数据再去查 这个 配置信息
        SysConfig retConfig = configMapper.selectConfig(config);

        //如果数据库中有这个配置   这个是判断对象空不空(关键)
        if (StringUtils.isNotNull(retConfig))
        {
            //在缓存中设置 这个配置             getCacheKey(configKey)  只是加一个常量 来标识是系统配置
            redisCache.setCacheObject(getCacheKey(configKey), retConfig.getConfigValue());

            // 将值返回
            return retConfig.getConfigValue();
        }

        // 如果 缓存没有 数据库中没有 那么 就返回 空   这里并没有直接写 ""
        return StringUtils.EMPTY;
    }

    /**
     * 获取验证码开关
     *
     * 根据 自己设置的 key 查找 返回的是 字符串 根据字符串进行判断
     * 但是 如果 字符串 为空 那就开启  ？？ 默认的吧  因为 空的情况 就是 缓存和数据库 中都没有
     * 不空 就转为bool类型返回  说白了 也就是 true 和 false 其中一个了
     *
     * @return true开启，false关闭
     */
    @Override
    public boolean selectCaptchaOnOff()
    {
        // 查询是否已经打开 验证码开关 这个配置       1.从缓存  2. 从数据库   都没有那就返回 空
        String captchaOnOff = selectConfigByKey("sys.account.captchaOnOff");

        //如果是 空 那就 返回 开启   ？？？
        if (StringUtils.isEmpty(captchaOnOff))
        {
            return true;
        }

        // 如果不空 那就转为 bool
        return Convert.toBool(captchaOnOff);
    }

    /**
     * 查询参数配置列表
     * 
     * @param config 参数配置信息
     * @return 参数配置集合
     */
    @Override
    public List<SysConfig> selectConfigList(SysConfig config)
    {

        return configMapper.selectConfigList(config);
    }

    /**
     * 新增参数配置
     * 
     * @param config 参数配置信息
     * @return 结果
     */
    @Override
    public int insertConfig(SysConfig config)
    {
        // 先放到数据库
        int row = configMapper.insertConfig(config);
        if (row > 0)
        {
            // 如果插入成功设为缓存
            redisCache.setCacheObject(getCacheKey(config.getConfigKey()), config.getConfigValue());
        }
        // 返回是否成功
        return row;
    }

    /**
     * 修改参数配置
     * 
     * @param config 参数配置信息
     * @return 结果
     */
    @Override
    public int updateConfig(SysConfig config)
    {
        int row = configMapper.updateConfig(config);
        if (row > 0)
        {
            redisCache.setCacheObject(getCacheKey(config.getConfigKey()), config.getConfigValue());
        }
        return row;
    }

    /**
     * 批量删除参数信息
     * 
     * @param configIds 需要删除的参数ID
     * @return 结果
     */
    @Override
    public void deleteConfigByIds(Long[] configIds)
    {
        // 拿到所有的配置  逐个遍历
        for (Long configId : configIds)
        {

            SysConfig config = selectConfigById(configId);

            // 系统默认的 无法删除
            if (StringUtils.equals(UserConstants.YES, config.getConfigType()))
            {
                throw new ServiceException(String.format("内置参数【%1$s】不能删除 ", config.getConfigKey()));
            }
            // 不是系统默认 则 删除
            configMapper.deleteConfigById(configId);
            // 从缓存中 删除
            redisCache.deleteObject(getCacheKey(config.getConfigKey()));
        }
    }

    /**
     * 加载参数缓存数据
     */
    @Override
    public void loadingConfigCache()
    {
        // 获取
        List<SysConfig> configsList = configMapper.selectConfigList(new SysConfig());
        for (SysConfig config : configsList)
        {
            // 设缓存
            redisCache.setCacheObject(getCacheKey(config.getConfigKey()), config.getConfigValue());
        }
    }

    /**
     * 清空参数缓存数据
     */
    @Override
    public void clearConfigCache()
    {
        //  根据模式 获得 缓存的 keys
        Collection<String> keys = redisCache.keys(Constants.SYS_CONFIG_KEY + "*");
        // 从缓存中 批量删除
        redisCache.deleteObject(keys);
    }

    /**
     * 重置参数缓存数据
     */
    @Override
    public void resetConfigCache()
    {
        clearConfigCache();
        loadingConfigCache();
    }

    /**
     * 校验参数键名是否唯一
     * 
     * @param config 参数配置信息
     * @return 结果
     */
    @Override
    public String checkConfigKeyUnique(SysConfig config)
    {
        //数据库中 和 传入的 判断是否相同
        Long configId = StringUtils.isNull(config.getConfigId()) ? -1L : config.getConfigId();
        SysConfig info = configMapper.checkConfigKeyUnique(config.getConfigKey());
        if (StringUtils.isNotNull(info) && info.getConfigId().longValue() != configId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 设置cache key
     * 
     * @param configKey 参数键
     * @return 缓存键key
     */
    private String getCacheKey(String configKey)
    {
        return Constants.SYS_CONFIG_KEY + configKey;
    }
}
