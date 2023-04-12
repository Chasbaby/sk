package com.ruoyi.web.controller.speech.permit;

import com.ruoyi.article.service.IArticleService;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.culCreativity.ISightsCulCreativityService;
import com.ruoyi.sights.service.ISightsBaseService;
import com.ruoyi.system.service.ISysAudioService;
import com.ruoyi.system.service.ISysVoiceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ruoyi.common.utils.baidu.TranslateUtils.getTranslateResult;

/**
 * @author chas
 * @introduction 动态语音播报  翻译
 * @date 2023-4
 */
@Api("动态语音")
@RestController
@RequestMapping("/sound")
public class SoundAnController extends BaseController {

    @Autowired
    private ISysAudioService audioService;

    @Autowired
    private ISysVoiceService voiceService;

    @Autowired
    private ISightsBaseService baseService;

    @Autowired
    private IArticleService articleService;

    @Autowired
    private ISightsCulCreativityService culCreativityService;

    /**
     * 短语句翻译
     * @param voice 内容
     * @param from  原语言
     * @param to    转后语言
     * @return
     */
    @Anonymous
    @GetMapping("/{voice}/{from}/{to}")
    @ApiOperation("随机数据播报")
    public AjaxResult randomVoice(@PathVariable("voice") String voice ,
                                  @PathVariable("from") String from,
                                  @PathVariable("to") String to){
        String result = getTranslateResult(voice, from, to);
        return AjaxResult.success(result);
    }

//    @ApiOperation("")
//    @Anonymous
//    @GetMapping("/")




}
