import request from '@/utils/request'

// 查询餐馆列表
export function listRestaurant(query) {
  return request({
    url: '/system/restaurant/list',
    method: 'get',
    params: query
  })
}

// 查询餐馆详细
export function getRestaurant(restaurantId) {
  return request({
    url: '/system/restaurant/' + restaurantId,
    method: 'get'
  })
}

// 新增餐馆
export function addRestaurant(data) {
  return request({
    url: '/system/restaurant',
    method: 'post',
    data: data
  })
}

// 修改餐馆
export function updateRestaurant(data) {
  return request({
    url: '/system/restaurant',
    method: 'put',
    data: data
  })
}

// 删除餐馆
export function delRestaurant(restaurantId) {
  return request({
    url: '/system/restaurant/' + restaurantId,
    method: 'delete'
  })
}

export function restaurantConnectSights(sightsId,restaurantId) {
  return request({
    url: '/system/restaurant/connect/'+sightsId +'/'+restaurantId,
    method: 'get',
  })
}
