import Vue from 'vue'
import VueRouter from 'vue-router'
// 引入Login组件
import Login from '@/components/Login'
// 引入register组件
import Register from '@/components/Register'
import Explain from '@/components/Explain'
// import Test from '@/components/Test'

// 用户组件
import LendBook from '@/components/userlendbook/LendBook'
import ReturnBook from '@/components/userlendbook/ReturnBook'
import Lendlog from '@/components/userlendbook/Lendlog'
import UserHome from '@/components/userlendbook/UserHome'
import ModifyInfo from '@/components/modify/ModifyInfo'
import ModifyPassword from '@/components/modify/ModifyPassword'
import UserMain from '@/components/userlendbook/UserMain'

// 管理员组件
import ManageHome from '@/components/manage/ManageHome'
import AddBook from '@/components/manage/AddBook'
import ModifyBook from '@/components/modify/ModifyBook'
import ManageMain from '@/components/manage/ManageMain'
import LendBookLog from '@/components/manage/LendBookLog'
import Storelog from '@/components/manage/Storelog'
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
      /*
      这个的作用是使/userhome默认进行 CommonMain这个组件
      */
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
      // {
      //   path: '/test',
      //   component: Test,
      //   meta: ['作业', '作业2']
      // }
    ]
  },
  {
    path: '/managehome',
    component: ManageHome,
    children: [
      {
        path: '',
        component: ManageMain,
        meta: []
      },
      {
        path: '/addbook',
        component: AddBook,
        meta: ['图书管理', '添加图书']
      },
      {
        path: '/modifybook',
        component: ModifyBook,
        meta: ['图书管理', '修改图书']
      },
      {
        path: '/storelog',
        component: Storelog,
        meta: ['图书管理', '入库记录']
      },
      {
        path: '/lendbooklog',
        component: LendBookLog,
        meta: ['图书管理', '租借记录']
      }
    ]
  }
]

const router = new VueRouter({
  routes
})
// 挂载路由导航守卫
router.beforeEach((to, from, next) => {
  // 通过to.path获取请求的路径，判断请求的是否为登录页
  if (to.path == '/login') return next()
  if (to.path == '/register') return next()
  // 如果请求的不是登录页则判断是否存在token,不存在就跳至登录页
  const token = window.sessionStorage.getItem('token')
  if (!token) return next('/login')
  next()
})

export default router// 暴露出去
