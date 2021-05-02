<template>
  <div>
    <head-top></head-top>
    <el-form :model="formData"
             ref="modifyRef"
             :rules="modifyRules"
             class="demo_form">
      <el-form-item label="账号">
        <span>{{userInfo.userName}}</span>
      </el-form-item>
      <el-form-item label="邮箱"
                    prop="email">
        <el-input v-model="formData.email"
                  style="width:300px"
                  :placeholder="userInfo.email"></el-input>
      </el-form-item>
      <el-form-item label="昵称"
                    prop="nickName">
        <el-input v-model="formData.nickName"
                  style="width:300px"
                  :placeholder="userInfo.userNickname"></el-input>
      </el-form-item>
      <el-form-item label="注册时间">
        <span>{{userInfo.registerTime | formatDate}}</span>
      </el-form-item>
      <el-form-item>
        <el-button type="primary"
                   @click="submitForm()">修改</el-button>
        <el-button @click="resetForm()">重置</el-button>
      </el-form-item>
    </el-form>
    <el-dialog title="是否修改"
               :visible.sync="modifyDialogVisible"
               width="25%"
               top="4vh"
               :before-close="handleClose"
               center>
      <el-form label-position="left"
               :data="formData">
        <el-form-item label="邮箱 :">
          <span>{{userInfo.email}}</span>
        </el-form-item>
        <el-form-item label="昵称 :">
          <span>{{userInfo.userNickname}}</span>
        </el-form-item>
        <el-form-item style="color:red">
          <span>将更改为以下数据</span>
        </el-form-item>
        <el-form-item label="邮箱 :">
          <span>{{formData.email}}</span>
        </el-form-item>
        <el-form-item label="昵称 :">
          <span>{{formData.nickName}}</span>
        </el-form-item>
      </el-form>
      <span slot="footer"
            class="dialog-footer">
        <el-button @click="closeDialogVisible()">取 消</el-button>
        <el-button type="primary"
                   @click="modifyUserInfo()">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
import HeadTop from '../HeadTop'
import { formatDate } from '@/assets/js/date'
export default {
  filters: {
    formatDate (time) {
      var date = new Date(time)
      return formatDate(date, 'yyyy-MM-dd hh:mm')
    }
  },
  components: {
    HeadTop
  },
  data () {
    return {
      modifyDialogVisible: false,
      userInfo: [],
      formData: {
        email: '',
        nickName: ''
      },
      modifyRules: {
        nickName: [
          {
            pattern: /^[\u4e00-\u9fa5a-zA-Z0-9]{2,9}$/,
            message: '只能输入2~9位中文、数字和字母',
            trigger: 'blur'
          }
        ],
        email: [
          {
            type: 'email', // 要检验的类型（number，email，date等）
            message: '请输入正确的邮箱地址',
            trigger: ['blur', 'change']
          }
        ]
      }
    }
  },
  created () {
    this.getUserInfo()
  },
  methods: {
    async getUserInfo () {
      const { data: res } = await this.$http.get('userInfo')
      if (res.message == 'success') {
        this.userInfo = res.data.userInfo
      } else {
        this.$message.error('服务器异常')
      }
    },
    async modifyUserInfo () {
      const { data: res } = await this.$http.put('modifyUserInfo?email=' +
        this.formData.email + '&nickName=' + this.formData.nickName)
      if (res.message == 'success') {
        this.$message.success('更新个人信息成功')
      } else {
        this.$message.error('服务器异常')
      }
      this.getUserInfo()
      this.modifyDialogVisible = false
      this.resetForm()
    },
    handleClose (done) {
      done()
      this.resetForm()
    },
    closeDialogVisible () {
      this.modifyDialogVisible = false
      this.resetForm()
    },
    submitForm () {
      this.$refs['modifyRef'].validate((valid) => {
        if (valid) {
          if (this.formData.nickName == '' && this.formData.email == '') {
            this.$message.error('请输入要修改的信息')
          } else {
            if (this.formData.nickName == '') {
              this.formData.nickName = this.userInfo.userNickname
            } else if (this.formData.email == '') {
              this.formData.email = this.userInfo.email
            }
            this.modifyDialogVisible = true
          }
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm () {
      console.log(122)
      this.$refs['modifyRef'].resetFields()
    }
  }
}
</script>
<style lang="less">
</style>
