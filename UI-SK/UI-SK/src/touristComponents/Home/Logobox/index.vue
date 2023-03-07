<template>
  <div id="logobox">
    <div class="img">
      <img
        src="https://guide.michelin.com/assets/images/home/about-img.jpg"
        alt=""
      />
    </div>
    <div class="text">
      <h1>关于丝路荟萃指南</h1>
      <span>米其林指南的历史与评审员</span>
      <button>关于丝路荟萃指南</button>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style lang="scss" scoped>
#logobox {
  position: relative;
  max-width: 1260px;
  padding: 0px 40px 70px 40px;
  height: 300px;
  margin: 0 auto;
  border: 5px solid #409eff;
  margin-bottom: 150px;
  .img {
    position: absolute;
    width: 300px;
    height: 400px;
    left: 5%;
    // background-color: #409eff;
    top: -20%;
    img {
      display: block;
      width: 100%;
      height: 100%;
      object-fit: cover;
    }
  }
  .text {
    position: absolute;
    left: 37%;
    top: 20%;
    h1 {
      font-size: 40px;
      font-weight: 700;
      font-family: "Noto Serif SC", serif !important;
      color: #191919;
      margin-bottom: 16px;
    }
    span {
      //   display: block;
      font-size: 20px;
      line-height: 1.5;
      color: #757575;
      //   padding: 24px 0;
      font-family: "open_sans", sans-serif !important;
    }
    button {
      display: block;
      width: 384px;
      height: 54px;
      border: none;
      background-color: #409eff;
      border-radius: 5px;
      margin-top: 20px;
      color: #fff;
      font-size: 16px;
      transition: all 0.2s ease;
      cursor: pointer;
      &:hover {
        background-color: #3481cd;
      }
    }
  }
}
</style>