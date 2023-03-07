<template>
  <div id="food">
    <div class="main">
      <el-header>
        <ComponentTitle>
          <template v-slot:Chinese>美食</template>
          <template v-slot:English>Cuisine</template>
        </ComponentTitle>
      </el-header>
      <el-main>
        <ul>
          <li
            v-for="(item, index) in FoodList"
            :key="index"
            @click="gotoFoodDetail(index)"
          >
            <!--            <router-link :to="'/frontHome/food/detail/'+index">-->
            <div class="leftbox">
              <img :src="item.src" alt="" />
            </div>
            <!--            </router-link>-->
            <div class="rightbox">
              <span class="title">{{ item.name }}</span>
              <span class="average">人均 ￥{{ item.average }}</span>
              <span class="type">{{ item.type }} </span>
              <span class="locatio">{{ item.location }}</span>
              <span class="recommend">{{ item.recommend }}</span>
            </div>
          </li>
        </ul>
      </el-main>
    </div>
  </div>
</template>

<script>
import ComponentTitle from "../components/ComponentsTitle";
export default {
  data() {
    return {
      FoodList: [
        {
          name: "南山翁新概念川菜(花园路店)",
          average: 91,
          recommend: "香辣鱿鱼须 川味粉皮（酸甜口） 山城毛血旺",
          type: "川菜馆",
          location: "火车站/玄武湖",
          src: "http://p0.meituan.net/merchant/04d936549d8767c29060165586810029144543.jpg%40340w_255h_1e_1c_1l%7Cwatermark%3D0",
        },
        {
          name: "南山翁新概念川菜(花园路店)",
          average: 91,
          recommend: "香辣鱿鱼须 川味粉皮（酸甜口） 山城毛血旺",
          type: "川菜馆",
          location: "火车站/玄武湖",
          src: "http://p0.meituan.net/merchant/04d936549d8767c29060165586810029144543.jpg%40340w_255h_1e_1c_1l%7Cwatermark%3D0",
        },
        {
          name: "南山翁新概念川菜(花园路店)",
          average: 91,
          recommend: "香辣鱿鱼须 川味粉皮（酸甜口） 山城毛血旺",
          type: "川菜馆",
          location: "火车站/玄武湖",
          src: "http://p0.meituan.net/merchant/04d936549d8767c29060165586810029144543.jpg%40340w_255h_1e_1c_1l%7Cwatermark%3D0",
        },
        {
          name: "南山翁新概念川菜(花园路店)",
          average: 91,
          recommend: "香辣鱿鱼须 川味粉皮（酸甜口） 山城毛血旺",
          type: "川菜馆",
          location: "火车站/玄武湖",
          src: "http://p0.meituan.net/merchant/04d936549d8767c29060165586810029144543.jpg%40340w_255h_1e_1c_1l%7Cwatermark%3D0",
        },
      ],
    };
  },
  methods: {
    gotoFoodDetail(index) {
      this.$router.push({
        path: "/frontHome/food/detail",
        query: { foodsId: index },
      });
    },
  },
  mounted() {},
  components: { ComponentTitle },
};
</script>

<style lang="scss" scoped>
#food {
  padding-top: 60px;
  background: #f6f6f6;
  .main {
    width: 1340px;
    margin: 0 auto;
    ul {
      border: 1px solid #f0f0f0;
      li {
        display: flex;
        padding: 20px;
        border-bottom: 1px solid #f0f0f0;
        transition: all 0.1s ease-in;
        background-color: #fff;
        &:hover {
          cursor: pointer;
          background: #f8f8f8;
        }
        .leftbox {
          width: 172px;
          height: 129px;
          img {
            width: 100%;
            height: 100%;
            object-fit: cover;
          }
        }
        .rightbox {
          flex: 1;
          padding-left: 20px;
          span {
            display: block;
            font-size: 12px;
            color: #999;
            padding-bottom: 10px;
          }
          .title {
            color: #333;
            font-size: 16px;
          }
        }
      }
    }
    .logo {
      i {
        font-size: 20px;
      }
    }
  }
}
</style>>
