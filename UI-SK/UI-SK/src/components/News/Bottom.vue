<template>
  <div class="bottom">
    <div class="relatedLink">
      <ul>
        <span>链接</span>
        <li><a href="">关于我们</a></li>
        <li><a href="">常见问题</a></li>
        <li><a href="">意见反馈</a></li>
        <li><a href="">更新日志</a></li>
      </ul>
    </div>
    <div class="community">
      <ul>
        <span>社区</span>
        <li><a href="">贡献指南</a></li>
        <li><a href="">常见问题</a></li>
        <li><a href="">常见问题</a></li>
        <li><a href="">常见问题</a></li>
      </ul>
    </div>
    <div class="logobox">
      <a href="#">Silk Road Collection</a>
      <span>© 2022 SilkRoad Collection.CN All Rights Reserved</span>
    </div>
  </div>
</template>

<script>
export default {
  name: "Bottom",
};
</script>

<style scoped>
a {
  text-decoration: none;
  color: #333333;
  transition: 0.3s all ease-in-out;
}
a:hover {
  color: rgb(0, 123, 255);
}
.bottom {
  height: 200px;
  width: 100%;
  padding-bottom: 45px;
}
.bottom .relatedLink {
  margin-right: 100px;
}
.bottom .relatedLink,
.bottom .community {
  display: inline-block;
}
.bottom .relatedLink ul li,
.bottom .community ul li {
  padding-bottom: 15px;
  font-size: 14px;
  list-style: none;
  color: #333333;
  font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB",
    "Microsoft YaHei", SimSun, sans-serif;
}
.bottom .relatedLink ul span,
.bottom .community ul span {
  display: block;
  font-size: 20px;
  font-weight: 700;
  color: #333333;
  margin-bottom: 15px;
}
.bottom .logobox {
  float: right;
  margin-top: 16px;
}
.bottom .logobox a {
  text-align: right;
  font-size: 25px;
  display: block;
  margin-bottom: 15px;
}
</style>