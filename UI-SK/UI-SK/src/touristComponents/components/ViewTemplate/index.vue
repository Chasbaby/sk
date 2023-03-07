<template>
  <div id="ViewList">
    <div v-if="type === 'Bulletins'">
      <!-- 判断传输过来的数据是哪一个部分的？ -->
      <ul>
        <li v-for="item in listInfo" :key="item.noticeId">
          <div class="total_div">
            <div class="time_div">
              <span class="day">{{ item.createTime.slice(7) }}</span>
              <span class="year_month">{{ item.createTime.slice(0, 6) }}</span>
            </div>
            <div class="img_div">
              <img :src="url+item.noticeImage" :alt="item.noticeTitle" />
            </div>
            <div class="info_div">
              <span class="title">
                <span class="link" @click="gotoLink(item.noticeId)">{{
                  item.noticeTitle
                }}</span></span
              >
              <span class="text">{{ item.noticeIntroduction }}</span>
            </div>
          </div>
        </li>
      </ul>
      <a href="#" class="globalview">
        <i class="fa fa-eye" aria-hidden="true"></i>
        总览</a
      >
    </div>
    <div v-else-if="type === 'Attractions'">
      <!-- 判断传输过来的数据是哪一个部分的？ -->
      <ul>
        <li v-for="item in listInfo" :key="item.sightsId">
          <div class="total_div">
            <div class="time_div">
              <span class="day">{{ parseTime(item.createTime,'{d}') }}</span>
              <span class="year_month">{{  parseTime(item.createTime,'{y}-{m}')  }}</span>
            </div>
            <div class="img_div">
              <img :src="url+item.sightsImage" :alt="item.sightsName" />
            </div>
            <div class="info_div">

              <router-link :to="'/frontHome/attractions/attraction/'+item.sightsId">
                <span class="title">
                  <span class="link">{{
                    item.sightsName
                  }}</span>
                </span
                >
              </router-link>
<!--              <span class="title">-->
<!--                <span class="link" @click="gotoLink(item.sightsId)">{{-->
<!--                  item.sightsName-->
<!--                }}</span></span-->
<!--              >-->
              <span class="text" v-html="item.sightsDetail"></span>
            </div>
          </div>
        </li>
      </ul>
      <a href="#" class="globalview">
        <i class="fa fa-eye" aria-hidden="true"></i>
        总览</a
      >
    </div>
    <div v-else-if="type === 'News'">
      <!-- 判断传输过来的数据是哪一个部分的？ -->
      <ul>
        <li v-for="item in listInfo" :key="item.newsId">
          <div class="total_div">
            <div class="time_div">
              <span class="day">{{ parseTime(item.createTime,'{d}') }}</span>
              <span class="year_month">{{  parseTime(item.createTime,'{y}-{m}')  }}</span>
            </div>
            <div class="img_div">
              <img :src="url+item.imageId" :alt="item.newsTitle" />
            </div>
            <div class="info_div">
              <span class="title">
                <router-link :to="'/frontHome/new/NewsPage/'+item.newsId">
                  <span class="link">{{item.newsTitle}}</span>
                </router-link>
<!--                <span class="link">{{-->
<!--                  item.newsTitle-->
<!--                }}</span>-->
              </span>
              <span class="text" v-html="item.newsContent"></span>
            </div>
          </div>
        </li>
      </ul>
      <a href="#" class="globalview">
        <i class="fa fa-eye" aria-hidden="true"></i>
        总览</a
      >
    </div>
  </div>
</template>

<script>
  export default {
    name: "ViewTemplate",
    data(){
      return{
        url:process.env.VUE_APP_BASE_API,
      }
    },
    props: ["listInfo", "type"],
    methods: {
      gotoLink(value) {
        if (this.type === "News") {
          this.$router.push({ path: "/NewsPage"});
        } else if (this.type === "Attractions") {
          this.$router.push({ path: "/Attractionspage", query: { id: value } });
        } else if (this.type === "Bulletins") {
          this.$router.push({ path: "/Bulletinspage", query: { id: value } });
        }
      },
    },
  };
</script>

<style scoped>
  #ViewList .link,
  #ViewList a {
    text-decoration: none;
    color: #333;
    transition: 0.3s all ease-in-out;
  }

  #ViewList {
    position: relative;
    width: 750px;
    margin-top: 30px;
    margin-bottom: 100px;
    margin-left: 100px;
  }
  #ViewList ul {
    width: 100%;
    height: 100%;
  }
  #ViewList ul li {
    display: block;
    width: 100%;
    height: 20%;
    list-style: none;
    margin-bottom: 20px;
  }
  #ViewList ul li a {
    display: block;
  }
  #ViewList ul li .link:hover {
    cursor: pointer;
    color: #007aff;
  }

  #ViewList ul li:hover img {
    transform: scale(1.2);
  }

  #ViewList ul li .total_div {
    display: flex;
    position: relative;
  }

  #ViewList ul li .img_div {
    display: inline-block;
    position: relative;
    width: 340px;
    height: 200px;
    overflow: hidden;
  }

  #ViewList ul li .time_div {
    position: absolute;
    bottom: 25px;
    left: -15px;
    width: 70px;
    height: 55px;
    /* background-color: pink; */
    background-image: linear-gradient(80deg, #d1e8ff, #35b6f7);
    z-index: 3;
  }
  #ViewList ul li .time_div .day,
  #ViewList ul li .time_div .year_month {
    display: block;
    text-align: center;
  }

  #ViewList ul li .time_div .day {
    font-size: 24px;
  }

  #ViewList ul li .time_div .year_month {
    font-size: 14px;
  }

  #ViewList ul li .img_div img {
    display: block;
    width: 100%;
    height: 100%;
    object-fit: cover;
    background: no-repeat;
    transition: 0.5s all ease-in-out;
  }

  #ViewList ul li .info_div {
    display: inline-block;
    width: 360px;
    height: 200px;
    margin-left: 15px;
    /* background-color: pink; */
  }

  #ViewList ul li .info_div .title {
    display: block;
    padding-left: 15px;
    padding-top: 15px;
    font-weight: 700;
    font-size: 17px;
  }

  #ViewList ul li .info_div .text {
    display: block;
    height: 100px;
    font-size: 13px;
    padding-left: 15px;
    padding-top: 20px;
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 6;
  }
  #ViewList .globalview {
    display: block;
    position: absolute;
    right: 30px;
  }
  #ViewList .globalview:hover {
    color: #007aff;
  }
</style>
