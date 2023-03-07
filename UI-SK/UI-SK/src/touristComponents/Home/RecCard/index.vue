<template>
  <div class="container">
    <div class="header">
      <h3>景点</h3>
    </div>
    <div class="card">
      <div class="item" v-for="(item, index) in list" :key="index">
        <div class="img">
          <img :src="item.src" alt="" />
        </div>
        <div class="subbox">
          <div class="subcontent">{{ item.subcontent }}</div>
          <h3 class="title">
            {{ item.title }}
          </h3>
          <p class="intro">
            {{ item.intro }}
          </p>
        </div>
        <div class="tagbox">
          <li class="tag">Live updates</li>
          <li class="tag">Live updates</li>
        </div>
      </div>
    </div>
    <button>继续阅读</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      list: [
        {
          subcontent: "精湛厨艺",
          title:
            "每月即时新增名单：2023 年二月 5 家新入选《新加坡米其林指南》的餐厅",
          intro:
            "二月，我们的评审员分享了 5 家新入选《新加坡米其林指南》的名单。",
          src: "https://d3h1lg3ksw6i6b.cloudfront.net/media/image/2023/02/13/926e710dae9b43a289cfca6c8cbe525b_IMG_7650.JPG",
        },
        {
          subcontent: "精湛厨艺",
          title:
            "每月即时新增名单：2023 年二月 5 家新入选《新加坡米其林指南》的餐厅",
          intro:
            "新加坡的牛车水汇聚许多涵盖不同烹饪风格与本地中餐菜式的必比登推介食肆。看看我们的评审员推荐哪些美食，当中还包括列入《米其林指南》的咖椰烤面包！",
          src: "https://d3h1lg3ksw6i6b.cloudfront.net/media/image/2022/12/19/54439e7c19024a96aedd02f1178741c5_8.Chinatown_B.jpg ",
        },
        {
          subcontent: "精湛厨艺",
          title: "新加坡美食之旅：牛车水美食探险",
          intro:
            "一月，我们的评审员分享了三家新入选《新加坡米其林指南》的名单。",
          src: "https://d3h1lg3ksw6i6b.cloudfront.net/media/image/2023/01/16/11e832c411384dc2bdeeda80de04704e_IMG_6822.JPG",
        },
      ],
    };
  },
};
</script>

<style lang="scss" scoped>
$Font: "Noto Serif SC", serif;
li {
  list-style: none;
}
.container {
  max-width: 1340px;
  padding: 0px 40px 70px 40px;
  // background-color: red;
  margin: 0 auto;
  .header {
    position: relative;
    padding-top: 5px;
    h3 {
      font-family: "Noto Serif SC", serif !important;
      font-size: 40px;
      line-height: 1.2;
      font-weight: 700;
      color: #191919;
    }
  }
  .header::before {
    background: #409eff;
    position: absolute;
    content: "";
    display: block;
    width: 56px;
    height: 4px;
    top: 0;
    left: 0;
  }
  .card {
    display: flex;
    align-items: center;
    flex-wrap: wrap;
    justify-content: space-between;
    min-width: 100%;
    .item {
      box-sizing: border-box;
      width: 31.9%;
      // padding: 0 13px;
      height: 770px;
      margin-bottom: 24px;
      min-width: 380px;
      box-shadow: 0px 0px 8px rgb(0 0 0 / 20%);
      .img {
        width: 100%;
        // vertical-align: top;
        height: 410px;
        img {
          display: block;
          width: 100%;
          object-fit: cover;
          height: 100%;
        }
      }
      .subbox {
        padding: 0 16px;
        padding-bottom: 30px;
        border-bottom: 1px solid #e8e8e8;
        .subcontent {
          height: 28px;
          color: #757575;
          margin-top: 16px;
          margin-bottom: 8px;
        }
        .title {
          overflow: hidden;
          text-overflow: ellipsis;
          -webkit-box-orient: vertical;
          display: -webkit-box;
          -webkit-line-clamp: 2;
          font-family: $Font;
          font-size: 24px;
          margin-bottom: 16px;
          margin-top: 5px;
          margin-bottom: 10px;
          font-weight: 700;
          height: 52.8px;
        }
        .intro {
          overflow: hidden;
          text-overflow: ellipsis;
          -webkit-box-orient: vertical;
          display: -webkit-box;
          -webkit-line-clamp: 2;
          color: #757575;
          font-size: 14px;
          font-family: "open_sans", sans-serif !important;
        }
      }
      .tagbox {
        margin-top: 20px;
        padding: 0 16px;
        .tag {
          display: inline-block;
          // display: flex;
          // width: auto;
          vertical-align: middle;
          height: 35px;
          text-align: center;
          padding: 8px;
          cursor: pointer;
          font-size: 12px;
          border: 1px solid #999999;
          background: #fff;
          color: #4d4d4d;
          border-radius: 4px;
          margin-right: 8px;
          font-family: "open_sans", sans-serif;
        }
      }
    }
  }
  button {
    display: block;
    margin: 0 auto;
    width: 384px;
    height: 54px;
    border: 1px solid #409eff;
    // background-color: #409eff;
    border-radius: 5px;
    margin-top: 20px;
    color: #409eff;
    font-size: 16px;
    transition: all 0.2s ease;
    cursor: pointer;
    &:hover {
      background-color: #409eff;
      color: #fff;
    }
  }
}
</style>