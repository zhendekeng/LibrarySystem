<template>
  <div class="register_container">
    <!-- 登录区域-->
    <div class="register_box">
      <!-- logo区域-->
      <div class="avatar_box">
        <img src="../assets/logo.png"
             alt />
      </div>
      <!-- 表单区域-->
      <el-form ref="registerFormRef"
               :model="registerForm"
               :rules="registerRules"
               class="register_form"
               label-width="0">
        <!--用户名-->
        <el-form-item prop="accountName">
          <el-input v-model="registerForm.accountName"
                    placeholder="用户名"
                    prefix-icon="el-icon-user"></el-input>
        </el-form-item>
        <!--昵称-->
        <el-form-item prop="userFullName">
          <el-input v-model="registerForm.userFullName"
                    prefix-icon="el-icon-s-custom"
                    placeholder="昵称"></el-input>
        </el-form-item>
        <!--密码-->
        <el-form-item prop="accountPass">
          <el-input v-model="registerForm.accountPass"
                    placeholder="密码"
                    prefix-icon="iconfont icon-icon-test"
                    type="password"></el-input>
        </el-form-item>
        <!--邮箱-->
        <el-form-item prop="userEmail">
          <el-input v-model="registerForm.userEmail"
                    placeholder="邮箱"
                    prefix-icon="el-icon-s-promotion"
                    type="email"></el-input>
        </el-form-item>
        <!--按钮-->
        <el-form-item class="btns">
          <el-button type="primary"
                     @click="returnLogin()">返回登录</el-button>
          <el-button type="success"
                     @click="register()">注册</el-button>
          <el-button type="info"
                     @click="resetForm()">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>
<script>
export default {
  data () {
    return {
      // 登录数据
      registerForm: {
        accountName: '',
        userFullName: '',
        accountPass: '',
        userEmail: '',
        accountRole: 2
      },
      // 数据校验
      registerRules: {
        accountName: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          {
            pattern: /[0-9a-zA-Z]{4,12}/,
            message: '账号必须是由4-12位数字和字母组合',
            trigger: 'blur'
          }
        ],
        userFullName: [
          { required: true, message: '请输入用户昵称', trigger: 'blur' },
          {
            pattern: /^[\u4e00-\u9fa5a-zA-Z0-9]{2,9}$/,
            message: '只能输入2~9位中文、数字和字母',
            trigger: 'blur'
          }
        ],
        accountPass: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur' }
        ],
        userEmail: [
          {
            type: 'email', // 要检验的类型（number，email，date等）
            message: '请输入正确的邮箱地址',
            trigger: ['blur', 'change']
          }
        ]
      }
    }
  },
  methods: {
    // 注册
    register () {
      this.$refs.registerFormRef.validate(async valid => {
        if (!valid) return
        const { data: res } = await this.$http.post('register', this.registerForm)
        if (res.message == 'success') {
          this.$message.success('注册成功')
          this.$router.push({
            path: '/login'
          })
        } else if (res.message == 'userExist') {
          this.$message.error('用户名已存在')
        } else if (res.message == 'fail') {
          this.$message.error('服务器出错')
        }
      })
    },
    // 返回登录
    returnLogin () {
      this.$router.push({
        path: '/login'
      })
    },
    resetForm () {
      this.$refs['registerFormRef'].resetFields()
    }
  }
}
</script>
<style lang = "less" scoped>
//根节点样式
.register_container {
  background-color: #2b4b6b;
  height: 100%;
}
.register_box {
  width: 500px;
  height: 430px;
  background-color: #fff;
  // border-radius: 3px;
  position: absolute;
  left: 50%;
  top: 50%;
  // 根据自己的图片大小居中
  transform: translate(-50%, -50%);
  .avatar_box {
    width: 130px;
    height: 130px;
    border: 1px solid #eee;
    border-radius: 50%;
    padding: 10px;
    box-shadow: 0 0 10px #ddd;
    position: absolute;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: #0ee;
    img {
      width: 100%;
      height: 100%;
      border-radius: 50%;
      background-color: #eee;
    }
  }
}
.btns {
  display: flex;
  justify-content: flex-end;
}
.register_form {
  position: absolute;
  bottom: 0%;
  width: 100%;
  padding: 0 10px;
  box-sizing: border-box;
}
</style>
