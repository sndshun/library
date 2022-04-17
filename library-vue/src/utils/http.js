/**
 * @Description : axios封装工具
 * @author : mr.sun
 * @date : 2021/11/1
 * @Version : 1.0
 * @Last update by : mr.sun
 * @Last update time : 2021/11/1
 */
import axios from "axios";

import {ElMessage} from "element-plus";


/*
  @ 创建自定义axios实例
  无加载动画
*/
const http = axios.create({
    timeout: 1000*5
})
/*
  @ request 请求拦截器=>请求发出前处理
*/
http.interceptors.request.use(
    config => {
        ElMessage.success('请求发出')
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
http.interceptors.response.use(
    response => {
        return Promise.resolve(response.data);
    },
    error => {
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

export default http;

