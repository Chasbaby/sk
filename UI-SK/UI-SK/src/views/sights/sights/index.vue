<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="景点名称" prop="sightsName" size="">
        <el-input
          v-model="queryParams.sightsName"
          placeholder="请输入景点名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="景点位置" prop="sightsLocation">
        <el-input
          v-model="queryParams.sightsLocation"
          placeholder="请输入景点位置"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="景点简介" prop="sightsIntro">
        <el-input
          v-model="queryParams.sightsIntro"
          placeholder="请输入景点简介"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="景点国家" prop="sightsNation">
        <el-input
          v-model="queryParams.sightsNation"
          placeholder="请输入景点国家"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="景点城市" prop="sightsCity">
        <el-input
          v-model="queryParams.sightsCity"
          placeholder="请输入景点城市"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="景点种类" prop="sightsCategory">
        <el-select
          v-model="queryParams.sightsCategory"
          placeholder="请选择景点种类"
          clearable
          size="small"
        >
          <el-option
            v-for="dict in dict.type.sights_category"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
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
          v-hasPermi="['sightBase:sights:add']"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['sightBase:sights:edit']"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['sightBase:sights:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['sightBase:sights:export']"
          >导出</el-button
        >
      </el-col>

      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="el-icon-upload2"
          size="mini"
          @click="handleImport"
          v-hasPermi="['sightBase:sights:import']"
          >导入</el-button
        >
      </el-col>

      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      stripe
      border
      tooltip-effect="light"
      v-loading="loading"
      :data="sightsList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column
        label="景点ID"
        width="70"
        align="center"
        prop="sightsId"
      />
      <el-table-column
        label="景点名称"
        :show-overflow-tooltip="true"
        align="center"
        prop="sightsName"
      />
      <el-table-column
        label="景点简介"
        :show-overflow-tooltip="true"
        align="center"
        prop="sightsIntro"
      />
      <el-table-column
        label="点赞量"
        sortable
        :show-overflow-tooltip="true"
        width="100"
        align="center"
        prop="sightsLike"
      />
      <el-table-column
        label="点击量"
        sortable
        :show-overflow-tooltip="true"
        width="100"
        align="center"
        prop="sightsHits"
      />
      <el-table-column
        label="浏览量"
        sortable
        :show-overflow-tooltip="true"
        width="100"
        align="center"
        prop="sightsView"
      />
      <el-table-column
        label="均分"
        sortable
        :show-overflow-tooltip="true"
        width="100"
        align="center"
        prop="sightsScore"
      />
      <el-table-column
        label="景点国家"
        :show-overflow-tooltip="true"
        width="100"
        align="center"
        prop="sightsNation"
      />
      <el-table-column
        label="景点城市"
        :show-overflow-tooltip="true"
        width="100"
        align="center"
        prop="sightsCity"
      />
      <el-table-column
        label="景点位置"
        :show-overflow-tooltip="true"
        align="center"
        prop="sightsLocation"
      />
      <el-table-column
        label="景点种类"
        align="center"
        width="100"
        prop="sightsCategory"
      >
        <template slot-scope="scope">
          <dict-tag
            :options="dict.type.sights_category"
            :value="scope.row.sightsCategory"
          />
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['sightBase:sights:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['sightBase:sights:remove']"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改景点基本信息对话框 -->
    <el-dialog
      :title="title"
      :visible.sync="open"
      width="1500px"
      append-to-body
    >
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="景点名称" prop="sightsName">
              <el-input
                v-model="form.sightsName"
                clearable
                autosize
                placeholder="景点名称"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="景点种类" prop="sightsCategory">
              <el-select
                v-model="form.sightsCategory"
                placeholder="请选择景点的种类"
              >
                <el-option
                  v-for="dict in dict.type.sights_category"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="景点国家" prop="sightsNation">
              <el-input
                v-model="form.sightsNation"
                placeholder="请输入景点国家"
              />
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="景点城市" prop="sightsCity">
              <el-input
                v-model="form.sightsCity"
                placeholder="请输入景点城市"
              />
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="景点经度" prop="sightsLongitude">
              <el-input
                v-model="form.sightsLongitude"
                placeholder="请输入景点经度"
              />
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="景点纬度" prop="sightsLatitude">
              <el-input
                v-model="form.sightsLatitude"
                placeholder="请输入景点纬度"
              />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="景点位置" prop="sightsLocation">
              <el-input
                v-model="form.sightsLocation"
                placeholder="请尽量将景点地址描述清楚方便提取信息"
              />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="景点简介" prop="sightsIntro">
              <el-input
                type="textarea"
                v-model="form.sightsIntro"
                placeholder="请输入景点简介"
              />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="景点细节">
              <editor v-model="form.sightsDetail" :min-height="192" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="景点图片">
              <imageUpload v-model="form.sightsImage" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="收费码">
              <imageUpload v-model="form.sightsCode" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 景点导入对话框 -->
    <el-dialog
      :title="upload.title"
      :visible.sync="upload.open"
      width="400px"
      append-to-body
    >
      <el-upload
        ref="upload"
        :limit="1"
        accept=".xlsx, .xls"
        :headers="upload.headers"
        :action="upload.url + '?updateSupport=' + upload.updateSupport"
        :disabled="upload.isUploading"
        :on-progress="handleFileUploadProgress"
        :on-success="handleFileSuccess"
        :auto-upload="false"
        drag
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip text-center" slot="tip">
          <div class="el-upload__tip" slot="tip">
            <el-checkbox v-model="upload.updateSupport" />
            是否更新已经存在的景点数据
          </div>
          <span>仅允许导入xls、xlsx格式文件。</span>
          <el-link
            type="primary"
            :underline="false"
            style="font-size: 12px; vertical-align: baseline"
            @click="importTemplate"
            >下载模板</el-link
          >
        </div>
      </el-upload>

      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listSights,
  getSights,
  delSights,
  addSights,
  updateSights,
} from "@/api/sights/sights";
import { getToken } from "@/utils/auth";

export default {
  name: "Sights",
  dicts: ["sights_category"],
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
      // 景点基本信息表格数据
      sightsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        sightsName: null,
        sightsLocation: null,
        sightsIntro: null,
        sightsCategory: null,
        sightsNation: null,
        sightsCity: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        sightsName: [
          { required: true, message: "景点名称不能为空", trigger: "blur" },
        ],
        sightsLocation: [
          { required: true, message: "景点位置不能为空", trigger: "blur" },
        ],
        sightsLongitude: [
          { required: true, message: "景点经度不能为空", trigger: "blur" },
        ],
        sightsLatitude: [
          { required: true, message: "景点纬度不能为空", trigger: "blur" },
        ],
      },
      // 景点导入参数
      upload: {
        // 是否显示弹出层（景点导入）
        open: false,
        // 弹出层标题（景点导入）
        title: "",
        // 是否禁用上传
        isUploading: false,
        // 是否更新已经存在的用户数据
        updateSupport: 0,
        // 设置上传的请求头部
        headers: { Authorization: "Bearer " + getToken() },
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/sightBase/sights/importData",
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询景点基本信息列表 */
    getList() {
      this.loading = true;
      listSights(this.queryParams).then((response) => {
        this.sightsList = response.rows;
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
        sightsId: null,
        sightsLike: null,
        sightsHits: null,
        sightsView: null,
        sightsScore: null,
        sightsName: null,
        sightsLocation: null,
        sightsLongitude: null,
        sightsLatitude: null,
        sightsImage: null,
        sightsCode: null,
        sightsIntro: null,
        sightsDetail: null,
        sightsCategory: null,
        sightsNation: null,
        sightsCity: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
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
      this.ids = selection.map((item) => item.sightsId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加景点基本信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const sightsId = row.sightsId || this.ids;
      getSights(sightsId).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改景点基本信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.sightsId != null) {
            updateSights(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSights(this.form).then((response) => {
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
      const sightsIds = row.sightsId || this.ids;
      this.$modal
        .confirm('是否确认删除景点基本信息编号为"' + sightsIds + '"的数据项？')
        .then(function () {
          return delSights(sightsIds);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "sightBase/sights/export",
        {
          ...this.queryParams,
        },
        `sights_${new Date().getTime()}.xlsx`
      );
    },
    /** 导入按钮 */
    handleImport() {
      this.upload.title = "用户导入";
      this.upload.open = true;
    },
    /** 下载模板操作 */
    importTemplate() {
      this.download(
        "/sightBase/sights/importSightsTemplate",
        {},
        `sights_template_${new Date().getTime()}.xlsx`
      );
    },
    /**文件上传中处理*/
    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true;
    },
    /**文件上传成功处理*/
    handleFileSuccess(response, file, fileList) {
      this.upload.open = false;
      this.upload.isUploading = false;
      this.$refs.upload.clearFiles();
      this.$alert(response.msg, "导入结果", { dangerouslyUseHTMLString: true });
      this.getList();
    },
    /**提交上传文件*/
    submitFileForm() {
      this.$refs.upload.submit();
    },
  },
};
</script>
