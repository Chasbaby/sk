<template>
  <div id="Attractionspage">
    <!-- <Header></Header> -->
    <div class="main">
      <el-container>
        <el-header>
          <ComponentTitle>
            <template v-slot:Chinese>景点</template>
            <template v-slot:English>Attractions</template>
          </ComponentTitle>
        </el-header>
        <el-main>
          <div class="introduction">
            <div class="carousel">
              <el-carousel trigger="click" height="450px">
                <el-carousel-item v-for="item in carouselInfo" :key="item.id">
                  <h3 class="small">
                    <img :src="item.src" alt="" />
                  </h3>
                </el-carousel-item>
              </el-carousel>
            </div>
            <div class="textPattern">
              <div class="mainBody">
                <h1>卢浮宫博物馆</h1>
                <span class="translation">Musée du Louvre</span>
                <div class="rating">
                  <span>4.7</span>/5分&nbsp;&nbsp;&nbsp;
                  <p @click="gotoComment()">{{ CommentList.length }}条点评 ></p>
                  <table
                    style="border-collapse: separate; border-spacing: 0px 20px"
                  >
                    <tr>
                      <td class="leftTag">地址</td>
                      <td>Rue de Rivoli, 75001 Paris</td>
                    </tr>
                    <tr>
                      <td class="leftTag">开放时间&nbsp;&nbsp;&nbsp;&nbsp;</td>
                      <td>开园中; 09:00-18:00开放</td>
                    </tr>
                    <tr>
                      <td class="leftTag">官方电话&nbsp;&nbsp;&nbsp;&nbsp;</td>
                      <td>+33-1-40205050,+33-1-40205317</td>
                    </tr>
                  </table>
                  <ul class="tags">
                    <li @click="gotonextpage('food', 1)">美食</li>
                    <li @click="gotonextpage('hotel')">酒店</li>
                  </ul>
                </div>
              </div>
              <el-button
                type="warning"
                :icon="fav.active"
                circle
                class="fav"
                @click="handlefav()"
              ></el-button>
              <el-popover
                placement="top-start"
                width="200"
                trigger="hover"
                content="根据用户访问量和点评量综合计算后得出"
                class="hotPopover"
              >
                <div class="hot" slot="reference">
                  <span>10</span>
                  <div>
                    <i class="fa fa-star" aria-hidden="true">热度</i>
                  </div>
                </div>
              </el-popover>
            </div>
          </div>
          <div class="mainText">
            <!-- 左侧正文部分 -->
            <div class="left_box" id="Comment">
              <div class="mainBody">
                <div class="para">
                  <h1>介绍</h1>
                  <span
                    >卢浮宫博物馆始建于1204年，这座城堡经历多次扩建，在法国大革命后改为博物馆，与伦敦大英博物馆、纽约大都会博物馆并称为世界三大博物馆。如今卢浮宫博物馆馆藏多达40万件，展品跨度1500多年之久，古希腊、古罗马、古埃及等文明瑰宝在这里华美呈现，可谓是神秘的万宝之宫，电影《卢浮魅影》《达·芬奇密码》还曾在此取景。</span
                  >
                </div>
                <div class="para">
                  <h1></h1>
                  <span></span>
                </div>
                <div class="para"></div>
                <div class="para"></div>
                <div class="para"></div>
                <div class="para"></div>
                <div class="para">
                  <h1>必看贴士</h1>
                  <ul>
                    <li>
                      1.
                      建议提前在网上购票或使用巴黎博物馆通票，可直接从玻璃金字塔的专属通道进入博物馆。
                    </li>
                    <li>
                      2. 博物馆入口处实行安检，请勿携带过多物品和行李箱等入馆。
                    </li>
                    <li>
                      3.
                      游客可以在售票柜台租借语音导览器（有法、英、日、韩语等，暂无中文版），价格5欧元。
                    </li>
                    <li>
                      4.
                      卢浮宫时常外借或修复藏品，因此在参观过程中可能会遇到个别展品不在原位的情况。
                    </li>
                    <li>
                      5.
                      馆内可以拍照，但禁止使用闪光灯，禁烟、禁食，请不要越界或触摸展品。
                    </li>
                    <li>
                      6.
                      人多混杂时馆内可能会有扒手出没，在参观和拍照时注意保管好自身物品。
                    </li>
                    <li>
                      7.博物馆提供90分钟的导游导览参观：除周二以外，每天11:00和14:00，周三19:00。
                    </li>
                    <li>8.提供13种语言的博物馆楼层图。</li>
                    <li>9. 团队参观可提前预约，预约电话+33-1-40205760。</li>
                  </ul>
                </div>
              </div>
              <div class="Comment">
                <Comment :CommentList="CommentList"></Comment>
              </div>
            </div>
            <!-- 右侧边推荐栏目 -->
            <div class="right_box">
              <el-tabs
                v-model="activeName"
                @tab-click="handleClick"
                :stretch="true"
              >
                <el-tab-pane label="附近景点" name="first">
                  <RecommendList :type="NearbyAttractions"></RecommendList>
                </el-tab-pane>
                <el-tab-pane label="推荐景点" name="second">
                  <RecommendList :type="RecommendAttractions"></RecommendList>
                </el-tab-pane>
              </el-tabs>
            </div>
          </div>
        </el-main>
        <el-footer>
          <Bottom></Bottom>
        </el-footer>
      </el-container>
    </div>
  </div>
</template>

<script>
import Crumbs from "../../New/Crumbs";
import Comment from "../../components/Comment";
import ComponentTitle from "../../components/ComponentsTitle";
import RecommendList from "../../components/RecommendList";
import Maintext from "../../New/Maintext";

export default {
  name: "Attractionspage",
  data() {
    return {
      fav: {
        active: "el-icon-star-off",
        isactive: false,
      },
      comment: 5188,
      carouselInfo: [
        {
          id: "001",
          src: "https://dimg08.c-ctrip.com/images/fd/tg/g3/M0A/07/3D/CggYGlaeCDaAQoagAAnBQ-n4YHs222_R_1600_10000.jpg",
        },
        {
          id: "002",
          src: "https://dimg04.c-ctrip.com/images/350o1b000001b3roo0C6A_R_1600_10000.jpg",
        },
        {
          id: "003",
          src: "https://dimg06.c-ctrip.com/images/350t1b000001b4z4vD0CB_R_1600_10000.jpg",
        },
        {
          id: "004",
          src: "https://dimg01.c-ctrip.com/images/0106r1200089hef2pD0D3_R_1600_10000.jpg",
        },
      ],
      CommentList: [
        {
          name: "zhr",
          rating: 5,
          IP: "未知",
          imageList: [
            {
              id: "001",
              src: "https://dimg06.c-ctrip.com/images/010283224o4h6zn6oC9EC_D_180_180.jpg?proc=autoorient",
            },
            {
              id: "",
              src: "https://dimg03.c-ctrip.com/images/010453224o4h6zn6pA1C8_D_180_180.jpg?proc=autoorient",
            },
            {
              id: "003",
              src: "https://dimg05.c-ctrip.com/images/0101k32158140x3qoB4F3_D_180_180.jpg?proc=autoorient",
            },
            {
              id: "004",
              src: "https://dimg02.c-ctrip.com/images/0103p32158140x3qpDFA1_D_180_180.jpg?proc=autoorient",
            },
            {
              id: "005",
              src: "https://dimg05.c-ctrip.com/images/0104h3224qmxb2wu8BE6F_D_180_180.jpg?proc=autoorient",
            },
          ],
          like: 13,
          hasliked: false,
        },
        {
          name: "zhr",
          rating: 5,
          IP: "未知",
          imageList: [
            {
              id: "001",
              src: "https://dimg06.c-ctrip.com/images/010283224o4h6zn6oC9EC_D_180_180.jpg?proc=autoorient",
            },
            {
              id: "",
              src: "https://dimg03.c-ctrip.com/images/010453224o4h6zn6pA1C8_D_180_180.jpg?proc=autoorient",
            },
            {
              id: "003",
              src: "https://dimg05.c-ctrip.com/images/0101k32158140x3qoB4F3_D_180_180.jpg?proc=autoorient",
            },
            {
              id: "004",
              src: "https://dimg02.c-ctrip.com/images/0103p32158140x3qpDFA1_D_180_180.jpg?proc=autoorient",
            },
            {
              id: "005",
              src: "https://dimg05.c-ctrip.com/images/0104h3224qmxb2wu8BE6F_D_180_180.jpg?proc=autoorient",
            },
          ],
          like: 13,
          hasliked: false,
        },
      ],
      activeName: "first",
      NearbyAttractions: "NearbyAttractions",
      RecommendAttractions: "RecommendAttractions",
    };
  },
  components: {
    Maintext,
    Crumbs,
    ComponentTitle,
    RecommendList,
    Comment,
  },
  methods: {
    handlefav() {
      if (this.fav.isactive) {
        this.fav.active = "el-icon-star-off";
        this.fav.isactive = !this.fav.isactive;
        this.$message("取消收藏");
      } else {
        this.fav.active = "el-icon-star-on";
        this.fav.isactive = !this.fav.isactive;
        this.$message("收藏成功");
      }
    },
    handleClick(tab, event) {
      console.log(tab, event);
    },
    gotoComment() {
      document.querySelector("#Comment").scrollIntoView({
        behavior: "smooth",
      });
    },
    gotonextpage(item, value) {
      switch (item) {
        case "food":
          this.$router.push({
            path: "/frontHome/attraction/food",
            query: { sightsId: value },
          });
          break;
        case "hotel":
          this.$router.push({ path: "/RecommendHotel" });
          break;
      }
    },
  },
  mounted() {
    console.log(this.$route);
  },
};
</script>

<style lang="scss" scoped>
#Attractionspage {
  a {
    text-decoration: none;
    color: #349eff;
    font-size: 14px;
  }
  li {
    list-style: none;
  }
  width: 100%;
  margin-top: 60px;
  color: #333;
  font-family: -apple-system, BlinkMacSystemFont, Segoe UI, PingFang SC,
    Hiragino Sans GB, Microsoft YaHei, Helvetica Neue, Helvetica, Arial,
    sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol;
  .main {
    width: 1340px;
    margin: 0 auto;
    .tags {
      display: flex;
      align-items: center;
      margin-bottom: 20px;
      li {
        width: 74px;
        height: 28px;
        display: inline-block;
        text-align: center;
        line-height: 28px;
        font: {
          size: 13px;
        }
        border: 1px solid #f1f2f3;
        border-radius: 6px;
        background-color: #f6f7f8;
        color: #61666d;
        transition: background-color 0.3s, color 0.3s;
        margin-right: 10px;
        cursor: pointer;
        &:hover {
          background-color: #e3e5e7;
        }
        &:hover {
          color: #18191c;
        }
        p {
          transition: background-color 0.3s, color 0.3s;
          color: #61666d;
        }
      }
    }
  }
  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 150px;
    margin: 0;
  }
  .el-carousel__item img {
    display: block;
    width: 100%;
    height: 100%;
    object-fit: cover;
  }
  .newstitle {
    height: 300px;
    width: 100%;
    .leftText {
      display: inline-block;
      padding-right: 15px;
      border-right: 3px solid rgb(0, 123, 255);
      vertical-align: middle;
      span {
        font-size: 45px;
        font-weight: 400;
      }
    }
    .right {
      padding-left: 10px;
      font-size: 25px;
      vertical-align: middle;
    }
  }
  .carousel {
    display: inline-block;
    width: 60%;
  }
  .textPattern {
    position: relative;
    float: right;
    width: 40%;
    h1 {
      font-size: 30px;
      font-weight: 400;
      margin-bottom: 10px;
    }
    .mainBody {
      padding-left: 20px;

      .rating {
        margin-top: 10px;
        p {
          display: inline-block;
          font-size: 13px;
          cursor: pointer;
          &:hover {
            color: #349eff;
          }
        }
        .leftTag {
          font-weight: 700;
        }
        span {
          font-size: 26px;
          font-weight: 700;
          color: #349eff;
          vertical-align: baseline;
        }
      }
      .translation {
        font-size: 14px;
      }
    }
    .fav {
      position: absolute;
      width: 50px;
      height: 50px;
      right: 5px;
      top: 100px;
    }
    .hotPopover {
      background-color: black;
    }
    .hot {
      position: absolute;
      top: 0;
      right: 0;
      width: 60px;
      height: 55px;
      background-color: pink;
      border-radius: 5px;
      text-align: center;
      background: linear-gradient(90deg, #ff716e, #ff902a);
      color: #d3dce6;
      span {
        display: block;
        font-size: 20px;
        margin-top: 3px;
      }
    }
  }
  .mainText {
    margin-top: 20px;
    .left_box {
      display: inline-block;
      width: 70%;
      h1 {
        margin-bottom: 10px;
      }
      .mainBody {
        padding-right: 30px;
      }
    }
    .right_box {
      float: right;
      width: 30%;
      .el_tab {
        width: 100%;
      }
    }
  }
}
</style>
