package com.ruoyi.article.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.article.domain.Article;
import com.ruoyi.article.domain.ArticleRecord;
import com.ruoyi.article.domain.dto.*;
import com.ruoyi.article.mapper.ArticleMapper;
import com.ruoyi.article.mapper.ArticleRecordMapper;
import com.ruoyi.article.service.IArticleService;
import com.ruoyi.common.core.domain.entity.DTO.UserDTO;
import com.ruoyi.common.core.domain.entity.DTO.VisitorDTO;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.entity.SysVisitor;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.sights.domain.DTO.SightsStatisticTopDTO;
import com.ruoyi.system.domain.SysAudio;
import com.ruoyi.system.mapper.SysAudioMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.SysVisitorMapper;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

import static com.ruoyi.common.utils.baidu.TranslateUtils.getTranslateResult;

/**
 * 文章Service业务层处理
 * 
 * @author ruoyi chas
 * @date 2023-03-05
 */
@Service
public class ArticleServiceImpl implements IArticleService
{
    private static final Logger log = LoggerFactory.getLogger(ArticleServiceImpl.class);

    private final static String pattern="<(\\S*?)[^>]*>.*?|<.*? />";

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleRecordMapper articleRecordMapper;

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysVisitorMapper visitorMapper;

    @Autowired
    private SysAudioMapper audioMapper;




    public void xxx(){
        for (int i = 0; i < 100; i++) {
            log.info("sights:info");
            log.warn("sights:warn");
            log.error("sights:error");
        }
    }

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
        int i = articleRecordMapper.judgeOnlyOneLikeArticle(record.getArticleId(),record.getUserId());
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

        int i = articleRecordMapper.judgeOnlyOneCollectArticle(record.getArticleId(),record.getUserId());

        if (i==0){
            articleRecordMapper.addCollectArticle(record.getArticleId(),record.getUserId());
        }else {
            articleRecordMapper.deleteCollectArticle(record);
        }
        return i;
    }

    /**
     *  增加浏览量
     * @param record
     * @return
     */
    @Transactional()
    @Override
    public int articleAddView(ArticleRecord record) {

        int i = articleRecordMapper.judgeOnlyOneViewArticle(record);
        // 说明之前已经浏览过了 那么更改时间
        if (i==1){
            articleRecordMapper.updateViewTime(record.getCreateTime());
        }else {
            // 没有浏览 增加即可
            articleRecordMapper.addViewArticle(record.getArticleId());
        }
        return i;
    }

    /**
     * 增加浏览量
     * @param articleId
     * @return
     */
    @Override
    public int articleAddView(Long articleId) {
        return articleMapper.updateArticleView(articleId);
    }

    /**
     * 获取某用户的收藏列表
     * @param userId 用户id
     * @return articleReturnDTO 列表
     */
    @Override
    public List<ArticleReturnDTO> getAllArticleCollect(Long userId) {
        List<Article> articles = articleMapper.selectArticleCollectByUserId(userId);
        List<ArticleReturnDTO> collect = getReturnDTOList(articles);

        return collect;
    }

    @Override
    public List<ArticleReturnDTO> getAllArticleLike(Long userId) {
        List<Article> articles = articleMapper.selectArticleLikeByUserId(userId);
        List<ArticleReturnDTO> collect = getReturnDTOList(articles);
        return collect;
    }

    private List<ArticleReturnDTO> getReturnDTOList(List<Article> articles) {
        return articles.stream().map(item -> {
            ArticleReturnDTO returnDTO = new ArticleReturnDTO();
            try {
                BeanUtils.copyProperties(returnDTO, item);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return returnDTO;
        }).collect(Collectors.toList());

    }

    @Override
    public List<ArticleReturnDTO> getAllArticleView(Long userId) {
        List<Article> articles = articleMapper.selectArticleViewByUserId(userId);
        List<ArticleReturnDTO> collect = getReturnDTOList(articles);
        return collect;
    }

    /**
     * 0是别人 1是自己
     * @param userId
     * @param way
     * @return
     */
    @Override
    public List<ArticleHomeDTO> getAllArticleByUserId(Long userId,int way ) {

        List<ArticleHomeDTO> createDTOS = new ArrayList<>();

        List<Article> articles = articleMapper.selectArticleInPersonPage(userId,way);

        articles.stream().forEach(item->{
            ArticleHomeDTO homeDTO = new ArticleHomeDTO();
            try {
                BeanUtils.copyProperties(homeDTO,item);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            createDTOS.add(homeDTO);
        });

        return createDTOS;
    }

    /**
     * 如果是查询自己的话 未审核的也可以看
     * 如果是看别人的话必须审核后才可以
     * @param articleId
     * @return
     */
    @Override
    public ArticleDetail getArticleDetail(Long articleId,Long user) {
        ArticleDetail detail = new ArticleDetail();
        UserDTO userDTO = new UserDTO();
        VisitorDTO visitorDTO = new VisitorDTO();

        // 获取单个文章信息
        Article article = articleMapper.selectArticleByArticleId(articleId);
        // 获取文章作者的id
        Long userId = article.getUserId();
        // 获取用户信息
        SysUser sysUser = userMapper.selectUserById(userId);
        // 获取visitor信息
        SysVisitor visitor = visitorMapper.selectVisitorById(userId);

        // 复制
        try {
            BeanUtils.copyProperties(userDTO,sysUser);
            BeanUtils.copyProperties(visitorDTO,visitor);
            BeanUtils.copyProperties(detail,article);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        detail.setUser(userDTO);
        detail.setVisitor(visitorDTO);

        detail.setIfCollect(articleRecordMapper.judgeOnlyOneCollectArticle(articleId,user));
        detail.setIfLike(articleRecordMapper.judgeOnlyOneLikeArticle(articleId,user));

        return detail;
    }

    /**
     * 获取用户草稿文件
     * @param userId
     * @return
     */
    @Override
    public List<ArticleReturnDTO> getDraft(Long userId) {
        List<Article> drafts = articleMapper.getArticleDraft(userId);
        List<ArticleReturnDTO> returnDTOList = getReturnDTOList(drafts);
        return returnDTOList;
    }

    /**
     * ways  0  审核通过
     *       1  审核不通过
     *       2  进行中
     * @param userId
     * @param ways
     * @return
     */
    @Override
    public List<ArticleStatusDTO> getUserAllArticleByWays(Long userId, Integer ways) {
        List<Article> articlesByWays = articleMapper.getAllArticlesByWays(userId, ways);
        List<ArticleStatusDTO> collect = articlesByWays.stream().map(item -> {
            ArticleStatusDTO statusDTO = new ArticleStatusDTO();
            try {
                BeanUtils.copyProperties(statusDTO, item);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return statusDTO;
        }).collect(Collectors.toList());
        return collect;
    }

    /**
     * 批量删除
     * @param articleIds
     * @return
     */
    @Override
    public int deleteArticlesByUser(Long[] articleIds) {
        return articleMapper.deleteArticleBatches(articleIds);
    }

    /**
     * 重新编辑文章
     * @param articleId
     * @return
     */
    @Override
    public ArticleCreateDTO reEditArticle(Long articleId) {
        Article article = selectArticleByArticleId(articleId);
        ArticleCreateDTO createDTO = new ArticleCreateDTO();
        try {
            BeanUtils.copyProperties(createDTO,article);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return createDTO;
    }

    @Override
    public Double getArticleRate() {
        List<Article> articleData = articleMapper.getArticleData();
        if (articleData.isEmpty()){
            return 1.0;
        }
        Long ok = 0L;
        Long all=0L;
        while (articleData.iterator().hasNext()) {
            Article next = articleData.iterator().next();
            if (next.getIsOk() == "Y"){
                ok++;
            }
            all++;
        }
        return ok/all.doubleValue();
    }

    /**
     * 获取 可视化数据
     * @return
     */
    @Override
    public ArticleStatisticPie getArticleData() {
        ArticleStatisticPie pie = new ArticleStatisticPie();
        pie.setArticleLike(0L);
        pie.setArticleView(0L);
        pie.setArticleCollect(0L);
        List<Article> articleData = articleMapper.getArticleData();
        if (articleData.isEmpty()){
            return pie;
        }
        while (articleData.iterator().hasNext()) {
            Article next = articleData.iterator().next();
            pie.setArticleLike(pie.getArticleLike()+next.getArticleLike());
            pie.setArticleCollect(pie.getArticleCollect()+next.getArticleCollect());
            pie.setArticleView(pie.getArticleView()+next.getArticleView());
        }
        return pie;
    }

    @Override
    public Long[] getJudgeData() {
        Long[] data = new Long[4];
        for (int i = 0; i < data.length; i++) {
            data[i] = 0L;
        }
        List<Article> articleData = articleMapper.getArticleData();
        System.out.println(articleData);
        if (articleData.isEmpty()) {
            for (int i = 0; i < data.length; i++) {
                data[i] = 0L;
            }
            return data;
        }
        while (articleData.iterator().hasNext()) {
            Article next = articleData.iterator().next();
            switch (next.getIsOk()){
                case "U" : data[3]++; data[0] ++; break;
                case "Y" : data[1]++; data[0] ++;break;
                case "N" : data[2]++; data[0] ++;break;
                default: log.warn("审核状态数据存在错误");
            }
        }
        return data;
    }

    @Override
    public Long[] getArticleDMY() {
        Long[] data = new Long[5];
        data[0] = articleMapper.getDayArtData();
        data[1] = articleMapper.getMonthArtData();
        data[2] = articleMapper.getYearArtData();
        data[3] = articleMapper.getYearOKArtData();
        data[4] = articleMapper.getYearNOArtData();
        return data;
    }

    /**
     * 文章排行
     * @return
     */
    @Override
    public List<ArticleTopDTO> getArticleTop() {
        List<ArticleTopDTO> articleTopDTOS = new ArrayList<>();
        List<Article> articleTop = articleMapper.getArticleTop();
        articleTop.stream().forEach(item->{
            ArticleTopDTO dto = new ArticleTopDTO();
            try {
                BeanUtils.copyProperties(dto,item);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            articleTopDTOS.add(dto);
        });
        return articleTopDTOS;
    }

    @Override
    public ArticleVoiceDTO transReturn(Long id, Integer position, Long audioId) {
        SysAudio audio = audioMapper.selectSysAudioByAudioId(audioId);
        ArticleVoiceDTO voiceDTO = new ArticleVoiceDTO();
        Article article = articleMapper.selectArticleByArticleId(id);
        String content = article.getArticleContent();
        String s = content.replaceAll(pattern, "");
        String result = getTranslateResult(s, null, audio.getBaiduLabel());
        voiceDTO.setArticleContentOUT(result);
        voiceDTO.setArticleId(id);
        voiceDTO.setSpeakTTS(audio.getSpeakLabel());
        return voiceDTO;

    }


}
