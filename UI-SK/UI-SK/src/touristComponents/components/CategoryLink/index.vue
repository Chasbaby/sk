<template>
  <div id="categorylink">
    <div class="item_box" v-for="category in listInfo" :key="category.id">
      <a href="#" class="category">{{ category.name }}</a>
      <ul>
        <li v-for="item in category.itemInfoList" :key="item.id">
          <span class="time">{{ item.time }}</span>
          <a href="#" class="title">{{ item.title }}</a>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
  export default {
    name: "CategoryLink",
    props: ["listInfo"],
  };
</script>

<style scoped>
  #categorylink {
    display: flex;
    justify-content: space-between;
    margin-bottom: 30px;
    /* background-color: pink; */
  }
  #categorylink a {
    text-decoration: none;
    color: #333;
    transition: 0.3s all ease-in-out;
  }

  #categorylink a:hover {
    color: #007aff;
  }
  #categorylink ul {
    padding: 0;
  }
  #categorylink .category {
    display: block;
    font-size: 20px;
    padding-bottom: 10px;
  }
  #categorylink .item_box {
    width: 350px;
  }
  #categorylink li {
    width: 100%;
    display: block;
    list-style: none;
    border-top: 1px solid #d6d6d6;
  }
  #categorylink li .time,
  #categorylink li .title {
    display: block;
  }

  #categorylink li .time {
    color: #007aff;
    font-size: 13px;
    padding-top: 10px;
    padding-bottom: 10px;
  }
  #categorylink li .title {
    padding-bottom: 15px;
  }
</style>
