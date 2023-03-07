import request from '@/utils/request'

export function sendEmail(data) {
  return request({
    url: '/system/email/send',
    method: 'post',
    return: data
  })
}
