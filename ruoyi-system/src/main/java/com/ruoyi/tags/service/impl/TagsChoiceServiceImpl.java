package com.ruoyi.tags.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.tags.mapper.TagsChoiceMapper;
import com.ruoyi.tags.domain.TagsChoice;
import com.ruoyi.tags.service.ITagsChoiceService;

/**
 * 标签选择Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-06-01
 */
@Service
public class TagsChoiceServiceImpl implements ITagsChoiceService 
{
    @Autowired
    private TagsChoiceMapper tagsChoiceMapper;

    /**
     * 查询标签选择
     * 
     * @param tagsId 标签选择主键
     * @return 标签选择
     */
    @Override
    public TagsChoice selectTagsChoiceByTagsId(Long tagsId)
    {
        return tagsChoiceMapper.selectTagsChoiceByTagsId(tagsId);
    }

    /**
     * 查询标签选择列表
     * 
     * @param tagsChoice 标签选择
     * @return 标签选择
     */
    @Override
    public List<TagsChoice> selectTagsChoiceList(TagsChoice tagsChoice)
    {
        return tagsChoiceMapper.selectTagsChoiceList(tagsChoice);
    }

    /**
     * 新增标签选择
     * 
     * @param tagsChoice 标签选择
     * @return 结果
     */
    @Override
    public int insertTagsChoice(TagsChoice tagsChoice)
    {
        tagsChoice.setCreateTime(DateUtils.getNowDate());
        return tagsChoiceMapper.insertTagsChoice(tagsChoice);
    }

    /**
     * 修改标签选择
     * 
     * @param tagsChoice 标签选择
     * @return 结果
     */
    @Override
    public int updateTagsChoice(TagsChoice tagsChoice)
    {
        tagsChoice.setUpdateTime(DateUtils.getNowDate());
        return tagsChoiceMapper.updateTagsChoice(tagsChoice);
    }

    /**
     * 批量删除标签选择
     * 
     * @param tagsIds 需要删除的标签选择主键
     * @return 结果
     */
    @Override
    public int deleteTagsChoiceByTagsIds(Long[] tagsIds)
    {
        return tagsChoiceMapper.deleteTagsChoiceByTagsIds(tagsIds);
    }

    /**
     * 删除标签选择信息
     * 
     * @param tagsId 标签选择主键
     * @return 结果
     */
    @Override
    public int deleteTagsChoiceByTagsId(Long tagsId)
    {
        return tagsChoiceMapper.deleteTagsChoiceByTagsId(tagsId);
    }
}
