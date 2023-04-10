package com.ruoyi.recommend.es.impl;

import cn.easyes.core.conditions.LambdaEsQueryWrapper;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.mapperEs.ee.ArticleESMapper;
import com.ruoyi.mapperEs.ee.CulESMapper;
import com.ruoyi.recommend.es.SearchStrategy;
import com.ruoyi.recommend.es.domain.MultiSearchDTO;
import com.ruoyi.recommend.es.domain.SearchAjaxDTO;
import com.ruoyi.sights.domain.SightsBase;
import com.ruoyi.search.SightsSearchDTO;
import com.ruoyi.mapperEs.ee.SightsESMapper;
import com.ruoyi.sights.mapper.SightsBaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.ruoyi.common.constant.ESConstant.POST_TAG;
import static com.ruoyi.common.constant.ESConstant.PRE_TAG;

/**
 * es搜索策略的实现
 * @author Chas
 * @date 2023-2
 */
@Service("esSearchStrategyImpl")
public class ESSearchStrategyImpl implements SearchStrategy {

    @Autowired
    private SightsESMapper sightsESMapper;

//    @Resource
//    private ArticleESMapper articleESMapper;
//
//    @Resource
//    private CulESMapper culESMapper;

    @Autowired
    private SightsBaseMapper baseMapper;



    @Override
    public List<SearchAjaxDTO> searchAll(String keywords) {
        if (StringUtils.isEmpty(keywords)){
            return new ArrayList<>();
        }
        LambdaEsQueryWrapper<SightsBase> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.and(i->i
                .like(SightsBase::getSightsDetail,keywords)
                .or()
                .like(SightsBase::getSightsIntro,keywords)
                .or()
                .like(SightsBase::getSightsName,keywords)
                .or()
                .like(SightsBase::getSightsLocation,keywords)
        );
        List<SightsBase> selectList = sightsESMapper.selectList(wrapper);
        selectList.stream().map(item->{
            //获取关键字第一次出现的未知
            String sightsDetail = item.getSightsDetail();
            int index = item.getSightsDetail().indexOf(keywords);
            if(index != -1){
                //获取关键词前面的文字  这里的 25 应该要改动
                int preIndex = index > 25 ? index - 25 : 0;
                String preText = item.getSightsDetail().substring(preIndex, index);
                //获取关键词后面的文字
                int last = index + keywords.length();
                int postLength = item.getSightsDetail().length() - last;
                int postIndex = postLength > 175 ? last + 175 : last + postLength;
                String postText = item.getSightsDetail().substring(index, postIndex);
                //内容高亮
                sightsDetail = (preText + postText).replaceAll(keywords, PRE_TAG + keywords + POST_TAG);
            }
            item.getSightsDetail().replaceAll(keywords,PRE_TAG + keywords + POST_TAG);
            return new SightsSearchDTO(item.getSightsId(),item.getSightsName(),
                    item.getSightsLocation(),item.getSightsIntro(),sightsDetail,item.getSightsImage());
        }).collect(Collectors.toList());
        return null;
    }

    @Override
    public List<MultiSearchDTO> showAllSearch(String keywords) {
        return null;
    }

    /**
     * 在项目开始时将所有景点注入es种
     */
    @PostConstruct
    public void initSightsInEs() {
        List<SightsBase> sightsBases = baseMapper.selectSightsBaseList(new SightsBase());
        Integer integer = sightsESMapper.insertBatch(sightsBases);
        System.out.println("一共"+integer+"数据加入es成功");
//        LambdaEsQueryWrapper<SightsBase> wrapper = new LambdaEsQueryWrapper<>();
//        List<SightsBase> baseList = sightsESMapper.selectList(wrapper);
//        baseList.stream().forEach(item->{
//            System.out.println(item);
//        });
    }
    /**
     * 项目关闭时 看看要不要把es所有的东西删了
     */
    public void deleteSightsInEs() {


    }
}
