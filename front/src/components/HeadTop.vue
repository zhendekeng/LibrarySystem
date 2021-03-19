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
        <el-dropdown-item command="userInfo">个人信息</el-dropdown-item>
        <el-dropdown-item command="signout">退出</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
    <el-dialog title="我的信息"
               :visible.sync="userInfoDialogVisible"
               width="25%"
               top="4vh"
               center>
      <el-form label-position="left"
               :data="userInfo">
        <el-form-item label="账号 :">
          <span>{{userInfo.userName}}</span>
        </el-form-item>
        <el-form-item label="邮箱 :">
          <span>{{userInfo.email}}</span>
        </el-form-item>
        <el-form-item label="昵称 :">
          <span>{{userInfo.userNickname}}</span>
        </el-form-item>
        <el-form-item label="注册时间 :">
          <span>{{userInfo.registerTime | formatDate}}</span>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { formatDate } from '@/assets/js/date'
export default {
  filters: {
    formatDate (time) {
      var date = new Date(time)
      return formatDate(date, 'yyyy-MM-dd hh:mm')
    }
  },
  data () {
    return {
      userInfo: [],
      userInfoDialogVisible: false
    }
  },
  methods: {
    async handleCommand (command) {
      console.log(123)
      if (command == 'userInfo') {
        var userId = window.sessionStorage.getItem('userId')
        const { data: res } = await this.$http.get('userInfo?userId=' + userId)
        if (res.result == 'success') {
          console.log(res.userInfo)
          this.userInfo = res.userInfo
          this.userInfoDialogVisible = true
        } else if (res.result == 'error') {
          this.$message.error('查询个人信息失败')
        } else if (res.result == 'fail') {
          this.$message.error('服务器异常')
        }
      } else if (command == 'signout') {
        window.sessionStorage.clear()
        this.$router.push({
          path: '/login'
        })
      }
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
