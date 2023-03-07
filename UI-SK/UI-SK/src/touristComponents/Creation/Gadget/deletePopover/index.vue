<template>
  <div id="deletepopover">
    <el-popover placement="top" width="160" v-model="visible">
      <p>确定删除该稿件吗？</p>
      <div style="text-align: right; margin: 0">
        <el-button size="mini" type="text" @click="visible = false"
        >取消</el-button
        >
        <el-button type="primary" size="mini" @click="deleteManuscript(data)"
        >确定</el-button
        >
      </div>
      <el-button slot="reference">删除</el-button>
    </el-popover>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        visible: false,
      };
    },
    methods: {
      deleteManuscript(data) {
        console.log(data);
        this.visible = false;
      },
    },
    props: ["data"],
  };
</script>
<style lang="scss" scoped>
  #deletepopover {
    display: inline-block;
  }
</style>
