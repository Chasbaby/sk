<template>
  <div>
    <el-row :gutter="15">
      <el-form ref="form" :model="formData" :rules="rules" size="medium" label-width="100px">

        <el-col :span="24">
          <el-form-item label="收件人" prop="to">
            <el-input v-model="formData.to"
                      placeholder="请输入收件人"
                      :maxlength="20"
                      show-word-limit
                      clearable
                      prefix-icon='el-icon-user-solid'
                      suffix-icon='el-icon-delete'
                      :style="{width: '100%'}">
              <template slot="append">@qq.com</template>
            </el-input>
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="主题" prop="title">
            <el-input v-model="formData.title"
                      placeholder="请输入主题"
                      :maxlength="100"
                      clearable
                      prefix-icon='el-icon-potato-strips'
                      suffix-icon='el-icon-delete'
                      :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="内容">
            <editor v-model="formData.emailContent" :min-height="300" :height="500"/>
          </el-form-item>
        </el-col>


        <el-col :span="12">
          <el-form-item label="上传" prop="multipart">
            <el-upload ref="multipart"
                       :file-list="multipartfileList"
                       :action="multipartAction"
                        multiple
                       :before-upload="multipartBeforeUpload"
                       show-file-list
                       limit=4

                       >
              <el-button size="small" type="primary" icon="el-icon-upload">点击上传</el-button>
              <div slot="tip" class="el-upload__tip">只能上传不超过 50MB 的文件</div>
            </el-upload>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item size="large">
            <el-button type="primary" @click="submitForm">提交</el-button>
            <el-button @click="resetForm">重置</el-button>
          </el-form-item>
        </el-col>
      </el-form>
    </el-row>
  </div>
</template>
<script>
  export default {
    components: {},
    props: [],
    data() {
      return {
        baseUrl: process.env.VUE_APP_BASE_API,
        formData: {
          to: '',
          title: '',
          multipart: "",
          emailContent:"",
        },
        rules: {
          to: [{
            required: true,
            message: '请输入收件人',
            trigger: 'blur'
          }],
          title: [{
            required: true,
            message: '请输入主题',
            trigger: 'blur'
          }],
        },
        multipartAction: "",
        multipartfileList: [],
      }
    },
    computed: {},
    watch: {},
    created() {},
    mounted() {},
    methods: {
      submitForm() {
        this.$refs['form'].validate(valid => {
          if (!valid) return
          // TODO 提交表单
          console.log(this.baseUrl)
        })
      },
      resetForm() {
        this.$refs['form'].resetFields()
      },
      multipartBeforeUpload(file) {
        let isRightSize = file.size / 1024 / 1024 < 50
        if (!isRightSize) {
          this.$message.error('文件大小超过 50MB')
        }
        return isRightSize
      },
    }
  }

</script>
<style>
  .el-upload__tip {
    line-height: 1.2;
  }

</style>
