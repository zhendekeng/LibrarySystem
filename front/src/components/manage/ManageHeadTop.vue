<template>
  <div class="header_container">

    <el-breadcrumb separator="/">
      <el-breadcrumb-item>首页</el-breadcrumb-item>
      <el-breadcrumb-item v-for="(item, index) in $route.meta"
                          :key="index">{{item}}</el-breadcrumb-item>
    </el-breadcrumb>
    <el-dropdown @command="handleCommand">
      <img src="//elm.cangdu.org/img/default.jpg"
           class="avator">
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item>退出</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
export default {
  methods: {
    handleCommand () {
      console.log(123)
      window.sessionStorage.clear()
      this.$router.push({
        path: '/login'
      })
    }
  }
}
</script>

<style lang="less">
.header_container {
  height: 60px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding-left: 20px;
}
.avator {
  width: 36px;
  height: 36px;
  border-radius: 50%;
  margin-right: 37px;
}
.el-dropdown-menu__item {
  text-align: center;
}
</style>
