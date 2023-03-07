<template>
  <div id="Acttractions">
    <!-- <Header></Header> -->
    <TitleSwiper></TitleSwiper>
    <div class="main">
      <el-container>
        <el-header class="header">
          <ComponentTitle>
            <template v-slot:Chinese>景点</template>
            <template v-slot:English>Acttractions</template>
          </ComponentTitle>
          <button class="refresh" @click="refresh()">
            <i
              :class="[rotate ? 'el-icon-refresh go' : 'el-icon-refresh aa ']"
              style="font-size: 20px"
            >
            </i
            >换一换
          </button>
        </el-header>
        <el-main>
          <div class="wrapper">
            <div
              class="item"
              v-for="(item, index) in ListInfo"
              :key="index"
              @click="gotoAttraction(item.sightsId)"
            >
              <div class="img">
                <img :src="item.sightsImage" alt="" />
                <div class="mask"></div>
              </div>
              <div class="text">
                <span class="intro">{{ item.sightsTitle }}</span>
                <span class="location">
                  <i class="el-icon-map-location"></i>{{ item.location }}</span
                >
              </div>
            </div>
          </div>
          <CategoryLink :listInfo="CategoryLinkInfo"></CategoryLink>
        </el-main>
        <el-footer>
          <!-- <Bottom></Bottom> -->
        </el-footer>
      </el-container>
    </div>
  </div>
</template>

<script>
import ComponentTitle from "../components/ComponentsTitle";
import TitleSwiper from "../components/TitleSwiper";
import CategoryLink from "../components/CategoryLink";
import ViewTemplate from "../components/ViewTemplate";
export default {
  name: "Acttractions",
  data() {
    return {
      rotate: false,
      ListInfo: [
        {
          sightsId: "003",
          sightsImage:
            "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
          sightsTitle: "清华大学学位评定委员会2022年第四次全体会议举行",
          location: "Beijing",
        },
        {
          sightsId: "004",
          sightsImage:
            "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
          sightsTitle: "清华大学学位评定委员会2022年第四次全体会议举行",
          location: "Beijing",
        },
        {
          sightsId: "005",
          sightsImage:
            "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
          sightsTitle: "清华大学学位评定委员会2022年第四次全体会议举行",
          location: "Beijing",
        },
        {
          sightsId: "mvOqQBlXBP7YufhHvHxh3",
          sightsImage:
            "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
          sightsTitle: "清华大学学位评定委员会2022年第四次全体会议举行",
          location: "Beijing",
        },
        {
          sightsId: "002",
          sightsImage:
            "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
          sightsTitle: "清华大学学位评定委员会2022年第四次全体会议举行",
          location: "Beijing",
        },
        {
          sightsId: "003",
          sightsImage:
            "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
          sightsTitle: "清华大学学位评定委员会2022年第四次全体会议举行",
          location: "Beijing",
        },
        {
          sightsId: "004",
          sightsImage:
            "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
          sightsTitle: "清华大学学位评定委员会2022年第四次全体会议举行",
          location: "Beijing",
        },
        {
          sightsId: "005",
          sightsImage:
            "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
          sightsTitle: "清华大学学位评定委员会2022年第四次全体会议举行",
          location: "Beijing",
        },
      ],
      CategoryLinkInfo: [
        {
          id: "001",
          name: "热门景点",
          itemInfoList: [
            {
              id: "001",
              time: "2022.8.26",
              title: "​清华大学台湾研究院主办“大变局下的台湾问题”研讨会",
            },
            {
              id: "002",
              time: "2022.8.26",
              title: "​清华大学台湾研究院主办“大变局下的台湾问题”研讨会",
            },
            {
              id: "003",
              time: "2022.8.26",
              title: "​清华大学台湾研究院主办“大变局下的台湾问题”研讨会",
            },
          ],
        },
        {
          id: "002",
          name: "大众好评",
          itemInfoList: [
            {
              id: "001",
              time: "2022.8.26",
              title: "​清华大学台湾研究院主办“大变局下的台湾问题”研讨会",
            },
            {
              id: "002",
              time: "2022.8.26",
              title: "​清华大学台湾研究院主办“大变局下的台湾问题”研讨会",
            },
            {
              id: "003",
              time: "2022.8.26",
              title: "​清华大学台湾研究院主办“大变局下的台湾问题”研讨会",
            },
          ],
        },
        {
          id: "003",
          name: "人文风情",
          itemInfoList: [
            {
              id: "001",
              time: "2022.8.26",
              title: "​清华大学台湾研究院主办“大变局下的台湾问题”研讨会",
            },
            {
              id: "002",
              time: "2022.8.26",
              title: "​清华大学台湾研究院主办“大变局下的台湾问题”研讨会",
            },
            {
              id: "003",
              time: "2022.8.26",
              title: "​清华大学台湾研究院主办“大变局下的台湾问题”研讨会",
            },
          ],
        },
      ],
      type: "Attractions",
    };
  },
  methods: {
    refresh() {
      this.rotate = !this.rotate;
      console.log(this.rotate);
    },
    gotoAttraction(value) {
      this.$router.push({
        path: "/frontHome/attractions/attraction/",
        query: { sightsId: value },
      });
    },
    // gotoLink(value) {
    //   if (this.type === "News") {
    //     this.$router.push({ path: "/Newspage", query: { id: value } });
    //   } else if (this.type === "Attractions") {
    //     this.$router.push({ path: "/Attractionspage", query: { id: value } });
    //   } else if (this.type === "Bulletins") {
    //     this.$router.push({ path: "/Bulletinspage", query: { id: value } });
    //   }
    // },
  },
  components: {
    ComponentTitle,
    TitleSwiper,
    CategoryLink,
    ViewTemplate,
  },
};
</script>

<style lang="scss" scoped>
#Acttractions {
  height: 0px;
}
#Acttractions .main {
  width: 80%;
  margin: 0 auto;
}
.eventswiper {
  width: 700px;
  height: 600px;
  margin: 0 auto;
  margin-bottom: 30px;
}
.aa {
  transition: all 0.2s;
}
.go {
  transform: rotate(-180deg);
  transition: all 0.2s;
}
.header {
  position: relative;
  .refresh {
    display: flex;
    justify-content: center;
    text-align: center;
    align-items: center;
    position: absolute;
    width: 80px;
    height: 40px;
    right: 30px;
    top: 10px;
    font-size: 16px;
    border-radius: 8px;
    font-size: 12px;
    color: #18191c;
    border: 1px solid #c0c0c0;
    transform-origin: center;
    transition: 0.2s;
    cursor: pointer;
    &:active {
      transform: scale(0.95) translateZ(0);
    }
  }
}
.wrapper {
  margin: 0 auto;
  /* 声明一个容器 */
  display: grid;
  /*  声明列的宽度  */
  grid-template-columns: repeat(5, 1fr);
  /*  声明行间距和列间距  */
  grid-gap: 40px;
  grid-column-gap: 20px;
  /*  声明行的高度  */
  grid-template-rows: 250px 250px;
}
.item {
  color: #fff;
  cursor: pointer;
  min-width: 250px;
  &:hover .img .mask {
    opacity: 1;
  }
  .img {
    position: relative;
    width: 100%;
    height: 170px;
    border-radius: 10px;
    overflow: hidden;
    .mask {
      position: absolute;
      width: 100%;
      height: 100%;
      display: block;
      top: 0;
      opacity: 0;
      left: 0;
      z-index: 2;
      transition: 0.2s all ease-in;
      background-color: #0000003d;
      display: flex;
      align-items: center;
      justify-content: space-around;
      flex-direction: column;
      pointer-events: none;
    }
    img {
      width: 100%;
      height: 100%;
    }
  }
  .text {
    margin-top: 10px;
    padding-right: 16px;
    font-size: 15px;
    letter-spacing: 1px;
    .intro {
      text-overflow: -o-ellipsis-lastline;
      overflow: hidden;
      text-overflow: ellipsis;
      display: -webkit-box;
      -webkit-line-clamp: 2;
      line-clamp: 2;
      -webkit-box-orient: vertical;
      line-height: 20px;
      color: #18191c;
    }
    .location {
      margin-top: 5px;
      display: block;
      color: #9499a0;
      font-size: 15px;
    }
  }
}
</style>
