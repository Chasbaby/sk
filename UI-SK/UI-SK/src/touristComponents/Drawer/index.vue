<template>
  <div id="Drawer">
    <div @click="drawer = true" type="primary" style="margin-left: 16px">
      <el-badge is-dot class="item"
      ><slot name="btn" class="avatar"></slot
      ></el-badge>
    </div>
    <el-drawer
      :title="title"
      :visible.sync="drawer"
      :direction="direction"
      :append-to-body="true"
    >
      <el-tabs :tab-position="tabPosition" style="height: 100%" id="tab">
        <el-tab-pane
          v-for="(item, index) in navList"
          :key="index"
          :index="item.name"
        >
          <span slot="label" class="leftIcon">
            <el-badge :value="99" class="iconNew">
              <el-tooltip
                class="item"
                effect="dark"
                :content="item.name"
                placement="left"
              >
                <i :class="item.icon"></i>
              </el-tooltip>
            </el-badge>
          </span>
          <ul v-if="index == 0" class="drawerUl">
            <li
              v-for="(item, index) in messageList"
              :key="index"
              :class="[item.type == 0 ? 'Read' : '']"
              @click="showMessageDialog(item)"
            >
              <span class="title">{{ item.sender }}</span>
              <span class="time">{{ item.time }}</span>
              <span class="content">{{ item.content }}</span>
            </li>
            <span v-if="messageList.length == 0" class="noTip">暂无消息哦</span>
            <el-dialog
              :visible.sync="dialogVisible"
              width="30%"
              :before-close="handleClose"
              :append-to-body="true"
            >
              <span slot="title">来自 {{ currentItem.sender }} 的消息</span>
              <span>{{ currentItem.content }}</span>
              <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="dialogVisible = false"
                >确 定</el-button
                >
              </span>
            </el-dialog>
          </ul>
          <ul v-if="index == 1">
            <span v-if="favouriteList.length == 0" class="noTip"
            >暂无收藏哦</span
            >
          </ul>
          <ul v-if="index == 2">
            <span v-if="historyList.length == 0" class="noTip"
            >暂无历史浏览记录哦</span
            >
          </ul>
        </el-tab-pane>
      </el-tabs>
    </el-drawer>
  </div>
</template>

<script>
  export default {
    name: "Drawer",
    data() {
      return {
        title: this.drawerTitle,
        drawer: false,
        dialogVisible: false,
        tabPosition: "left",
        currentItem: {},
        navList: [
          { icon: "el-icon-message", name: "消息" },
          { icon: "el-icon-star-off", name: "收藏" },
          { icon: "el-icon-time", name: "历史" },
        ],
        messageList: [
          {
            sender: "朱澔然",
            content:
              "消息内容哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈",
            time: "2023-1-6-13:06",
            type: 0, //0表示已读，1表示未读
          },
          {
            sender: "池声楼",
            content:
              "消息内容哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈",
            time: "2023-1-6-13:22",
            type: 1, //0表示已读，1表示未读
          },
          {
            sender: "朱澔然",
            content: "11",
            time: "2023-1-6-13:06",
            type: 0, //0表示已读，1表示未读
          },
          {
            sender: "朱澔然",
            content: "11",
            time: "2023-1-6-13:06",
            type: 0, //0表示已读，1表示未读
          },
          {
            sender: "朱澔然",
            content: "11",
            time: "2023-1-6-13:06",
            type: 0, //0表示已读，1表示未读
          },
          {
            sender: "朱澔然",
            content: "11",
            time: "2023-1-6-13:06",
            type: 0, //0表示已读，1表示未读
          },
          {
            sender: "朱澔然",
            content: "11",
            time: "2023-1-6-13:06",
            type: 0, //0表示已读，1表示未读
          },
          {
            sender: "朱澔然",
            content: "11",
            time: "2023-1-6-13:06",
            type: 0, //0表示已读，1表示未读
          },
          {
            sender: "朱澔然",
            content: "11",
            time: "2023-1-6-13:06",
            type: 0, //0表示已读，1表示未读
          },
          {
            sender: "朱澔然",
            content: "11",
            time: "2023-1-6-13:06",
            type: 0, //0表示已读，1表示未读
          },
          {
            sender: "朱澔然",
            content: "11",
            time: "2023-1-6-13:06",
            type: 0, //0表示已读，1表示未读
          },
          {
            sender: "朱澔然",
            content: "11",
            time: "2023-1-6-13:06",
            type: 0, //0表示已读，1表示未读
          },
          {
            sender: "朱澔然",
            content: "11",
            time: "2023-1-6-13:06",
            type: 0, //0表示已读，1表示未读
          },
          {
            sender: "朱澔然",
            content: "11",
            time: "2023-1-6-13:06",
            type: 0, //0表示已读，1表示未读
          },
          {
            sender: "朱澔然",
            content: "11",
            time: "2023-1-6-13:06",
            type: 0, //0表示已读，1表示未读
          },
          {
            sender: "朱澔然",
            content: "11",
            time: "2023-1-6-13:06",
            type: 0, //0表示已读，1表示未读
          },
        ],
        favouriteList: [],
        historyList: [],
      };
    },
    methods: {
      handleOpen() {},
      handleClose(done) {
        this.$confirm("确认关闭？")
          .then((_) => {
            done();
          })
          .catch((_) => {});
      },
      showMessageDialog(item) {
        this.dialogVisible = true;
        console.log(item.sender);
        this.currentItem = item;
        item.type = 0;
      },
    },
    props: ["drawerTitle", "direction"],
  };
</script>

<style lang="scss" scoped>
  .leftIcon {
    font-size: 22px;
  }
  .tabPane {
    height: 55px;
    line-height: 55px;
  }
  #tab {
    ::v-deep .el-tooltip__popper {
      padding: 8px 10px;
    }
    ::v-deep .el-tabs__item {
      height: 63px;
      line-height: 63px;
      font-size: 20px;
    }
    ::v-deep .drawerUl{
      margin-block-start: 0em;
      margin-block-end: 0em;
      padding-inline-start:0px
    }
    ul li {
      position: relative;
      height: 63px;
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
      transition: all 0.3s ease-in-out;
      padding-left: 0px;
    }
    .title {
      display: block;
      padding-bottom: 10px;
      padding-top: 5px;
    }
    .time {
      position: absolute;
      top: 9px;
      right: 12px;
      font-size: 12px;
      color: #409eff;
    }
    .content {
      font-size: 14px;
    }
    .noTip {
      display: block;
      padding-top: 20px;
      height: 100%;
      text-align: center;
      color: #9499a0;
    }
    .Read {
      color: #5c5c5c;
      .time,
      .content,
      .time {
        color: #5c5c5c;
      }
    }
    ul li:hover {
      cursor: pointer;
      background-color: #e6e8ea;
    }
    ::v-deep .el-badge__content.is-fixed {
      top: 13px;
      right: 8px;
    }
    ::v-deep .el-badge__content {
      border-radius: 18px;
      color: #fff;
      display: inline-block;
      font-size: 6px;
      height: 18px;
      line-height: 18px;
      padding: 1px 5px;
    }
    ::v-deep .el-tabs__content {
      height: 100%;
      overflow-y: auto; /*浏览器决定*/
    }
  }
</style>
