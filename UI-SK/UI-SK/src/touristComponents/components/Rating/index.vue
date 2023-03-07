<template>
  <div id="Rating">
    <!-- <el-rate
      v-model="value"
      :icon-classes="iconClasses"
      void-icon-class="icon-rate-face-off"
      :colors="['#99A9BF', '#F7BA2A', '#FF9900']"
    >
    </el-rate> -->
    <el-rate v-model="value2" :colors="colors"> </el-rate>
  </div>
</template>

<script>
  export default {
    name: "Rating",
    data() {
      return {
        value1: null,
        value2: null,
        colors: ["#99A9BF", "#F7BA2A", "#FF9900"], // 等同于 { 2: '#99A9BF', 4: { value: '#F7BA2A', excluded: true }, 5: '#FF9900' }
      };
    },
  };
</script>

<style scoped>
  #Rating {
    height: 100%;
    margin-top: 12px;
  }
</style>
