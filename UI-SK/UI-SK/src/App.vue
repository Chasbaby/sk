<template>
  <div id="app">
    <!-- <transition name="fade" mode="out-in"> -->
    <router-view />
    <!-- </transition> -->
  </div>
</template>

<script>
export default {
  name: "App",
  metaInfo() {
    return {
      title:
        this.$store.state.settings.dynamicTitle &&
        this.$store.state.settings.title,
      titleTemplate: (title) => {
        return title
          ? `${title} - ${process.env.VUE_APP_TITLE}`
          : process.env.VUE_APP_TITLE;
      },
    };
  },
};
</script>
<style>
body {
  overflow: overlay;
  padding-right: 0 !important;
  padding-left: 0 !important;
  cursor: url(https://cdn.jsdelivr.net/gh/sviptzk/HexoStaticFile@latest/Hexo/img/default.cur),
    default;
}
::-webkit-scrollbar {
  width: 3px;
  height: 1px;
}
::-webkit-scrollbar-thumb {
  background-color: #333;
  border-radius: 20px;
}
::-webkit-scrollbar-track {
  background-color: #eee;
}
/* .fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
} */
</style>
