import request from '@/utils/request'

export function saveStaff(data) {
  return request({
    url: '/staff/sava',
    method: 'put',
    data
  })
}

export function deleteStaff(data) {
  return request({
    url: '/staff/delete',
    method: 'delete',
    data
  })
}

export function updateStaff(data) {
  return request({
    url: '/staff/update',
    method: 'post',
    data
  })
}

export function selectStaffPage(data) {
  return request({
    url: '/staff/page',
    method: 'get',
    params: data
  })
}

export function staffDownload() {
  return request({
    url: '/staff/download',
    method: 'get'
  })
}
