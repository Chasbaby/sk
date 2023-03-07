<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="票状态" prop="ticketStatus">
        <el-select v-model="queryParams.ticketStatus" placeholder="请选择票状态" clearable size="small">
          <el-option
            v-for="dict in dict.type.sights_ticket_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="开始时间" prop="ticketBegintime">
        <el-date-picker clearable size="small"
          v-model="queryParams.ticketBegintime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择门票使用时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="截止时间" prop="ticketEndtime">
        <el-date-picker clearable size="small"
          v-model="queryParams.ticketEndtime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择门票的截止时间">
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
          v-hasPermi="['system:ticket:add']"
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
          v-hasPermi="['system:ticket:edit']"
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
          v-hasPermi="['system:ticket:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:ticket:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="ticketList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="门票ID" align="center" prop="ticketId" />
      <el-table-column label="景点id" align="center" prop="sightsId" />
      <el-table-column label="门票数量" align="center" prop="ticketNum" />
      <el-table-column label="门票价格" align="center" prop="ticketPrice" />
      <el-table-column label="折扣" align="center" prop="discount" />
      <el-table-column label="票状态" align="center" prop="ticketStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sights_ticket_status" :value="scope.row.ticketStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="开始时间" align="center" prop="ticketBegintime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.ticketBegintime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="截止时间" align="center" prop="ticketEndtime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.ticketEndtime, '{y}-{m}-{d}') }}</span>
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
            circle
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:ticket:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            circle
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:ticket:remove']"
          >删除</el-button>
          <el-button
           size="mini"
           type="text"
           icon="el-icon-message"
           circle
           @click="handleAddSights(scope.row)"
           v-hasPermi="['system:ticket:edit']"
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

    <!-- 添加或修改门票对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="门票数量" prop="ticketNum">
          <el-input v-model="form.ticketNum" placeholder="请输入门票数量" />
        </el-form-item>
        <el-form-item label="门票价格" prop="ticketPrice">
          <el-input v-model="form.ticketPrice" placeholder="请输入门票价格" />
        </el-form-item>
        <el-form-item label="开头编码" prop="ticketCode">
          <el-input v-model="form.ticketCode" placeholder="请输入门票独立编码开头" />
        </el-form-item>
        <el-form-item label="折扣" prop="discount">
          <el-input v-model="form.discount" placeholder="请输入折扣" />
        </el-form-item>
        <el-form-item label="专享服务" prop="selfService">
          <el-input v-model="form.selfService" placeholder="请输入专享服务介绍" />
        </el-form-item>
        <el-form-item label="票状态">
          <el-radio-group v-model="form.ticketStatus">
            <el-radio
              v-for="dict in dict.type.sights_ticket_status"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="开始时间" prop="ticketBegintime">
          <el-date-picker clearable size="small"
            v-model="form.ticketBegintime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择门票开始使用时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="截止时间" prop="ticketEndtime">
          <el-date-picker clearable size="small"
            v-model="form.ticketEndtime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择门票的截止使用时间">
          </el-date-picker>
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
import { listTicket, getTicket, delTicket, addTicket, updateTicket ,ticketConnectSights} from "@/api/sights/ticket";
import {listSights , getSights} from "@/api/sights/sights"

export default {
  name: "Ticket",
  dicts: ['sights_ticket_status'],
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
      // 门票表格数据
      ticketList: [],
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
        ticketStatus: null,
        ticketBegintime: null,
        ticketEndtime: null,
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
        ticketPrice: [
          { required: true, message: "门票价格不能为空", trigger: "blur" }
        ],
        ticketCode: [
          { required: true, message: "门票独立编码开头不能为空", trigger: "blur" }
        ],
        ticketBegintime: [
          { required: true, message: "门票开始使用时间不能为空", trigger: "blur" }
        ],
        ticketEndtime: [
          { required: true, message: "门票的截止使用时间不能为空", trigger: "blur" }
        ],
        createBy: [
          { required: true, message: "创建者不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询门票列表 */
    getList() {
      this.loading = true;
      listTicket(this.queryParams).then(response => {
        this.ticketList = response.rows;
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
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        ticketId: null,
        sightsId: null,
        ticketNum: null,
        ticketPrice: null,
        ticketCode: null,
        discount: null,
        selfService: null,
        ticketStatus: "0",
        ticketBegintime: null,
        ticketEndtime: null,
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
      this.ids = selection.map(item => item.ticketId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加门票";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const ticketId = row.ticketId || this.ids
      getTicket(ticketId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改门票";
      });
    },
    handleChoice(row){
      const sightsId = row.sightsId;
      const sightsName = row.sightsName;
      this.$modal.confirm('是否确定将门票与景点编号为'+sightsId+'名称为'+sightsName+'的景点关联').then(
        ticketConnectSights(sightsId,this.protect)
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
          if (this.form.ticketId != null) {
            updateTicket(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTicket(this.form).then(response => {
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
      const ticketIds = row.ticketId || this.ids;
      this.$modal.confirm('是否确认删除门票编号为"' + ticketIds + '"的数据项？').then(function() {
        return delTicket(ticketIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    handleAddSights(row){
      this.openSights=true
      this.getSightsList()
      this.protect = row.ticketId
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/ticket/export', {
        ...this.queryParams
      }, `ticket_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
