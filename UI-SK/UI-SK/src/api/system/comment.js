import request from '@/utils/request'

// 查询评论审核列表
export function listComment(query) {
  return request({
    url: '/system/comment/list',
    method: 'get',
    params: query
  })
}

// 查询评论审核详细
export function getComment(commentId) {
  return request({
    url: '/system/comment/' + commentId,
    method: 'get'
  })
}

// 新增评论审核
export function addComment(data) {
  return request({
    url: '/system/comment',
    method: 'post',
    data: data
  })
}

// 修改评论审核
export function updateComment(data) {
  return request({
    url: '/system/comment',
    method: 'put',
    data: data
  })
}

// 删除评论审核
export function delComment(commentId) {
  return request({
    url: '/system/comment/' + commentId,
    method: 'delete'
  })
}

export function getCommentStatistic() {
  return request({
    url:'/system/comment/statistic',
    method:'get'
  })
}
