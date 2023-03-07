<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="餐馆名称" prop="restaurantName">
        <el-input
          v-model="queryParams.restaurantName"
          placeholder="请输入餐馆名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="餐馆地址" prop="restaurantAddress">
        <el-input
          v-model="queryParams.restaurantAddress"
          placeholder="请输入餐馆地址"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="餐馆管理" prop="restaurantManager">
        <el-input
          v-model="queryParams.restaurantManager"
          placeholder="请输入餐馆管理者"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="显示资格" prop="showFlag">
        <el-select v-model="queryParams.showFlag" placeholder="请选择显示资格(是否)" clearable size="small">
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
          v-hasPermi="['system:restaurant:add']"
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
          v-hasPermi="['system:restaurant:edit']"
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
          v-hasPermi="['system:restaurant:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:restaurant:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="restaurantList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="景点餐馆id" align="center" prop="restaurantId" />
      <el-table-column label="景点id" align="center" prop="sightsId" />
      <el-table-column label="餐馆名称" align="center" prop="restaurantName" />
      <el-table-column label="餐馆简介" align="center" prop="restaurantIntro" />
      <el-table-column label="餐馆地址" align="center" prop="restaurantAddress" />
      <el-table-column label="餐馆管理者" align="center" prop="restaurantManager" />
      <el-table-column label="订购电话" align="center" prop="restaurantHotline" />
      <el-table-column label="餐馆菜风格" align="center" prop="restaurantStyle" />
      <el-table-column label="点评均分" align="center" prop="restaurantScore" />
      <el-table-column label="显示资格" align="center" prop="showFlag">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_yes_no" :value="scope.row.showFlag"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:restaurant:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:restaurant:remove']"
          >删除</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-message"
            circle
            @click="handleAddSights(scope.row)"
            v-hasPermi="['system:restaurant:edit']"
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
              v-hasPermi="['system:restaurant:edit']"
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

    <!-- 添加或修改餐馆对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="景点id" prop="sightsId">
          {{form.sightsId}}
        </el-form-item>
        <el-form-item label="餐馆名称" prop="restaurantName">
          <el-input v-model="form.restaurantName" placeholder="请输入餐馆名称" />
        </el-form-item>
        <el-form-item label="餐馆地址" prop="restaurantAddress">
          <el-input v-model="form.restaurantAddress" placeholder="请输入餐馆地址" />
        </el-form-item>
        <el-form-item label="餐馆管理者" prop="restaurantManager">
          <el-input v-model="form.restaurantManager" placeholder="请输入餐馆管理者" />
        </el-form-item>
        <el-form-item label="订购电话" prop="restaurantHotline">
          <el-input v-model="form.restaurantHotline" placeholder="请输入订购电话" />
        </el-form-item>
        <el-form-item label="餐馆简介" prop="restaurantIntro">
          <el-input v-model="form.restaurantIntro" placeholder="请输入餐馆简介" />
        </el-form-item>
        <el-form-item label="详细介绍">
          <editor v-model="form.restaurantDetail" :min-height="192"/>
        </el-form-item>
        <el-form-item label="餐馆风格" prop="restaurantStyle">
          <el-input v-model="form.restaurantStyle" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="对外通告">
          <editor v-model="form.restaurantNotice" :min-height="192"/>
        </el-form-item>
        <el-form-item label="主打菜">
          <editor v-model="form.restaurantMaindish" :min-height="192"/>
        </el-form-item>
        <el-form-item label="餐馆图片">
          <imageUpload v-model="form.restaurantPhoto"/>
        </el-form-item>
        <el-form-item label="点评均分" prop="restaurantScore">
          {{form.restaurantScore}}
        </el-form-item>
        <el-form-item label="点评人数" prop="restaurantReviewers">
          {{form.restaurantReviewers}}
        </el-form-item>
        <el-form-item label="显示资格" prop="showFlag">
          <el-select v-model="form.showFlag" placeholder="请选择显示资格">
            <el-option
              v-for="dict in dict.type.sys_yes_no"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="合同">
          <fileUpload v-model="form.contract"/>
        </el-form-item>
        <el-form-item label="荣誉合格证明">
          <fileUpload v-model="form.restaurantCertificate"/>
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
import { listRestaurant, getRestaurant, delRestaurant, addRestaurant, updateRestaurant ,restaurantConnectSights} from "@/api/sights/restaurant";
import {listSights , getSights} from "@/api/sights/sights"
export default {
  name: "Restaurant",
  dicts: ['sys_yes_no'],
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
      // 餐馆表格数据
      restaurantList: [],
      sightsList:[],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      openSights:false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        restaurantName: null,
        restaurantAddress: null,
        restaurantManager: null,
        restaurantStyle: null,
        showFlag: null,
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
        restaurantName: [
          { required: true, message: "餐馆名称不能为空", trigger: "blur" }
        ],        restaurantAddress: [
          { required: true, message: "餐馆地址不能为空", trigger: "blur" }
        ],        restaurantManager: [
          { required: true, message: "餐馆管理者不能为空", trigger: "blur" }
        ],        restaurantHotline: [
          { required: true, message: "订购电话不能为空", trigger: "blur" }
        ],        restaurantIntro: [
          { required: true, message: "餐馆简介不能为空", trigger: "blur" }
        ],        restaurantDetail: [
          { required: true, message: "详细介绍不能为空", trigger: "blur" }
        ],        restaurantPhoto: [
          { required: true, message: "餐馆图片不能为空", trigger: "blur" }
        ],        restaurantCertificate: [
          { required: true, message: "荣誉合格证明不能为空", trigger: "blur" }
        ],      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询餐馆列表 */
    getList() {
      this.loading = true;
      listRestaurant(this.queryParams).then(response => {
        this.restaurantList = response.rows;
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
        restaurantId: null,        sightsId: null,        restaurantName: null,        restaurantAddress: null,        restaurantManager: null,        restaurantHotline: null,        restaurantIntro: null,        restaurantDetail: null,        restaurantStyle: null,        restaurantNotice: null,        restaurantPhoto: null,        restaurantMaindish: null,        restaurantCertificate: null,        restaurantScore: null,        restaurantReviewers: null,        showFlag: null,        contract: null,        createBy: null,        createTime: null,        updateBy: null,        updateTime: null,        remark: null      };
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
    resetQuerySights(){
      this.resetForm("querySights");
      this.handleQuerySights();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.restaurantId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加餐馆";
    },
    handleAddSights(row){
      this.openSights=true
      this.getSightsList()
      this.protect = row.restaurantId
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const restaurantId = row.restaurantId || this.ids
      getRestaurant(restaurantId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改餐馆";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.restaurantId != null) {
            updateRestaurant(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRestaurant(this.form).then(response => {
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
      this.$modal.confirm('是否确定将restaurant与景点编号为'+sightsId+'名称为'+sightsName+'的景点关联').then(
        restaurantConnectSights(sightsId,this.protect)
      ).then(()=>{
        this.getList();
        this.$modal.msgSuccess("绑定成功");
        this.openSights = false;
      }).catch(()=>{})
      this.protect = -1;
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const restaurantIds = row.restaurantId || this.ids;
      this.$modal.confirm('是否确认删除餐馆编号为"' + restaurantIds + '"的数据项？').then(function() {
        return delRestaurant(restaurantIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    handleAddSights(row){
      this.openSights=true
      this.getSightsList()
      this.protect = row.restaurantId
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/restaurant/export', {
        ...this.queryParams
      }, `restaurant_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
