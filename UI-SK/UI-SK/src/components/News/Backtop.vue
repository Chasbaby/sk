<template>
  <div class=".el-scrollbar__wrap">
    <el-backtop
      target=".page-component__scroll .el-scrollbar__wrap"
      :bottom="100"
    >
      <div
        style="
           {
            height: 100%;
            width: 100%;
            background-color: #f2f5f6;
            box-shadow: 0 0 6px rgba(0, 0, 0, 0.12);
            text-align: center;
            line-height: 40px;
            color: #1989fa;
          }
        "
      >
        UP
      </div>
    </el-backtop>
  </div>
</template>

<script>
export default {
  name: "Backtop",
};
</script>

<style scoped>
html,
body {
  height: 100%;
}
#app {
  height: 100%;
}
.content {
  height: 100%;
}
.backtop {
  overflow: scroll;
  height: 100%;
}
</style>