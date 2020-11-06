import request from '@/utils/request'
const service = config.services.wecom + '/material'
const serviceCategory = config.services.wecom + '/category'

/**
 * 查询素材列表
 * @param {*} params 
 * { categoryId:类目id
search:搜索的值
 }
 */
export function getList(params) {
  return request({ url: service + '/list', params })
}

/**
 *查询素材详细信息
 * @param {*} id 素材id
 */
export function getDetail(id) {
  return request({ url: service + '/' + id })
}
/**
 * 添加素材信息
 * @param {*} data 
 * {
categoryId:分类id
materialUrl:本地资源文件地址
content:文本内容、图片文案
materialName:图片名称
digest:摘要
coverUrl:封面本地资源文件
}
 */
export function add(data) {
  return request({ url: service, method: 'post', params: data })
}

/**
 * 更新素材信息
 * @param {*} data 
 * {
 * id:素材id
categoryId:分类id
materialUrl:本地资源文件地址
content:文本内容、图片文案
materialName:图片名称
digest:摘要
coverUrl:封面本地资源文件
}
 */
export function update(data) {
  return request({ url: service, method: 'put', data })
}

/**
 * 删除素材信息
 * @param {*} id
 */
export function remove(id) {
  return request({ url: service + '/' + id, method: 'DELETE' })
}

/**
 * 上传素材信息
 * @param {*} data 
 * {
 * file:文件
type:0 图片（image）、1 语音（voice）、2 视频（video），3 普通文件(file), 4 文本
}
 */
export function upload(data) {
  return request({
    url: config.services.wecom + '/upload',
    method: 'post',
    params: data,
  })
}

/**
 * 类目树
 * @param {*} mediaType 0 图片（image）、1 语音（voice）、2 视频（video），3 普通文件(file), 4 文本
 */
export function getTree(mediaType) {
  return request({ url: serviceCategory + '/list', params: { mediaType } })
}

/**
 *通过id查询类目详细信息
 * @param {*} id
 */
export function getTreeDetail(id) {
  return request({ url: serviceCategory + '/' + id })
}

/**
 * 添加类目
 * @param {*} data
 * {
    "mediaType": "0 图片（image）、1 语音（voice）、2 视频（video），3 普通文件(file) 4 文本",
    "name": "名称",
    "parentId": "父id"
}
 */
export function addTree(data) {
  return request({ url: serviceCategory, method: 'post', data })
}

/**
 * 修改类目
 * @param {*} data
 * {
    "mediaType": "0 图片（image）、1 语音（voice）、2 视频（video），3 普通文件(file) 4 文本",
    "name": "名称",
    "parentId": "父id"
}
 */
export function updateTree(data) {
  return request({ url: serviceCategory, method: 'put', data })
}

/**
 * 删除类目
 * @param {*} data
 */
export function removeTree(ids) {
  return request({
    url: serviceCategory + '/' + ids,
    method: 'DELETE',
  })
}