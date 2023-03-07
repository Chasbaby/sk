<template>
  <div id="comment">
    <h3>评论管理</h3>
    <el-table
      :data="tableData"
      style="width: 100%"
      @selection-change="handleSelectionChange"
      ref="multipleTable"
    >
      <el-table-column type="selection" width="55"> </el-table-column>
      <el-table-column label="日期" width="180">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.date }}</span>
        </template>
      </el-table-column>
      <el-table-column label="用户" width="180">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>用户: {{ scope.row.name }}</p>
            <div class="img">
              <img
                class="avatar"
                src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
                alt=""
              />
            </div>

            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row.name }}</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column label="内容" width="500" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <p>{{ scope.row.address }}</p>
        </template>
      </el-table-column>
      <el-table-column label="来源" :show-overflow-tooltip="true" width="250">
        <template slot-scope="scope">
          <p>{{ scope.row.address }}</p>
        </template>
      </el-table-column>
      <el-table-column label="执行操作" align="right">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)"
          >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <div style="margin-top: 20px">
      <el-button
        :disabled="multipleSelection.length == 0"
        @click="toggleSelection()"
      >取消选择</el-button
      >
      <el-button
        :disabled="multipleSelection.length == 0"
        @click="deleteSelecction()"
      >一键删除</el-button
      >
    </div>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        tableData: [
          {
            date: "2016-05-02",
            name: "王小虎",
            address: "上海市普陀区金沙江路 1518 弄",
          },
          {
            date: "2016-05-04",
            name: "王小虎",
            address: "上海市普陀区金沙江路 1517 弄",
          },
          {
            date: "2016-05-01",
            name: "王小虎",
            address:
              "哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈",
          },
          {
            date: "2016-05-03",
            name: "王小虎",
            address: "上海市普陀区金沙江路 1516 弄",
          },
        ],
        multipleSelection: [],
      };
    },
    methods: {
      handleEdit(index, row) {
        console.log(index, row);
      },
      handleDelete(index, row) {
        console.log(index, row);
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      deleteSelecction() {
        this.$alert("删除所选中！");
      },
    },
  };
</script>

<style lang="scss" scoped>
  $backcolor: #ffffff;
  $bluecolor: #00aeec;
  #comment {
    background-color: $backcolor;
    padding: 20px 40px 20px 40px;
    h3 {
      color: $bluecolor;
    }
    .img {
      width: 50px;
      height: 50px;
      img {
        width: 100%;
        height: 100%;
        object-fit: cover;
      }
    }
  }
</style>
