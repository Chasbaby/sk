import request from '@/utils/request'

// 查询景点基本信息列表
export function listSights(query) {
  return request({
    url: '/sightBase/sights/list',
    method: 'get',
    params: query
  })
}

// 查询景点基本信息详细
export function getSights(sightsId) {
  return request({
    url: '/sightBase/sights/' + sightsId,
    method: 'get'
  })
}

// 新增景点基本信息
export function addSights(data) {
  return request({
    url: '/sightBase/sights',
    method: 'post',
    data: data
  })
}

// 修改景点基本信息
export function updateSights(data) {
  return request({
    url: '/sightBase/sights',
    method: 'put',
    data: data
  })
}

// 删除景点基本信息
export function delSights(sightsId) {
  return request({
    url: '/sightBase/sights/' + sightsId,
    method: 'delete'
  })
}
//测试使用
export function getAllSights() {
  return request({
    url:'/sights/getAll',
    method:"post"
  })
}

//测试使用
export function getSights2() {
  return request({
    url:'/sights/all',
    method:'post'
  })
}

//获取某用户推荐列表
export function getRecommendSights() {
  return request({
    url: '/sights/recommend',
    method:'post'
  })
}
//历史热门景点推荐
export function getSightsInfo(sightsId) {
  return request({
    url: '/sights/'+sightsId,
    method:'get',
    sightsId:sightsId
  })
}
//近期热门景点推荐
export function getHistoryHotSights() {
  return request({
    url: '/sights/historySights',
    method:'post'
  })
}
//优质景点推荐
export function getGoodSights() {
  return request({
    url: '/sights/goodSights',
    method:'post'
  })
}
//为景点 添加 tag
export function addSightsTags(sightsId,tag) {
  return request({
    url: '/sights/addTags',
    method:'post',
    sightsId:sightsId,
    tag:tag
  })
}
//默认tag
export function defaultTags() {
  return request({
    url: '/sights/defaultTags',
    method:'post'
  })
}

//点击量++
export function improveHits(sightsId) {
  return request({
    url: '/sights/hit/'+sightsId,
    method:'get',
    sightsId:sightsId
  })
}
//点击量排行榜
export function getHitRank() {
  return request({
    url: '/sights/hits/rank',
    method:'post'
  })
}
//点赞 — — + +
export function changeLike(sightsId) {
  return request({
    url: '/sights/like/'+sightsId,
    method:'get',
    sightsId:sightsId
  })
}
//点赞排行榜
export function getLikeRank() {
  return request({
    url: '/sights/like/rank',
    method:'post'
  })
}
//浏览量++
export function improveView(sightsId) {
  return request({
    url: '/sights/view/'+sightsId,
    method:'get',
    sightsId:sightsId
  })
}
//浏览量排行榜
export function getViewRank() {
  return request({
    url: '/sights/view/rank',
    method:'post'
  })
}
//收藏景点
export function collectSightsId(sightsId) {
  return request({
    url: '/sights/collect/'+sightsId,
    method:'get',
    sightsId:sightsId
  })
}
//取消收藏景点
export function reverseSightsCollection(sightsId) {
  return request({
    url: '/sights/reverse/collect/'+sightsId,
    method:'get',
    sightsId:sightsId
  })
}
//获取用户景点 收藏记录
export function getCollection() {
  return request({
    url: '/sights/collect',
    method:'post'
  })
}

//增加景点历史记录
export function setSightsHistory(sightsId) {
  return request({
    url: '/sights/history/'+sightsId,
    method:'get',
    sightsId:sightsId
  })
}

//获取用户景点历史记录
export function getHistory() {
  return request({
    url: '/sights/history',
    method:'post'
  })
}
// 评分 + 修改评分
export function changeSightsScore(sightsId,score) {
  return request({
    url: '/sights/'+sightsId+'/'+score,
    method:'get',
    sightsId:sightsId,
    score:score
  })
}
//判断某用户是否对景点评分
export function judgeUserToSightsExistScore(sightsId) {
  return request({
    url: '/sights/judge/'+sightsId,
    method:'get',
    sightsId:sightsId
  })
}
//获取景点评分排行榜
export function getSightsScoreRank() {
  return request({
    url: '/sights/score/rank',
    method:'get'
  })
}
















