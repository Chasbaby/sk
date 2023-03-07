import request from '@/utils/request'

// 查询政策列表
export function listPolicy(query) {
  return request({
    url: '/system/policy/list',
    method: 'get',
    params: query
  })
}

// 查询政策详细
export function getPolicy(policyId) {
  return request({
    url: '/system/policy/' + policyId,
    method: 'get'
  })
}

// 新增政策
export function addPolicy(data) {
  return request({
    url: '/system/policy',
    method: 'post',
    data: data
  })
}

// 修改政策
export function updatePolicy(data) {
  return request({
    url: '/system/policy',
    method: 'put',
    data: data
  })
}

// 删除政策
export function delPolicy(policyId) {
  return request({
    url: '/system/policy/' + policyId,
    method: 'delete'
  })
}
