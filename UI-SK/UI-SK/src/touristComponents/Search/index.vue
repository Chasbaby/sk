<template>
  <div id="search">
    <el-popover
      placement="bottom"
      width="400"
      trigger="click"
      v-model="showPopover"
    >
      <el-input
        placeholder="请输入内容"
        prefix-icon="el-icon-search"
        v-model="input"
        slot="reference"
        @focus="hotsearch"
        @keyup.enter.native="goToSearch()"
        style="width: 400px"
        ref="input"
      >
      </el-input>
      <!-- <el-collapse v-model="activeNames" @change="handleChange">
        <el-collapse-item title="一致性 Consistency" name="1">
          <div>
            与现实生活一致：与现实生活的流程、逻辑保持一致，遵循用户习惯的语言和概念；
          </div>
          <div>
            在界面中一致：所有的元素和结构需保持一致，比如：设计样式、图标和文本、元素的位置等。
          </div>
        </el-collapse-item>
      </el-collapse> -->
      <div class="box">
        <div class="title tagbox">
          <div>{{ title }}</div>
          <ul v-if="this.title == '热搜榜'">
            <li v-if="this.hotSearch.length >= 1"></li>
            <li v-else class="null">暂无热搜</li>
          </ul>
        </div>
        <div class="suggestbox" v-if="this.title == '搜索建议'">
          <div class="article tagbox">
            <i class="el-icon-reading"></i> <span class="tag">文章</span>
            <ul>
              <div v-if="this.article.searchReturns.length >= 1">
                <li
                  v-for="(item, index) in article.searchReturns"
                  :key="index"
                  class="have"
                >
                  <span v-html="item.articleTitle"></span>
                  <!-- {{ item.articleTitle }} -->
                </li>
              </div>
              <li v-else class="null">暂无文章</li>
            </ul>
          </div>
          <div class="creation tagbox">
            <i class="el-icon-shopping-bag-1"></i> <span class="tag">文创</span>
            <ul>
              <div v-if="this.creation.searchReturns.length >= 1">
                <li
                  v-for="(item, index) in creation.searchReturns"
                  :key="index"
                  class="have"
                >
                  <span v-html="item.culCreativityTitle"></span>
                </li>
              </div>
              <li v-else class="null">暂无文创</li>
            </ul>
          </div>
          <div class="attraction tagbox">
            <i class="el-icon-map-location"></i> <span class="tag">景点</span>
            <ul>
              <div v-if="this.attraction.searchReturns.length >= 1">
                <li
                  v-for="(item, index) in attraction.searchReturns"
                  :key="index"
                  class="have"
                >
                  <span v-html="item.sightsName"></span>
                </li>
              </div>
              <li v-else class="null">暂无景点</li>
            </ul>
          </div>
        </div>
      </div>
    </el-popover>
  </div>
</template>

<script>
import { searchSights } from "@/api/search/search.js";
export default {
  data() {
    return {
      showPopover: false,
      activeNames: ["1"],
      title: "热搜榜",
      input: "",
      hotSearch: [],
      suggestSearch: {},
      article: {
        route: "",
        searchReturns: [],
        type: "",
      },
      creation: {
        route: "",
        searchReturns: [],
        type: "",
      },
      attraction: {
        route: "",
        searchReturns: [],
        type: "",
      },
    };
  },
  watch: {
    input: function () {
      if (this.input != "") {
        this.title = "搜索建议";
      } else {
        this.title = "热搜榜";
        this.article = {};
        this.creation = {};
        this.attraction = {};
      }
      this.suggestsearch(this.input);
    },
  },
  methods: {
    hotsearch() {}, // 热搜建议
    suggestsearch(keywords) {
      searchSights(keywords).then((response) => {
        console.log(response);
        this.attraction = response.data[0];
        this.creation = response.data[1];
        this.article = response.data[2];
        console.log(this.attraction);
      });
    }, //搜索建议
    handleChange(val) {
      console.log(val);
    },
    clickMusic(item) {},
    gotoDetail(id) {},
    goToSearch() {
      if (this.input != "") {
        this.$router.push({
          path: "/frontHome/search",
          query: { keywords: this.input },
        });
        this.$refs.input.blur();
        this.showPopover = false;
      }
    },
  },
};
</script>

<style lang="scss" scoped>
li {
  list-style: none;
}
.box {
  // min-height: 150px;
  max-height: 500px;
  overflow: scroll;
  overflow-x: hidden;
  transition: all 0.3s ease-in-out;
}
.tag {
  font-size: 14px;
}
.have {
  padding: 10px 10px 10px 20px;
  transition: all 0.3s ease-in-out;
  cursor: pointer;
  &:hover {
    background-color: rgba(131, 131, 131, 0.561);
  }
}
.null {
  text-align: center;
  color: rgba(90, 90, 90, 0.609);
}
.tagbox {
  padding-bottom: 10px;
}
</style>
