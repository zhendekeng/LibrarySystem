<template>
  <div>
    <head-top></head-top>
    <p class="explain_text">图书租借管理系统</p>
    <p class="explain_text">第一个springboot+vue项目</p>
  </div>
</template>
<script>
import HeadTop from './HeadTop'
export default {
  components: {
    HeadTop
  }
}
</script>
<style lang="less" scoped>
.explain_text {
  margin-top: 20px;
  text-align: center;
  font-size: 40px;
  color: #333;
}
</style>
