<template>
  <div id="searchpage">
    <div class="main">
      <ul>
        <li v-for="(item, index) in list" :key="index">
          <div class="infobox">
            <div class="top">
              <div class="tag article" v-if="item.multipleType == 'ARTICLE'">
                文章
              </div>
              <div class="tag creation" v-if="item.multipleType == 'CREATION'">
                文创
              </div>
              <div class="tag sights" v-if="item.multipleType == 'SIGHTS'">
                景点
              </div>
              <span class="title">{{ item.multipleName }}</span>
            </div>
            <div class="box">
              <div class="leftbox">
                <img :src="url + item.multipleImage" alt="" />
              </div>
              <div class="rightbox">
                <span v-html="item.multipleContent" class="content"> </span>
                <!-- 是否使用 vue-router 的模式，启用该模式会在激活导航时以 index
                作为 path 进行路由跳转 -->
              </div>
            </div>
            <div class="buttonList">
              <button class="thumbup">
                <i class="el-icon-caret-top icon"></i> 赞同
                {{ item.multipleLike }}
              </button>
              <button class="thumbdown">
                <i class="el-icon-caret-bottom icon"></i>
              </button>
              <span class="comment inline"
                ><i class="el-icon-chat-line-round"></i> 评论</span
              >
              <span class="share inline"
                ><i class="el-icon-s-promotion"></i> 分享</span
              >
              <span class="fav inline"
                ><i class="el-icon-star-off"> 收藏</i></span
              >
            </div>
          </div>
        </li>
        <!-- <li>
          <div class="infobox">
            <div class="top">
              <div class="tag">文章</div>
              <span class="title">是不是认真准备考研的几乎都上岸了</span>
            </div>
            <div class="box">
              <div class="leftbox">
                <img
                  src="https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg"
                  alt=""
                />
              </div>
              <div class="rightbox">
                <span>
                  李献计：
                  呵呵，你要是说22年以前害真是这样，23年以后我不敢保证了。新千禧一代人用自己的实际行动证明了当年2018高考是怎么卷成功的，现在到了考研的年纪，照样可以卷成功。
                  K12教培去年遭受打击以后，各…阅读全文​
                </span>
              </div>
            </div>
            <div class="buttonList">
              <button class="thumbup">
                <i class="el-icon-caret-top icon"></i> 赞同 9199
              </button>
              <button class="thumbdown">
                <i class="el-icon-caret-bottom icon"></i>
              </button>
              <span class="comment inline"
                ><i class="el-icon-chat-line-round"></i> 评论</span
              >
              <span class="share inline"
                ><i class="el-icon-s-promotion"></i> 分享</span
              >
              <span class="fav inline"
                ><i class="el-icon-star-off"> 收藏</i></span
              >
            </div>
          </div>
        </li>
        <li>
          <div class="infobox">
            <div class="top">
              <div class="tag">文章</div>
              <span class="title">是不是认真准备考研的几乎都上岸了</span>
            </div>
            <div class="box">
              <div class="leftbox">
                <img
                  src="https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg"
                  alt=""
                />
              </div>
              <div class="rightbox">
                <span>
                  李献计：
                  呵呵，你要是说22年以前害真是这样，23年以后我不敢保证了。新千禧一代人用自己的实际行动证明了当年2018高考是怎么卷成功的，现在到了考研的年纪，照样可以卷成功。
                  K12教培去年遭受打击以后，各…阅读全文​
                </span>
              </div>
            </div>
            <div class="buttonList">
              <button class="thumbup">
                <i class="el-icon-caret-top icon"></i> 赞同 9199
              </button>
              <button class="thumbdown">
                <i class="el-icon-caret-bottom icon"></i>
              </button>
              <span class="comment inline"
                ><i class="el-icon-chat-line-round"></i> 评论</span
              >
              <span class="share inline"
                ><i class="el-icon-s-promotion"></i> 分享</span
              >
              <span class="fav inline"
                ><i class="el-icon-star-off"> 收藏</i></span
              >
            </div>
          </div>
        </li> -->
      </ul>
      <!-- <pagination
        v-show="total > 0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="search"
      /> -->
    </div>
  </div>
</template>

<script>
import { searchAll } from "@/api/search/search.js";
export default {
  data() {
    return {
      total: 0,
      // queryParams: {
      //   pageNum: 1,
      //   pageSize: 10,
      //   keywords: "",
      // },
      url: process.env.VUE_APP_BASE_API,
      list: [],
    };
  },
  methods: {
    // search() {
    //   //pagnition use
    //   searchAll(this.queryParams).then((response) => {
    //     this.list = response.rows;
    //     this.total = response.total;
    //     console.log(response);
    //   });
    // },
    search(value) {
      searchAll(value).then((response) => {
        this.list = response.data;
        // this.total = response.total;
        console.log(response);
      });
    },
  },
  mounted() {},
  created() {
    //this.queryParams.keywords = this.$route.query.keywords;
    this.search(this.$route.query.keywords);
  },
};
</script>

<style lang="scss" scoped>
li {
  list-style: none;
}
#searchpage {
  width: 100%;
  padding-top: 60px;
  .main {
    width: 1340px;
    margin: 0 auto;
    padding: 15px;
    // background-color: red;
    ul {
      li {
        border-bottom: 1px solid #d6d6d6;
        padding: 5px;
        padding-bottom: 15px;
        .infobox {
          cursor: pointer;
          .top {
            display: flex;
            align-items: center;
            padding: 10px 0px 10px 0px;
            .tag {
              display: inline-block;
              width: 34px;
              height: 20px;
              font-size: 13px;
              line-height: 20px;
              text-align: center;
              border-radius: 5px;
              color: #fff;
              margin-right: 5px;
            }
            .article {
              background-color: #1e90ff;
            }
            .creation {
              background-color: #f08080;
            }
            .sights {
              background-color: #67c23a;
            }
            .title {
              display: inline-block;
              font-size: 25px;
              transition: all 0.1s ease-in-out;
              font-weight: 600;
            }
          }
          .box {
            display: flex;
            margin-bottom: 15px;
            height: 130px;
            overflow: hidden;
            .leftbox {
              width: 210px;
              height: 130px;
              border-radius: 10px;
              overflow: hidden;
              margin-right: 15px;
              img {
                width: 100%;
                height: 100%;
                object-fit: cover;
              }
            }
            .rightbox {
              flex: 1;
              word-break: break-word;
              line-height: 1.6;
              white-space: normal;
              .content {
                word-break: break-all;
                display: -webkit-box;
                -webkit-box-orient: vertical;
                -webkit-line-clamp: 3;
                overflow-y: hidden;
                line-height: 30px;
              }
            }
          }
          .buttonList {
            button {
              text-align: center;
              display: inline-block;
              height: 30px;
              border: none;
              cursor: pointer;
              border-radius: 3px;
              color: #056de8;
              &:hover {
                background-color: #dae9fc;
              }
            }
            .inline {
              margin-right: 20px;
              cursor: pointer;
              color: #8590a6;
              &:hover {
                color: #606a7e;
              }
            }
            .thumbup {
              display: flex;
              display: inline-block;
              justify-content: center;
              align-items: center;
              width: 100px;
              margin-right: 5px;
              .icon {
                font-size: 17px;
              }
            }
            .thumbdown {
              width: 30px;
              margin-right: 15px;
            }
          }
        }
        .box:hover {
          + .top {
            .title {
              color: #175199;
            }
          }
        }
      }
    }
  }
}
</style>