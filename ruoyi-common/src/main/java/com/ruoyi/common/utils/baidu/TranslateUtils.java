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

    public static void main(String[] args) {
        String content ="文章1984年出生于陕西省西安市。上高三的时候，文章被保送到四川师范大学艺术学院学习影视表演，但是他并未进入这个学校，而是决心去北京学习。在填写大学志愿之前，文章专门去北京考察了中国两大艺术院校—北京电影学院和中央戏剧学院。回到西安之后，文章不顾父母阻拦，将大学志愿从一本到专科总共八个志愿全部填成中央戏剧学院。2002年文章被中央戏剧学院表演系录取";
        String result = getTranslateResult(content, null,"kor");
        System.out.println(result);
    }

}
