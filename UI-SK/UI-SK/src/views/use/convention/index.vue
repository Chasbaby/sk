<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="标题" prop="conventionLabel">
        <el-input
          v-model="queryParams.conventionLabel"
          placeholder="请输入选项卡标题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="别名" prop="conventionName">
        <el-input
          v-model="queryParams.conventionName"
          placeholder="请输入选项卡别名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否禁用" prop="conventionDisabled">
        <el-select v-model="queryParams.conventionDisabled" placeholder="请选择是否禁用" clearable size="small">
          <el-option
            v-for="dict in dict.type.sys_yes_no"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="是否可关" prop="conventionClosable">
        <el-select v-model="queryParams.conventionClosable" placeholder="请选择标签是否可关闭" clearable size="small">
          <el-option
            v-for="dict in dict.type.sys_yes_no"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="是否延渲" prop="conventionLazy">
        <el-select v-model="queryParams.conventionLazy" placeholder="请选择标签是否延迟渲染" clearable size="small">
          <el-option
            v-for="dict in dict.type.sys_yes_no"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          v-hasPermi="['system:convention:add']"
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
          v-hasPermi="['system:convention:edit']"
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
          v-hasPermi="['system:convention:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:convention:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="conventionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" width="60" align="center" prop="conventionId" />
      <el-table-column label="选项卡标题" align="center" prop="conventionLabel" />
      <el-table-column label="选项卡别名" align="center" prop="conventionName" />
      <el-table-column prop="conventionIcon" label="图标" align="center" width="100">
        <template slot-scope="scope">
          <svg-icon :icon-class="scope.row.conventionIcon" />
        </template>
      </el-table-column>
      <el-table-column label="是否禁用" align="center" prop="conventionDisabled">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_yes_no" :value="scope.row.conventionDisabled"/>
        </template>
      </el-table-column>
      <el-table-column label="标签是否可关闭" align="center" prop="conventionClosable">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_yes_no" :value="scope.row.conventionClosable"/>
        </template>
      </el-table-column>
      <el-table-column label="标签是否延迟渲染" align="center" prop="conventionLazy">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_yes_no" :value="scope.row.conventionLazy"/>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:convention:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:convention:remove']"
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

    <!-- 添加或修改创作公约对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="6">
            <el-form-item label="标题" prop="conventionLabel">
              <el-input v-model="form.conventionLabel" placeholder="请输入选项卡标题" />
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="别名" prop="conventionName">
              <el-input v-model="form.conventionName" placeholder="请输入选项卡别名" />
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="是否禁用">
              <el-radio-group v-model="form.conventionDisabled">
                <el-radio
                  v-for="dict in dict.type.sys_yes_no"
                  :key="dict.value"
                  :label="dict.value"
                >{{dict.label}}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="是否可关">
              <el-radio-group v-model="form.conventionClosable">
                <el-radio
                  v-for="dict in dict.type.sys_yes_no"
                  :key="dict.value"
                  :label="dict.value"
                >{{dict.label}}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="是否延渲">
              <el-radio-group v-model="form.conventionLazy">
                <el-radio
                  v-for="dict in dict.type.sys_yes_no"
                  :key="dict.value"
                  :label="dict.value"
                >{{dict.label}}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="18">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="form.remark" placeholder="请输入备注" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="菜单图标" prop="conventionIcon">
              <el-popover
                placement="bottom-start"
                width="460"
                trigger="click"
                @show="$refs['iconSelect'].reset()"
              >
                <IconSelect ref="iconSelect" @selected="selected" />

                <el-input slot="reference" v-model="form.conventionIcon" placeholder="点击选择图标" readonly>
                  <svg-icon
                    v-if="form.conventionIcon"
                    slot="prefix"
                    :icon-class="form.conventionIcon"
                    class="el-input__icon"
                    style="height: 32px;width: 16px;"
                  />
                  <i v-else slot="prefix" class="el-icon-search el-input__icon" />
                </el-input>
              </el-popover>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="具体内容">
              <editor v-model="form.conventionContent" :min-height="192"/>
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
import { listConvention, getConvention, delConvention, addConvention, updateConvention } from "@/api/use/convention";
import IconSelect from "@/components/IconSelect";
export default {
  name: "Convention",
  dicts: ['sys_yes_no'],
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
      // 创作公约表格数据
      conventionList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        conventionLabel: null,
        conventionName: null,
        conventionDisabled: null,
        conventionClosable: null,
        conventionLazy: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  components:{
    IconSelect
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询创作公约列表 */
    getList() {
      this.loading = true;
      listConvention(this.queryParams).then(response => {
        this.conventionList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 选择图标
    selected(name) {
      this.form.conventionIcon = name;
    },
    // 表单重置
    reset() {
      this.form = {
        conventionId: null,
        conventionLabel: null,
        conventionName: null,
        conventionContent: null,
        conventionDisabled: "N",
        conventionClosable: "N",
        conventionIcon:null,
        conventionLazy: "N",
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
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
      this.ids = selection.map(item => item.conventionId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加创作公约";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const conventionId = row.conventionId || this.ids
      getConvention(conventionId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改创作公约";
      });
    },
    /** 提交按钮 */
    submitForm() {

      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.conventionId != null) {
            console.log(this.form.conventionContent);
            updateConvention(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addConvention(this.form).then(response => {
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
      const conventionIds = row.conventionId || this.ids;
      this.$modal.confirm('是否确认删除创作公约编号为"' + conventionIds + '"的数据项？').then(function() {
        return delConvention(conventionIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/convention/export', {
        ...this.queryParams
      }, `convention_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
