package com.ruoyi.page.mapper;

import java.util.List;
import com.ruoyi.page.domain.SysTypeset;

/**
 * 动态排版Mapper接口
 * 
 * @author ruoyi
 * @date 2023-02-04
 */
public interface SysTypesetMapper 
{
    /**
     * 查询动态排版
     * 
     * @param typesetId 动态排版主键
     * @return 动态排版
     */
    public SysTypeset selectSysTypesetByTypesetId(Long typesetId);

    /**
     * 查询动态排版列表
     * 
     * @param sysTypeset 动态排版
     * @return 动态排版集合
     */
    public List<SysTypeset> selectSysTypesetList(SysTypeset sysTypeset);

    /**
     * 新增动态排版
     * 
     * @param sysTypeset 动态排版
     * @return 结果
     */
    public int insertSysTypeset(SysTypeset sysTypeset);

    /**
     * 修改动态排版
     * 
     * @param sysTypeset 动态排版
     * @return 结果
     */
    public int updateSysTypeset(SysTypeset sysTypeset);

    /**
     * 删除动态排版
     * 
     * @param typesetId 动态排版主键
     * @return 结果
     */
    public int deleteSysTypesetByTypesetId(Long typesetId);

    /**
     * 批量删除动态排版
     * 
     * @param typesetIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysTypesetByTypesetIds(Long[] typesetIds);

    /**
     * 获取三大景点展示排版
     * @return
     */
    public List<SysTypeset> getTypesetSights3();

    /**
     * 获取三大文创展示排序
     * @return
     */
    public List<SysTypeset> getTypesetCul3();

    /**
     * 获取创作中心活动图
     */
    public SysTypeset getPersonPicture();
}
