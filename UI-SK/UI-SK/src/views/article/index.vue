<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="文章分类" prop="articleCategory">
        <el-select v-model="queryParams.articleCategory" placeholder="请选择文章分类" clearable size="small">
          <el-option
            v-for="dict in dict.type.article_category"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="文章标题" prop="articleTitle">
        <el-input
          v-model="queryParams.articleTitle"
          placeholder="请输入文章标题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="文章类型" prop="articleType">
        <el-select v-model="queryParams.articleType" placeholder="请选择文章类型" clearable size="small">
          <el-option
            v-for="dict in dict.type.article_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="是否删除" prop="isDelete">
        <el-select v-model="queryParams.isDelete" placeholder="请选择是否删除" clearable size="small">
          <el-option
            v-for="dict in dict.type.sys_yes_no"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="审核情况" prop="isOk">
        <el-select v-model="queryParams.isOk" placeholder="请选择审核情况" clearable size="small">
          <el-option
            v-for="dict in dict.type.sys_yes_no"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="状态值" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态值" clearable size="small">
          <el-option
            v-for="dict in dict.type.article_state"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="审核者" prop="judgeBy">
        <el-input
          v-model="queryParams.judgeBy"
          placeholder="请输入审核者"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="审核时间" prop="judgeTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.judgeTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择审核时间">
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
          v-hasPermi="['system:article:add']"
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
          v-hasPermi="['system:article:edit']"
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
          v-hasPermi="['system:article:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:article:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="articleList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="文章id" align="center" prop="articleId" />
      <el-table-column label="作者" align="center" prop="userId" />
      <el-table-column label="文章分类" align="center" prop="articleCategory">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.article_category" :value="scope.row.articleCategory"/>
        </template>
      </el-table-column>
      <el-table-column label="文章缩略图" align="center" prop="articleCover" />
      <el-table-column label="文章标题" :show-overflow-tooltip="true" align="center" prop="articleTitle" />
      <el-table-column label="文章内容" :show-overflow-tooltip="true" align="center" prop="articleContent" />
      <el-table-column label="文章类型" align="center" prop="articleType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.article_type" :value="scope.row.articleType"/>
        </template>
      </el-table-column>
      <el-table-column label="点赞数" align="center" prop="articleLike" />
      <el-table-column label="浏览量" align="center" prop="articleView" />
      <el-table-column label="收藏量" align="center" prop="articleCollect" />
      <el-table-column label="审核情况" align="center" prop="isOk">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_yes_no" :value="scope.row.isOk"/>
        </template>
      </el-table-column>
      <el-table-column label="状态值" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.article_state" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="作者名称" align="center" prop="createBy" />
      <el-table-column label="审核者" align="center" prop="judgeBy" />
      <el-table-column label="审核时间" align="center" prop="judgeTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.judgeTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:article:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:article:remove']"
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

    <!-- 添加或修改文章对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="文章分类" prop="articleCategory">
          <el-select v-model="form.articleCategory" placeholder="请选择文章分类">
            <el-option
              v-for="dict in dict.type.article_category"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="文章缩略图">
          <imageUpload v-model="form.articleCover"/>
        </el-form-item>
        <el-form-item label="文章标题" prop="articleTitle">
          <el-input v-model="form.articleTitle" placeholder="请输入文章标题" />
        </el-form-item>
        <el-form-item label="文章内容">
          <editor v-model="form.articleContent" :min-height="192"/>
        </el-form-item>
        <el-form-item label="文章类型" prop="articleType">
          <el-select v-model="form.articleType" placeholder="请选择文章类型">
            <el-option
              v-for="dict in dict.type.article_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="原文链接" prop="originalUrl">
          <el-input v-model="form.originalUrl" placeholder="请输入原文链接" />
        </el-form-item>
        <el-form-item label="点赞数" prop="articleLike">
          <el-input v-model="form.articleLike" placeholder="请输入点赞数" />
        </el-form-item>
        <el-form-item label="浏览量" prop="articleView">
          <el-input v-model="form.articleView" placeholder="请输入浏览量" />
        </el-form-item>
        <el-form-item label="收藏量" prop="articleCollect">
          <el-input v-model="form.articleCollect" placeholder="请输入收藏量" />
        </el-form-item>
        <el-form-item label="是否置顶">
          <el-radio-group v-model="form.isTop">
            <el-radio
              v-for="dict in dict.type.sys_yes_no"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="是否删除">
          <el-radio-group v-model="form.isDelete">
            <el-radio
              v-for="dict in dict.type.sys_yes_no"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="审核情况">
          <el-radio-group v-model="form.isOk">
            <el-radio
              v-for="dict in dict.type.sys_yes_no"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="状态值" prop="status">
          <el-select v-model="form.status" placeholder="请选择状态值">
            <el-option
              v-for="dict in dict.type.article_state"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="审核者" prop="judgeBy">
          <el-input v-model="form.judgeBy" placeholder="请输入审核者" />
        </el-form-item>
        <el-form-item label="审核时间" prop="judgeTime">
          <el-date-picker clearable size="small"
            v-model="form.judgeTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择审核时间">
          </el-date-picker>
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
import { listArticle, getArticle, delArticle, addArticle, updateArticle } from "@/api/article/article";

export default {
  name: "Article",
  dicts: ['article_type', 'article_state', 'article_category', 'sys_yes_no'],
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
      // 文章表格数据
      articleList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        articleCategory: null,        articleTitle: null,        articleType: null,        isDelete: null,        isOk: null,        status: null,        judgeBy: null,        judgeTime: null      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询文章列表 */
    getList() {
      this.loading = true;
      listArticle(this.queryParams).then(response => {
        this.articleList = response.rows;
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
        articleId: null,        userId: null,        articleCategory: null,        articleCover: null,        articleTitle: null,        articleContent: null,        articleType: null,        originalUrl: null,        articleLike: null,        articleView: null,        articleCollect: null,        isTop: "0",        isDelete: "0",        isOk: "0",        status: null,        createTime: null,        updateTime: null,        createBy: null,        updateBy: null,        judgeBy: null,        judgeTime: null      };
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
      this.ids = selection.map(item => item.articleId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加文章";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const articleId = row.articleId || this.ids
      getArticle(articleId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改文章";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.articleId != null) {
            updateArticle(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addArticle(this.form).then(response => {
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
      const articleIds = row.articleId || this.ids;
      this.$modal.confirm('是否确认删除文章编号为"' + articleIds + '"的数据项？').then(function() {
        return delArticle(articleIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/article/export', {
        ...this.queryParams
      }, `article_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
