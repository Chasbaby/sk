package com.ruoyi.sights.service.impl;

import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.ruoyi.mapperEs.ee.SightsESMapper;
import com.ruoyi.sights.domain.SightsBase;
import com.ruoyi.sights.mapper.SightsBaseMapper;
import com.ruoyi.sights.service.ISightsESService;
import org.elasticsearch.action.search.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chas
 * @introduction Sights ES 实现类
 * @date 2023-5-31
 */
@Service
public class SightsESService implements ISightsESService {

    @Autowired
    private SightsESMapper sightsESMapper;

    @Autowired
    private SightsBaseMapper baseMapper;

    @Override
    public int deleteSightsById(Long sightsId) {
        SearchRequest request = new SearchRequest();

        return 0;
    }

    @Override
    public int importAllSightsToEs() {
        return 0;
    }

    @Override
    public int deleteSightsBatchesById(Long[] ids) {
        return 0;
    }

    @Override
    public SightsBase addSightsToESById(Long id) {
        return null;
    }

    @Override
    public int addSightsToESByIds(Long[] ids) {
        return 0;
    }
}
