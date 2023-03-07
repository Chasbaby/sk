<template>
  <div id="CommentList">
    <ul>
      <li v-for="item in CommentList" :key="item.id" class="Commentli">
        <div class="left_box">
          <div>
            <el-avatar
              src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
            ></el-avatar>
            <span class="name">{{ item.name }}</span>
          </div>
        </div>
        <div class="right_box">
          <div class="mainBody">
            <div class="rating">
              <div class="rating_img">
                <img
                  src="@/assets/images/dianping-score-5.png"
                  alt=""
                  v-if="item.rating >= 5"
                />
                <img
                  src="@/assets/images/dianping-score-4.png"
                  alt=""
                  v-else-if="item.rating < 5"
                />
              </div>
              {{ item.rating }}分&nbsp;&nbsp;超棒
            </div>
            <div class="text">
              卢浮宫是世界四大博物馆之一，该宫始建于1204年，原是法国的王宫，现以收藏丰富的绘画和雕刻而闻名于世。以收藏丰富的古典绘画和雕刻而闻名于世，是法国文艺复兴时期最珍贵的建筑物之一。古代埃及、希腊、埃特鲁里亚、罗马的艺术品，到东方各国的艺术品，有从中世纪到现代的雕塑作品，还有数量惊人的王室珍玩，以及绘画精品等等。
            </div>
            <div class="image">
              <ul>
                <li v-for="p in item.imageList" :key="p.id">
                  <div class="img_div">
                    <img :src="p.src" alt="" />
                  </div>
                </li>
              </ul>
            </div>
            <div class="bottom_div">
              <span class="time">2022-04-05</span>
              <span class="ip">IP属地: {{ item.IP }}</span>
              <span class="tipOff" @click="handleTipOff(item)"
              ><i
                class="fa fa-exclamation-triangle fa-lg"
                aria-hidden="true"
              ></i
              >&nbsp;&nbsp;举报</span
              >
              <span
                class="like"
                @click="handleLike(item)"
                :class="[{ hasLiked: item.hasliked }]"
              ><i class="fa fa-thumbs-o-up fa-lg" aria-hidden="true"></i
              >&nbsp;&nbsp;{{ item.like }}</span
              >
            </div>
          </div>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
  export default {
    name: "CommentList",
    data() {
      return {};
    },
    methods: {
      handleTipOff(item) {
        const h = this.$createElement;
        this.$msgbox({
          title: "消息",
          message: "确定举报吗？",
          showCancelButton: true,
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          beforeClose: (action, instance, done) => {
            if (action === "confirm") {
              instance.confirmButtonLoading = true;
              instance.confirmButtonText = "执行中...";
              setTimeout(() => {
                done();
                setTimeout(() => {
                  instance.confirmButtonLoading = false;
                }, 300);
              }, 1000);
            } else {
              done();
            }
          },
        }).then((action) => {
          this.$message({
            type: "info",
            message: "action: 举报 " + action,
          });
        });
      },
      handleLike(item) {
        if (!item.hasliked) {
          item.like++;
          item.hasliked = !item.hasliked;
          this.$message("点赞成功");
        } else {
          item.like--;
          item.hasliked = !item.hasliked;
          this.$message("取消点赞");
        }
      },
    },
    props: ["CommentList"],
  };
</script>

<style lang="scss" scoped>
  $bcolor: #00aeec;
  #CommentList {
    width: 100%;
    li {
      list-style: none;
      display: inline-block;
    }
    .Commentli {
      margin-bottom: 50px;
    }
    .left_box {
      display: inline-block;
      width: 10%;
      height: 100%;
      text-align: center;
    }
    .left_box .name {
      display: block;
    }
    .right_box {
      float: right;
      width: 90%;
      height: 100%;
      .rating {
        font-size: 12px;
        line-height: 20px;
        height: 20px;
        margin-top: 13px;
      }
      .rating_img {
        position: relative;
        display: inline-block;
        width: 20px;
        height: 20px;
        img {
          width: 100%;
          height: 100%;
          vertical-align: middle;
        }
      }
      .rating_img::after {
        content: "";
        position: absolute;
        width: 55px;
        height: 20px;
        left: 20px;
        top: 3px;
        background-color: #fff2bf;
        z-index: -1;
      }
      .mainBody {
        padding-left: 15px;
      }
      .text {
        margin-bottom: 10px;
        margin-top: 15px;
        font-size: 14px;
      }
      .image {
        li {
          margin-right: 10px;
        }
        margin-top: 15px;
      }
      .img_div {
        width: 120px;
        height: 120px;
        img {
          width: 100%;
          height: 100%;
        }
      }
      .bottom_div {
        margin-top: 10px;
        font-size: 12px;
        .like {
          float: right;

          &:hover {
            color: $bcolor;
            cursor: pointer;
          }
        }
        .hasLiked {
          color: $bcolor;
        }

        .tipOff {
          float: right;
          &:hover {
            color: $bcolor;
            cursor: pointer;
          }
        }
        span {
          margin-right: 10px;
        }
      }
    }
  }
</style>
