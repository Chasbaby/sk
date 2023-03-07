<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="公告标题" prop="bulletinTitle">
        <el-input
          v-model="queryParams.bulletinTitle"
          placeholder="请输入景点公告标题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="公告类型" prop="bulletinType">
        <el-select v-model="queryParams.bulletinType" placeholder="请选择景点公告类型" clearable size="small">
          <el-option
            v-for="dict in dict.type.sys_notice_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="公告简介" prop="bulletinIntro">
        <el-input
          v-model="queryParams.bulletinIntro"
          placeholder="请输入景点公告简介"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="公告状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择公告状态" clearable size="small">
          <el-option
            v-for="dict in dict.type.sys_notice_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="删除标志" prop="delFlag">
        <el-select v-model="queryParams.delFlag" placeholder="请选择是否删除" clearable size="small">
          <el-option
            v-for="dict in dict.type.sys_yes_no"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="置顶标志" prop="topFlag">
        <el-select v-model="queryParams.topFlag" placeholder="请选择是否置顶" clearable size="small">
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
          v-hasPermi="['system:bulletin:add']"
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
          v-hasPermi="['system:bulletin:edit']"
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
          v-hasPermi="['system:bulletin:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:bulletin:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="bulletinList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="景点公告ID" align="center" prop="bulletinId" />
      <el-table-column label="景点id" align="center" prop="sightsId" />
      <el-table-column label="公告标题" align="center" prop="bulletinTitle" />
      <el-table-column label="公告类型" align="center" prop="bulletinType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_notice_type" :value="scope.row.bulletinType"/>
        </template>
      </el-table-column>
      <el-table-column label="公告简介" align="center" prop="bulletinIntro" />
      <el-table-column label="公告状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_notice_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="删除标志" align="center" prop="delFlag">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_yes_no" :value="scope.row.delFlag"/>
        </template>
      </el-table-column>
      <el-table-column label="置顶标志" align="center" prop="topFlag">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_yes_no" :value="scope.row.topFlag"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:bulletin:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:bulletin:remove']"
          >删除</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-message"
            circle
            @click="handleAddSights(scope.row)"
            v-hasPermi="['system:bulletin:edit']"
          >关联景点</el-button>
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

    <!--    附加景点对话框-->
    <el-dialog title="关联景点" :visible.sync="openSights" width="1000" append-to-body>
      <el-form :model="queryParamsSights" ref="querySights" :inline="true" label-width="100px">
        <el-form-item label="景点名称" prop="sightsName" size="">
          <el-input
            v-model="queryParamsSights.sightsName"
            placeholder="请输入景点名称"
            clearable
            size="small"
            @keyup.enter.native="handleQuerySights"
          />
        </el-form-item>
        <el-form-item label="景点国家" prop="sightsNation">
          <el-input
            v-model="queryParamsSights.sightsNation"
            placeholder="请输入景点国家"
            clearable
            size="small"
            @keyup.enter.native="handleQuerySights"
          />
        </el-form-item>
        <el-form-item label="景点城市" prop="sightsCity">
          <el-input
            v-model="queryParamsSights.sightsCity"
            placeholder="请输入景点城市"
            clearable
            size="small"
            @keyup.enter.native="handleQuerySights"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuerySights">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuerySights">重置</el-button>
        </el-form-item>
      </el-form>
      <el-table v-loading="loadingSights" :data="sightsList">
        <el-table-column label="景点ID" align="center" prop="sightsId" />
        <el-table-column label="景点名称" align="center" prop="sightsName" />
        <el-table-column label="景点国家" align="center" prop="sightsNation" />
        <el-table-column label="景点城市" align="center" prop="sightsCity" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              circle
              @click="handleChoice(scope.row)"
              v-hasPermi="['system:bulletin:edit']"
            >选择</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination
        v-show="totalSights>0"
        :total="totalSights"
        :page.sync="queryParamsSights.pageNum"
        :limit.sync="queryParamsSights.pageSize"
        @pagination="getSightsList"
      />
    </el-dialog>

    <!-- 添加或修改景点公告对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="景点id" prop="sightsId">
          {{form.sightsId}}
        </el-form-item>
        <el-form-item label="公告标题" prop="bulletinTitle">
          <el-input v-model="form.bulletinTitle" placeholder="请输入景点公告标题" />
        </el-form-item>
        <el-form-item label="公告类型">
          <el-radio-group v-model="form.bulletinType">
            <el-radio
              v-for="dict in dict.type.sys_notice_type"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="公告简介" prop="bulletinIntro">
          <el-input v-model="form.bulletinIntro" placeholder="请输入景点公告简介" />
        </el-form-item>
        <el-form-item label="公告内容">
          <editor v-model="form.bulletinContent" :min-height="192"/>
        </el-form-item>
        <el-form-item label="相关文件" prop="bulletinMultiparts">
          <fileUpload v-model="form.bulletinMultiparts"/>
        </el-form-item>
        <el-form-item label="公告状态">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in dict.type.sys_notice_status"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="是否删除">
          <el-radio-group v-model="form.delFlag">
            <el-radio
              v-for="dict in dict.type.sys_yes_no"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="是否置顶">
          <el-radio-group v-model="form.topFlag">
            <el-radio
              v-for="dict in dict.type.sys_yes_no"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
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
import { listBulletin, getBulletin, delBulletin, addBulletin, updateBulletin ,bulletinConnectSights} from "@/api/sights/bulletin";
import {listSights , getSights} from "@/api/sights/sights"
export default {
  name: "Bulletin",
  dicts: ['sys_notice_type', 'sys_notice_status', 'sys_yes_no'],
  data() {
    return {
      // 遮罩层
      loading: true,
      loadingSights:true,
      protect:-1,
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
      totalSights:0,
      // 景点公告表格数据
      bulletinList: [],
      sightsList:[],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      openSights: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        bulletinTitle: null,
        bulletinType: null,
        bulletinIntro: null,
        status: null,
        delFlag: null,
        topFlag: null,
      },
      queryParamsSights:{
        pageNum:1,
        pageSize:10,
        sightsName:null,
        sightsNation:null,
        sightsCity:null,
      },
      // 表单参数
      form: {},
      sightsForm:{},
      // 表单校验
      rules: {
        bulletinTitle: [
          { required: true, message: "景点公告标题不能为空", trigger: "blur" }
        ],
        bulletinType: [
          { required: true, message: "景点公告类型不能为空", trigger: "blur" }
        ],
        delFlag: [
          { required: true, message: "是否删除不能为空", trigger: "blur" }
        ],
        topFlag: [
          { required: true, message: "是否置顶不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询景点公告列表 */
    getList() {
      this.loading = true;
      listBulletin(this.queryParams).then(response => {
        this.bulletinList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getSightsList(){
      this.loadingSights = true;
      listSights(this.queryParamsSights).then(response =>{
        this.sightsList = response.rows;
        this.totalSights = response.total;
        this.loadingSights = false;
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
        bulletinId: null,
        sightsId: null,
        bulletinTitle: null,
        bulletinType: "0",
        bulletinIntro: null,
        bulletinContent: null,
        bulletinMultiparts: null,
        status: "0",
        delFlag: "0",
        topFlag: "0",
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
    handleQuerySights(){
      this.queryParamsSights.pageNum = 1;
      this.getSightsList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    resetQuerySights(){
      this.resetForm("querySights");
      this.handleQuerySights();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.bulletinId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加景点公告";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const bulletinId = row.bulletinId || this.ids
      getBulletin(bulletinId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改景点公告";
      });
    },
    handleAddSights(row){
      this.openSights=true
      this.getSightsList()
      this.protect = row.bulletinId
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.bulletinId != null) {
            updateBulletin(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBulletin(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    handleChoice(row){
      const sightsId = row.sightsId;
      const sightsName = row.sightsName;
      this.$modal.confirm('是否确定将门票与景点编号为'+sightsId+'名称为'+sightsName+'的景点关联').then(
        bulletinConnectSights(sightsId,this.protect)
      ).then(()=>{
        this.getList();
        this.$modal.msgSuccess("绑定成功");
        this.openSights = false;
      }).catch(()=>{})
      this.protect = -1;
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const bulletinIds = row.bulletinId || this.ids;
      this.$modal.confirm('是否确认删除景点公告编号为"' + bulletinIds + '"的数据项？').then(function() {
        return delBulletin(bulletinIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/bulletin/export', {
        ...this.queryParams
      }, `bulletin_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
