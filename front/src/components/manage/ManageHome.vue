<template>
  <el-container class="home_container">
    <el-aside>
      <el-menu :default-active="defaultActive"
               background-color="#324057"
               text-color="#F5F5F5"
               router>
        <el-menu-item index="managehome"><i class="el-icon-menu"></i>管理员</el-menu-item>
        <el-submenu index="2">
          <template slot="title"><i class="el-icon-document"></i>图书管理</template>
          <el-menu-item index="addbook">添加图书</el-menu-item>
          <el-menu-item index="modifybook">修改图书</el-menu-item>
        </el-submenu>
        <el-submenu index="3">
          <template slot="title"><i class="el-icon-info"></i>图书记录</template>
          <el-menu-item index="storelog">入库记录</el-menu-item>
          <el-menu-item index="lendbooklog">租借记录</el-menu-item>
        </el-submenu>
      </el-menu>
    </el-aside>
    <el-main>
      <router-view></router-view>
    </el-main>
  </el-container>
</template>
<script>
export default {
  computed: {
    defaultActive: function () {
      return this.$route.path.replace('/', '')
    }
  }
}
</script>
<style lang='less'>
.home_container {
  height: 100%;
}
.el-aside {
  background-color: #324057;
  .el-menu {
    border-right: none; // 设置边框对齐
  }
}
</style>
