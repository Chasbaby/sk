<template>
  <div id="Newspage">
    <el-container>
      <el-header>
        <ComponentTitle>
          <template v-slot:Chinese>快讯</template>
          <template v-slot:English>News</template>
        </ComponentTitle>
      </el-header>
      <Crumbs></Crumbs>
      <el-main>
        <Maintext
          class="maintext"
          :newsInformation="newsInformation"
        ></Maintext>
      </el-main>
    </el-container>
  </div>
</template>

<script>
  import Maintext from "../Maintext";
  import Crumbs from "../Crumbs";
  import ComponentTitle from "../../components/ComponentsTitle";
  export default {
    name: "Newspage",
    data() {
      return {
        newsInformation: [
          {
            title: "【快讯】清华大学2022级本科生开启追梦",
            text1:
              "清华新闻网8月17日电（记者 王晓霞 摄影 霍元东 杨屿涵）“欢迎二字班新同学！”“同学，请根据指引完成报到手续！”8月17日，田田荷叶在微风吹拂下摇曳舞动，似是挥手迎接一批崭新面孔的到来。3700余名清华大学2022级本科新生踏入美丽的清华园，开启一段崭新的追梦之旅。",
            text2:
              "典礼上，校长王希勤发表题为“慎独不孤 众行能远”的讲话；航天航空学院教授殷雅俊代表全体教师发言；校学生会主席王睿、日新书院新生吕宇轩分别代表在校生和新生发言。校党委书记邱勇为新生代表佩戴清华大学徽章。副校长彭刚主持典礼。",
            text3:
              "受疫情影响，未能到场的新生和新生亲友通过网络直播参加典礼。部分中学的校长和教师代表、学校全体新入职教师特别受邀线上参加典礼，共同见证这一美好时刻。",
          },
          [
            {
              id: "001", //使用nanoid()函数，自己改一下
              title: "【快讯】清华大学2021级本科生新生报到",
              time: "2022.9.9",
            },
            {
              id: "002",
              title: "清华大学举行2020级本科生新生开学典礼",
              time: "2022.9.9",
            },
            {
              id: "003",
              title: "xxx",
              time: "2022.9.9",
            },
          ],
          [
            "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
          ],
        ],
      };
    },
    components:{
      Maintext,
      Crumbs,
      ComponentTitle
    },
    mounted() {
      alert(this.$route.params && this.$route.params.newsId);
    }

  }
</script>

<style scoped>

</style>
