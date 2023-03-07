<template>
  <div id="foodDetail">
    <div class="main">
      <div class="production">
        <h3>{{ info.name }}</h3>
        <span class="location">地址: {{ info.location }}</span>
        <span class="tel">电话: {{ info.tel }}</span>
        <span class="businesstime">营业时间: {{ info.businesstime }}</span>
      </div>
      <div class="recipe">
        <el-tabs v-model="activeName">
          <el-tab-pane label="推荐菜" name="first">
            <el-row :gutter="20">
              <el-col :span="4" v-for="(item, index) in recipeList" :key="index"
                ><div class="grid-content bg-purple">
                  <div class="img" v-viewer="{ movable: false }">
                    <div class="mask">
                      <span class="cost">￥{{ item.cost }}</span>
                    </div>
                    <!-- <img :src="item.src" :id="item.src" alt="" /> -->
                    <ImagePreview :src="item.src" width="100%" height="100%" />
                  </div>
                  <div class="name">{{ item.name }}</div>
                </div></el-col
              >
            </el-row>
          </el-tab-pane>
          <el-tab-pane label="环境" name="second"
            ><el-row :gutter="20">
              <el-col :span="4" v-for="(item, index) in recipeList" :key="index"
                ><div class="grid-content bg-purple">
                  <div class="img" v-viewer="{ movable: false }">
                    <div class="mask">
                      <span class="cost">￥{{ item.cost }}</span>
                    </div>
                    <img :src="item.src" :id="item.src" alt="" />
                  </div>
                  <div class="name">{{ item.name }}</div>
                </div></el-col
              >
            </el-row></el-tab-pane
          >
          <el-tab-pane label="价目表" name="third"
            ><el-row :gutter="20">
              <el-col :span="4" v-for="(item, index) in recipeList" :key="index"
                ><div class="grid-content bg-purple">
                  <div class="img" v-viewer="{ movable: false }">
                    <div class="mask">
                      <span class="cost">￥{{ item.cost }}</span>
                    </div>
                    <img :src="item.src" :id="item.src" alt="" />
                  </div>
                  <div class="name">{{ item.name }}</div>
                </div></el-col
              >
            </el-row></el-tab-pane
          >
          <el-tab-pane label="官方相册" name="fourth"
            ><el-row :gutter="20">
              <el-col :span="4" v-for="(item, index) in recipeList" :key="index"
                ><div class="grid-content bg-purple">
                  <div class="img" v-viewer="{ movable: false }">
                    <div class="mask">
                      <span class="cost">￥{{ item.cost }}</span>
                    </div>
                    <img :src="item.src" :id="item.src" alt="" />
                  </div>
                  <div class="name">{{ item.name }}</div>
                </div></el-col
              >
            </el-row></el-tab-pane
          >
        </el-tabs>
      </div>
    </div>
  </div>
</template>

<script>
import "viewerjs/dist/viewer.css";
import Viewer from "v-viewer";
export default {
  data() {
    return {
      activeName: "first",
      info: {
        name: "花千醉（金茂览秀城店）",
        location: "中央路201号L703铺位",
        tel: 19951680147,
        businesstime: "周一至周日 11:00-14:00 17:00-21:00",
      },
      recipeList: [
        {
          name: "招牌焗香芋",
          cost: 32,
          src: "https://qcloud.dpfile.com/pc/-Tqi5uEEtYQAAEn1mcOIIK0XWvq69SJw-6o-5TJU0KOgyRN0TVJ3isxCoRdI3HU3uzFvxlbkWx5uwqY2qcjixFEuLYk00OmSS1IdNpm8K8sG4JN9RIm2mTKcbLtc2o2vfCF2ubeXzk49OsGrXt_KYDCngOyCwZK-s3fqawWswzk.jpg",
        },
        {
          name: "招牌焗香芋",
          cost: 32,
          src: "https://qcloud.dpfile.com/pc/-Tqi5uEEtYQAAEn1mcOIIK0XWvq69SJw-6o-5TJU0KOgyRN0TVJ3isxCoRdI3HU3uzFvxlbkWx5uwqY2qcjixFEuLYk00OmSS1IdNpm8K8sG4JN9RIm2mTKcbLtc2o2vfCF2ubeXzk49OsGrXt_KYDCngOyCwZK-s3fqawWswzk.jpg",
        },
        {
          name: "招牌焗香芋",
          cost: 32,
          src: "https://qcloud.dpfile.com/pc/-Tqi5uEEtYQAAEn1mcOIIK0XWvq69SJw-6o-5TJU0KOgyRN0TVJ3isxCoRdI3HU3uzFvxlbkWx5uwqY2qcjixFEuLYk00OmSS1IdNpm8K8sG4JN9RIm2mTKcbLtc2o2vfCF2ubeXzk49OsGrXt_KYDCngOyCwZK-s3fqawWswzk.jpg",
        },
        {
          name: "招牌焗香芋",
          cost: 32,
          src: "https://qcloud.dpfile.com/pc/-Tqi5uEEtYQAAEn1mcOIIK0XWvq69SJw-6o-5TJU0KOgyRN0TVJ3isxCoRdI3HU3uzFvxlbkWx5uwqY2qcjixFEuLYk00OmSS1IdNpm8K8sG4JN9RIm2mTKcbLtc2o2vfCF2ubeXzk49OsGrXt_KYDCngOyCwZK-s3fqawWswzk.jpg",
        },
        {
          name: "招牌焗香芋",
          cost: 32,
          src: "https://qcloud.dpfile.com/pc/-Tqi5uEEtYQAAEn1mcOIIK0XWvq69SJw-6o-5TJU0KOgyRN0TVJ3isxCoRdI3HU3uzFvxlbkWx5uwqY2qcjixFEuLYk00OmSS1IdNpm8K8sG4JN9RIm2mTKcbLtc2o2vfCF2ubeXzk49OsGrXt_KYDCngOyCwZK-s3fqawWswzk.jpg",
        },
        {
          name: "招牌焗香芋",
          cost: 32,
          src: "https://qcloud.dpfile.com/pc/-Tqi5uEEtYQAAEn1mcOIIK0XWvq69SJw-6o-5TJU0KOgyRN0TVJ3isxCoRdI3HU3uzFvxlbkWx5uwqY2qcjixFEuLYk00OmSS1IdNpm8K8sG4JN9RIm2mTKcbLtc2o2vfCF2ubeXzk49OsGrXt_KYDCngOyCwZK-s3fqawWswzk.jpg",
        },
        {
          name: "招牌焗香芋",
          cost: 32,
          src: "https://qcloud.dpfile.com/pc/-Tqi5uEEtYQAAEn1mcOIIK0XWvq69SJw-6o-5TJU0KOgyRN0TVJ3isxCoRdI3HU3uzFvxlbkWx5uwqY2qcjixFEuLYk00OmSS1IdNpm8K8sG4JN9RIm2mTKcbLtc2o2vfCF2ubeXzk49OsGrXt_KYDCngOyCwZK-s3fqawWswzk.jpg",
        },
      ],
    };
  },
  methods: {},
  // mounted() {
  //   alert(this.$route.query.foodsId);
  // },
};
</script>

<style lang="scss" scoped>
#foodDetail {
  padding-top: 60px;
  background: #f8f8f8;
  .main {
    width: 1340px;
    margin: 0 auto;
    .production {
      padding: 20px;
      background-color: #fff;
      border: 1px solid #f0f0f0;
      margin-bottom: 15px;
      h3 {
        font: {
          weight: 700;
          size: 22px;
        }
      }
      span {
        display: block;
        padding-top: 10px;
        color: #666;
        font-size: 14px;
      }
    }
    .recipe {
      padding: 20px;
      background-color: #fff;
      border: 1px solid #f0f0f0;

      .el-row {
        margin-bottom: 20px;
        &:last-child {
          margin-bottom: 0;
        }
      }
      .el-col {
        border-radius: 4px;
        text-align: center;
        margin-bottom: 10px;
      }
      .grid-content {
        border-radius: 4px;
        min-height: 36px;
        cursor: pointer;
        &:hover .img .mask {
          opacity: 1;
        }
        .img {
          position: relative;
          width: 186px;
          height: 138px;
          border-radius: 4px;
          overflow: hidden;
          margin: 0 auto;
          img {
            width: 100%;
            height: 100%;
          }
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
            background-color: #3baaff;
            display: flex;
            align-items: center;
            justify-content: space-around;
            flex-direction: column;
            pointer-events: none;
            .cost {
              color: #fff;
              font-size: 14px;
            }
          }
        }
        .name {
          padding-top: 5px;
        }
      }
      .row-bg {
        padding: 10px 0;
        background-color: #f9fafc;
      }
      :deep .el-tabs__item {
        font-size: 15px;
      }
    }
  }
}
</style>
