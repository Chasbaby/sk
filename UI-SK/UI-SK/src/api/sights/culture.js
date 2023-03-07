import request from '@/utils/request'

// 查询景点特色文化列表
export function listCulture(query) {
  return request({
    url: '/sights/culture/list',
    method: 'get',
    params: query
  })
}

// 查询景点特色文化详细
export function getCulture(cultureId) {
  return request({
    url: '/sights/culture/' + cultureId,
    method: 'get'
  })
}

// 新增景点特色文化
export function addCulture(data) {
  return request({
    url: '/sights/culture',
    method: 'post',
    data: data
  })
}

// 修改景点特色文化
export function updateCulture(data) {
  return request({
    url: '/sights/culture',
    method: 'put',
    data: data
  })
}

// 删除景点特色文化
export function delCulture(cultureId) {
  return request({
    url: '/sights/culture/' + cultureId,
    method: 'delete'
  })
}
