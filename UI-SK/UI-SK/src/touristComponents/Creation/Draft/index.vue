<template>
  <div id="draft">
    <h3 v-text="'草稿箱 ' + '(' + draftList.length + ')'"></h3>
    <draft-list :List="draftList"></draft-list>
  </div>
</template>

<script>
  import draftList from '../Draft/index'
  export default {
    data() {
      return {
        draftList: [
          {
            name: "虽然杂鲷,但是生命之舞！",
            src: "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
            editTime: "23-01-11 13:16:22",
            view: 10,
            thumbsup: 2,
            favourite: 0,
            comment: 1,
          },
          {
            name: "浪花一朵朵~",
            src: "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
            editTime: "22-04-03 22:21:32",
            view: 10,
            thumbsup: 2,
            favourite: 0,
            comment: 1,
          },
          {
            name: "/小缸三湖慈鲷/",
            src: "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
            editTime: "22-01-27 00:41:28",
            view: 10,
            thumbsup: 2,
            favourite: 0,
            comment: 1,
          },
          {
            name: "夜半修罗场",
            src: "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
            editTime: "22-01-16 23:44:38",
            view: 10,
            thumbsup: 2,
            favourite: 0,
            comment: 1,
          },
        ],
      };
    },
    components: { draftList },
  };
</script>

<style lang="scss" scoped>
  $backcolor: #ffffff;
  $bluecolor: #00aeec;
  #draft {
    background-color: $backcolor;
    padding: 20px 40px 20px 40px;
    h3 {
      color: $bluecolor;
    }
  }
</style>
