<template>
  <el-breadcrumb class="app-breadcrumb" separator="/">

    <transition-group name="breadcrumb">
<!--      用于控制有多少个开启的  levelList
          handleLink(item)  当点击 上面的开启的 直接跳转
-->
      <el-breadcrumb-item v-for="(item,index) in levelList" :key="item.path">

        <span v-if="item.redirect === 'noRedirect' || index == levelList.length - 1" class="no-redirect">{{ item.meta.title }}</span>

        <a v-else @click.prevent="handleLink(item)">{{ item.meta.title }}</a>
      </el-breadcrumb-item>

    </transition-group>

  </el-breadcrumb>
</template>

<script>
export default {
  data() {
    return {
      levelList: null
    }
  },
  watch: {
    $route(route) {
      // if you go to the redirect page, do not update the breadcrumbs
      if (route.path.startsWith('/redirect/')) {
        return
      }
      this.getBreadcrumb()
    }
  },
  created() {
    //初始化 加载满足3个条件的 路由  这些路由是可以显示哒
    this.getBreadcrumb()
  },
  methods: {
    getBreadcrumb() {
      // only show routes with meta.title
      console.log("路由的信息看看",this.$route.matched)
      //拿到路由的东西  然后 过滤信息
      let matched = this.$route.matched.filter(item => item.meta && item.meta.title)
      console.log("matched",matched) // 就这里面才有 item.meta
      // meta保存
      const first = matched[0]

      //如果 first 不是 index
      if (!this.isDashboard(first)) {
        // concat()  Combines two or more arrays.
        matched = [{ path: '/index', meta: { title: '首页' }}].concat(matched)
      }

      // breadcrumb: 如果设置为false，则不会在breadcrumb面包屑中显示     三者要全部满足 就可以被放到levelList
      this.levelList = matched.filter(item => item.meta && item.meta.title && item.meta.breadcrumb !== false)
      console.log("最终的名单是什么呢",this.levelList) //看你到哪里就是什么呀
    },
    //
    isDashboard(route) {
      console.log("这里到底是什么意思呢",route && route.name) // 起始index  点击后system
      const name = route && route.name
      // 没名字
      if (!name) {
        return false
      }
      // 路由的名字是不是index呢  那边有几个都是index哒
      return name.trim() === 'Index'
    },

    handleLink(item) {
      const { redirect, path } = item
      console.log("path",path)
      console.log("re",redirect)
      //如果可以重定向
      if (redirect) {
        this.$router.push(redirect)
        return
      }
      this.$router.push(path)
    }

  }
}
</script>

<style lang="scss" scoped>
.app-breadcrumb.el-breadcrumb {
  display: inline-block;
  font-size: 14px;
  line-height: 50px;
  margin-left: 8px;

  .no-redirect {
    color: #97a8be;
    cursor: text;
  }
}
</style>
