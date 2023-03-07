<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="酒店名称" prop="hotelName">
        <el-input
          v-model="queryParams.hotelName"
          placeholder="请输入酒店名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="酒店电话" prop="hotelHotline">
        <el-input
          v-model="queryParams.hotelHotline"
          placeholder="请输入酒店电话"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="酒店管理" prop="hotelManager">
        <el-input
          v-model="queryParams.hotelManager"
          placeholder="请输入酒店管理"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="酒店地址" prop="hotelAddress">
        <el-input
          v-model="queryParams.hotelAddress"
          placeholder="请输入酒店地址"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="酒店简介" prop="hotelIntro">-->
<!--        <el-input-->
<!--          v-model="queryParams.hotelIntro"-->
<!--          placeholder="请输入酒店简介"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
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
          v-hasPermi="['system:hotel:add']"
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
          v-hasPermi="['system:hotel:edit']"
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
          v-hasPermi="['system:hotel:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:hotel:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>
<!--    :default-sort = "{prop: 'hotelScore', order: 'descending'}"-->
    <el-table v-loading="loading" :data="hotelList" @selection-change="handleSelectionChange" >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="酒店ID" align="center" prop="hotelId" />
      <el-table-column label="景点id" align="center" prop="sightsId"/>
      <el-table-column label="酒店名称" align="center" prop="hotelName"/>
      <el-table-column label="酒店电话" align="center" prop="hotelHotline" />
      <el-table-column label="酒店管理" align="center" prop="hotelManager" />
      <el-table-column label="酒店地址" align="center" prop="hotelAddress" />
      <el-table-column label="酒店简介" align="center" prop="hotelIntro" />
      <el-table-column label="点评均分" align="center" prop="hotelScore" sortable/>
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
            v-hasPermi="['system:hotel:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:hotel:remove']"
          >删除</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-message"
            circle
            @click="handleAddSights(scope.row)"
            v-hasPermi="['system:hotel:edit']"
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

    <!-- 添加或修改酒店对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="酒店名称" prop="hotelName">
          <el-input v-model="form.hotelName" placeholder="请输入酒店名称" />
        </el-form-item>
        <el-form-item label="酒店电话" prop="hotelHotline">
          <el-input v-model="form.hotelHotline" placeholder="请输入酒店电话" />
        </el-form-item>
        <el-form-item label="酒店管理" prop="hotelManager">
          <el-input v-model="form.hotelManager" placeholder="请输入酒店管理" />
        </el-form-item>
        <el-form-item label="酒店地址" prop="hotelAddress">
          <el-input v-model="form.hotelAddress" placeholder="请输入酒店地址" />
        </el-form-item>
        <el-form-item label="酒店图片">
          <imageUpload v-model="form.hotelPhoto"/>
        </el-form-item>
        <el-form-item label="酒店简介" prop="hotelIntro">
          <el-input v-model="form.hotelIntro" placeholder="请输入酒店简介" />
        </el-form-item>
        <el-form-item label="详细介绍">
          <editor v-model="form.hotelDetail" :min-height="192"/>
        </el-form-item>
        <el-form-item label="对外通告">
          <editor v-model="form.hotelNotice" :min-height="192"/>
        </el-form-item>
        <el-form-item label="点评人数" prop="hotelReviewers">
          {{form.hotelReviewers}}
        </el-form-item>
        <el-form-item label="点评均分" prop="hotelScore">
          {{form.hotelScore}}
        </el-form-item>
        <el-form-item label="显示资格" prop="showFlag">
          <el-select v-model="form.showFlag" placeholder="请选择显示资格(是否)">
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
        <el-form-item label="荣誉证明">
          <fileUpload v-model="form.hotelCertificate"/>
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
import { listHotel, getHotel, delHotel, addHotel, updateHotel ,hotelConnectSights} from "@/api/sights/hotel";
import {listSights , getSights} from "@/api/sights/sights"
export default {
  name: "Hotel",
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
      // 酒店表格数据
      hotelList: [],
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
        hotelName: null,
        hotelHotline: null,
        hotelManager: null,
        hotelAddress: null,
        hotelIntro: null,
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
        hotelName: [
          { required: true, message: "酒店名称不能为空", trigger: "blur" }
        ],
        hotelHotline: [
          { required: true, message: "酒店电话不能为空", trigger: "blur" }
        ],
        hotelManager: [
          { required: true, message: "酒店管理不能为空", trigger: "blur" }
        ],
        hotelAddress: [
          { required: true, message: "酒店地址不能为空", trigger: "blur" }
        ],
        hotelPhoto: [
          { required: true, message: "酒店图片不能为空", trigger: "blur" }
        ],
        hotelIntro: [
          { required: true, message: "酒店简介不能为空", trigger: "blur" }
        ],
        hotelCertificate: [
          { required: true, message: "荣誉合格证明不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询酒店列表 */
    getList() {
      this.loading = true;
      listHotel(this.queryParams).then(response => {
        this.hotelList = response.rows;
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
        hotelId: null,
        sightsId: null,
        hotelName: null,
        hotelHotline: null,
        hotelManager: null,
        hotelAddress: null,
        hotelPhoto: null,
        hotelIntro: null,
        hotelDetail: null,
        hotelNotice: null,
        hotelCertificate: null,
        hotelReviewers: null,
        hotelScore: null,
        showFlag: null,
        contract: null,
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
      this.ids = selection.map(item => item.hotelId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加酒店";
    },
    handleAddSights(row){
      this.openSights=true
      this.getSightsList()
      this.protect = row.hotelId
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const hotelId = row.hotelId || this.ids
      getHotel(hotelId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改酒店";
      });
    },
    handleChoice(row){
      const sightsId = row.sightsId;
      const sightsName = row.sightsName;
      this.$modal.confirm('是否确定将hotel与景点编号为'+sightsId+'名称为'+sightsName+'的景点关联').then(
        hotelConnectSights(sightsId,this.protect)
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
          if (this.form.hotelId != null) {
            updateHotel(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addHotel(this.form).then(response => {
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
      const hotelIds = row.hotelId || this.ids;
      this.$modal.confirm('是否确认删除酒店编号为"' + hotelIds + '"的数据项？').then(function() {
        return delHotel(hotelIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    handleAddSights(row){
      this.openSights=true
      this.getSightsList()
      this.protect = row.hotelId
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/hotel/export', {
        ...this.queryParams
      }, `hotel_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
