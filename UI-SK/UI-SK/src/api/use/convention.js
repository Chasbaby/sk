import request from '@/utils/request'

// 查询创作公约列表
export function listConvention(query) {
  return request({
    url: '/system/convention/list',
    method: 'get',
    params: query
  })
}

// 查询创作公约详细
export function getConvention(conventionId) {
  return request({
    url: '/system/convention/' + conventionId,
    method: 'get'
  })
}

// 新增创作公约
export function addConvention(data) {
  return request({
    url: '/system/convention',
    method: 'post',
    data: data
  })
}

// 修改创作公约
export function updateConvention(data) {
  return request({
    url: '/system/convention',
    method: 'put',
    data: data
  })
}

// 删除创作公约
export function delConvention(conventionId) {
  return request({
    url: '/system/convention/' + conventionId,
    method: 'delete'
  })
}

export function getAllConvention() {
  return request({
    url:'/convention/getAllConvention',
    method:'get'
  })
}
