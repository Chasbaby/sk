<template>
  <div id="subscription">
    <div class="left">
      <div class="profile">
        <div class="background">
          <img src="@/assets/images/profilebackground.webp" alt="" />
        </div>
        <div class="info">
          <div class="avatar">
            <img src="@/assets/images/avatar.webp" alt="" />
          </div>
          <div class="name">{{ profile.name }}</div>
          <div class="tags">
            <a class="tags_item">
              <div class="number">
                {{ profile.subscribeNumber }}
              </div>
              <div class="chinese">订阅</div></a
            >
            <a class="tags_item">
              <div class="number">
                {{ profile.fans }}
              </div>
              <div class="chinese">关注</div>
            </a>
            <a class="tags_item">
              <div class="number">
                {{ profile.manuscriptNumber }}
              </div>
              <div class="chinese">投稿</div></a
            >
          </div>
        </div>
      </div>
      <div class="convention">
        <span class="title">投稿公约</span>
        <router-link to="/Creation/convention"
          ><div class="img">
            <img src="@/assets/images/biliconvention.webp" alt="" /></div
        ></router-link>
      </div>
    </div>

    <div class="subcriptionList">
      <ul>
        <li v-for="(item, index) in subscriptionList" :key="index">
          <div class="leftbox">
            <img :src="item.avatar" alt="" />
          </div>
          <div class="rightbox">
            <span class="name">{{ item.name }}</span>
            <span class="time">{{ item.time }}</span>
            <span class="richtext">{{ item.richtext }}</span>
            <div class="manuscript" @click="gotoCulcreation()">
              <div class="img">
                <img :src="item.cover" alt="" />
                <div class="cover"></div>
              </div>
              <div class="data">
                <span class="title">{{ item.title }}</span>
                <span class="introduction">{{ item.introduction }}</span>
                <div class="viewcomment">
                  <span class="tag"
                    ><i class="fa fa-eye"></i>{{ item.view }}</span
                  ><span class="tag"
                    ><i class="fa fa-comment"></i>{{ item.comment }}</span
                  >
                </div>
              </div>
            </div>
          </div>
        </li>
      </ul>
    </div>
    <div class="right">
      <span class="title">话题</span>
      <div class="topicList">
        <ul>
          <li v-for="(item, index) in topicList" :key="index">
            <a href="#">
              <div class="main">
                <div class="leftbox">
                  <div class="icon">
                    <i class="el-icon-connection"></i>
                  </div>
                </div>
                <div class="rightbox">
                  <div class="name">{{ item.name }}</div>
                </div>
              </div>
            </a>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      subscriptionList: [
        {
          name: "Chas神",
          avatar:
            "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
          richtext: "城堡",
          time: "2022-04-03 22:27",
          thumbsUp: 1,
          source: "浪花一朵朵~",
          ifThumb: false,
          cover:
            "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
          title: "美军出动53辆油罐车再次盗运叙石油",
          view: 6982,
          comment: 9,
          introduction:
            "据叙利亚通讯社消息，当地时间14日，美国在叙利亚非法驻军同当地反对派武装合作，盗运叙利亚石油。据消息人士称，此次美军出动53辆油罐车，满载从叙利亚哈塞克省盗运的石油，运往美军在伊拉克的基地。",
        },
        {
          name: "Chas神",
          avatar:
            "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
          richtext: "城堡",
          time: "2022-04-03 22:27",
          thumbsUp: 1,
          source: "浪花一朵朵~",
          ifThumb: false,
          cover:
            "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
          title: "美军出动53辆油罐车再次盗运叙石油",
          view: 6982,
          comment: 9,
          introduction:
            "据叙利亚通讯社消息，当地时间14日，美国在叙利亚非法驻军同当地反对派武装合作，盗运叙利亚石油。据消息人士称，此次美军出动53辆油罐车，满载从叙利亚哈塞克省盗运的石油，运往美军在伊拉克的基地。",
        },
        {
          name: "Chas神",
          avatar:
            "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
          richtext: "城堡",
          time: "2022-04-03 22:27",
          thumbsUp: 1,
          source: "浪花一朵朵~",
          ifThumb: false,
          cover:
            "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
          title: "美军出动53辆油罐车再次盗运叙石油",
          view: 6982,
          comment: 9,
          introduction:
            "据叙利亚通讯社消息，当地时间14日，美国在叙利亚非法驻军同当地反对派武装合作，盗运叙利亚石油。据消息人士称，此次美军出动53辆油罐车，满载从叙利亚哈塞克省盗运的石油，运往美军在伊拉克的基地。",
        },
        {
          name: "Chas神",
          avatar:
            "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
          richtext: "城堡",
          time: "2022-04-03 22:27",
          thumbsUp: 1,
          source: "浪花一朵朵~",
          ifThumb: false,
          cover:
            "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
          title: "美军出动53辆油罐车再次盗运叙石油",
          view: 6982,
          comment: 9,
          introduction:
            "据叙利亚通讯社消息，当地时间14日，美国在叙利亚非法驻军同当地反对派武装合作，盗运叙利亚石油。据消息人士称，此次美军出动53辆油罐车，满载从叙利亚哈塞克省盗运的石油，运往美军在伊拉克的基地。",
        },
        {
          name: "Chas神",
          avatar:
            "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
          richtext: "城堡",
          time: "2022-04-03 22:27",
          thumbsUp: 1,
          source: "浪花一朵朵~",
          ifThumb: false,
          cover:
            "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
          title: "美军出动53辆油罐车再次盗运叙石油",
          view: 6982,
          comment: 9,
          introduction:
            "据叙利亚通讯社消息，当地时间14日，美国在叙利亚非法驻军同当地反对派武装合作，盗运叙利亚石油。据消息人士称，此次美军出动53辆油罐车，满载从叙利亚哈塞克省盗运的石油，运往美军在伊拉克的基地。",
        },
        {
          name: "Chas神",
          avatar:
            "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
          richtext: "城堡",
          time: "2022-04-03 22:27",
          thumbsUp: 1,
          source: "浪花一朵朵~",
          ifThumb: false,
          cover:
            "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
          title: "美军出动53辆油罐车再次盗运叙石油",
          view: 6982,
          comment: 9,
          introduction:
            "据叙利亚通讯社消息，当地时间14日，美国在叙利亚非法驻军同当地反对派武装合作，盗运叙利亚石油。据消息人士称，此次美军出动53辆油罐车，满载从叙利亚哈塞克省盗运的石油，运往美军在伊拉克的基地。",
        },
        {
          name: "Chas神",
          avatar:
            "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
          richtext: "城堡",
          time: "2022-04-03 22:27",
          thumbsUp: 1,
          source: "浪花一朵朵~",
          ifThumb: false,
          cover:
            "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
          title: "美军出动53辆油罐车再次盗运叙石油",
          view: 6982,
          comment: 9,
          introduction:
            "据叙利亚通讯社消息，当地时间14日，美国在叙利亚非法驻军同当地反对派武装合作，盗运叙利亚石油。据消息人士称，此次美军出动53辆油罐车，满载从叙利亚哈塞克省盗运的石油，运往美军在伊拉克的基地。",
        },
        {
          name: "Chas神",
          avatar:
            "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
          richtext: "城堡",
          time: "2022-04-03 22:27",
          thumbsUp: 1,
          source: "浪花一朵朵~",
          ifThumb: false,
          cover:
            "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
          title: "美军出动53辆油罐车再次盗运叙石油",
          view: 6982,
          comment: 9,
          introduction:
            "据叙利亚通讯社消息，当地时间14日，美国在叙利亚非法驻军同当地反对派武装合作，盗运叙利亚石油。据消息人士称，此次美军出动53辆油罐车，满载从叙利亚哈塞克省盗运的石油，运往美军在伊拉克的基地。",
        },
        {
          name: "Chas神",
          avatar:
            "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
          richtext: "城堡",
          time: "2022-04-03 22:27",
          thumbsUp: 1,
          source: "浪花一朵朵~",
          ifThumb: false,
          cover:
            "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
          title: "美军出动53辆油罐车再次盗运叙石油",
          view: 6982,
          comment: 9,
          introduction:
            "据叙利亚通讯社消息，当地时间14日，美国在叙利亚非法驻军同当地反对派武装合作，盗运叙利亚石油。据消息人士称，此次美军出动53辆油罐车，满载从叙利亚哈塞克省盗运的石油，运往美军在伊拉克的基地。",
        },
        {
          name: "Chas神",
          avatar:
            "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
          richtext: "城堡",
          time: "2022-04-03 22:27",
          thumbsUp: 1,
          source: "浪花一朵朵~",
          ifThumb: false,
          cover:
            "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
          title: "美军出动53辆油罐车再次盗运叙石油",
          view: 6982,
          comment: 9,
          introduction:
            "据叙利亚通讯社消息，当地时间14日，美国在叙利亚非法驻军同当地反对派武装合作，盗运叙利亚石油。据消息人士称，此次美军出动53辆油罐车，满载从叙利亚哈塞克省盗运的石油，运往美军在伊拉克的基地。",
        },
      ],
      profile: {
        avatar: "@/assets/images/avatar.webp",
        background: "@/assets/images/profilebackground.webp",
        name: "浩然是个大魔王",
        subscribeNumber: 142,
        fans: 1,
        manuscriptNumber: 6,
      },
      topicList: [
        {
          name: "官宣！《黑神话：悟空》2024夏发售",
        },
        {
          name: "爱奇艺被曝禁止HDMI连线播放",
        },
        {
          name: "大陆输台螺蛳粉包装现“你是中国人”",
        },
        {
          name: "尼泊尔空难副驾因丈夫坠机接力飞行梦",
        },
        {
          name: "N95口罩血氧仪价格大跳水",
        },
      ],
    };
  },
  methods: {
    gotoCulcreation() {
      this.$router.push({ path: "/frontHome/culcreation" });
    },
  },
  watch: {
    "this.$route.path"() {
      console.log(this.$route.path);
    },
  },
};
</script>

<style lang="scss" scoped>
$bluecolor: #00aeec;
$backcolor: #ffffff;
$font: Helvetica Neue, Helvetica, Arial, Microsoft Yahei, Hiragino Sans GB,
  Heiti SC, WenQuanYi Micro Hei, sans-serif;
li {
  list-style: none;
}
#subscription {
  padding-top: 60px;
  display: flex;
  font-family: $font;
  /*background-image: url("../../assets/images/pageBg.png"); 下面删了no-repeat*/
  background-image: url("../../assets/images/background.png");
  background-size: 100% 100%;
  background-attachment: fixed;
  justify-content: center;
  margin: 0 auto;
  .left {
    width: 244px;
    flex-shrink: 0;
    .profile {
      // position: sticky;
      // top: 60px;
      background-color: #fff;
      border-radius: 4px;
      width: 100%;
      height: 188px;
      overflow: hidden;
      margin-bottom: 10px;
      .background {
        width: 100%;
        height: 64px;
        img {
          width: 100%;
          height: 100%;
        }
      }
      .info {
        background-color: #fff;
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        box-sizing: border-box;
        width: 100%;
        height: calc(100% - 64px);
        position: relative;
        padding: 10px 26px;
        .avatar {
          position: absolute;
          width: 56px;
          height: 56px;
          left: 16px;
          top: -16px;
          border-radius: 50%;
          overflow: hidden;
          cursor: pointer;
          img {
            width: 100%;
            height: 100%;
          }
        }
        .name {
          padding-left: 54px;
          font-size: 13px;
          -webkit-line-clamp: 1;
          cursor: pointer;
        }
        .tags {
          display: flex;
          justify-content: space-between;
          .tags_item {
            display: flex;
            justify-content: space-between;
            width: 33.33333%;
            height: 46px;
            flex-direction: column;
            align-items: center;
            cursor: pointer;
            &:hover .number {
              color: $bluecolor;
            }
            .number {
              font-weight: 700;
              line-height: 19px;
              font-size: 14px;
              color: #222;
            }
            .chinese {
              font-size: 12px;
              color: #99a2aa;
            }
          }
        }
      }
    }
    .convention {
      position: sticky;
      top: 70px;
      background-color: #fff;
      border-radius: 4px;
      width: 100%;
      // height: 188px;
      overflow: hidden;
      padding-top: 10px;
      padding: 12px 16px 16px;
      .title {
        display: block;
        font-size: 14px;
        margin-bottom: 8px;
      }
      .img {
        width: 100%;
        height: 100px;
        border-radius: 4px;
        overflow: hidden;
        img {
          width: 100%;
          height: 100%;
          object-fit: cover;
        }
      }
    }
  }
  .right {
    width: 268px;
    flex-shrink: 0;
    background-color: #fff;
    height: max-content;
    border-radius: 4px;
    font-family: PingFang SC;
    font-style: normal;
    padding: 16px 16px 1px;
    // position: sticky;
    // top: 60px;
    .title {
      display: block;
      color: #222;
      font-size: 16px;
      font-weight: 500;
      line-height: 22px;
    }

    .topicList {
      padding: 15px 0px 10px 0px;
      ul {
        li {
          padding-bottom: 30px;
          a {
            &:hover .main .rightbox .name {
              color: #00a1d6;
            }
            .main {
              display: flex;
              .leftbox {
                width: 20px;
                margin-right: 10px;
                .icon {
                  width: 20px;
                  height: 20px;
                  background-color: #008ac5;
                  text-align: center;
                  line-height: 20px;
                  border-radius: 3px;
                  i {
                    color: #fff;
                  }
                }
              }
              .rightbox {
                flex: 1;
                .name {
                  display: block;
                  -webkit-line-clamp: 2;
                  -webkit-box-orient: vertical;
                  color: #18191c;
                  display: -webkit-box;
                  font-size: 14px;
                  line-height: 20px;
                  overflow: hidden;
                  position: relative;
                  text-overflow: ellipsis;
                }
              }
            }
          }
        }
      }
    }
  }
  .subcriptionList {
    padding: 0px 10px 0px 10px;
    ul {
      li {
        display: flex;
        padding: 10px;
        width: 630px;
        background-color: #fff;
        border-radius: 4px;
        margin-bottom: 10px;
        .leftbox {
          width: 80px;
          img {
            width: 50px;
            height: 50px;
            border-radius: 50%;
          }
        }
        .rightbox {
          flex: 1;
          background-color: #ffffff;
          padding-top: 10px;
          .name {
            display: block;
            font: {
              family: $font;
              size: 16px;
            }
            letter-spacing: 0;
          }
          .time {
            display: block;
            color: #99a2aa;
            font-size: 12px;
            height: 22px;
            line-height: 18px;
            padding-top: 4px;
          }
          .richtext {
            display: block;
            padding-bottom: 10px;
            padding-top: 10px;
            font-size: 14px;
            letter-spacing: 0.5px;
            line-height: 22px;
            white-space: pre-wrap;
            word-break: break-word;
          }
          // a {
          //   &:hover .manuscript .title {
          //     color: $bluecolor;
          //   }
          //   &:hover .manuscript .cover {
          //     opacity: 1;
          //   }
          // }
          .manuscript {
            display: flex;
            width: 520px;
            height: 129px;
            border: 1px solid #e5e9ef;
            border-radius: 4px;
            overflow: hidden;
            transition: all 0.3s ease-in-out;
            &:hover {
              cursor: pointer;
            }
            .img {
              display: inline-block;
              position: relative;
              width: 203px;
              height: 100%;
              img {
                width: 100%;
                height: 100%;
                object-fit: cover;
              }
              .cover {
                top: 0px;
                width: 100%;
                height: 100%;
                text-align: center;
                position: absolute;
                background-color: rgba(0, 0, 0, 0.2);
                opacity: 0;
                transition: all 0.3s ease-in-out;
              }
            }
            .data {
              background-color: #fff;
              display: flex;
              flex: 1;
              flex-direction: column;
              justify-content: space-between;
              overflow: hidden;
              padding: 9px 12px 6px 16px;
              .title {
                display: block;
                padding-bottom: 10px;
                -webkit-box-orient: vertical;
                display: -webkit-box;
                overflow: hidden;
                text-overflow: ellipsis;
                word-break: break-all;
                -webkit-line-clamp: 2;
                color: #212121;
                font-size: 14px;
                transition: color 0.2s;
              }
              .introduction {
                text-overflow: -o-ellipsis-lastline;
                overflow: hidden;
                text-overflow: ellipsis;
                display: -webkit-box;
                -webkit-line-clamp: 2;
                line-clamp: 2;
                -webkit-box-orient: vertical;
                -webkit-line-clamp: 2;
                color: #666;
                font-size: 12px;
                // height: 38px;
                // line-height: 19px;
              }
              .viewcomment {
                align-items: center;
                color: #999;
                display: flex;
                font-size: 12px;
                height: 18px;
                line-height: 18px;
                .tag {
                  display: flex;
                  align-items: center;
                  width: 72px;
                  i {
                    padding-right: 5px;
                  }
                }
              }
            }
          }
          .manuscript:hover .data .title {
            color: $bluecolor;
          }
          .manuscript:hover .img .cover {
            opacity: 1;
          }
        }
      }
    }
  }
}
</style>
