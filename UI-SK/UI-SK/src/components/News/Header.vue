<template>
  <div class="block">
    <div class="avatar">
      <span>{{ User.name }}</span>
      <el-avatar
        src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
      ></el-avatar>
    </div>
  </div>
</template>

<script>
export default {
  name: "Header",
  data() {
    return {
      activeIndex: "1",
      activeIndex2: "1",
      User: {
        name: "朱澔然",
      },
    };
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
  },
};
</script>

<style scoped>
.block {
  margin-bottom: 30px;
}
.el-menu-demo {
  width: 100%;
}
.avatar {
  position: absolute;
  display: flex;
  text-align: center;
  height: 40px;
  line-height: 40px;
  right: 5%;
  top: 5px;
}
.avatar span {
  font-size: 14px;
  margin-right: 10px;
}
</style>