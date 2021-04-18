<template>
  <div class="login_container">
    <!-- 登录区域-->
    <div class="login_box">
      <!-- logo区域-->
      <div class="avatar_box">
        <img src="../assets/logo.png"
             alt />
      </div>
      <!-- 表单区域-->
      <el-form ref="loginFormRef"
               :model="loginForm"
               :rules="loginRules"
               class="login_form"
               label-width="0">
        <!--用户名-->
        <el-form-item prop="username">
          <el-input v-model="loginForm.userName"
                    prefix-icon="el-icon-user"></el-input>
        </el-form-item>
        <!--密码-->
        <el-form-item prop="password">
          <el-input v-model="loginForm.password"
                    prefix-icon="iconfont icon-icon-test"
                    type="password"></el-input>
        </el-form-item>
        <!--按钮-->
        <el-form-item class="btns">
          <el-button type="primary"
                     @click="login()">登录</el-button>
          <el-button type="info"
                     @click="register()">注册</el-button>
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
      loginForm: {
        userName: '',
        password: ''
      },
      // 数据校验
      loginRules: {
        userName: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          {
            pattern: /[0-9a-zA-Z]{4,12}/,
            message: '账号必须是由4-12位数字和字母组合',
            trigger: 'blur'
          }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    // 注册
    register () {
      this.$router.push({
        path: '/register'
      })
    },
    // 登录
    login () {
      this.$refs.loginFormRef.validate(async valid => {
        if (!valid) return
        const { data: res } = await this.$http.get('login',
          {
            params: {
              userName: this.loginForm.userName,
              password: this.loginForm.password
            }
          })
        if (res.result == 'success') {
          this.$message.success('登录成功')
          window.sessionStorage.setItem('token', JSON.stringify(res.token)) // 存储userId
          this.$router.push({
            path: '/userhome'
          })
        } else if (res.result == 'noexist') {
          this.$message.error('用户名错误')
        } else if (res.result == 'incorrect') {
          this.$message.error('密码错误')
        } else if (res.result == 'fail') {
          this.$message.error('服务器错误')
        }
      })
    }
  }
}
</script>
<style lang = "less" scoped>
//根节点样式
.login_container {
  background-color: #2b4b6b;
  height: 100%;
}
.login_box {
  width: 500px;
  height: 300px;
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
.login_form {
  position: absolute;
  bottom: 0%;
  width: 100%;
  padding: 0 10px;
  box-sizing: border-box;
}
</style>
