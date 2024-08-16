import { createRouter, createWebHistory } from 'vue-router'
import LoginView from '../views/LoginView.vue'
import { userStore } from '@/stores/userStore'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'login',
      component: LoginView
    },
    {
      path:'/project/:projectId',
      name:'main',
      props: true,
      component: () => import('../views/main/ProjectMainView.vue'),
      children:[
        {
          
          path: '/project/:projectId/tasks',
          name: 'tasks',
          props: true,
          component: () => import('../views/main/TasksView.vue')
        
      },
        {
          
            path: '/project/:projectId/about',
            name: 'about',
            props: true,
            component: () => import('../views/main/AboutView.vue')
          
        }
      ]
    },
    {
      path:'/sys',
      redirect: "/sys/project",
      name:'sys',
      component: () => import('../views/sysSetting/SysSettingView.vue'),
      children:[
          {
            path: '/sys/project',
            name: 'sys_project',
            props: true,
            component: () => import('../views/sysSetting/ProjectView.vue')
          },
          {
            path: '/sys/user',
            name: 'sys_user',
            props: true,
            component: () => import('../views/sysSetting/user/UserView.vue')
          },
          {
            path: '/sys/userconnector',
            name: 'sys_userconnector',
            props: true,
            component: () => import('../views/sysSetting/user/UserConnectorView.vue')
          }
      ]
    }
  ]
})

router.beforeEach((to,from,next)=>{

  if(to.name == 'login'){
    next()
  }
  let user  = localStorage.getItem('user');
  let token = localStorage.getItem('token')
  if( token == null ||  user == null ){
    next({ name: 'login',query: { redirect: to.fullPath }})
  }

  next()
       
 // let username = localStorage.getItem('username');//这里根据自己项目的实际情况添加是否登录的判断条件
 // username= '111'
  // if (to.name !== 'login' && !username) next({ name: 'login',query: { redirect: to.fullPath }})
  // else next()
})



export default router
