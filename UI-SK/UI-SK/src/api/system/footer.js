import request from '@/utils/request'

// 查询底部展示列表
export function listFooter(query) {
  return request({
    url: '/system/footer/list',
    method: 'get',
    params: query
  })
}

// 查询底部展示详细
export function getFooter(footerId) {
  return request({
    url: '/system/footer/' + footerId,
    method: 'get'
  })
}

// 新增底部展示
export function addFooter(data) {
  return request({
    url: '/system/footer',
    method: 'post',
    data: data
  })
}

// 修改底部展示
export function updateFooter(data) {
  return request({
    url: '/system/footer',
    method: 'put',
    data: data
  })
}

// 删除底部展示
export function delFooter(footerId) {
  return request({
    url: '/system/footer/' + footerId,
    method: 'delete'
  })
}

export function getDyFooter() {
  return request({
    url:'/page/footer',
    method:'get'
  })
}
