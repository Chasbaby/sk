<template>
  <div id="appeal">
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane :label="'全部 ' + '(' + tagList[0] + ')'" name="first">
        <el-empty
          description="没有该类型的申诉(''▔□▔)"
          v-if="tagList[0] == 0"
        ></el-empty>
      </el-tab-pane>
      <el-tab-pane :label="'进行中 ' + '(' + tagList[1] + ')'" name="second"
      ><el-empty
        description="没有该类型的申诉(''▔□▔)"
        v-if="tagList[1] == 0"
      ></el-empty
      ></el-tab-pane>
      <el-tab-pane :label="'已完成 ' + '(' + tagList[2] + ')'" name="third"
      ><el-empty
        description="没有该类型的申诉(''▔□▔)"
        v-if="tagList[2] == 0"
      ></el-empty
      ></el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        activeName: "first",
        tagList: [10, 0, 10],
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
  #appeal {
    background-color: $backcolor;
    padding: 20px 40px 20px 40px;
  }
</style>
