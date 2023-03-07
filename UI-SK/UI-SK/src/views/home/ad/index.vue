<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="广告标题" prop="adTitle">
        <el-input
          v-model="queryParams.adTitle"
          placeholder="请输入广告标题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="广告类型" prop="adType">
        <el-select
          v-model="queryParams.adType"
          placeholder="请选择广告类型"
          clearable
          size="small"
        >
          <el-option
            v-for="dict in dict.type.home_ad_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="点击量" prop="adHits">
        <el-input
          v-model="queryParams.adHits"
          placeholder="请输入广告点击量"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="关键字" prop="adKey">
        <el-input
          v-model="queryParams.adKey"
          placeholder="请输入广告关键字"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="团队介绍" prop="adTeam">
        <el-input
          v-model="queryParams.adTeam"
          placeholder="请输入广告团队介绍"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="赞助商" prop="adSponsor">
        <el-input
          v-model="queryParams.adSponsor"
          placeholder="请输入广告赞助商"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="开始时间" prop="beginTime">
        <el-date-picker
          clearable
          size="small"
          v-model="queryParams.beginTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择开始时间"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间" prop="overTime">
        <el-date-picker
          clearable
          size="small"
          v-model="queryParams.overTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择结束时间"
        >
        </el-date-picker>
      </el-form-item>

      <el-form-item label="是否置顶" prop="topFlag">
        <el-select
          v-model="queryParams.topFlag"
          placeholder="请选择是否置顶"
          clearable
          size="small"
        >
          <el-option
            v-for="dict in dict.type.sys_yes_no"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="查看删除" prop="delFlag">
        <el-select
          v-model="queryParams.delFlag"
          placeholder="请选择删除状态"
          clearable
          size="small"
        >
          <el-option
            v-for="dict in dict.type.sys_yes_no"
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

    <el-descriptions
      v-show="statistic == false"
      class="margin-top"
      title="数据统计"
      :column="3"
      size="mini"
      border
      direction="vertical"
    >
      <template slot="extra">
        <el-button type="success" size="small" @click="openStatistic()"
          >关闭数据统计</el-button
        >
      </template>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-user"></i>
          置顶数量
        </template>
        {{ statisticData.topNum }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-user"></i>
          逻辑删除数量
        </template>
        {{ statisticData.delNum }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-user"></i>
          定时删除cron执行表达式
        </template>
        <div align="center">
          {{ statisticData.cron }}
          <el-button
            size="mini"
            type="primary"
            plain
            @click="runAdJob()"
            v-hasPermi="['home:ad:statistic']"
            >立即执行一次</el-button
          >
        </div>
      </el-descriptions-item>
    </el-descriptions>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['home:ad:add']"
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
          v-hasPermi="['home:ad:edit']"
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
          v-hasPermi="['home:ad:remove']"
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
          v-hasPermi="['home:ad:export']"
          >导出</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          v-show="statistic == true"
          type="success"
          size="mini"
          @click="openStatistic()"
          >开启数据统计</el-button
        >
      </el-col>
      <el-col :span="1.5"> </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="adList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="广告ID" width="80" align="center" prop="adId" />
      <el-table-column
        label="广告标题"
        show-overflow-tooltip="true"
        align="center"
        prop="adTitle"
      />
      <el-table-column label="类型" width="60" align="center" prop="adType">
        <template slot-scope="scope">
          <dict-tag
            :options="dict.type.home_ad_type"
            :value="scope.row.adType"
          />
        </template>
      </el-table-column>
      <el-table-column label="点击量" width="80" align="center" prop="adHits" />
      <el-table-column
        label="广告关键字"
        show-overflow-tooltip="true"
        align="center"
        prop="adKey"
      />
      <el-table-column
        label="广告团队介绍"
        show-overflow-tooltip="true"
        align="center"
        prop="adTeam"
      />
      <el-table-column
        label="广告图片"
        show-overflow-tooltip="true"
        align="center"
        prop="adImage"
      >
        <template slot-scope="scope">
          <ImagePreview :src="scope.row.adImage" :width="50" :height="50" />
        </template>
      </el-table-column>
      <el-table-column
        label="广告赞助商"
        show-overflow-tooltip="true"
        align="center"
        prop="adSponsor"
      />
      <el-table-column label="备注" align="center" prop="remark" />
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
            v-hasPermi="['home:ad:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['home:ad:remove']"
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

    <!-- 添加或修改广告对话框 -->
    <el-dialog
      :title="title"
      :visible.sync="open"
      width="1500px"
      append-to-body
    >
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="广告标题" prop="adTitle">
              <el-input v-model="form.adTitle" placeholder="请输入广告标题" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="广告类型" prop="adType">
              <el-select v-model="form.adType" placeholder="请选择广告类型">
                <el-option
                  v-for="dict in dict.type.home_ad_type"
                  :key="dict.value"
                  :label="dict.label"
                  :value="parseInt(dict.value)"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="广告文档" prop="adDocument">
              <el-input
                type="textarea"
                v-model="form.adDocument"
                placeholder="请输入广告文档"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="关键字" prop="adKey">
              <el-input
                type="textarea"
                v-model="form.adKey"
                placeholder="请输入广告关键字"
              />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="广告内容">
              <editor v-model="form.adContent" :min-height="192" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="团队介绍" prop="adTeam">
              <el-input
                v-model="form.adTeam"
                placeholder="请输入广告团队介绍"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="赞助商" prop="adSponsor">
              <el-input
                v-model="form.adSponsor"
                placeholder="请输入广告赞助商"
              />
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="是否置顶">
              <el-radio-group v-model="form.topFlag">
                <el-radio
                  v-for="dict in dict.type.sys_yes_no"
                  :key="dict.value"
                  :label="dict.value"
                  >{{ dict.label }}</el-radio
                >
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="删除标志">
              <el-radio-group v-model="form.delFlag">
                <el-radio
                  v-for="dict in dict.type.sys_yes_no"
                  :key="dict.value"
                  :label="dict.value"
                  @change="judgeDel(dict.value)"
                  >{{ dict.label }}</el-radio
                >
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="开始时间" prop="beginTime">
              <el-date-picker
                clearable
                size="small"
                v-model="form.beginTime"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="选择开始时间"
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="结束时间" prop="overTime">
              <el-date-picker
                clearable
                size="small"
                v-model="form.overTime"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="选择结束时间"
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="广告图片">
              <imageUpload v-model="form.adImage" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="备注" prop="remark">
              <el-input
                type="textarea"
                v-model="form.remark"
                placeholder="请输入备注"
              />
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
import {
  listAd,
  getAd,
  delAd,
  addAd,
  updateAd,
  getAdStatistic,
} from "@/api/home/ad";
import { runJob } from "@/api/monitor/job";

export default {
  name: "Ad",
  dicts: ["home_ad_type", "sys_yes_no"],
  data() {
    return {
      // 数据统计
      statistic: true,
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
      // 广告表格数据
      adList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        adTitle: null,
        adType: null,
        adHits: null,
        adKey: null,
        adTeam: null,
        adSponsor: null,
        beginTime: null,
        overTime: null,
        topFlag: null,
        delFlag: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        adTitle: [
          { required: true, message: "广告标题不能为空", trigger: "blur" },
        ],
        adContent: [
          { required: true, message: "广告内容不能为空", trigger: "blur" },
        ],
      },
      statisticData: {},
    };
  },
  created() {
    this.getList();
  },
  methods: {
    //在修改信息中 若选择了 删除 弹出确认框 进行提示  若 确定则 无变化 若不确定则设为 N
    judgeDel(value) {
      //console.log(value);
      if (value === "Y") {
        //console.log(value);
        this.$modal.confirm("是否确认删除此广告").catch(() => {
          /**
           * 下面这个方法可以做 但是报警告了 还是别了吧*/
          //this.$refs.wi.value="N";  wi为父组件
          this.form.delFlag = "N";
        });
      }
    },
    /** 查询广告列表 */
    getList() {
      this.loading = true;
      listAd(this.queryParams).then((response) => {
        this.adList = response.rows;
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
        adId: null,
        adTitle: null,
        adType: null,
        adContent: null,
        adHits: null,
        adImage: null,
        adKey: null,
        adDocument: null,
        adTeam: null,
        adSponsor: null,
        beginTime: null,
        overTime: null,
        topFlag: "N",
        delFlag: "N",
        createTime: null,
        createBy: null,
        updateTime: null,
        updateBy: null,
        remark: null,
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
      this.ids = selection.map((item) => item.adId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加广告";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const adId = row.adId || this.ids;
      getAd(adId).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改广告";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.adId != null) {
            updateAd(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAd(this.form).then((response) => {
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
      const adIds = row.adId || this.ids;
      this.$modal
        .confirm('是否确认删除广告编号为"' + adIds + '"的数据项？')
        .then(function () {
          return delAd(adIds);
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
        "home/ad/export",
        {
          ...this.queryParams,
        },
        `ad_${new Date().getTime()}.xlsx`
      );
    },
    /** 开启数据统计 */
    openStatistic: function () {
      if (this.statistic == false) {
        this.statistic = true;
      } else {
        getAdStatistic().then((response) => {
          this.statisticData = response;
        });
        this.statistic = false;
      }
    },
    /** 无条件执行定时任务(物理删除广告)一次*/
    runAdJob() {
      this.$modal
        .confirm("确认要立即执行一次任务吗？")
        .then(function () {
          return runJob(100, "DEFAULT");
        })
        .then(() => {
          this.$modal.msgSuccess("执行成功");
        })
        .catch(() => {});
    },
  },
};
</script>
