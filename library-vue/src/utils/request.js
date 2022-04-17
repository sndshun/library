/**
 * @Description : axios封装工具
 * @author : mr.sun
 * @date : 2021/11/1
 * @Version : 1.0
 * @Last update by : mr.sun
 * @Last update time : 2021/11/1
 */
import axios from "axios";

import {ElLoading,ElMessage} from "element-plus";
import {getLibrary, getRememberMeLibrary} from "./auth";


let loading;
/*
  @ 创建自定义axios实例
*/
const request = axios.create({
    baseURL: "/api",
    timeout: 1000*5
})
/*
  @ request 请求拦截器=>请求发出前处理
*/
request.interceptors.request.use(
    config => {
        loading=ElLoading.service()
        let library=getLibrary()||getRememberMeLibrary()
        if (library) {
            config.headers['library']=library
        }
        return config;
    }, error => {
        // 接口返回失败
        console.log(error.response);
        return Promise.reject(error.response);
    }
)

/*
  @ request 响应拦截器=>处理响应数据
*/
request.interceptors.response.use(
    response => {
        loading.close()
        //获取统一响应返回体
        const r=response.data
        //在这里处理响应体中的状态码
        const code=r.code
        if (code != 200) {
            ElMessage.error(r.message)
        }
        //返回统一响应返回体
        return Promise.resolve(r);
    },
    error => {
        loading.close()
        // //请求失败，这个地方可以根据error.response.status统一处理一些界面逻辑，比如status为401未登录,可以进行重定向
        // router.replace({
        //     path: '/login',
        //     query: { redirect: router.currentRoute.fullPath }
        //    });
        ElMessage({
            showClose: true,
            message: '请求失败',
            type: 'error',
        })
        return Promise.reject(error.response);
    }
)

export default request;

