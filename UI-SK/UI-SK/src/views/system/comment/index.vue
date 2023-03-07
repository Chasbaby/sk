<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="浏览量" prop="commentView">
        <el-input
          v-model="queryParams.commentView"
          placeholder="请输入浏览量"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="点击量" prop="commentHits">
        <el-input
          v-model="queryParams.commentHits"
          placeholder="请输入点击量"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="点赞量" prop="commentLike">
        <el-input
          v-model="queryParams.commentLike"
          placeholder="请输入点赞量"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="评论来源" prop="commentSource">
        <el-select v-model="queryParams.commentSource" placeholder="请选择评论来源" clearable size="small">
          <el-option
            v-for="dict in dict.type.commend_source"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="评论者IP" prop="commentIp">
        <el-input
          v-model="queryParams.commentIp"
          placeholder="请输入评论者IP"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="审核状态" prop="judgeStatus">
        <el-select v-model="queryParams.judgeStatus" placeholder="请选择审核状态" clearable size="small">
          <el-option
            v-for="dict in dict.type.comment_judge_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="审核人" prop="judger">
        <el-input
          v-model="queryParams.judger"
          placeholder="请输入审核人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="可见状态" prop="visableStatus">
        <el-select v-model="queryParams.visableStatus" placeholder="请选择状态" clearable size="small">
          <el-option
            v-for="dict in dict.type.sys_yes_no"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="删除状态" prop="delFlag">
        <el-select v-model="queryParams.delFlag" placeholder="请选择是否删除" clearable size="small">
          <el-option
            v-for="dict in dict.type.sys_yes_no"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.createTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择创建时间">
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
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:comment:edit']"
        >单个审核</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:comment:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:comment:export']"
        >导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          plain
          size="mini"
          icon="el-icon-smoking"
          type="primary"
          :disabled="multiple"
        >批量通过</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          plain
          size="mini"
          icon="el-icon-no-smoking"
          type="info"
          :disabled="multiple"
        >批量不通过</el-button>
      </el-col>
      <el-button
        v-show="statistic==true"
        type="success"
        size="mini"
        @click="openStatistic()"
        v-hasPermi="['system:comment:statistic']"
      >开启数据统计</el-button>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-descriptions v-show="statistic==false" class="margin-top" title="数据统计" :column="2" size="mini" border direction="vertical">
      <template slot="extra">
        <el-button type="success" size="small" @click="openStatistic()">关闭数据统计</el-button>
      </template>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-user"></i>
          未审核数量
        </template>
        {{statisticData.noJudge}}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-user"></i>
          最多的评论来源
        </template>
          <dict-tag :options="dict.type.commend_source" :value="statisticData.maxSource"/>
      </el-descriptions-item>

    </el-descriptions>

    <el-table
      v-loading="loading"
      stripe
      border
      :data="commentList"
      tooltip-effect="light"
      @selection-change="handleSelectionChange"
      :row-class-name="tableRowClassName"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="评论ID" width="60":show-overflow-tooltip="true" align="center" prop="commentId" />
      <el-table-column label="浏览量" sortable width="120":show-overflow-tooltip="true" align="center" prop="commentView" />
      <el-table-column label="点击量" sortable width="120" :show-overflow-tooltip="true"align="center" prop="commentHits" />
      <el-table-column label="点赞量" sortable width="120" :show-overflow-tooltip="true"align="center" prop="commentLike" />
      <el-table-column label="来源" width="60" align="center" prop="commentSource">
<!--        到时候这里可以直接链接过去-->
        <template slot-scope="scope">
          <dict-tag :options="dict.type.commend_source" :value="scope.row.commentSource"/>
        </template>
      </el-table-column>
      <el-table-column label="评论者IP" width="150" align="center" prop="commentIp" />
      <el-table-column label="审核状态" width="100" align="center" prop="judgeStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.comment_judge_status" :value="scope.row.judgeStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="审核人"  width="100" align="center" prop="judger" />
      <el-table-column label="创建时间" sortable align="center" prop="createTime"show-overflow-tooltip="true" width="100">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="内容" align="center" prop="commentContent" show-overflow-tooltip="true"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:comment:edit']"
          >审核</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:comment:remove']"
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

    <!-- 添加或修改评论对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">

        <el-descriptions class="margin-top" border :column="4" colon direction="vertical">
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-user"></i>
                父级ID
            </template>
            {{form.parentId}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-user"></i>
              用户ID
            </template>
            {{form.userId}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-user"></i>
              评论来源
            </template>
            <dict-tag :options="dict.type.commend_source" :value="form.commentSource"/>
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-user"></i>
              IP
            </template>
            {{form.commentIp}}
          </el-descriptions-item>

          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-user"></i>
              浏览量
            </template>
            {{form.commentView}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-user"></i>
              点击量
            </template>
            {{form.commentHits}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-user"></i>
              点赞量
            </template>
            {{form.commentLike}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-user"></i>
              审核者
            </template>
            {{form.judger}}
          </el-descriptions-item>
        </el-descriptions>
        <el-descriptions class="margin-top" border :column="2" colon direction="vertical">
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-user"></i>
              可见状态
            </template>
            <el-radio-group v-model="form.visableStatus">
              <el-radio
                v-for="dict in dict.type.sys_yes_no"
                :key="dict.value"
                :label="dict.value"
              >{{dict.label}}</el-radio>
            </el-radio-group>
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-user"></i>
              删除状态
            </template>
            <el-radio-group v-model="form.delFlag">
              <el-radio
                v-for="dict in dict.type.sys_yes_no"
                :key="dict.value"
                :label="dict.value"
              >{{dict.label}}</el-radio>
            </el-radio-group>
          </el-descriptions-item>
          <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-user"></i>
                审核状态
              </template>
              <el-radio-group v-model="form.judgeStatus">
                <el-radio
                  v-for="dict in dict.type.comment_judge_status"
                  :key="dict.value"
                  :label="dict.value"
                >{{dict.label}}</el-radio>
              </el-radio-group>
            </el-descriptions-item>
        </el-descriptions>

        <el-card shadow="hover">
          <div>内容:</div>
          {{form.commentContent}}
        </el-card>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listComment, getComment, delComment, updateComment ,getCommentStatistic} from "@/api/system/comment";


export default {
  name: "Comment",
  dicts: ['commend_source', 'comment_judge_status', 'sys_yes_no'],
  data() {
    return {
      statisticData:{},
      // 数据统计
      statistic:true,
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
      // 评论表格数据
      commentList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        commentView: null,
        commentHits: null,
        commentLike: null,
        commentSource: null,
        commentIp: null,
        judgeStatus: null,
        judger: null,
        visableStatus: null,
        delFlag: null,
        createTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        judgeStatus: [
          { required: true, message: "审核状态不能为空", trigger: "blur" }
        ],        visableStatus: [
          { required: true, message: "状态不能为空", trigger: "blur" }
        ],        delFlag: [
          { required: true, message: "是否删除不能为空", trigger: "blur" }
        ],      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    tableRowClassName({row, rowIndex}){
      if (row.judgeStatus === 1){
        return 'warning-row';
      }
      return '';
    },
    /** 查询评论列表 */
    getList() {
      this.loading = true;
      listComment(this.queryParams).then(response => {
        this.commentList = response.rows;
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
        commentId: null,
        parentId: null,
        userId: null,
        commentContent: null,
        commentView: null,
        commentHits: null,
        commentLike: null,
        commentSource: "0",
        commentIp: null,
        judgeStatus: "0",
        judger: null,
        visableStatus: "0",
        delFlag: "0",
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
      this.ids = selection.map(item => item.commentId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 审核按钮操作 */
    handleUpdate(row) {
      this.reset();
      const commentId = row.commentId || this.ids
      getComment(commentId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "审核评论";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.commentId != null) {
            updateComment(this.form).then(response => {
              this.$modal.msgSuccess("审核成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const commentIds = row.commentId || this.ids;
      this.$modal.confirm('是否确认删除评论编号为"' + commentIds + '"的数据项？').then(function() {
        return delComment(commentIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/comment/export', {
        ...this.queryParams
      }, `comment_${new Date().getTime()}.xlsx`)
    },
    /** 开启数据统计 */
    openStatistic: function () {
      if (this.statistic == false) {
        this.statistic = true;
      } else {
        getCommentStatistic().then(response =>{
          this.statisticData=response;
        });
        this.statistic = false;
      }
    },
  }
};
</script>

