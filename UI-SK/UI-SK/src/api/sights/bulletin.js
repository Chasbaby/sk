import request from '@/utils/request'

// 查询景点公告列表
export function listBulletin(query) {
  return request({
    url: '/system/bulletin/list',
    method: 'get',
    params: query
  })
}

// 查询景点公告详细
export function getBulletin(bulletinId) {
  return request({
    url: '/system/bulletin/' + bulletinId,
    method: 'get'
  })
}

// 新增景点公告
export function addBulletin(data) {
  return request({
    url: '/system/bulletin',
    method: 'post',
    data: data
  })
}

// 修改景点公告
export function updateBulletin(data) {
  return request({
    url: '/system/bulletin',
    method: 'put',
    data: data
  })
}

// 删除景点公告
export function delBulletin(bulletinId) {
  return request({
    url: '/system/bulletin/' + bulletinId,
    method: 'delete'
  })
}

export function bulletinConnectSights(sightsId,bulletinId) {
  return request({
    url: '/system/bulletin/connect/'+sightsId +'/'+bulletinId,
    method: 'get',
  })
}
