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

/* eslint-disable no-new */
new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
