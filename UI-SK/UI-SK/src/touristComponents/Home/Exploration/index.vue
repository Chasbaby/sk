<template>
  <div class="container">
    <div class="header">
      <h3>探索丝路荟萃推荐文创名单</h3>
    </div>
    <div class="card">
      <div class="item" v-for="(item, index) in list" :key="index">
        <div class="img">
          <img :src="item.src" alt="" />
        </div>
        <div class="subbox">
          <div class="subcontent"></div>
          <h3 class="title">
            {{ item.name }}
          </h3>
          <p class="intro">
            {{ item.location }}
          </p>
          <p class="tag">$ · 街头小吃</p>
        </div>
        <div class="like" :class="[{ haslike: item.islike }]">
          <i class="el-icon-message-solid icon" @click="like(item)"></i>
        </div>
      </div>
    </div>
    <button><i class="el-icon-s-promotion"></i> 寻找更多文创作品</button>
  </div>
</template>
  
  <script>
export default {
  data() {
    return {
      list: [
        {
          subcontent: "精湛厨艺",
          name: "海南仔",
          islike: 0,
          location: "新加坡",
          src: "https://d3h1lg3ksw6i6b.cloudfront.net/media/image/2023/02/13/926e710dae9b43a289cfca6c8cbe525b_IMG_7650.JPG",
        },
        {
          subcontent: "精湛厨艺",
          name: "蕉财海鲜",
          islike: 0,
          location: "新加坡",
          src: "https://d3h1lg3ksw6i6b.cloudfront.net/media/image/2022/12/19/54439e7c19024a96aedd02f1178741c5_8.Chinatown_B.jpg ",
        },
        {
          subcontent: "精湛厨艺",
          name: "Nasi Lemak Ayam Taliwang",
          location: "新加坡",
          islike: 0,
          src: "https://d3h1lg3ksw6i6b.cloudfront.net/media/image/2023/01/16/11e832c411384dc2bdeeda80de04704e_IMG_6822.JPG",
        },
      ],
    };
  },
  methods: {
    like(item) {
      if (item.islike == 0) {
        item.islike = 1;
      } else {
        item.islike = 0;
      }
    },
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
  padding: 0px 40px 100px 40px;
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
      height: 600px;
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
          height: 10px;
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
          font-size: 20px;
          //   margin-bottom: 16px;
          margin-top: 5px;
          //   margin-bottom: 10px;
          font-weight: 700;
          height: 25.8px;
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
        .tag {
          font-family: "open_sans", sans-serif !important;
          font-weight: 400;
          font-size: 12px;
          line-height: 1;
        }
      }
      .like {
        position: relative;
        color: #6e6e70;
        transition: all 0.2s ease;
        // &:hover {
        //   color: #4b4b4d;
        // }
        .icon {
          position: absolute;
          right: 0px;
          font-size: 30px;
          cursor: pointer;
          //   transition: all 0.2s ease;
        }
      }
      .haslike {
        color: #79bbff;
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
    i {
      font-size: 20px;
    }
    &:hover {
      background-color: #409eff;
      color: #fff;
    }
  }
}
</style>