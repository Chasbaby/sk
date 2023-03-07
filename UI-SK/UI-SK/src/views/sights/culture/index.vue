<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="文化标题" prop="cultureTitle">
        <el-input
          v-model="queryParams.cultureTitle"
          placeholder="请输入文化信息标题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="文化类别" prop="cultureCategory">
        <el-input
          v-model="queryParams.cultureCategory"
          placeholder="请输入文化信息类别"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="关键字" prop="cultureKey">
        <el-input
          v-model="queryParams.cultureKey"
          placeholder="请输入关键字"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否展示" prop="showFlag">
        <el-input
          v-model="queryParams.showFlag"
          placeholder="请输入是否展示(0:是 1:否)"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="发布者" prop="createBy">
        <el-input
          v-model="queryParams.createBy"
          placeholder="请输入发布者"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['sights:culture:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['sights:culture:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['sights:culture:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['sights:culture:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="cultureList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="文化信息ID" align="center" prop="cultureId" />
      <el-table-column label="文化信息标题" align="center" prop="cultureTitle" />
      <el-table-column label="文化信息简介" align="center" prop="cultureIntro" />
      <el-table-column label="文化信息类别" align="center" prop="cultureCategory" />
      <el-table-column label="关键字" align="center" prop="cultureKey" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['sights:culture:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['sights:culture:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改景点特色文化对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="文化标题" prop="cultureTitle">
          <el-input v-model="form.cultureTitle" placeholder="请输入文化信息标题" />
        </el-form-item>
        <el-form-item label="文化历史" prop="cultureHistory">
          <el-input v-model="form.cultureHistory" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="文化特色">
          <editor v-model="form.cultureFeature" :min-height="192"/>
        </el-form-item>
        <el-form-item label="文化简介" prop="cultureIntro">
          <el-input v-model="form.cultureIntro" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="信息文件">
          <fileUpload v-model="form.cultureDocument"/>
        </el-form-item>
        <el-form-item label="文化类别" prop="cultureCategory">
          <el-input v-model="form.cultureCategory" placeholder="请输入文化信息类别" />
        </el-form-item>
        <el-form-item label="关键字" prop="cultureKey">
          <el-input v-model="form.cultureKey" placeholder="请输入关键字" />
        </el-form-item>
        <el-form-item label="是否展示" prop="showFlag">
          <el-input v-model="form.showFlag" placeholder="请输入是否展示(0:是 1:否)" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCulture, getCulture, delCulture, addCulture, updateCulture } from "@/api/sights/culture";

export default {
  name: "Culture",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 景点特色文化表格数据
      cultureList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        cultureTitle: null,
        cultureFeature: null,
        cultureCategory: null,
        cultureKey: null,
        showFlag: null,
        createBy: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        cultureTitle: [
          { required: true, message: "文化信息标题不能为空", trigger: "blur" }
        ],
        cultureFeature: [
          { required: true, message: "文化信息特色不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询景点特色文化列表 */
    getList() {
      this.loading = true;
      listCulture(this.queryParams).then(response => {
        this.cultureList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        cultureId: null,
        cultureTitle: null,
        cultureHistory: null,
        cultureFeature: null,
        cultureIntro: null,
        cultureDocument: null,
        cultureCategory: null,
        cultureKey: null,
        showFlag: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.cultureId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加景点特色文化";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const cultureId = row.cultureId || this.ids
      getCulture(cultureId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改景点特色文化";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.cultureId != null) {
            updateCulture(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCulture(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const cultureIds = row.cultureId || this.ids;
      this.$modal.confirm('是否确认删除景点特色文化编号为"' + cultureIds + '"的数据项？').then(function() {
        return delCulture(cultureIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('sights/culture/export', {
        ...this.queryParams
      }, `culture_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
