package com.ruoyi.common.utils.baidu;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.baidu.translate.TransApi;
import com.ruoyi.common.utils.baidu.translate.domain.TranslateDomain;
import com.ruoyi.common.utils.baidu.translate.domain.transResult;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

/**
 * @author chas
 * @introduction 翻译工具类
 * @date 2023-4
 */
public class TranslateUtils {
    private final static String APP_ID = "20230411001637577";
    private final static String SECURITY_KEY = "E9tdbkYH75MOAyUfoyVQ";


    /**
     *
     * @param content 内容
     * @param from    原语言
     * @param to      转后语言
     * @return
     */
    public static String getTranslateResult(@NotNull String content,String from,@NotNull String to){
        TransApi transApi = new TransApi(APP_ID, SECURITY_KEY);
        if (from == null){
            from = "auto";
        }
        String result = transApi.getTransResult(content, from, to);
        TranslateDomain domain = JSONObject.parseObject(result, TranslateDomain.class);
        transResult[] results = domain.getTransResult();
        StringBuilder transResult  = new StringBuilder();
        Arrays.stream(results).forEach(item->{
            transResult.append(item.getDst());
        });
        return transResult.toString();
    }
}
