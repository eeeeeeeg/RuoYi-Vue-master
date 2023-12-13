import request from '@/utils/request'

// 查询站点维护
export function listSite(query) {
  return request({
    url: '/system/site/list',
    method: 'get',
    params: query
  })
}

// 查询站点维护
export function getSite(id) {
  return request({
    url: '/system/site/' + id,
    method: 'get'
  })
}

// 新增站点维护

export function addSite(data) {
  return request({
    url: '/system/site',
    method: 'post',
    data: data
  })
}

// 修改站点维护

export function updateSite(data) {
  return request({
    url: '/system/site',
    method: 'put',
    data: data
  })
}

// 删除站点维护

export function delSite(id) {
  return request({
    url: '/system/site/' + id,
    method: 'delete'
  })
}
