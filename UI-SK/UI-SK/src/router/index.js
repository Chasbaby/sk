import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'
import touristLayout from '@/touristLayout'

/**
 * Note: 路由配置项
 *
 * hidden: true                     // 当设置 true 的时候该路由不会再侧边栏出现 如401，login等页面，或者如一些编辑页面/edit/1
 * alwaysShow: true                 // 当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式--如组件页面
 *                                  // 只有一个时，会将那个子路由当做根路由显示在侧边栏--如引导页面
 *                                  // 若你想不管路由下面的 children 声明的个数都显示你的根路由
 *                                  // 你可以设置 alwaysShow: true，这样它就会忽略之前定义的规则，一直显示根路由
 * redirect: noRedirect             // 当设置 noRedirect 的时候该路由在面包屑导航中不可被点击
 * name:'router-name'               // 设定路由的名字，一定要填写不然使用<keep-alive>时会出现各种问题
 * query: '{"id": 1, "name": "ry"}' // 访问路由的默认传递参数
 * meta : {
    noCache: true                   // 如果设置为true，则不会被 <keep-alive> 缓存(默认 false)
    title: 'title'                  // 设置该路由在侧边栏和面包屑中展示的名字
    icon: 'svg-name'                // 设置该路由的图标，对应路径src/assets/icons/svg
    breadcrumb: false               // 如果设置为false，则不会在breadcrumb面包屑中显示
    activeMenu: '/system/user'      // 当路由设置了该属性，则会高亮相对应的侧边栏。
  }
 */

// 公共路由
export const constantRoutes = [
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path(.*)',
        component: (resolve) => require(['@/views/redirect'], resolve)
      }
    ]
  },

  // 下面四个都是 没有公共组件的单页面哦
  {
    path: '/login',
    component: (resolve) => require(['@/views/login'], resolve),
    hidden: true
  },

  {
    path: '/register',
    component: (resolve) => require(['@/views/register'], resolve),
    hidden: true
  },

  {
    path: '/404',
    component: (resolve) => require(['@/views/error/404'], resolve),
    hidden: true
  },

  {
    path: '/401',
    component: (resolve) => require(['@/views/error/401'], resolve),
    hidden: true
  },

  // 当输入路径不存在时，我们可以设置路径跳转到主页面。
  {
    path: '',
    component: Layout,
    redirect: 'index', //当路径不存在时  重定向到 首页 但是这是子路由呀
    children: [
      {
        path: 'index',// 为什么这是 index
        component: (resolve) => require(['@/views/index'], resolve),
        name: 'Index',
        meta: { title: '首页', icon: 'dashboard', affix: true }
      }
    ]
  },

  {
    path: '/user',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',  // 当设置 noRedirect 的时候该路由在面包屑导航中不可被点击
    children: [
      {
        path: 'profile',
        component: (resolve) => require(['@/views/system/user/profile/index'], resolve),
        name: 'Profile',
        meta: { title: '个人中心', icon: 'user' }
      }
    ]
  },

  {
    path: '/system/user-auth',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'role/:userId(\\d+)',
        component: (resolve) => require(['@/views/system/user/authRole'], resolve),
        name: 'AuthRole',
        meta: { title: '分配角色', activeMenu: '/system/user' }
      }
    ]
  },

  {
    path: '/system/role-auth',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'user/:roleId(\\d+)',
        component: (resolve) => require(['@/views/system/role/authUser'], resolve),
        name: 'AuthUser',
        meta: { title: '分配用户', activeMenu: '/system/role' }
      }
    ]
  },

  {
    path: '/system/dict-data',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'index/:dictId(\\d+)',
        component: (resolve) => require(['@/views/system/dict/data'], resolve),
        name: 'Data',
        meta: { title: '字典数据', activeMenu: '/system/dict' }
      }
    ]
  },

  {
    path: '/monitor/job-log',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'index',
        component: (resolve) => require(['@/views/monitor/job/log'], resolve),
        name: 'JobLog',
        meta: { title: '调度日志', activeMenu: '/monitor/job' }
      }
    ]
  },

  {
    path: '/tool/gen-edit',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'index',
        component: (resolve) => require(['@/views/tool/gen/editTable'], resolve),
        name: 'GenEdit',
        meta: { title: '修改生成配置', activeMenu: '/tool/gen' }
      }
    ]
  },

  //当路径为 /frontHome 的时候 重定向为 '/frontHome/home'

  {
    path: '/frontHome',
    component: touristLayout,
    hidden: true,
    redirect: '/frontHome/home',
    children: [
      {//主页
        path: 'home',
        component: (resolve) => require(['@/touristComponents/Home/index'], resolve),
        name: 'Home',
        meta: { title: '主页' }
      },
      {
        path: 'new',
        component: (resolve) => require(['@/touristComponents/New/index'], resolve),
        name: 'News',
        meta: { title: '新闻' }
      },
      {//景点
        path: 'attractions',
        component: (resolve) => require(['@/touristComponents/Attractions/index'], resolve),
        name: 'Attractions',
        meta: { title: '景点' }
      },
      {
        path: 'bulletins',
        component: (resolve) => require(['@/touristComponents/Bulletins/index'], resolve),
        name: 'Bulletins',
        meta: { title: '公告' }
      },
      {//文创
        path: 'creation',
        component: (resolve) => require(['@/touristComponents/Creation/Creation/index'], resolve),
        redirect: 'index',
        children: [
          {
            path: 'index',
            component: (resolve) => require(['@/touristComponents/Creation/index'], resolve),
          },
          {
            path: 'submission',
            component: (resolve) => require(['@/touristComponents/Creation/Submission/index'], resolve),
          },
          {
            path: 'manuscript',
            component: (resolve) => require(['@/touristComponents/Creation/Manuscript/index'], resolve)
          },
          {
            path: 'appeal',
            component: (resolve) => require(['@/touristComponents/Creation/Appeal/index'], resolve)
          },
          {
            path: 'draft',
            component: (resolve) => require(['@/touristComponents/Creation/Draft/index'], resolve)
          },
          {
            path: 'comment',
            component: (resolve) => require(['@/touristComponents/Creation/Comment/index'], resolve),
          },
          {
            path: 'convention',
            component: (resolve) => require(['@/touristComponents/Creation/Convention/index'], resolve),
          },

        ]
      },
      {
        path: 'subscription',
        component: (resolve) => require(['@/touristComponents/Subscription/index'], resolve),
      },
      {
        path: 'culcreation',
        component: (resolve) => require(['@/touristComponents/Culcreation/index'], resolve),
      },
      {
        path: 'search',
        component: (resolve) => require(['@/touristComponents/Searchpage/index'], resolve),
      }
    ]
  },
  {
    path: '/frontHome/new',
    component: touristLayout,
    hidden: true,
    children: [
      {
        path: 'NewsPage/:newsId(\\d+)',
        component: (resolve) => require(['@/touristComponents/New/Newspage'], resolve),
        name: 'NewsPage',
      }
    ],
  },
  // {
  //   path: '/frontHome/subscription',
  //   component: touristLayout,
  //   children: [
  //     {
  //       path: 'culcreation',
  //       component: (resolve) => require(['@/touristComponents/Subscription/Culcreation/index'], resolve),
  //     }]
  // },
  {
    path: '/frontHome/attractions',
    component: touristLayout,
    hidden: true,
    children: [{
      path: 'attraction',//path: 'attraction/:sightsId(\\d+)',
      component: (resolve) => require(['@/touristComponents/Attractions/AttractionPage'], resolve),
      name: 'attraction',
    }]
  },
  {
    path: '/frontHome/attraction',
    component: touristLayout,
    hidden: true,
    children: [{
      path: 'food',//path: 'food/:sight(\\d+)',
      component: (resolve) => require(['@/touristComponents/Food'], resolve),
    }]
  },
  {
    path: '/frontHome/food',
    component: touristLayout,
    hidden: true,
    children: [{
      path: 'detail',
      component: (resolve) => require(['@/touristComponents/Food/FoodDetail'], resolve),
    }]
  },
  // {
  //   path: '/frontHome/culcreation',
  //   hidden: true,
  //   component: (resolve) => require(['@/touristComponents/Culcreation/index'], resolve),
  // },
]
const VueRouterPush = Router.prototype.push
Router.prototype.push = function push(to) {
  return VueRouterPush.call(this, to).catch(err => err)
}
export default new Router({
  mode: 'history', // 去掉url中的#
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})
