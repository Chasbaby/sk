<template>
  <div>
    <svg-icon icon-class="github" @click="goto" />
  </div>
</template>

<script>
  export default {
    data() {
      return {
        url: 'https://gitee.com/y_project/RuoYi-Vue'
      }
    },
    methods: {
      goto() {
        window.open(this.url)
      }
    }
  }
</script>
