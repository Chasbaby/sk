import request from '@/utils/request'

// 查询广告列表
export function listAd(query) {
  return request({
    url: '/home/ad/list',
    method: 'get',
    params: query
  })
}

// 查询广告详细
export function getAd(adId) {
  return request({
    url: '/home/ad/' + adId,
    method: 'get'
  })
}

// 新增广告
export function addAd(data) {
  return request({
    url: '/home/ad',
    method: 'post',
    data: data
  })
}
// 修改广告
export function updateAd(data) {
  return request({
    url: '/home/ad',
    method: 'put',
    data: data
  })
}

// 删除广告
export function delAd(adId) {
  return request({
    url: '/home/ad/' + adId,
    method: 'delete'
  })
}

// 获得推荐广告
export function getRecommendAd() {
  return request({
    url: '/permitAll/Ad/allAd',
    method: 'post'
  })
}

// 历史热门广告
export function getHistHotAd() {
  return request({
    url: '/permitAll/Ad/hisHot',
    method:'post'
  })
}

// 近期热门广告
export function getRecentAd() {
  return request({
    url:'/permitAll/Ad/recent',
    method:'post'
  })
}

// 优质广告
export function getGoodAd() {
  return request({
    url: '/permitAll/Ad/good',
    method: 'post'
  })
}

// 查询广告详细
export function getAdPermitAll(adId) {
  return request({
    url:'/permitAll/Ad/'+adId,
    method: 'get'
  })
}

// 点击广告
export function improveHits(adId) {
  return request({
    url:'/permitAll/Ad/hit/'+adId,
    method: 'get'
  })
}

export function getAll() {
  return request({
    url: '/allNews',
    method:'get'
  })
}

export function getAdStatistic() {
  return request({
    url: '/home/ad/statistic',
    method:'get'
  })
}
