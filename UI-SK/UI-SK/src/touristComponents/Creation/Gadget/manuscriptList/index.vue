<template>
  <div id="manuscriptlist">
    <ul>
      <li v-for="(item, index) in List" :key="index">
        <div class="img">
          <img :src="item.src" alt="" />
        </div>
        <div class="rightbox">
          <span class="title">
            {{ item.name }}
          </span>
          <span class="time">{{ item.time }}</span>
          <div class="tags">
            <span class="view"><i class="fa fa-eye"></i>{{ item.view }}</span>
            <span class="comment"
            ><i class="fa fa-comment"></i>{{ item.comment }}</span
            >
            <span class="thumbsup"
            ><i class="fa fa-thumbs-up"></i>{{ item.thumbsup }}</span
            >
            <span class="favourite"
            ><i class="fa fa-star"></i>{{ item.favourite }}</span
            >
          </div>
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
          color: #505050;
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
