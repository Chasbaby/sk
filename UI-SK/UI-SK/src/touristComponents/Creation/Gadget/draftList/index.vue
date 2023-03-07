<template>
  <div id="draftList">
    <ul>
      <li v-for="(item, index) in List" :key="index">
        <div class="img">
          <img :src="item.src" alt="" />
        </div>
        <div class="rightbox">
          <span class="title">
            {{ item.name }}
          </span>
          <span class="time">最后修改时间：{{ item.editTime }}</span>
          <div class="btn">
            <el-button icon="el-icon-edit" class="edit">编辑</el-button>
            <delete-popover :data="item"></delete-popover>
          </div>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
  import deletePopover from "../deletePopover/index"
  export default {
    components: { deletePopover },
    props: ["List"],
  };
</script>

<style lang="scss" scoped>
  $font: -apple-system, BlinkMacSystemFont, "Helvetica Neue", Helvetica, Arial,
  "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", sans-serif;
  ul {
    li {
      width: 750px;
      margin: 0 auto;
      display: flex;
      padding-bottom: 20px;
      padding-top: 20px;
      border-bottom: 1px solid #e7e7e7;
      .img {
        width: 154px;
        height: 96px;
        border-radius: 10px;
        padding-right: 20px;
        img {
          width: 100%;
          height: 100%;
        }
      }
      .rightbox {
        position: relative;
        flex: 1;
        .title {
          display: block;
          font-size: 16px;
          color: #212121;
          padding-bottom: 15px;
          font-family: $font;
        }
        .time {
          display: block;
          font-size: 14px;
          color: #99a2aa;
          font-family: $font;
          padding-bottom: 20px;
        }
        .tags {
          span {
            padding-right: 20px;
            font-family: $font;
            font-size: 12px;
            color: #99a2aa;
            i {
              padding-right: 5px;
            }
          }
        }
        .btn {
          position: absolute;
          right: 20px;
          top: 40px;
          .edit {
            margin-right: 10px;
          }
        }
      }
    }
  }
</style>
