package com.ruoyi.tags.mapper;

import java.util.List;
import com.ruoyi.tags.domain.TagsChoice;

/**
 * 标签选择Mapper接口
 * 
 * @author ruoyi
 * @date 2023-06-01
 */
public interface TagsChoiceMapper 
{
    /**
     * 查询标签选择
     * 
     * @param tagsId 标签选择主键
     * @return 标签选择
     */
    public TagsChoice selectTagsChoiceByTagsId(Long tagsId);

    /**
     * 查询标签选择列表
     * 
     * @param tagsChoice 标签选择
     * @return 标签选择集合
     */
    public List<TagsChoice> selectTagsChoiceList(TagsChoice tagsChoice);

    /**
     * 新增标签选择
     * 
     * @param tagsChoice 标签选择
     * @return 结果
     */
    public int insertTagsChoice(TagsChoice tagsChoice);

    /**
     * 修改标签选择
     * 
     * @param tagsChoice 标签选择
     * @return 结果
     */
    public int updateTagsChoice(TagsChoice tagsChoice);

    /**
     * 删除标签选择
     * 
     * @param tagsId 标签选择主键
     * @return 结果
     */
    public int deleteTagsChoiceByTagsId(Long tagsId);

    /**
     * 批量删除标签选择
     * 
     * @param tagsIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTagsChoiceByTagsIds(Long[] tagsIds);
}
