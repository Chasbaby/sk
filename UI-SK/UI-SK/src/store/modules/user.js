import { login, logout, getInfo } from '@/api/login'
import { getToken, setToken, removeToken } from '@/utils/auth'

/**
 * 这里是放用户的东西哒
 * @type {{mutations: {SET_TOKEN: user.mutations.SET_TOKEN, SET_ROLES: user.mutations.SET_ROLES, SET_AVATAR: user.mutations.SET_AVATAR, SET_PERMISSIONS: user.mutations.SET_PERMISSIONS, SET_NAME: user.mutations.SET_NAME}, state: {permissions: [], roles: [], name: string, avatar: string, token: *}, actions: {LogOut({commit?: *, state: *}): Promise<unknown>, FedLogOut({commit: *}): Promise<unknown>, Login({commit?: *}, *): Promise<unknown>, GetInfo({commit?: *, state: *}): Promise<unknown>}}}
 */
const user = {
  //state 存放状态
  state: {
    token: getToken(),
    name: '',
    avatar: '',
    roles: [],
    permissions: []
  },
  // 更改 state 中的状态逻辑 操作同步
  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token
    },
    SET_NAME: (state, name) => {
      state.name = name
    },
    SET_AVATAR: (state, avatar) => {
      state.avatar = avatar
    },
    SET_ROLES: (state, roles) => {
      state.roles = roles
    },
    SET_PERMISSIONS: (state, permissions) => {
      state.permissions = permissions
    }
  },
  // 提交mutation 异步操作
  actions: {
    // 登录
    Login({ commit }, userInfo) {
      const username = userInfo.username.trim()
      const password = userInfo.password
      const code = userInfo.code
      const uuid = userInfo.uuid

      return new Promise((resolve, reject) => {
        login(username, password, code, uuid).then(res => {
          //发送请求  设置  token
          setToken(res.token)
          //唤醒 设置 token
          commit('SET_TOKEN', res.token)
          //懒加载
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 获取用户信息
    GetInfo({ commit, state }) {
      return new Promise((resolve, reject) => {
        // 请求
        getInfo().then(res => {
          // 获取响应 用户
          const user = res.user
          // 头像           如果为空               则 用默认的                              否则 用 自定义
          const avatar = user.avatar == "" ? require("@/assets/images/profile.jpg") : process.env.VUE_APP_BASE_API + user.avatar;


          // 响应 有角色 且 长度>0
          if (res.roles && res.roles.length > 0) { // 验证返回的roles是否是一个非空数组

            // 设置 值      结果是 admin  common culture 对应着我们的设置 所以 对于游客应该单独开来
            console.log(1+res.roles)

            commit('SET_ROLES', res.roles)

            console.log(2+res.roles)

            commit('SET_PERMISSIONS', res.permissions)

          } else {

            // 如果没有 那么就 把 默认 的加上去
            commit('SET_ROLES', ['ROLE_DEFAULT'])

          }

          // 设置 用户名 和 图像
          commit('SET_NAME', user.userName)
          commit('SET_AVATAR', avatar)
          resolve(res)

        }).catch(error => {
          reject(error)
        })
      })
    },

    // 退出系统
    LogOut({ commit, state }) {
      return new Promise((resolve, reject) => {
        logout(state.token).then(() => {

          // 全部 重置
          commit('SET_TOKEN', '')
          commit('SET_ROLES', [])
          commit('SET_PERMISSIONS', [])
          //移除 token
          removeToken()

          //路由懒加载
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 前端 登出
    FedLogOut({ commit }) {
      return new Promise(resolve => {
        commit('SET_TOKEN', '')
        removeToken()
        resolve()
      })
    }
  }
}

export default user
