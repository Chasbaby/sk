<template>
  <div id="manuscript">
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane :label="'进行中 ' + inProgresstList.length" name="first">
        <el-empty
          description="一个稿件都没有，请换个筛选条件(''▔□▔)"
          v-if="inProgresstList.length == 0"
        ></el-empty>
        <manuscript-list v-else :List="inProgresstList"></manuscript-list>
      </el-tab-pane>
      <el-tab-pane :label="'已通过 ' + passedList.length" name="second">
        <el-empty
          description="一个稿件都没有，请换个筛选条件(''▔□▔)"
          v-if="passedList.length == 0"
        ></el-empty>
        <manuscript-list v-else :List="passedList"></manuscript-list>
      </el-tab-pane>
      <el-tab-pane :label="'未通过 ' + failedList.length" name="third">
        <el-empty
          description="一个稿件都没有，请换个筛选条件(''▔□▔)"
          v-if="failedList.length == 0"
        ></el-empty>
        <manuscript-list v-else :List="failedList"></manuscript-list>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
  import ManuscriptList from '../Gadget/manuscriptList/index'
  export default {
    components: { ManuscriptList },
    data() {
      return {
        // visible: true,
        activeName: "first",
        tagList: [10, 0, 10],
        inProgresstList: [
          {
            name: "虽然杂鲷,但是生命之舞！",
            src: "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
            time: "23-01-11 13:16:22",
            view: 10,
            thumbsup: 2,
            favourite: 0,
            comment: 1,
          },
          {
            name: "浪花一朵朵~",
            src: "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
            time: "22-04-03 22:21:32",
            view: 10,
            thumbsup: 2,
            favourite: 0,
            comment: 1,
          },
          {
            name: "/小缸三湖慈鲷/",
            src: "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
            time: "22-01-27 00:41:28",
            view: 10,
            thumbsup: 2,
            favourite: 0,
            comment: 1,
          },
          {
            name: "夜半修罗场",
            src: "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
            time: "22-01-16 23:44:38",
            view: 10,
            thumbsup: 2,
            favourite: 0,
            comment: 1,
          },
        ],
        passedList: [],
        failedList: [],
      };
    },
    methods: {
      handleClick(tab, event) {
        console.log(tab, event);
      },
    },
  };
</script>

<style lang="scss" scoped>
  $backcolor: #ffffff;
  #manuscript {
    background-color: $backcolor;
    padding: 20px 40px 20px 40px;
  }
</style>
