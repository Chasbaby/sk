package com.ruoyi.page.mapper;

import java.util.List;
import com.ruoyi.page.domain.SysFooter;

/**
 * 底部展示Mapper接口
 * 
 * @author ruoyi
 * @date 2023-02-03
 */
public interface SysFooterMapper 
{
    /**
     * 查询底部展示
     * 
     * @param footerId 底部展示主键
     * @return 底部展示
     */
    public SysFooter selectSysFooterByFooterId(Long footerId);

    /**
     * 查询底部展示列表
     * 
     * @param sysFooter 底部展示
     * @return 底部展示集合
     */
    public List<SysFooter> selectSysFooterList(SysFooter sysFooter);

    /**
     * 新增底部展示
     * 
     * @param sysFooter 底部展示
     * @return 结果
     */
    public int insertSysFooter(SysFooter sysFooter);

    /**
     * 修改底部展示
     * 
     * @param sysFooter 底部展示
     * @return 结果
     */
    public int updateSysFooter(SysFooter sysFooter);

    /**
     * 删除底部展示
     * 
     * @param footerId 底部展示主键
     * @return 结果
     */
    public int deleteSysFooterByFooterId(Long footerId);

    /**
     * 批量删除底部展示
     * 
     * @param footerIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysFooterByFooterIds(Long[] footerIds);
}
