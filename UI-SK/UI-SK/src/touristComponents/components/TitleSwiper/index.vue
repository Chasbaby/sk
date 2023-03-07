<template>
  <div id="TitleSwiper">
    <div class="titleswiper mySwiper">
      <div class="swiper-wrapper">
        <div
          class="swiper-slide"
          v-for="(item, index) in listInfo"
          :key="index"
        >
          <div class="img_div">
            <img :src="item.src" alt="item.title" />
          </div>
        </div>
      </div>
      <div class="swiper-pagination"></div>
    </div>
  </div>
</template>

<script>
  import Swiper from "swiper";
  import "swiper/css/swiper.css";
  export default {
    name: "TitleSwiper",
    data() {
      return {
        listInfo: [
          {
            title: "11",
            src: "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
          },
          {
            title: "11",
            src: "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
          },
          {
            title: "11",
            src: "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
          },
          {
            title: "11",
            src: "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
          },
        ],
      };
    },
    mounted() {
      var titleswiper = new Swiper(".titleswiper", {
        spaceBetween: 30,
        effect: "fade",
        navigation: {
          nextEl: ".swiper-button-next",
          prevEl: ".swiper-button-prev",
        },
        pagination: {
          el: ".swiper-pagination",
          clickable: true,
        },
        autoplay: {
          delay: 5500,
          disableOnInteraction: false,
        },
        loop: true,
      });
    },
  };
</script>

<style scoped>
  #TitleSwiper {
    position: relative;
    margin-bottom: 40px;
  }
  #TitleSwiper .titleswiper {
    /* position: relative; */
    width: 100%;
    height: 400px;
  }

  #TitleSwiper .titleswiper .swiper-slide {
    width: 100%;
    height: 100%;
    text-align: center;
    font-size: 18px;
    background: #fff;

    /* Center slide text vertically */
    display: -webkit-box;
    display: -ms-flexbox;
    display: -webkit-flex;
    display: flex;
    -webkit-box-pack: center;
    -ms-flex-pack: center;
    -webkit-justify-content: center;
    justify-content: center;
    -webkit-box-align: center;
    -ms-flex-align: center;
    -webkit-align-items: center;
    align-items: center;
    background-color: pink;
  }

  #TitleSwiper .titleswiper .swiper-slide .img_div {
    width: 100%;
    height: 100%;
  }
  #TitleSwiper .titleswiper .swiper-slide img {
    display: block;
    width: 100%;
    height: 100%;
    object-fit: cover;
  }
  #TitleSwiper .swiper-pagination {
    position: absolute;
    width: 100%;
    bottom: 0%;
    margin-bottom: 20px;
    /* z-index: ; */
  }

  #TitleSwiper .titleswiper .swiper-pagination-bullet {
    width: 20px;
    height: 20px;
    text-align: center;
    line-height: 20px;
    font-size: 12px;
    color: #000;
    opacity: 1;
    background: rgba(0, 0, 0, 0.2);
  }

  #TitleSwiper .titleswiper .swiper-pagination-bullet-active {
    color: #fff;
    background: #007aff;
  }
</style>
