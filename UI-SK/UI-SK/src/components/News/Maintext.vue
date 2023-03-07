<template>
  <div class="box">
    <div class="mainbox">
      <h1 class="title">{{Top[0].newsTitle}}</h1>
      <!-- 文章标题 -->
      <div class="newsbox" v-html="Top[0].newsContent">
        {{Top[0].newsContent}}}
<!--        <span class="newstext">{{ newsInformation[0].text1 }} </span>-->
<!--        <Picture :src="newsInformation[2][0]">我是这张图片的注释</Picture>-->
<!--        <span class="newstext">{{ newsInformation[0].text2 }} </span>-->
<!--        <Picture :src="newsInformation[2][0]">我是这张图片的注释</Picture>-->
<!--        <span class="newstext">{{ newsInformation[0].text3 }} </span>-->
      </div>

      <div class="linkList">
        <ul>
          <div class="relatedNews">相关新闻</div>
          <a href="#" v-for="news in newsInformation" :key="news.newsId"
            ><li>
              {{ news.newsTitle }}<span>{{ news.createTime }}</span>
            </li></a
          >
        </ul>
      </div>
      <!-- 推荐算法 -->
    </div>
  </div>
</template>

<script>
import Picture from "./Picture.vue";
export default {
  name: "Maintext",
  data() {
    return {};
  },
  components: {
    Picture,
  },
  props: {
    newsInformation: {
      type: Array,
      required: true,
      default: () => [],
    },
    Top:{
      type: Array,
      required: true,
      default: () =>[]
    }
  },
};
</script>

<style scoped>
a {
  text-decoration: none;
  color: #333333;
  transition: 0.3s all ease-in-out;
}
a:hover {
  color: rgb(0, 123, 255);
}
.box {
  display: flex;
}
.title {
  font-size: 36px;
  letter-spacing: 1px;
}
.mainbox {
  flex: 7;
}
.timeline {
  flex: 3;
  top: 30%;
}
.mainbox .newsbox {
  flex: 7;
}
.newsbox img{
  width: 550px;
  height: 340px;
  margin: 0 auto;
}
.newsbox .newstext {
  display: block;
  font-size: 17px;
  text-indent: 2em;
}
.linkList ul {
  margin-top: 30px;
  padding-left: 0px;
}
.linkList .relatedNews {
  font-size: 21px;
  border-bottom: solid 1px #dcdcdc;
  padding: 10px;
}
.linkList li {
  padding: 15px;
  font-size: 17px;
  list-style: none;
  border-bottom: solid 1px #dcdcdc;
}
.linkList li span {
  float: right;
  font-size: 12px;
  color: rgb(0, 123, 255);
}
</style>
