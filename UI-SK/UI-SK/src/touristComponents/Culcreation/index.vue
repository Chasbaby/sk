<template>
  <div class="block">
    <div class="header">
      <ul>
        <li v-for="(item, index) in imgList" :key="index">
          <img :src="item.src" alt="" />
        </li>
      </ul>
    </div>
    <div class="main">
      <div class="left">
        <div class="top">
          <p class="name">文东记 (马里士他路)</p>
          <p class="info">
            马里士他路399. 401 & 403 号, Singapore, 329801, 新加坡
          </p>
        </div>
        <div class="text">
          <h2 class="productiontitle">米其林指南的观点</h2>
          <p class="text">
            要在马里士他路上找到文东记一点也不难，店面的明亮招牌和门前不绝的人龙将引领您到达。老闆最初在牛车水的小摊档摆卖，现已扩展至八间店子，而这家于1983年开业的则是品牌首家店铺。其招牌海南鸡广受老饕欢迎，更有三种大小选择；此外，煮炒菜式也做得不俗。若不想排队，宜避开繁忙时间。
          </p>
        </div>
        <div class="commentDiv">
          <h2 class="commenttitle">评论</h2>
          <div class="mycommentbox">
            <div>
              <el-input
                id="input"
                type="textarea"
                :rows="2"
                placeholder="请输入内容"
                v-model="textarea"
                class="input"
                :autosize="{ minRows: 3, maxRows: 4 }"
                resize="none"
              >
              </el-input>
            </div>
            <button @click="submitcomment()">发表评论</button>
          </div>
          <div class="emoji">
            <el-col>
              <el-button
                type="text"
                size="mini"
                @click.stop="showDialog = !showDialog"
                >添加表情</el-button
              >
              <div class="emojibanner">
                <VEmojiPicker v-show="showDialog" @select="selectEmoji" />
              </div>
            </el-col>
          </div>
          <ul>
            <li v-for="(item, index) in commentList" :key="index">
              <div class="leftbox"><img :src="item.avatar" alt="" /></div>
              <div class="rightbox">
                <span class="name">{{ item.name }} · </span>
                <span class="time">{{ item.time }}</span>
                <span class="content">{{ item.content }}</span>
                <span
                  class="thumbsUp"
                  @click="handleThumbsUp(item)"
                  :class="[{ hasThumbup: item.ifThumb }]"
                  ><i class="fa fa-thumbs-o-up" aria-hidden="true"></i
                  >{{ item.thumbsUp }}</span
                >
                <span class="reply"
                  ><i class="fa fa-comment-o" aria-hidden="true"></i>回复</span
                >
                <div class="line"></div>
              </div>
            </li>
          </ul>
        </div>
      </div>
      <div class="right">
        <p>即刻参与购买</p>
        <button>购买</button>
      </div>
    </div>
  </div>
</template>

<script>
import { VEmojiPicker } from "v-emoji-picker";
export default {
  data() {
    return {
      loading: true,
      textarea: "",
      showDialog: false,
      imgList: [
        {
          src: "https://axwwgrkdco.cloudimg.io/v7/__gmpics__/984b4267233a471db0008d185ce94c0e?w=63&org_if_sml=1",
        },
        {
          src: "https://axwwgrkdco.cloudimg.io/v7/__gmpics__/b9601d9a934941a298fdaf1b94c212b7?w=63&org_if_sml=1",
        },
        {
          src: "https://axwwgrkdco.cloudimg.io/v7/__gmpics__/984b4267233a471db0008d185ce94c0e?w=63&org_if_sml=1",
        },
      ],
      commentList: [
        {
          name: "Chas神",
          avatar:
            "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
          content: "城堡",
          time: "2022-04-03 22:27",
          thumbsUp: 1,
          source: "浪花一朵朵~",
          ifThumb: false,
        },
        {
          name: "Chas神",
          avatar:
            "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
          content: "帅哥",
          time: "2022-04-03 22:27",
          thumbsUp: 1,
          source: "浪花一朵朵~",
          ifThumb: false,
        },
        {
          name: "Chas神",
          avatar:
            "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
          content: "啊啊啊啊 啊",
          time: "2022-04-03 22:27",
          thumbsUp: 1,
          source: "浪花一朵朵~",
          ifThumb: false,
        },
      ],
    };
  },
  methods: {
    handleThumbsUp(item) {
      if (!item.ifThumb) {
        item.thumbsUp++;
        item.ifThumb = !item.ifThumb;
        //axios请求已经点赞
      } else {
        item.thumbsUp--;
        item.ifThumb = !item.ifThumb;
        //axios请求取消点赞
      }
    },
    selectEmoji(emoji) {
      // 选择emoji后调用的函数
      let input = document.getElementById("input");
      let startPos = input.selectionStart;
      let endPos = input.selectionEnd;
      let resultText =
        input.value.substring(0, startPos) +
        emoji.data +
        input.value.substring(endPos);
      input.value = resultText;
      input.focus();
      input.selectionStart = startPos + emoji.data.length;
      input.selectionEnd = startPos + emoji.data.length;
      this.text = resultText;
    },
    submitcomment() {
      if (this.textarea == "") {
        this.$message.error("评论内容不能为空哈");
      }
    },
  },
  components: {
    VEmojiPicker,
  },
  mounted() {
    // document.addEventListener("click", (e) => {
    //   if (
    //     e.target.className !== "emoji" &&
    //     e.target.className !== "emojibanner"
    //   ) {
    //     this.showDialog = false;
    //   }
    // });
    console.log("culcreation挂载");
  },
  beforeDestroy() {
    window.removeEventListener("click", () => {}, true);
  },
};
</script>

<style lang="scss" scoped>
$bluecolor: #00aeec;
li {
  list-style: none;
}
p {
  font-family: "AvenirNext-DemiBold", "Helvetica Neue Medium", sans-serif;
  font-size: 16px;
  line-height: 1.5;
  font-weight: normal;
  color: #191919;
}
.block {
  width: 100%;
  padding-top: 60px;
  height: 1800px;
  .header {
    width: 100%;
    ul {
      width: 100vw;
      display: flex;
      align-items: center;
      li {
        flex: auto;
        border: 1px solid #fff;
        &:hover {
          cursor: pointer;
        }
        img {
          width: 100%;
        }
      }
    }
  }
  .main {
    padding-top: 30px;
    width: 1340px;
    margin: 0 auto;
    position: relative;
    display: flex;
    .left {
      width: 800px;
      .top {
        .name {
          font-family: "Noto Serif SC", serif !important;
          font-weight: 700;
          font-size: 36px;
          margin-bottom: 13px;
          line-height: 1;
        }
        .info {
          font-style: normal;
          font-stretch: normal;
          line-height: 1;
          padding-top: 10px;
        }
        padding-bottom: 20px;
        border-bottom: 1px solid #979797;
        margin-bottom: 40px;
      }
      .text {
        .productiontitle {
          font-family: "Noto Serif SC", serif !important;
          font-weight: 700;
          font-size: 28px;
          line-height: 1;
          padding-bottom: 30px;
        }
      }
      .commentDiv {
        padding-top: 20px;
        .commenttitle {
          font-family: "Noto Serif SC", serif !important;
          font-weight: 700;
          font-size: 28px;
          line-height: 1;
          padding-bottom: 30px;
        }
        .mycommentbox {
          display: flex;
          .input {
            width: 650px;
          }
          button {
            flex: 1;
            margin-left: 10px;
            &:hover {
              cursor: pointer;
            }
          }
        }
        .emoji {
          position: relative;
          .emojibanner {
            position: absolute;
            top: 25px;
            left: 0;
          }
        }
        ul {
          margin-top: 40px;
          li {
            // background-color: $bluecolor;
            display: flex;
            flex: 0 0 32%;
            border-radius: 10px;
            margin-bottom: 15px;
            // align-items: center;
            justify-content: space-between;
            .leftbox {
              width: 50px;
              text-align: center;
              img {
                width: 45px;
                height: 45px;
                border-radius: 50%;
              }
            }
            .rightbox {
              padding-left: 15px;
              flex: 1;
              // background-color: blue;
              .name {
                color: #505050;
                font: {
                  size: 13px;
                  family: PingFangSC-Medium, sans-serif;
                }
              }
              .time {
                color: #505050;
                font: {
                  size: 13px;
                  family: PingFangSC-Medium, sans-serif;
                }
              }
              .thumbsUp {
                display: inline-block;
                margin-right: 30px;
                color: #505050;

                &:hover {
                  cursor: pointer;
                }
                font: {
                  size: 12px;
                }
                i {
                  font-size: 15px;
                  padding-right: 10px;
                }
              }
              .hasThumbup {
                color: $bluecolor;
              }
              .reply {
                color: #505050;
                font: {
                  size: 12px;
                }
                i {
                  font-size: 15px;
                  padding-right: 10px;
                }
              }
              .content {
                display: block;
                padding-top: 10px;
                padding-bottom: 15px;
                color: #212121;
              }
              .line {
                border: 1px solid #f4f4f4;
                margin-top: 15px;
                margin-bottom: 10px;
              }
              .source {
                font-size: 12px;
                color: #757575;
                text-overflow: ellipsis;
                overflow: hidden;
              }
            }
          }
        }
      }
    }
    .right {
      width: 300px;
      height: 150px;
      position: sticky;
      top: 150px;
      margin-left: 50px;
      box-shadow: 0 9px 17px 0 rgb(0 0 0 / 10%);
      // background-color: #d8d8d8;
      padding: 15px;
      margin-bottom: 32px;
      text-align: left;
      p {
        font-family: "Noto Serif SC", serif !important;
        font-weight: 700;
      }
      button {
        font-family: "open_sans", sans-serif !important;
        font-weight: 400;
        padding: 16px 24px;
        border-radius: 4px;
        line-height: 1.3;
        font-size: 16px;
        font-family: "AvenirNext-DemiBold", "Helvetica Neue Medium", sans-serif;
        border: 1px solid transparent;
        text-decoration: none;
        display: block;
        width: 100%;
        transition: all ease-in 0.3s;
        background-color: #00aeec;
        margin-top: 30px;
        color: #fff;
        &:hover {
          cursor: pointer;
          background-color: #0092c7;
        }
      }
    }
  }
}
</style>