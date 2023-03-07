<template>
  <div>
    <el-row :gutter="15">
      <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
        <el-col :span="24">
          <el-form-item label="新闻标题" prop="field101">
            <el-input v-model="formData.field101" placeholder="请输入新闻标题" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="10">
          <el-form-item label="新闻类别" prop="field102">
            <el-select v-model="formData.field102" placeholder="请选择新闻类别" clearable :style="{width: '100%'}">
              <el-option v-for="(item, index) in dict.type.news_type"
                         :key="index"
                         :label="item.label"
                         :value="item.value"
                         :disabled="item.disabled"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="新闻内容" prop="field104">
            <el-input v-model="formData.field104" type="textarea" placeholder="请输入新闻内容"
                      :autosize="{minRows: 4, maxRows: 4}" :style="{width: '100%'}"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="上传" prop="field106" required>
            <el-upload ref="field106" :file-list="field106fileList" :action="field106Action"
                       :before-upload="field106BeforeUpload" list-type="picture-card">
              <i class="el-icon-plus"></i>
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
    dicts: ['news_type'],
    components: {},
    props: [],
    data() {
      return {
        formData: {
          field101: '',
          field102: undefined,
          field104: '富文本',
          field106: null,
          field108: undefined,
        },
        rules: {
          field101: [{
            required: true,
            message: '请输入新闻标题',
            trigger: 'blur'
          }],
          field102: [{
            required: true,
            message: '请选择新闻类别',
            trigger: 'change'
          }],
          field104: [{
            required: true,
            message: '请输入新闻内容',
            trigger: 'blur'
          }],
        },
        field106Action: 'https://jsonplaceholder.typicode.com/posts/',
        field106fileList: [],
        field102Options: [{
          "label": "",
          "value": ""
        }, {
          "label": "",
          "value": ""
        }, {
          "label": "",
          "value": ""
        }],
      }
    },
    computed: {},
    watch: {},
    created() {},
    mounted() {},
    methods: {
      submitForm() {
        this.$refs['elForm'].validate(valid => {
          if (!valid) return

        })
      },
      resetForm() {
        this.$refs['elForm'].resetFields()
      },
      field106BeforeUpload(file) {
        let isRightSize = file.size / 1024 / 1024 < 2
        if (!isRightSize) {
          this.$message.error('文件大小超过 2MB')
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
