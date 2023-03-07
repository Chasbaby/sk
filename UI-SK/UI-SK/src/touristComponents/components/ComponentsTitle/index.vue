<template>
  <div id="Componenttitle">
    <div class="total_div">
      <div class="leftText">
        <span class="left">
          <slot name="Chinese"></slot>
        </span>
      </div>
      <span class="right">
        <slot name="English"></slot>
      </span>
    </div>
  </div>
</template>

<script>
  export default {
    name: "ComponentTitle",
  };
</script>

<style scoped>
  #Componenttitle {
    height: 300px;
    width: 100%;
    /* background-color: pink; */
  }

  #Componenttitle .leftText {
    display: inline-block;
    padding-right: 15px;
    border-right: 3px solid rgb(0, 123, 255);
    vertical-align: middle;
  }

  #Componenttitle .leftText span {
    font-size: 45px;
    font-weight: 400;
  }
  #Componenttitle .right {
    padding-left: 10px;
    font-size: 25px;
    vertical-align: middle;
  }
</style>
