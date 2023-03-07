<template>
  <div id="app">
    <Header class="nav_header" />

    <transition name="fade" mode="out-in">
      <router-view />
    </transition>
    <!-- <div>
      <Audio voice="我是你大爷" :volume="5" />
    </div> -->
    <!-- <div>
      <Bottom />
    </div> -->
  </div>
</template>

<script>
import Bottom from "./components/Bottom";
import Header from "./components/Header";
import Audio from "../components/Audio";

export default {
  components: {
    Bottom,
    Header,
    Audio,
  },
  computed: {
    // key() {
    //   return this.$route.path;
    // },
  },
};
</script>
<style scoped>
* {
  margin: 0;
  padding: 0;
}
.nav_header {
  top: 0px;
  left: 0px;
  z-index: 1;
  height: 50px;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>
