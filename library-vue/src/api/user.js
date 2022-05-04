import request from "../utils/request";


// 获取用户列表
export function getUserPage(params) {
  return request({
    url: "sysUser/page",
    method: "get",
    params
  });
}

export function roleList(param) {
  return request({
    url:"sysRole/list"
    ,method:"get"
    ,params: param
  })
}

export function delUser(data) {
  return request({
    url: "sysUser"
    , method: "delete"
    , data: data
  })
}

export function addRoleToUser(data) {
  return request({
    url: "sysUserRole"
    , method: "post"
    , data: data
  })
}
export function delRoleToUser(data) {
  return request({
    url: "sysUserRole"
    , method: "delete"
    , params: {id:data}
  })
}

export function addUser(data) {
  return request({
    url: "sysUser"
    , method: "post"
    , data: data
  })
}


export function updateUser(data) {
  return request({
    url: "sysUser",
    method: "put",
    data:data
  });
}

export function pageBorrow(params) {
  return request({
    url: "borrowing/page",
    method: "get",
    params
  });
}
export function addBorrow(currentNumber,data) {
  return request({
    url: "borrowing",
    method: "post",
    params: {
      currentNumber:currentNumber
    },
    data:data
  });
}
export function borrowingBooks(id) {
  return request({
    url: "borrowing/borrowingBooks",
    method: "get",
    params: {
      id
    },
  });
}
export function retBooks(data) {
  return request({
    url: "borrowing",
    method: "put",
    data
  });
}