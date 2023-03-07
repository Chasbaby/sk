import request from '@/utils/request'

// 查询文创列表
export function listCul_creativity(query) {
  return request({
    url: '/system/cul_creativity/list',
    method: 'get',
    params: query
  })
}

// 查询文创详细
export function getCul_creativity(culCreativityId) {
  return request({
    url: '/system/cul_creativity/' + culCreativityId,
    method: 'get'
  })
}

// 新增文创
export function addCul_creativity(data) {
  return request({
    url: '/system/cul_creativity',
    method: 'post',
    data: data
  })
}

// 修改文创
export function updateCul_creativity(data) {
  return request({
    url: '/system/cul_creativity',
    method: 'put',
    data: data
  })
}

// 删除文创
export function delCul_creativity(culCreativityId) {
  return request({
    url: '/system/cul_creativity/' + culCreativityId,
    method: 'delete'
  })
}

//获取规定数目的文创信息
export function getAllCul_creativity() {
  return request({
    url: '/culCreativity/getAll',
    method: 'post'
  })
}

export function culConnectSights(sightsId,culCreativityId) {
  return request({
    url: '/system/cul_creativity/connect/'+sightsId +'/'+culCreativityId,
    method: 'get',
  })
}
