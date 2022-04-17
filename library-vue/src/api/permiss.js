import request from "../utils/request";

export function rolePage(param) {
    return request({
        url:'/sysRole/page'
        ,method:'get'
        ,params: param
    })
}
export function getAllPer() {
    return request({
        url:'/sysPermission/getAllPer'
        ,method:'get'
    })
}
export function getRoleIsMenuPer() {
    return request({
        url:'/sysRolePermission/getRoleIsMenuPer'
        ,method:'get'
    })
}
export function updRolePer(rid,name,uid,permissions) {
    return request({
        url:'/sysRolePermission/updRolePer'
        ,method:'post'
        ,params:{rid,name,uid}
        ,data: permissions
    })
}