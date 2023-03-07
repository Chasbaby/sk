<template>
  <div>
    <el-form
      :model="ruleForm"
      :rules="rules"
      ref="ruleForm"
      label-width="100px"
      class="demo-ruleForm"
    >
      <el-form-item label="文本编辑区域">
        <mavon-editor v-model="ruleForm.article_content"></mavon-editor>
      </el-form-item>
      <el-form-item label="文章标题" prop="title">
        <el-input v-model="ruleForm.articleTitle"></el-input>
      </el-form-item>
      <el-form-item label="景点图片">
        <imageUpload v-model="ruleForm.articleCover" />
      </el-form-item>
      <el-form-item label="文章状态" prop="status">
        <el-radio-group v-model="ruleForm.status">
          <el-radio
            v-for="dict in dict.type.article_state"
            :key="dict.value"
            :label="dict.value"
            >{{ dict.label }}</el-radio
          >
        </el-radio-group>
      </el-form-item>
      <el-form-item label="文章分类" prop="articleCategory">
        <el-radio-group v-model="ruleForm.articleCategory">
          <el-radio
            v-for="dict in dict.type.article_category"
            :key="dict.value"
            :label="dict.value"
            >{{ dict.label }}</el-radio
          >
        </el-radio-group>
      </el-form-item>
      <el-form-item label="文章类型" prop="articleType">
        <el-radio-group v-model="ruleForm.articleType">
          <el-radio
            v-for="dict in dict.type.article_type"
            :key="dict.value"
            :label="dict.value"
            >{{ dict.label }}</el-radio
          >
        </el-radio-group>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')"
          >立即创建</el-button
        >
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
export default {
  dicts: ["article_state", "article_category", "article_type"],
  data() {
    return {
      ruleForm: {
        articleTitle: "",
        articleContent: "",
        articleCover: "",
        status: "",
        articleCategory: "",
        articleType: "",
      },
      rules: {
        title: [
          { required: true, message: "请输入活动名称", trigger: "blur" },
          { min: 3, max: 5, message: "长度在 3 到 5 个字符", trigger: "blur" },
        ],
        articleType: [
          { required: true, message: "请选择文章类型", trigger: "change" },
        ],
        status: [
          { required: true, message: "请选择文章状态", trigger: "change" },
        ],
        articleCategory: [
          { required: true, message: "请选择文章分类", trigger: "change" },
        ],
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
};
</script>