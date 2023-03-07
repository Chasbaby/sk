<template>
  <div class="container">
    <div class="header">
      <h3>我们的优质品牌合作伙伴</h3>
    </div>
    <div class="piclist">
      <li>
        <div class="pic">
          <img src="../../../assets/images/vue.jpg" alt="" />
        </div>
        <div class="text">Vue.js</div>
      </li>
    </div>
    <button>了解所有合作伙伴</button>
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
  .piclist {
    li {
      display: inline-block;
      .pic {
        width: 160px;
        height: 160px;
        border: 1px solid #a1a1a1;
        img {
          display: block;
          width: 100%;
          height: 100%;
          object-fit: cover;
        }
      }
      .text {
        margin-top: 15px;
        text-align: center;
        font-family: "Noto Serif SC", serif !important;
        font-weight: 700;
        font-size: 20px;
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