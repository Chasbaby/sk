<template>
  <div id="Comment">
    <div class="top_box">
      <h1>用户评论({{ CommentNumber }})</h1>
      <div class="btn">
        <el-button
          type="primary"
          icon="el-icon-edit"
          @click="dialogVisible = true"
        >写评价</el-button
        >
      </div>
      <el-dialog
        title="提示"
        :visible.sync="dialogVisible"
        width="30%"
        :before-close="handleClose"
      >
        <Form></Form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogVisible = false"
          >提交</el-button
          >
        </span>
      </el-dialog>
    </div>
    <el-tabs tab-position="top" style="height: 200px">
      <el-tab-pane label="智能排序">
        <CommentList :CommentList="CommentList"></CommentList>
      </el-tab-pane>
      <el-tab-pane label="时间排序">时间排序</el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
  import CommentList from "../CommentList";
  import Form from "../Form";
  import Drawer from "../../Drawer"
  export default {
    name: "Comment",
    data() {
      return {
        CommentNumber: this.CommentList.length,
        dialogVisible: false,
      };
    },
    methods: {
      handleClose(done) {
        this.$confirm("确认关闭？")
          .then((_) => {
            done();
          })
          .catch((_) => {});
      },
    },
    components: { CommentList, Form },
    props: ["CommentList"],
  };
</script>

<style scoped>
  #Comment {
    margin-top: 40px;
    padding-right: 30px;
  }
  #Comment .top_box {
    margin-bottom: 10px;
  }
  #Comment .top_box h1,
  #Comment .top_box .btn {
    display: inline-block;
  }

  #Comment .top_box .btn {
    float: right;
  }
</style>
