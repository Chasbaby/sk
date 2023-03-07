<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="文创标题" prop="culCreativityTitle">
        <el-input
          v-model="queryParams.culCreativityTitle"
          placeholder="请输入文创标题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="文创类别" prop="culCreativityCategory">
        <el-select v-model="queryParams.culCreativityCategory" placeholder="请选择文创类别" clearable size="small">
          <el-option
            v-for ="dict in dict.type.cul_creativity_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="标签" prop="culCreativityTags">
        <el-input
          v-model="queryParams.culCreativityTags"
          placeholder="请输入标签"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="关键字" prop="culCreativityKey">
        <el-input
          v-model="queryParams.culCreativityKey"
          placeholder="请输入关键字"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否删除" prop="delFlag">
        <el-select v-model="queryParams.delFlag" placeholder="请选择是否删除" clearable size="small">
          <el-option
            v-for="dict in dict.type.sys_yes_no"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="是否置顶" prop="topFlag">
        <el-select v-model="queryParams.topFlag" placeholder="请选择是否置顶" clearable size="small">
          <el-option
            v-for="dict in dict.type.sys_yes_no"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="创建者" prop="createBy">
        <el-input
          v-model="queryParams.createBy"
          placeholder="请输入创建者"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.createTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="更新者" prop="updateBy">
        <el-input
          v-model="queryParams.updateBy"
          placeholder="请输入更新者"
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
          v-hasPermi="['system:cul_creativity:add']"
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
          v-hasPermi="['system:cul_creativity:edit']"
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
          v-hasPermi="['system:cul_creativity:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:cul_creativity:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="cul_creativityList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="文创ID" align="center" prop="culCreativityId" />
      <el-table-column label="文创标题" align="center" prop="culCreativityTitle" />
      <el-table-column label="文创简介" align="center" prop="culCreativityIntro" />
      <el-table-column label="文创类别" align="center" prop="culCreativityCategory" />
      <el-table-column label="标签" align="center" prop="culCreativityTags" />
      <el-table-column label="关键字" align="center" prop="culCreativityKey" />
      <el-table-column label="点赞量" align="center" prop="culCreativityLike" />
      <el-table-column label="不喜欢量" align="center" prop="culCreativityDislike" />
      <el-table-column label="收藏量" align="center" prop="culCreativityCollection" />
      <el-table-column label="点击量" align="center" prop="culCreativityHits" />
      <el-table-column label="是否删除" align="center" prop="delFlag">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_yes_no" :value="scope.row.delFlag"/>
        </template>
      </el-table-column>
      <el-table-column label="是否置顶" align="center" prop="topFlag">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_yes_no" :value="scope.row.topFlag"/>
        </template>
      </el-table-column>
      <el-table-column label="创建者" align="center" prop="createBy" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:cul_creativity:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:cul_creativity:remove']"
          >删除</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-message"
            circle
            @click="handleAddSights(scope.row)"
            v-hasPermi="['system:cul_creativity:edit']"
          >关联景点</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"/>
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
              v-hasPermi="['system:ticket:edit']"
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
    <!-- 添加或修改文创对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="景点ID" prop="sightsId">
          <el-input v-model="form.sightsId" placeholder="请输入景点ID" />
        </el-form-item>
        <el-form-item label="文创标题" prop="culCreativityTitle">
          <el-input v-model="form.culCreativityTitle" placeholder="请输入文创标题" />
        </el-form-item>
        <el-form-item label="文创简介" prop="culCreativityIntro">
          <el-input v-model="form.culCreativityIntro" placeholder="请输入文创简介" />
        </el-form-item>
        <el-form-item label="文创类别" prop="culCreativityCategory">
          <el-select v-model="form.culCreativityCategory" placeholder="请选择文创类别">
            <el-option
              v-for ="dict in dict.type.cul_creativity_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="标签" prop="culCreativityTags">
          <el-input v-model="form.culCreativityTags" placeholder="请输入标签" />
        </el-form-item>
        <el-form-item label="关键字" prop="culCreativityKey">
          <el-input v-model="form.culCreativityKey" placeholder="请输入关键字" />
        </el-form-item>
        <el-form-item label="是否删除" prop="delFlag">
          <el-select v-model="form.delFlag" placeholder="请选择是否删除">
            <el-option
              v-for="dict in dict.type.sys_yes_no"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="是否置顶" prop="topFlag">
          <el-select v-model="form.topFlag" placeholder="请选择是否置顶">
            <el-option
              v-for="dict in dict.type.sys_yes_no"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="景点图片">
          <imageUpload v-model="form.culCreativityImage"/>
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
import { listCul_creativity, getCul_creativity, delCul_creativity, addCul_creativity, updateCul_creativity ,culConnectSights} from "@/api/sights/cul_creativity";
import {listSights , getSights} from "@/api/sights/sights"
export default {
  name: "Cul_creativity",
  dicts: ['sys_yes_no', 'cul_creativity_type'],
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
      // 文创表格数据
      cul_creativityList: [],
      sightsList:[],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      openSights:false,
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        culCreativityTitle: null,
        culCreativityCategory: null,
        culCreativityTags: null,
        culCreativityKey: null,
        culCreativityImage:null,
        delFlag: null,
        topFlag: null,
        createBy: null,
        createTime: null,
        updateBy: null,
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
        culCreativityTitle: [
          { required: true, message: "文创标题不能为空", trigger: "blur" }
        ],        delFlag: [
          { required: true, message: "是否删除(Y表示已删除N表示未删除)不能为空", trigger: "change" }
        ],        topFlag: [
          { required: true, message: "是否置顶(Y表示置顶N表示未置顶)不能为空", trigger: "change" }
        ],      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询文创列表 */
    getList() {
      this.loading = true;
      listCul_creativity(this.queryParams).then(response => {
        this.cul_creativityList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 查询景点*/
    getSightsList(){
      this.loadingSights = true;
      listSights(this.queryParamsSights).then(response =>{
        this.sightsList = response.rows;
        this.totalSights = response.total;
        this.loadingSights = false;
      });
    }
    ,
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        culCreativityId: null,        sightsId: null,        culCreativityTitle: null,        culCreativityIntro: null,        culCreativityCategory: null,        culCreativityTags: null,        culCreativityKey: null,        culCreativityLike: null,        culCreativityDislike: null,        culCreativityCollection: null,        culCreativityHits: null,        delFlag: null,        topFlag: null,        createBy: null,        createTime: null,        updateBy: null,        updateTime: null      };
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
      this.ids = selection.map(item => item.culCreativityId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加文创";
    },
    handleAddSights(row){
      this.openSights=true
      this.getSightsList()
      this.protect = row.culCreativityId
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const culCreativityId = row.culCreativityId || this.ids
      getCul_creativity(culCreativityId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改文创";
      });
    },
    handleChoice(row){
      const sightsId = row.sightsId;
      const sightsName = row.sightsName;
      this.$modal.confirm('是否确定将门票与景点编号为'+sightsId+'名称为'+sightsName+'的景点关联').then(
        culConnectSights(sightsId,this.protect)
      ).then(()=>{
        this.getList();
        this.$modal.msgSuccess("绑定成功");
        this.openSights = false;
      }).catch(()=>{})
      this.protect = -1;
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.culCreativityId != null) {
            updateCul_creativity(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCul_creativity(this.form).then(response => {
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
      const culCreativityIds = row.culCreativityId || this.ids;
      this.$modal.confirm('是否确认删除文创编号为"' + culCreativityIds + '"的数据项？').then(function() {
        return delCul_creativity(culCreativityIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/cul_creativity/export', {
        ...this.queryParams
      }, `cul_creativity_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
