<template>
  <div id="Header" :class="{ isHide: isHide }">
    <div class="header">
      <div class="nav_div">
        <router-link to="/frontHome/home">主页</router-link>
        <router-link to="/frontHome/new" active-class="active"
          >快讯</router-link
        >
        <router-link to="/frontHome/attractions" active-class="active"
          >景点</router-link
        >
        <router-link to="/frontHome/bulletins" active-class="active"
          >公告</router-link
        >
      </div>
      <div class="search" v-if="this.$route.path != '/frontHome/home'">
        <Search></Search>
      </div>

      <div class="avatar">
        <!-- <el-button
          class="login"
          @click="jumpToLogin"
          v-if="this.$store.state.user.token == ''"
          >登录</el-button
        >
        <el-button class="register" v-if="this.$store.state.user.token == ''"
          >注册</el-button
        > -->
        <ul>
          <li>
            <router-link to="/frontHome/creation/index">
              <div class="icon_box">
                <div>
                  <i class="fa fa-puzzle-piece fa-lg" aria-hidden="true"></i>
                </div>
                <div class="text">创作中心</div>
              </div>
            </router-link>
          </li>
          <li>
            <router-link to="/frontHome/subscription" active-class="active">
              <div class="icon_box">
                <i class="fa fa-bell fa-lg" aria-hidden="true"></i>
                <div class="text">文创订阅</div>
              </div>
            </router-link>
          </li>
        </ul>
        <span class="username">{{ $store.state.user.name }}</span>

        <InfoDrawer
          :drawerTitle="drawer.drawerTitle"
          :direction="drawer.direction"
        >
          <el-avatar
            slot="btn"
            src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
            id="avatar"
          ></el-avatar>
        </InfoDrawer>
      </div>
    </div>
  </div>
</template>

<script>
import Drawer from "@/touristComponents/Drawer";
import InfoDrawer from "@/touristComponents/InfoDrawer";
import Search from "@/touristComponents/Search";
export default {
  name: "Header",
  data() {
    return {
      drawer: {
        drawerTitle: "你好 ! " + this.$store.state.user.name,
        direction: "rtl",
      },
      oldScrollTop: 0,
      isHide: false,
      style: { backgroundColor: "", transition: "all ease-in-out .3s" },
    };
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    jumpToLogin(item) {
      if (this.$route.path === "/Login") {
        this.$message("你已经在登陆界面啦~");
        return;
      }
      this.$router.push("/Login");
    },
    handleScroll() {
      let scrollTop =
        window.pageYOffset ||
        document.documentElement.scrollTop ||
        document.body.scrollTop;
      // 滚动条滚动的距离
      let scrollStep = scrollTop - this.oldScrollTop;
      // 更新——滚动前，滚动条距文档顶部的距离
      this.oldScrollTop = scrollTop;
      if (scrollStep < 0) {
        this.isHide = false;
      } else {
        this.isHide = true;
      }
    },
  },
  mounted() {
    window.addEventListener("scroll", this.handleScroll);
    console.log(this.$store);
    if (
      this.$route.path == "/Attractionspage" ||
      this.$route.path == "/Attractionspage"
    ) {
    }
  },
  beforeDestroy() {
    window.removeEventListener("scroll", this.handleScroll);
  },

  components: { Drawer, Search, InfoDrawer },
};
</script>
<style scoped>
li {
  list-style: none;
  font-size: 14px;
}
#Header a {
  text-decoration: none;
  color: rgb(204, 204, 204);
  text-shadow: 0.05rem 0.05rem 0.1rem rgb(0 0 0 / 30%);
  transition: 0.3s all ease-in-out;
}
#Header a:hover {
  color: rgb(0, 123, 255);
}
#Header .active {
  color: rgb(0, 123, 255);
}
#Header {
  height: 60px;
  margin-bottom: 30px;
  position: fixed;
  z-index: 3;
  width: 100%;
  top: 0;
  color: red;

  transition: all 0.3s;
  font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Helvetica Neue, Lato,
    Roboto, PingFang SC, Microsoft YaHei, sans-serif !important;
}
#Header .header {
  width: 80%;
  margin: 0 auto;
  margin-top: 10px;
}
#Header.isHide {
  top: -60px;
  background-color: rgba(255, 255, 255, 0.294);
}

#Header .header.isHide {
  color: rgb(0, 0, 0);
}
#Header .el-menu-demo {
  width: 100%;
}
#Header .nav_div a {
  display: inline-block;
  font-size: 18px;
  text-shadow: 0.05rem 0.05rem 0.1rem rgb(0 0 0 / 30%);
  line-height: 40px;
  margin-right: 20px;
}
#Header .nav_div {
  height: 40px;
  display: inline-block;
}

#Header .search {
  display: inline-block;
  height: 100%;
}
#Header .avatar {
  position: absolute;
  display: flex;
  text-align: center;
  height: 40px;
  line-height: 40px;
  right: 90px;
  top: 10px;
}
#Header .avatar .login,
#Header .avatar .register {
  color: #333;
  background-color: transparent;
  border: none;
  transition: all 0.3s ease-in-out;
}

#Header .avatar .login:hover,
#Header .avatar .register:hover {
  background-color: #409eff;
  color: #fff;
}
#Header .avatar .register {
  margin-right: 15px;
}
#Header .avatar span {
  font-size: 14px;
  padding-right: 10px;
}

#Header ul li {
  display: inline-block;
  text-shadow: 0.05rem 0.05rem 0.1rem rgb(0 0 0 / 30%);
}
#Header ul li .icon_box {
  position: relative;
  height: 50px;
  text-align: center;
  margin-top: -10px;
  margin-right: 15px;
}

#Header ul li i {
  margin: 0 auto;
}
#Header ul li .text {
  margin: -20px auto;
}
#Header #avatar:hover {
  cursor: pointer;
}
.username {
  color: #fff;
}
</style>
