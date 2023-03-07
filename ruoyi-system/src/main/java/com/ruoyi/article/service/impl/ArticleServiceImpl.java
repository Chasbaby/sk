package com.ruoyi.article.service.impl;

import java.util.List;

import com.ruoyi.article.domain.Article;
import com.ruoyi.article.domain.ArticleRecord;
import com.ruoyi.article.mapper.ArticleMapper;
import com.ruoyi.article.mapper.ArticleRecordMapper;
import com.ruoyi.article.service.IArticleService;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 文章Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-05
 */
@Service
public class ArticleServiceImpl implements IArticleService
{
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleRecordMapper articleRecordMapper;

    /**
     * 查询文章
     * 
     * @param articleId 文章主键
     * @return 文章
     */
    @Override
    public Article selectArticleByArticleId(Long articleId)
    {
        return articleMapper.selectArticleByArticleId(articleId);
    }

    /**
     * 查询文章列表
     * 
     * @param article 文章
     * @return 文章
     */
    @Override
    public List<Article> selectArticleList(Article article)
    {
        return articleMapper.selectArticleList(article);
    }

    /**
     * 新增文章
     * 
     * @param article 文章
     * @return 结果
     */
    @Override
    public int insertArticle(Article article)
    {
        article.setCreateTime(DateUtils.getNowDate());
        return articleMapper.insertArticle(article);
    }

    /**
     * 修改文章
     * 
     * @param article 文章
     * @return 结果
     */
    @Override
    public int updateArticle(Article article)
    {
        article.setUpdateTime(DateUtils.getNowDate());
        return articleMapper.updateArticle(article);
    }

    /**
     * 批量删除文章
     * 
     * @param articleIds 需要删除的文章主键
     * @return 结果
     */
    @Override
    public int deleteArticleByArticleIds(Long[] articleIds)
    {
        return articleMapper.deleteArticleByArticleIds(articleIds);
    }

    /**
     * 删除文章信息
     * 
     * @param articleId 文章主键
     * @return 结果
     */
    @Override
    public int deleteArticleByArticleId(Long articleId)
    {
        return articleMapper.deleteArticleByArticleId(articleId);
    }

    /**
     * 点赞和取消
     * @param record
     * @return
     */
    @Transactional()
    @Override
    public int articleAddCancelLike(ArticleRecord record) {
        // 判断是否点赞 1为点赞了  0为没有点赞
        int i = articleRecordMapper.judgeOnlyOneLikeArticle(record);
        if (i==0){
            articleRecordMapper.addLikeArticle(record);
        }else {
            articleRecordMapper.deleteLikeArticle(record);
        }
        return i;
    }

    /**
     * 收藏和取消
     * @param  record
     * @return
     */
    @Transactional()
    @Override
    public int articleAddCancelCollect(ArticleRecord record) {

        int i = articleRecordMapper.judgeOnlyOneCollectArticle(record);
        if (i==0){
            articleRecordMapper.addCollectArticle(record);
        }else {
            articleRecordMapper.deleteCollectArticle(record);
        }
        return i;
    }

    /**
     *  增加浏览量
     * @param articleId
     * @return
     */
    @Transactional()
    @Override
    public int articleAddView(Long articleId) {

        return articleRecordMapper.addViewArticle(articleId);
    }





}
