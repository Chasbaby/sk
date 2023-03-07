<template>
  <div id="RecommendList">
    <ul v-if="this.type == 'NearbyAttractions'">
      <li v-for="item in AttractionsRecommendList" :key="item.id">
        <div class="img_div">
          <img :src="item.src" alt="" />
        </div>
        <div class="Info">
          <h3>{{ item.name }}</h3>
          <span class="bottom">直线距离{{ item.ditance }}</span>
        </div>
      </li>
    </ul>
    <ul v-else-if="this.type == 'RecommendAttractions'">
      <li v-for="item in AttractionsNearbyList" :key="item.id">
        <div class="img_div">
          <img :src="item.src" alt="" />
        </div>
        <div class="Info">
          <h3>{{ item.name }}</h3>
          <span class="rating">{{ item.rating }}分</span>
          <span class="comment">{{ item.comment }}条点评</span>
          <span class="bottom">{{ item.feature }}</span>
        </div>
      </li>
    </ul>
    <div class="link">
      <el-link type="primary">
        <i class="fa fa-eye" aria-hidden="true"></i> 更多</el-link
      >
    </div>
  </div>
</template>

<script>
  export default {
    name: "RecommendList",
    data() {
      return {
        AttractionsRecommendList: [
          {
            id: "001",
            name: "Le Fronton La Victoire",
            src: "https://dimg03.c-ctrip.com/images/ww0n1f000001godx06B20_R_228_10000.png",
          },
          {
            id: "002",
            name: "Le Fronton La Victoire",
            src: "https://youimg1.c-ctrip.com/target/0ww3m120008yfzfjw9B44_D_140_114.jpg?proc=autoorient",
          },
          {
            id: "003",
            name: "Le Fronton La Victoire",
            src: "https://youimg1.c-ctrip.com/target/0ww3m120008yfzfjw9B44_D_140_114.jpg?proc=autoorient",
          },
          {
            id: "004",
            name: "Le Fronton La Victoire",
            src: "https://youimg1.c-ctrip.com/target/0ww3m120008yfzfjw9B44_D_140_114.jpg?proc=autoorient",
          },
          {
            id: "005",
            name: "Le Fronton La Victoire",
            src: "https://youimg1.c-ctrip.com/target/0ww3m120008yfzfjw9B44_D_140_114.jpg?proc=autoorient",
          },
        ],
        AttractionsNearbyList: [
          {
            id: "001",
            name: "Le Fronton La Victoire",
            src: "https://dimg03.c-ctrip.com/images/ww0n1f000001godx06B20_R_228_10000.png",
            feature: "游乐场",
            rating: 4.7,
            comment: 3352,
          },
          {
            id: "002",
            name: "巴黎迪士尼乐园",
            src: "https://youimg1.c-ctrip.com/target/0102r12000a2pb9ayA915_D_140_114.jpg?proc=autoorient",
            feature: "游乐场",
            rating: 4.7,
            comment: 3352,
          },
          {
            id: "003",
            name: "巴黎圣母院",
            src: "https://youimg1.c-ctrip.com/target/100k0u000000jguly8D69_D_140_114.jpg?proc=autoorient",
            feature: "城堡教堂",
            rating: 4.7,
            comment: 3352,
          },
          {
            id: "004",
            name: "塞纳河游船",
            src: "https://youimg1.c-ctrip.com/target/10030u000000j8z43BAF4_D_140_114.jpg?proc=autoorient",
            feature: "游船观光",
            rating: 4.7,
            comment: 3352,
          },
          {
            id: "005",
            name: "塞纳河",
            src: "https://youimg1.c-ctrip.com/target/fd/tg/g4/M06/07/CB/CggYHlXJUymAcJfXAAXD6rTuhCg853_D_140_114.jpg?proc=autoorient",
            feature: "游山玩水",
            rating: 4.7,
            comment: 3352,
          },
        ],
      };
    },
    props: ["type"],
  };
</script>

<style scoped>
  @import "~@/assets/styles/RecommendList.css";
</style>
