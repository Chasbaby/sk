import router from './router'
import store from './store'
import { Message } from 'element-ui'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
import { getToken } from '@/utils/auth'
import { isRelogin } from '@/utils/request'

NProgress.configure({ showSpinner: false })

const whiteList = ['/login', '/auth-redirect', '/bind', '/register','/frontHome/home'
  ,'/frontHome/News','/frontHome/Attractions','/frontHome/Bulletins','/frontHome/Newspage']

/**
 * to: router即将进入的路由对象
 * from:当前导航即将离开的路由
 * next:Function,进行管道中的一个钩子，如果执行完了，则导航的状态就是 confirmed （确认的）；否则为false，终止导航。
 */
router.beforeEach((to, from, next) => {
  // 进度条显示
  NProgress.start()

  /**
   *    路由思想
   * 1. (如果有方法设置) 用户的起始界面为 用户界面，管理员的起始界面为 后台
   * 2. 对于用户:   如果选择了需要用户权限的功能,会自动跳到 登录界面 拥有账号之后 自动返回之前的界面
   * 3. 对于管理员: 就是 ruoyi 本身s
   */

  if (getToken()) {
    /**
     *   设置网页标题
     */
    to.meta.title && store.dispatch('settings/setTitle', to.meta.title)
    /* has token*/

    // 在有token的情况下 如果去登录界面
    if (to.path === '/login') {
      //放行到 主页
      next({ path: '/frontHome/home' })

      // 加载完成
      NProgress.done()

    } else {
      // 计算 角色 长度 = 0  也就是说还没有获得角色 所以要先得到 才能继续
      if (store.getters.roles.length === 0) {
        isRelogin.show = true
        // 判断当前用户是否已拉取完user_info信息       也就是根据 角色 用户 设置一些信息 eg: token
        store.dispatch('GetInfo').then(() => {
          isRelogin.show = false
          // 生成路由
          store.dispatch('GenerateRoutes').then(accessRoutes => {
            //console.log(accessRoutes) 出来的都是 目录   老大 是 7 个
            // 根据roles权限生成可访问的路由表
            router.addRoutes(accessRoutes)// 动态添加可访问路由表
            // 游客也是获得这些 然后 该去哪里就去哪里
            next({ ...to, replace: true }) // hack方法 确保addRoutes已完成
          })
        }).catch(err => {
            store.dispatch('LogOut').then(() => {
              Message.error(err)
              // 放行 到 ‘ 游客首页 ’
              next({ path: '/frontHome/home' })
            })
          })
      } else {
        //获得到角色的话直接放行
        console.log("xx",store.getters.roles.indexOf("common"))
        console.log("xxx",store.getters.roles)
        //放行
        next()
      }
    }
  } else {
    // 没有token
    if (whiteList.indexOf(to.path) !== -1) {
      // 在免登录白名单，直接进入
      next()
    } else {
      // 这个后面的 redirect=${to.fullPath} 其实是 记录信息  因为可能是从其他地方来登录的 解决这个login之后就会返回之前的地方
      next(`/login?redirect=${to.fullPath}`)  //否则全部重定向到登录页
      NProgress.done()
    }
  }
})

router.afterEach(() => {
  NProgress.done()
})
