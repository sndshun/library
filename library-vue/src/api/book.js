import * as qs from 'qs';
import request from "../utils/request";
import ajaxRequest from "../utils/ajaxRequest";
import http from "../utils/http";

const key = '12360.cb285f5d3a226ccf0cba2d38c76e2e72.7bb24a8075895d02fe65bab18b2fe88b'

//------------------------------------------------------------------------------- 书籍前台接口
export function getBooks(params) {
    return request({
        url: "/book/list",
        method: "get",
        params,
        paramsSerializer: function (params) {
            return qs.stringify(params, {arrayFormat: 'repeat'})
        }
    });
}

//-------------------------------------- 标签
export function hotTags(count) {
    return request({
        url: "/bookTags/hotTags",
        method: "get",
        params: {
            count
        }
    })
}
export function getAllTags() {
    return request({
        url: "/bookTag/list",
        method: "get",
    })
}

//------------------------------------------------------------------------------- 书籍后台接口

export function getTestBook(params) {
    return http({
        url: `https://api.jike.xyz/situ/book/isbn/${params}?apikey=${key}`,
        method: "get",
    });
}


export function getBookList(params) {
    return request({
        url: "/book/page",
        method: "get",
        params,
        paramsSerializer: function (params) {
            return qs.stringify(params, {arrayFormat: 'repeat'})
        }
    });
}

export function getBookDetail(id) {
    return request({
        url: `/book/${id}`,
        method: "get",
    });
}

export function getBookTags(id) {
    return request({
        url: `/book/getTags/${id}`,
        method: "get",
    });
}

export function addBook(data) {
    return request({
        url: '/book'
        , method: 'post'
        , data: data
    })
}

export function updBook(data) {
    return request({
        url: '/book'
        , method: 'put'
        , data: data
    })
}

export function delBook(data) {
    return request({
        url: '/book'
        , method: 'delete'
        , data: data
    })
}

//------------------------------------------------------ 书籍分类
export function getBookTypeList(params) {
    return request({
        url: "/bookType/list",
        method: "get",
        params
    });
}

export function getBookTypePage(params) {
    return request({
        url: '/bookType/page'
        , method: 'get'
        , params
    })
}


export function addBookType(data) {
    return request({
        url: '/bookType'
        , method: 'post'
        , data: data
    })
}

export function updBookType(data) {
    return request({
        url: '/bookType'
        , method: 'put'
        , data: data
    })
}

export function delBookType(data) {
    return request({
        url: '/bookType'
        , method: 'delete'
        , params: {id: data}
    })
}

export function batchDelBookType(ids) {
    return request({
        url: '/bookType/batchDel'
        , method: 'delete'
        , data: ids
    })
}

//------------------------------------------------------ 书籍标签
// 异步获取书籍标签列表 模糊查询
export function getBookTagList(params) {
    return ajaxRequest({
        url: "/bookTag/list",
        method: "get",
        params
    });
}

export function getBookTagPage(params) {
    return request({
        url: '/bookTag/page'
        , method: 'post'
        , params
    })
}

export function addBookTag(data) {
    return request({
        url: '/bookTag'
        , method: 'post'
        , data: data
    })
}


export function updBookTag(data) {
    return request({
        url: '/bookTag'
        , method: 'put'
        , data: data
    })
}

export function delBookTag(data) {
    return request({
        url: '/bookTag'
        , method: 'delete'
        , params: {id: data}
    })
}

export function batchDelBookTag(ids) {
    return request({
        url: '/bookTag/batchDel'
        , method: 'delete'
        , data: ids
    })
}

//------------------------------------------------------ 标签关联
export function addBookTags(data) {
    return request({
        url: '/bookTags'
        , method: 'post'
        , data: data
    })
}

export function delBookTags(data) {
    return request({
        url: '/bookTags'
        , method: 'delete'
        , params: {id: data}
    })
}
