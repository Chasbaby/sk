<template>
  <div>
<!--    <el-button-->
<!--    type="success"-->
<!--    class="el-icon-microphone"-->
<!--    @click="play()"-->
<!--    ></el-button>-->
    <i class="el-icon-microphone" @click="play()"/>
    <i class="el-icon-video-pause" @click="goahead()"/>
    <i class="el-icon-video-play" @click="paused()"/>
  </div>
</template>

<script>
  /**
   * https://www.npmjs.com/package/speak-tts 官方
   *Speech.setVoice('Fiona')
   *Speech.setRate(1)
   *Speech.setVolume(1)
   *Speech.setPitch(1)
   *Speech.pause()
   *Speech.resume()
   *Speech.cancel()
   *Speech.pending()
   *Speech.paused()
   *Speech.speaking()
   */
  import Speech from 'speak-tts'
  export default {
    props:{
      voice: {
        type:String,
        required:true
      },
      volume: {
        type: Number,
        default: 1
      },
      lang:{
        type:String,
        default: 'zh-CN', // en.US
      },
      pitch: {
        type:Number,
        default:1,
      },
      splitSentences:{
        type:Boolean,
        default:true
      },
      queue:{
        type:Boolean,
        default:false
      }

    },
    data(){
      return {
        speech:null,
        support:true,
      }
    },
    mounted() {
        this.SpeechInit();
    },
    methods:{
      SpeechInit(){
        this.speech = new Speech()
        // this.support = this.speech.hasBrowserSupport()
        this.speech.setLanguage(this.lang)
        this.speech.init({
          volume:this.volume,
          lang:this.lang,
          pitch:this.pitch,
          voice:this.voice,
          // listeners: {
          //   onvoiceschanged:(voices)=>{
          //     console.log('呵呵')
          //   }
          // }
        }).then(()=>{

        })
      },
      // 播放按钮
      play(){
        this.speech.speak({
          text:this.voice,
          queue:this.queue,
          listeners:{
            // 开始播放
            onstart:()=>{
              console.log("开始播放")
            },
            // 判断播放是否完毕
            onend:()=>{
              console.log("播放结束")
            },
            // 回复播放
            onresume:()=>{},
            onboundary:(event) =>{
            }
          },
        }).then(()=>{}).catch(e=>{

        })
      },
      // 暂停
      paused(){
        this.speech.pause();
      },
      // 从暂停处继续播放
      goahead(){
        this.speech.resume();
      },
    },
    // 离开页面取消语音
    destroyed() {
      this.speech.cancel();
    }

  }
</script>


