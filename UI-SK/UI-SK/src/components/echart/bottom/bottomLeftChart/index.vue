<template>
  <div>
    <Chart :cdata="cdata"/>
  </div>

</template>

<script>
import Chart from './chart'
export default {
  data(){
    return{
      cdata:{
        category: [
          "池池",
          "季季"
        ],
        lineData: [
          520,
          521
        ],
        rateData: [
          110,
          119
        ],
        barData: [
          15,
          16
        ]
      }
    };
  },
  components:{
    Chart,
  },
  mounted() {
    this.setData();
  },
  methods:{
    setData(){
      for (let i = 0; i< this.cdata.barData.length -1;i++){
        let rate = this.cdata.barData[i] / this.cdata.lineData[i];
        this.cdata.rateData.push(rate.toFixed())
      }
    }
  }

}
</script>
