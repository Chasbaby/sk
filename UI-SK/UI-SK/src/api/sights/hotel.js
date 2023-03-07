import request from '@/utils/request'

// 查询酒店列表
export function listHotel(query) {
  return request({
    url: '/system/hotel/list',
    method: 'get',
    params: query
  })
}

// 查询酒店详细
export function getHotel(hotelId) {
  return request({
    url: '/system/hotel/' + hotelId,
    method: 'get'
  })
}

// 新增酒店
export function addHotel(data) {
  return request({
    url: '/system/hotel',
    method: 'post',
    data: data
  })
}

// 修改酒店
export function updateHotel(data) {
  return request({
    url: '/system/hotel',
    method: 'put',
    data: data
  })
}

// 删除酒店
export function delHotel(hotelId) {
  return request({
    url: '/system/hotel/' + hotelId,
    method: 'delete'
  })
}

export function hotelConnectSights(sightsId,hotelId) {
  return request({
    url: '/system/hotel/connect/'+sightsId +'/'+hotelId,
    method: 'get',
  })
}
