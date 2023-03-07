<template>
  <div id="app">
    <el-container>
      <Header></Header>

      <el-header>
        <div class="newstitle">
          <div class="leftText">
            <span class="left">新闻</span>
          </div>
          <span class="right">News</span>
        </div>
      </el-header>

      <Crumbs></Crumbs>

      <el-main>
        <Maintext
          class="maintext"
          :newsInformation="homeNews"
          :Top="Top"
        ></Maintext>
      </el-main>

      <Backtop></Backtop>

      <el-footer>
        <Bottom></Bottom>
      </el-footer>
    </el-container>
  </div>
</template>

<script>
  import {getAll} from "../../../api/home/news";
  import Header from "../../../components/News/Header"
  import Backtop from "../../../components/News/Backtop";
  import Maintext from "../../../components/News/Maintext";
  import Bottom from "../../../components/News/Bottom";
  import Crumbs from "../../../components/News/Crumbs";

  export default {
    name: "index",
    data() {
      return {
        homeNews:[],
        Top:[],
      };
    },
    components: {
      Header,
      Maintext,
      Backtop,
      Bottom,
      Crumbs,
    },
    mounted() {
      console.log(this.newsInformation);
    },
    methods:{
      getAllNews(){
        getAll().then(response => {
          console.log(response)
          for (let i = 0;i<response.data.length;i++){
            if (response.data[i].topFlag==="Y"){
              this.Top.push(response.data[i]);
            }else {
              this.homeNews.push(response.data[i])
            }
          }
        })

        console.log(this.Top)
        console.log(this.homeNews)
      }
    },
    created() {
      this.getAllNews();
    }
  };
</script>

<style  scoped>
  #app {
    width: 80%;
    margin: 0 auto;
  }
  .newstitle {
    height: 300px;
    width: 100%;
    /* background-color: pink; */
  }
  .newstitle .leftText {
    display: inline-block;
    padding-right: 15px;
    border-right: 3px solid rgb(0, 123, 255);
    vertical-align: middle;
  }

  .newstitle .leftText span {
    font-size: 45px;
    font-weight: 400;
  }
  .newstitle .right {
    padding-left: 10px;
    font-size: 25px;
    vertical-align: middle;
  }
</style>
