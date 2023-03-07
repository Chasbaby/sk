<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="政策主题" prop="policyTitle">
        <el-input
          v-model="queryParams.policyTitle"
          placeholder="请输入政策主题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="政策类型" prop="policyType">
        <el-select v-model="queryParams.policyType" placeholder="请选择政策类型" clearable size="small">
          <el-option
            v-for="dict in dict.type.sys_policy_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="适用范围" prop="policyScope">
        <el-input
          v-model="queryParams.policyScope"
          placeholder="请输入适用范围"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="重要程度" prop="policyImportant">
        <el-input
          v-model="queryParams.policyImportant"
          placeholder="请输入重要性程度(1-5)"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="开始时间" prop="policyExecuteTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.policyExecuteTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择开始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间" prop="policyEndingTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.policyEndingTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择结束时间">
        </el-date-picker>
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
          v-hasPermi="['system:policy:add']"
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
          v-hasPermi="['system:policy:edit']"
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
          v-hasPermi="['system:policy:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:policy:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="policyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="政策ID" width="60" align="center" prop="policyId" />
      <el-table-column label="政策主题" align="center" prop="policyTitle" />
      <el-table-column label="政策类型" align="center" prop="policyType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_policy_type" :value="scope.row.policyType"/>
        </template>
      </el-table-column>
      <el-table-column label="适用范围" align="center" prop="policyScope" />
      <el-table-column label="重要性程度(1-5)" align="center" prop="policyImportant" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:policy:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:policy:remove']"
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

    <!-- 添加或修改政策对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1000px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="政策主题" prop="policyTitle">
              <el-input v-model="form.policyTitle" placeholder="请输入政策主题" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="政策内容">
              <editor v-model="form.policyContent" :min-height="192"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="政策类型" prop="policyType">
              <el-select v-model="form.policyType" placeholder="请选择政策类型">
                <el-option
                  v-for="dict in dict.type.sys_policy_type"
                  :key="dict.value"
                  :label="dict.label"
                  :value="parseInt(dict.value)"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="适用范围" prop="policyScope">
              <el-input v-model="form.policyScope" placeholder="请输入适用范围" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="重要程度" prop="policyImportant">
              <el-input v-model="form.policyImportant" placeholder="请输入重要性程度(1-5)" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="法律支撑">
              <fileUpload v-model="form.policyLaw"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="开始时间" prop="policyExecuteTime">
              <el-date-picker clearable size="small"
                              v-model="form.policyExecuteTime"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="选择开始执行时间">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="结束时间" prop="policyEndingTime">
              <el-date-picker clearable size="small"
                              v-model="form.policyEndingTime"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="选择结束执行时间">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listPolicy, getPolicy, delPolicy, addPolicy, updatePolicy } from "@/api/system/policy";

export default {
  name: "Policy",
  dicts: ['sys_policy_type'],
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
      // 政策表格数据
      policyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        policyTitle: null,
        policyType: null,
        policyScope: null,
        policyImportant: null,
        policyExecuteTime: null,
        policyEndingTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        policyTitle: [
          { required: true, message: "政策主题不能为空", trigger: "blur" }
        ],
        policyContent: [
          { required: true, message: "政策内容不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询政策列表 */
    getList() {
      this.loading = true;
      listPolicy(this.queryParams).then(response => {
        this.policyList = response.rows;
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
        policyId: null,
        policyTitle: null,
        policyContent: null,
        policyType: null,
        policyScope: null,
        policyImportant: null,
        policyLaw: null,
        policyExecuteTime: null,
        policyEndingTime: null,
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
      this.ids = selection.map(item => item.policyId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加政策";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const policyId = row.policyId || this.ids
      getPolicy(policyId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改政策";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.policyId != null) {
            updatePolicy(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPolicy(this.form).then(response => {
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
      const policyIds = row.policyId || this.ids;
      this.$modal.confirm('是否确认删除政策编号为"' + policyIds + '"的数据项？').then(function() {
        return delPolicy(policyIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/policy/export', {
        ...this.queryParams
      }, `policy_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
