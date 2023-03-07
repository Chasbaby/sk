<template>
  <div id="convention">
    <el-tabs
      v-model="activeName"
      @tab-click="handleClick"
      tab-position="left"
      type="border-card"
    >
      <el-tab-pane
                   v-for="item in conventionList"
                   :key="item.conventionId"
                   :label="item.conventionLabel"
                   :name="item.conventionName"
                   :disabled="item.conventionDisabled==='Y'"
                   :closable="item.conventionClosable==='Y'"
                   :lazy="item.conventionLazy==='Y'"
                   v-html="item.conventionContent"
      >
        <span slot="label"><svg-icon :icon-class="item.conventionIcon" />{{item.conventionLabel}}</span>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
  import {getAllConvention} from "@/api/use/convention";
  export default {
    data() {
      return {
        activeName: "first",
        conventionList:[]
      };
    },
    methods: {
      handleClick(tab, event) {
        console.log(tab, event);
      },
      getConventionList(){
        getAllConvention().then(response=>{
          this.conventionList = response.data
        })
      }
    },
    mounted() {
      this.getConventionList()
    }
  };
</script>

<style lang="scss" scoped>
  $backcolor: #ffffff;
  $textcolor: #00a1d6;
  #convention {
    background-color: $backcolor;
    padding: 20px 40px 20px 40px;
    .img {
      width: 350px;
      height: 250px;
      margin: 0 auto;
      padding: 20px 0px 20px 0px;
      img {
        display: block;
        width: 100%;
        height: 100%;
        object-fit: cover;
      }
    }
    .content1 {
      height: 400px;
      span {
        display: block;
      }
      .title {
        font-size: 16px;
        font-weight: 500;
        padding-bottom: 17px;
        color: $textcolor;
      }
      .span {
        font-size: 14px;
        color: #222;
      }
    }
    v::deep .el-tabs__content{
      padding: 0px 10px 0px 10px;
    }
  }
</style>
