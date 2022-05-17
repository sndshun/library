import request from "../utils/request";

export function getCredentials(params) {
    return request({
        url: "credentials/list",
        method: "get",
        params
    });
}

export function getCredentialsPage(params) {
    return request({
        url: "credentials/page"
        , method: "get"
        , params
    })
}


export function addCredentials(data) {
    return request({
        url: "credentials"
        , method: "post"
        , data: data
    })
}

export function updCredentials(data) {
    return request({
        url: "credentials"
        , method: "put"
        , data: data
    })
}

export function delCredentials(data) {
    return request({
        url: "credentials"
        , method: "delete"
        , data:data
    })
}
