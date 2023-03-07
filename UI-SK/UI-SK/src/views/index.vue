<template>
  <div class="content">
  </div>
</template>

<script>
  import { getAd,listAd ,getHistHotAd,getRecentAd} from "@/api/home/ad";
  import getkafka from "@/api/home/news"
  import Xiao from "./xxx";

  export default {
    name: "Index",
    data() {
      return {
        adList: [],
        total: 0,
        url: process.env.VUE_APP_BASE_API,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          adTitle: null,
          adType: null,
          adHits: null,
          adKey: null,
          adTeam: null,
          adSponsor: null,
          beginTime: null,
          overTime: null,
          topFlag: null,
          delFlag: null,
        }
      };
    },
    created() {
      this.getList()
    },

    methods:{
      getList() {
        getHistHotAd().then(response=>{
          console.log(1,response)
        })
        getRecentAd().then(response=>{
          console.log(2,response)
        })
        // listAd(this.queryParams).then(response => {
        //   console.log(response)
        //   this.adList = response.rows;
        //   this.total = response.total;
        // });
      },
    }

  };
</script>




