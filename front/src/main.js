// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import './assets/css/global.css'
// 引入外部的iconfont
import './assets/font/iconfont.css'
// 引入axios
import axios from 'axios'
Vue.config.productionTip = false
Vue.use(ElementUI)
// 挂载axios
Vue.prototype.$http = axios
// 设置访问根路径
axios.defaults.baseURL = 'http://localhost:9000'
// 配置axios请求拦截器实现发送请求时携带token
axios.interceptors.request.use((config) => {
  config.headers.Authorization = window.sessionStorage.getItem('token')
  return config
})

/* 响应拦截器 */
axios.interceptors.response.use(function (response) { // ①10010 token过期（30天） ②10011 token无效
  // console.log(response.headers.token)
  if (response.data.msg == 'token过期') {
    window.sessionStorage.removeItem('token') // 删除已经失效或过期的token（不删除也可以，因为登录后覆盖）

    router.replace({

      path: '/login' // 到登录页重新获取token

    })
  } else if (response.headers.token) { // 判断token是否存在，如果存在说明需要更新token
    window.sessionStorage.setItem('token', JSON.stringify(response.headers.token)) // 覆盖原来的token(默认一天刷新一次)
  }

  return response
}, function (error) {
  return Promise.reject(error)
})

/* eslint-disable no-new */
new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
