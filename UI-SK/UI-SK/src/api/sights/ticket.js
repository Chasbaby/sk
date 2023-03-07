import request from '@/utils/request'

// 查询门票列表
export function listTicket(query) {
  return request({
    url: '/system/ticket/list',
    method: 'get',
    params: query
  })
}

// 查询门票详细
export function getTicket(ticketId) {
  return request({
    url: '/system/ticket/' + ticketId,
    method: 'get'
  })
}

// 新增门票
export function addTicket(data) {
  return request({
    url: '/system/ticket',
    method: 'post',
    data: data
  })
}

// 修改门票
export function updateTicket(data) {
  return request({
    url: '/system/ticket',
    method: 'put',
    data: data
  })
}

export function ticketConnectSights(sightsId,ticketId) {
  return request({
    url: '/system/ticket/connect/'+sightsId +'/'+ticketId,
    method: 'get',
  })
}

// 删除门票
export function delTicket(ticketId) {
  return request({
    url: '/system/ticket/' + ticketId,
    method: 'delete'
  })
}
