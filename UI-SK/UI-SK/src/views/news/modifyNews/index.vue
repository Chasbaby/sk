<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="${comment}" prop="newTitle">
        <el-input
          v-model="queryParams.newTitle"
          placeholder="请输入${comment}"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="imageId">
        <el-input
          v-model="queryParams.imageId"
          placeholder="请输入${comment}"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="publicTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.publicTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择${comment}">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="${comment}" prop="author">
        <el-input
          v-model="queryParams.author"
          placeholder="请输入${comment}"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="点击量" prop="hits">
        <el-input
          v-model="queryParams.hits"
          placeholder="请输入点击量"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="置顶(0否 1是)" prop="ifTop">
        <el-input
          v-model="queryParams.ifTop"
          placeholder="请输入置顶(0否 1是)"
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
          v-hasPermi="['system:new:add']"
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
          v-hasPermi="['system:new:edit']"
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
          v-hasPermi="['system:new:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:new:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="newList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="${comment}" align="center" prop="newId" />
      <el-table-column label="${comment}" align="center" prop="newTitle" />
      <el-table-column label="${comment}" align="center" prop="newType" />
      <el-table-column label="${comment}" align="center" prop="newContent" />
      <el-table-column label="${comment}" align="center" prop="imageId" />
      <el-table-column label="${comment}" align="center" prop="publicTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.publicTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="${comment}" align="center" prop="author" />
      <el-table-column label="点击量" align="center" prop="hits" />
      <el-table-column label="置顶(0否 1是)" align="center" prop="ifTop" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:new:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:new:remove']"
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

    <!-- 添加或修改新闻管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="${comment}" prop="newTitle">
          <el-input v-model="form.newTitle" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}">
          <editor v-model="form.newContent" :min-height="192"/>
        </el-form-item>
        <el-form-item label="${comment}" prop="imageId">
          <el-input v-model="form.imageId" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="publicTime">
          <el-date-picker clearable size="small"
            v-model="form.publicTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择${comment}">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="${comment}" prop="author">
          <el-input v-model="form.author" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="点击量" prop="hits">
          <el-input v-model="form.hits" placeholder="请输入点击量" />
        </el-form-item>
        <el-form-item label="置顶(0否 1是)" prop="ifTop">
          <el-input v-model="form.ifTop" placeholder="请输入置顶(0否 1是)" />
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
import { listNew, getNew, delNew, addNew, updateNew } from "@/api/news/new";

export default {
  name: "New",
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
      // 新闻管理表格数据
      newList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        newTitle: null,
        newType: null,
        newContent: null,
        imageId: null,
        publicTime: null,
        author: null,
        hits: null,
        ifTop: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        newTitle: [
          { required: true, message: "$comment不能为空", trigger: "blur" }
        ],
        newType: [
          { required: true, message: "$comment不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询新闻管理列表 */
    getList() {
      this.loading = true;
      listNew(this.queryParams).then(response => {
        this.newList = response.rows;
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
        newId: null,
        newTitle: null,
        newType: null,
        newContent: null,
        imageId: null,
        publicTime: null,
        author: null,
        hits: null,
        ifTop: null
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
      this.ids = selection.map(item => item.newId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加新闻管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const newId = row.newId || this.ids
      getNew(newId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改新闻管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.newId != null) {
            updateNew(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addNew(this.form).then(response => {
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
      const newIds = row.newId || this.ids;
      this.$modal.confirm('是否确认删除新闻管理编号为"' + newIds + '"的数据项？').then(function() {
        return delNew(newIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/new/export', {
        ...this.queryParams
      }, `new_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
