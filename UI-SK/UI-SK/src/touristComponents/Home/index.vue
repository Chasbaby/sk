<template>
  <div id="Home">
    <div class="fullscreenswiper">
      <FirstCarousel></FirstCarousel>
    </div>
    <div>
      <RecCard></RecCard>
    </div>
    <div>
      <Exploration></Exploration>
    </div>
    <div>
      <Logobox></Logobox>
    </div>
    <div>
      <Partner></Partner>
    </div>
  </div>
</template>

<script>
import FirstCarousel from "./FirstCarousel";
import Exploration from "./Exploration";
import RecCard from "./RecCard";
import Logobox from "./Logobox";
import Partner from "./Partner";
import { getAllSights, getSights2 } from "@/api/sights/sights";
import { getAll } from "../../api/home/news";
export default {
  name: "Home",
  data() {
    return {
      FirstCarousel: [],
    };
  },
  components: {
    FirstCarousel,
    RecCard,
    Exploration,
    Logobox,
    Partner,
  },
  methods: {
    getMiddleSwiper() {
      getAll().then((response) => {
        // this.MiddleSwiper = response.data;
      });
    },
    getPreview() {
      getSights2().then((response) => {
        // this.PreviewSwiper = response.data;
      });
    },
  },
  mounted() {
    // this.getFirstSwiper();
    this.getMiddleSwiper();
    this.getPreview();
  },
};
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  font-family: "Noto Serif SC", serif !important;
}
.main {
  width: 1340px;
  margin: 0 auto;
}
.fullscreenswiper {
  margin-bottom: 50px;
}
.eventswiper {
  width: 700px;
  height: 600px;
  margin: 0 auto;
  margin-bottom: 30px;
}
.maininformation {
  width: 1200px;
  height: 600px;
  margin: 0 auto;
  margin-bottom: 50px;
}
.previewswiper {
  width: 100%;
  height: 600px;
}
</style>
