package com.ruoyi.web.controller.speech.permit;

import com.ruoyi.article.domain.dto.ArticleVoiceDTO;
import com.ruoyi.article.service.IArticleService;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.culCreativity.ISightsCulCreativityService;
import com.ruoyi.culCreativity.domain.dto.CulVoiceDTO;
import com.ruoyi.sights.domain.DTO.SightsVoiceDTO;
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


    @Anonymous
    @GetMapping("/allAudio")
    @ApiOperation("选取语言")
    public AjaxResult choice(){
        return AjaxResult.success(audioService.selectAllAudio());
    }

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

    /**
     * @param source   source : 0 景点  1 文创  2 文章
     * @param id       对应 id
     * @param position 对应位置
     * @param audioId  转后的语言id
     * @return
     */
    @ApiOperation("段落翻译")
    @Anonymous
    @GetMapping("/{source}/{id}/{position}/{audioId}")
    public AjaxResult fullVoice(@PathVariable("source") Integer source ,
                                @PathVariable("id") Long id ,
                                @PathVariable("position") Integer position,
                                @PathVariable("audioId") Long audioId){
        switch (source){
            case 0:{
                SightsVoiceDTO voiceDTO = baseService.transReturn(id,position,audioId);
                return AjaxResult.success(voiceDTO);
            }
            case 1:{
                CulVoiceDTO voiceDTO = culCreativityService.transReturnCul(id, position, audioId);
                return AjaxResult.success(voiceDTO);
            }
            case 2:{
                ArticleVoiceDTO voiceDTO = articleService.transReturn(id, position, audioId);
                return AjaxResult.success(voiceDTO);
            }
        }
        return AjaxResult.error("找不到所读内容,请联系管理员修改");
    }




}
