<template>
  <div id="Form">
    <el-form
      :model="ruleForm"
      :rules="rules"
      ref="ruleForm"
      label-width="100px"
      class="demo-ruleForm"
    >
      <el-form-item label="点评内容" prop="desc">
        <el-input type="textarea" v-model="ruleForm.desc"></el-input>
      </el-form-item>
      <el-form-item label="旅行时间" required>
        <el-col :span="11">
          <el-form-item prop="date1">
            <el-date-picker
              type="date"
              placeholder="选择日期"
              v-model="ruleForm.date1"
              style="width: 100%"
            ></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col class="line" :span="2">-</el-col>
        <el-col :span="11">
          <el-form-item prop="date2">
            <el-time-picker
              placeholder="选择时间"
              v-model="ruleForm.date2"
              style="width: 100%"
            ></el-time-picker>
          </el-form-item>
        </el-col>
      </el-form-item>
      <el-form-item label="特殊资源" prop="resource">
        <el-radio-group v-model="ruleForm.resource">
          <el-radio label="线上品牌商赞助"></el-radio>
          <el-radio label="线下场地免费"></el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="评分">
        <Rating></Rating>
      </el-form-item>
      <el-form-item label="上传照片">
        <UploadPicture></UploadPicture>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')"
        >提交评价</el-button
        >
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import Rating from "../Rating";
  import UploadPicture from "../UploadPicture";
  export default {
    name: "Form",
    data() {
      return {
        ruleForm: {
          date1: "",
          date2: "",
          type: [],
          resource: "",
          desc: "",
        },
        rules: {
          date1: [
            {
              type: "date",
              required: true,
              message: "请选择日期",
              trigger: "change",
            },
          ],
          date2: [
            {
              type: "date",
              required: true,
              message: "请选择时间",
              trigger: "change",
            },
          ],
          desc: [{ required: true, message: "请填写评价内容", trigger: "blur" }],
        },
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            alert("submit!");
          } else {
            console.log("error submit!!");
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
    },
    components: { Rating, UploadPicture },
  };
</script>

<style>
</style>
