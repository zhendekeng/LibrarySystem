import Vue from 'vue'
import VueRouter from 'vue-router'
// 引入Login组件
import Login from '@/components/Login'
// 引入register组件
import Register from '@/components/Register'
import Explain from '@/components/Explain'
import UserMain from '@/components/userlendbook/UserMain'
import LendBook from '@/components/userlendbook/LendBook'
import ReturnBook from '@/components/userlendbook/ReturnBook'
import Lendlog from '@/components/userlendbook/Lendlog'
import UserHome from '@/components/userlendbook/UserHome'
import ModifyInfo from '@/components/modify/ModifyInfo'
import ModifyPassword from '@/components/modify/ModifyPassword'
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    component: Login
  },
  {
    path: '/register',
    component: Register
  },
  {
    path: '/userhome',
    component: UserHome,
    children: [
      {
        path: '',
        component: UserMain,
        meta: []
      },
      {
        path: '/lendbook',
        component: LendBook,
        meta: ['图书', '图书租借']
      },
      {
        path: '/returnbook',
        component: ReturnBook,
        meta: ['图书', '图书归还']
      },
      {
        path: '/lendlog',
        component: Lendlog,
        meta: ['图书', '租借记录']
      },
      {
        path: '/modifyinfo',
        component: ModifyInfo,
        meta: ['修改', '修改信息']
      },
      {
        path: '/modifypassword',
        component: ModifyPassword,
        meta: ['修改', '修改密码']
      },
      {
        path: '/explain',
        component: Explain,
        meta: ['说明', '说明']
      }
    ]
  }
]

const router = new VueRouter({
  routes
})

export default router// 暴露出去
