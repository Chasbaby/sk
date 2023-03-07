<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="标签" prop="footerLabel">
        <el-input
          v-model="queryParams.footerLabel"
          placeholder="请输入标签"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="展示类型" prop="footerType">
        <el-select v-model="queryParams.footerType" placeholder="请选择footer区展示类型(社区链接)" clearable size="small">
          <el-option
            v-for="dict in dict.type.sys_footer_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="是否开启" prop="flag">
        <el-input
          v-model="queryParams.flag"
          placeholder="请输入是否开启"
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
          v-hasPermi="['system:footer:add']"
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
          v-hasPermi="['system:footer:edit']"
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
          v-hasPermi="['system:footer:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:footer:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="footerList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="footerId" align="center" prop="footerId" />
      <el-table-column label="标签" align="center" prop="footerLabel" />
      <el-table-column label="展示类型" align="center" prop="footerType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_footer_type" :value="scope.row.footerType"/>
        </template>
      </el-table-column>
      <el-table-column label="是否开启" align="center" prop="flag">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_yes_no" :value="scope.row.flag"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:footer:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:footer:remove']"
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

    <!-- 添加或修改底部展示对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="8">
            <el-form-item label="标签" prop="footerLabel">
              <el-input v-model="form.footerLabel" placeholder="请输入标签" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="展示类型" prop="footerType">
            <el-select v-model="form.footerType" placeholder="请选择footer区展示类型(社区链接)">
              <el-option
                v-for="dict in dict.type.sys_footer_type"
                :key="dict.value"
                :label="dict.label"
                :value="parseInt(dict.value)"
              ></el-option>
            </el-select>
          </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="是否开启" prop="flag">
            <el-radio-group v-model="form.flag">
              <el-radio
                v-for="dict in dict.type.sys_yes_no"
                :key="dict.value"
                :label="dict.value"
              >{{dict.label}}</el-radio>
            </el-radio-group>
          </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="展示类型" prop="footerShow">
              <el-radio-group v-model="form.footerShow">
                <el-radio label="content">内容展示</el-radio>
                <el-radio label="outer">外部链接</el-radio>
                <el-radio label="inner">内部链接</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="8" v-if="form.footerShow=='inner'">
            <el-form-item label="路由" prop="footerRoute">
              <el-input v-model="form.footerRoute" placeholder="请输入如果展示的需要路由那么放在这里" />
            </el-form-item>
          </el-col>
          <el-col :span="8" v-if="form.footerShow=='inner'">
            <el-form-item label="组件位置" prop="footerComponent">
              <el-input v-model="form.footerComponent" placeholder="请输入组件位置" />
            </el-form-item>
          </el-col>
          <el-col :span="8" v-if="form.footerShow=='outer'">
            <el-form-item label="链接路径" prop="footerSrc">
              <el-input v-model="form.footerSrc" placeholder="请输入链接路径" />
            </el-form-item>
          </el-col>

          <el-col :span="24" v-if="form.footerShow=='content'">
            <el-form-item label="展示内容">
              <editor v-model="form.footerContent" :min-height="192"/>
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
import { listFooter, getFooter, delFooter, addFooter, updateFooter } from "@/api/system/footer";

export default {
  name: "Footer",
  dicts: ['sys_footer_type','sys_yes_no'],
  data() {
    return {
      footerType:null,
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
      // 底部展示表格数据
      footerList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        footerLabel: null,
        footerType: null,
        flag: null,
        footerRoute: null,
        footerComponent: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        footerLabel: [
          { required: true, message: "标签不能为空", trigger: "blur" }
        ],      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询底部展示列表 */
    getList() {
      this.loading = true;
      listFooter(this.queryParams).then(response => {
        this.footerList = response.rows;
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
        footerId: null,
        footerLabel: null,
        footerType: null,
        flag: null,
        footerContent: null,
        footerRoute: null,
        footerComponent: null,
        footerShow:null,
        footerSrc: null,
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
      this.ids = selection.map(item => item.footerId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加底部展示";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const footerId = row.footerId || this.ids
      getFooter(footerId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改底部展示";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.footerId != null) {
            updateFooter(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addFooter(this.form).then(response => {
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
      const footerIds = row.footerId || this.ids;
      this.$modal.confirm('是否确认删除底部展示编号为"' + footerIds + '"的数据项？').then(function() {
        return delFooter(footerIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/footer/export', {
        ...this.queryParams
      }, `footer_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
