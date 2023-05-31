package com.ruoyi.sights.service;

import com.ruoyi.sights.domain.SightsBase;

/**
 * @author chas
 * @introduction sights Service接口
 * @date 2023-5-31
 */
public interface ISightsESService{
    /**
     * 根据 id 删除
     */
    public int deleteSightsById(Long sightsId);

    /** 将全部数据引入 ES **/
    public int importAllSightsToEs();

    /** 根据 ids 批量删除**/
    public int deleteSightsBatchesById(Long[] ids);

    /** 将 id 对应的数据 加入至 ES**/
    public SightsBase addSightsToESById(Long id);

    /***将 ids 批量插入 ES**/
    public int addSightsToESByIds(Long[] ids);


}
