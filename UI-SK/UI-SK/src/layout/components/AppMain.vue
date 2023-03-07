<template>
  <section class="app-main">
    <transition name="fade-transform" mode="out-in">

<!--      keep-alive包裹动态组件时，会缓存不活动的组件实例，而不是摧毁它们-->
<!--      和 transition 相似  时一个抽象组件 它自身不会渲染一个DOM元素，也不会出现在父组件链中-->
<!--      include: 字符串或正则表达式 只有匹配的组件会被缓存-->
<!--      exclude: 字符串或正则表达式 任何匹配的组件都不会被缓存-->

      <keep-alive :include="cachedViews">

        <router-view :key="key" />

      </keep-alive>

    </transition>
  </section>
</template>

<script>
export default {
  name: 'AppMain',
  computed: {
    cachedViews() {
      return this.$store.state.tagsView.cachedViews
    },
    key() {
      console.log("appMain中的路由跳转哦",this.$route.path)
      return this.$route.path
    }
  }
}
</script>

<style lang="scss" scoped>
.app-main {
  /* 50= navbar  50  */
  min-height: calc(100vh - 50px);
  width: 100%;
  position: relative;
  overflow: hidden;
}

.fixed-header+.app-main {
  padding-top: 50px;
}

.hasTagsView {
  .app-main {
    /* 84 = navbar + tags-review = 50 + 34 */
    min-height: calc(100vh - 84px);
  }

  .fixed-header+.app-main {
    padding-top: 84px;
  }
}
</style>

<style lang="scss">
// fix css style bug in open el-dialog
.el-popup-parent--hidden {
  .fixed-header {
    padding-right: 17px;
  }
}
</style>
