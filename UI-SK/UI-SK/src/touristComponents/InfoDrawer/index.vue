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
      <el-container>
        <el-aside width="70px" class="aside">
          <el-menu
            default-active="1"
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose"
            :collapse="true"
          >
            <el-menu-item index="1" @click="go('1')">
              <i class="el-icon-message"></i>
              <span slot="title">消息</span>
              <span slot="label" class="leftIcon">
                <el-badge :value="navList[0].new" class="iconNew"> </el-badge>
              </span>
            </el-menu-item>
            <el-submenu index="2">
              <template slot="title">
                <i class="el-icon-star-off"></i>
                <span slot="title">收藏</span>
                <span slot="label" class="leftIcon">
                  <el-badge :value="this.navList[1].new" class="iconNew">
                  </el-badge>
                </span>
              </template>
              <el-menu-item-group>
                <span slot="title">类别</span>
                <el-menu-item index="2-1" @click="go('2-1')">景点</el-menu-item>
                <el-menu-item index="2-2" @click="go('2-2')">文章</el-menu-item>
                <el-menu-item index="2-3" @click="go('2-3')"
                  >文创作品</el-menu-item
                >
              </el-menu-item-group>
            </el-submenu>
            <el-menu-item index="3" @click="go('3')">
              <i class="el-icon-time"></i>
              <span slot="title">历史记录</span>
              <span slot="label" class="leftIcon">
                <el-badge :value="this.navList[2].new" class="iconNew">
                </el-badge>
              </span>
            </el-menu-item>
          </el-menu>
        </el-aside>
        <el-container class="container">
          <div id="tab">
            <transition name="fade" mode="out-in">
              <div v-if="this.type == '1'" class="view">
                <ul class="drawerUl message">
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
                  <span v-if="messageList.length == 0" class="noTip"
                    >暂无消息哦</span
                  >
                  <el-dialog
                    :visible.sync="dialogVisible"
                    width="30%"
                    :before-close="handleClose"
                    :append-to-body="true"
                  >
                    <span slot="title"
                      >来自 {{ currentItem.sender }} 的消息</span
                    >
                    <span>{{ currentItem.content }}</span>
                    <span slot="footer" class="dialog-footer">
                      <el-button type="primary" @click="dialogVisible = false"
                        >确 定</el-button
                      >
                    </span>
                  </el-dialog>
                </ul>
              </div>
            </transition>
            <transition name="fade" mode="out-in">
              <div v-if="this.type == '2-1'" class="view">
                <ul class="fav">
                  <li
                    v-for="(item, index) in favouriteList"
                    :key="index"
                    @click="gotoAttraction(item)"
                  >
                    <div class="leftbox">
                      <img :src="item.src" alt="" />
                      <div class="cover"></div>
                    </div>
                    <div class="rightbox">
                      <span class="location"
                        ><i class="el-icon-position"></i>{{ item.name }}</span
                      >
                      <span class="favtime">收藏于 {{ item.favtime }}</span>
                    </div>
                  </li>
                  <span v-if="favouriteList.length == 0" class="noTip"
                    >暂无收藏哦</span
                  >
                </ul>
              </div>
            </transition>
            <transition name="fade" mode="out-in">
              <div v-if="this.type == '2-2'" class="view">zzz</div>
            </transition>
            <transition name="fade" mode="out-in">
              <div v-if="this.type == '2-3'" class="view">zzz</div>
            </transition>
            <transition name="fade" mode="out-in">
              <div v-if="this.type == '3'" class="view">
                <ul>
                  <span v-if="historyList.length == 0" class="noTip"
                    >暂无历史浏览记录哦</span
                  >
                </ul>
              </div>
            </transition>
          </div>
        </el-container>
      </el-container>
    </el-drawer>
  </div>
</template>

<script>
export default {
  data() {
    return {
      type: "1",
      title: this.drawerTitle,
      drawer: false,
      dialogVisible: false,
      tabPosition: "left",
      currentItem: {},
      navList: [
        { icon: "el-icon-message", name: "消息", new: 1 }, //new指的是未读消息数量
        { icon: "el-icon-star-off", name: "收藏", new: 1 },
        { icon: "el-icon-time", name: "历史" },
      ],
      messageList: [
        {
          id: "", //id为唯一标识，点击消息列表时会发送已阅读请求，传这个id
          sender: "朱澔然",
          content: "消息内容哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈",
          time: "2023-1-6-13:06",
          type: 0, //0表示已读，1表示未读
        },
        {
          id: "",
          sender: "池声楼",
          content:
            "hahah哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈",
          time: "2023-1-6-13:22",
          type: 1, //0表示已读，1表示未读
        },
      ], //message
      favouriteList: [
        {
          id: "",
          name: "卢浮宫",
          favtime: "2023.1.29",
          src: "https://dimg08.c-ctrip.com/images/fd/tg/g3/M0A/07/3D/CggYGlaeCDaAQoagAAnBQ-n4YHs222_R_1600_10000.jpg",
        },
      ],
      historyList: [],
    };
  },
  methods: {
    go(value) {
      this.type = value;
      console.log(this.type);
    },
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
      // 发请求
      // request({
      //   url: "xxxx" + item.id, //接口，修改已阅表示，需要重新返回未读总数
      //   method: "get",
      // });
    },
    gotoAttraction(item) {
      this.$router.push({ path: "/Attractionspage", query: { id: item.id } });
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
.container {
  width: 100%;

  #tab {
    width: 100%;
    position: relative;
    .view {
      position: absolute;
      top: 0;
      width: 100%;
      ::v-deep .el-tabs__item {
        height: 63px;
        line-height: 63px;
        font-size: 20px;
      }
      ::v-deep .drawerUl {
        padding-inline-start: 0px;
      }
      li {
        transition: all 0.3s ease-in-out;
      }
      .message li {
        position: relative;
        height: 63px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        padding-left: 5px;
      }
      .fav li {
        display: flex;
        padding: 5px;
        &:hover .leftbox .cover {
          opacity: 1;
        }
        .leftbox {
          position: relative;
          width: 250px;
          height: 140px;
          background-color: red;
          img {
            width: 100%;
            height: 100%;
            object-fit: cover;
          }
          .cover {
            top: 0px;
            width: 100%;
            height: 100%;
            text-align: center;
            position: absolute;
            background-color: rgba(0, 0, 0, 0.2);
            opacity: 0;
            transition: all 0.3s ease-in-out;
          }
        }
        .rightbox {
          flex: 1;
          padding-left: 5px;
          padding-top: 5px;
          position: relative;
          .location {
            font-size: 18px;
            letter-spacing: 2px;
          }
          .favtime {
            position: absolute;
            bottom: 5px;
            left: 5px;
            font-size: 12px;
            color: #999;
          }
        }
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
        top: 11px;
        right: 10px;
      }
      ::v-deep .el-badge__content {
        border-radius: 20px;
        color: #fff;
        display: inline-block;
        font-size: 6px;
        height: 21px;
        line-height: 18px;
        padding: 0px 6px;
      }
      ::v-deep .el-tabs__content {
        height: 100%;
        overflow-y: auto; /*浏览器决定*/
      }
    }
  }
}

.aside {
  height: 100%;
}
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>
