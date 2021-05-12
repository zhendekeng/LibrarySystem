<template>
  <el-container class="home_container">
    <el-aside>
      <el-menu :default-active="defaultActive"
               background-color="#324057"
               text-color="#F5F5F5"
               router>
        <el-menu-item index="userhome"><i class="el-icon-menu"></i>首页</el-menu-item>
        <el-submenu index="2">
          <template slot="title"><i class="el-icon-document"></i>图书</template>
          <el-menu-item index="lendbook">图书租借</el-menu-item>
          <el-menu-item index="returnbook">图书归还</el-menu-item>
          <el-menu-item index="lendlog">租借记录</el-menu-item>
        </el-submenu>
        <el-submenu index="3">
          <template slot="title"><i class="el-icon-edit"></i>修改</template>
          <el-menu-item index="modifyinfo">修改信息</el-menu-item>
          <el-menu-item index="modifypassword">修改密码</el-menu-item>
        </el-submenu>
        <el-submenu index="4">
          <template slot="title"><i class="el-icon-warning"></i>说明</template>
          <el-menu-item index="explain">说明</el-menu-item>
        </el-submenu>
        <!-- <el-submenu index="5">
          <template slot="title"><i class="el-icon-warning"></i>作业</template>
          <el-menu-item index="test">2</el-menu-item>
        </el-submenu> -->
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
