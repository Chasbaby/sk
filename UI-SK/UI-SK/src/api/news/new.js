import request from '@/utils/request'

// 查询新闻管理列表
export function listNew(query) {
  return request({
    url: '/system/new/list',
    method: 'get',
    params: query
  })
}

// 查询新闻管理详细
export function getNew(newId) {
  return request({
    url: '/system/new/' + newId,
    method: 'get'
  })
}

// 新增新闻管理
export function addNew(data) {
  return request({
    url: '/system/new',
    method: 'post',
    data: data
  })
}

// 修改新闻管理
export function updateNew(data) {
  return request({
    url: '/system/new',
    method: 'put',
    data: data
  })
}

// 删除新闻管理
export function delNew(newId) {
  return request({
    url: '/system/new/' + newId,
    method: 'delete'
  })
}
