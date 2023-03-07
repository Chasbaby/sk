import request from '@/utils/request'

// 获取路由
export const getRouters = () => {
  return request({
    url: '/getRouters',
    method: 'get'
  })
}
/**
 * 获取路由信息
 *
 * @return 路由信息
 */
// @GetMapping("getRouters")
// public AjaxResult getRouters()
// {
//   Long userId = SecurityUtils.getUserId();
//   List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
//   return AjaxResult.success(menuService.buildMenus(menus));
// }
