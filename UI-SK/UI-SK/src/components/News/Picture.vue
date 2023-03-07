<template>
  <div>
    <div class="block">
      <img :src="src" alt="#" />
      <span><slot></slot></span>
    </div>
  </div>
</template>

<script>
export default {
  name: "Picture",
  data() {
    return {};
  },
  props: {
    src: {
      type: String,
    },
  },
};
</script>

<style scoped>
.block {
  width: 550px;
  height: 340px;
  margin: 0 auto;
  padding-bottom: 30px;
  margin-top: 30px;
  margin-bottom: 30px;
}
.block img {
  object-fit: cover;
  width: 100%;
  height: 100%;
  margin-bottom: 15px;
}
.block span {
  display: block;
  font-size: 14px;
  text-align: center;
}
</style>