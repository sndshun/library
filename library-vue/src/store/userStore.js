import {defineStore} from 'pinia'
import router, {afterRouter, routerList} from "../router";
import {getUserInfo, logout} from "../api/login";
import {clearLibrary, clearRememberMeLibrary} from "../utils/auth";
import {ElMessage} from "element-plus";

/**
 * 递归过滤异步路由表，返回符合用户菜单权限的路由表
 * @param asyncRouterMap
 * @param menus
 */
function filterAsyncRouter(asyncRouterMap, menus) {
    const accessedRouters = asyncRouterMap.filter(route => {
        if (route.children) {
            route.children=route.children.filter(item=>{
                if(item.children){
                    item.children=filterAsyncRouter(item.children,menus)
                }
                if(!item.menu)return true
                return menus.indexOf(item.menu)>-1
            })
            return (route.children && route.children.length)
        } else {
            if(!route.menu)return true
            return menus.indexOf(route.menu)>-1
        }
    })
    return accessedRouters
}

export const userStore = defineStore('user', {
    state: () => {
        return {
            userInfo: null,
            userPermission: [],
            userMenu:[]
        }
    },
    getters: {},
    actions: {
        async getInfo() {
            const res = await getUserInfo()
            if (res.code !== 200) {
                ElMessage.error(res.message)
                this.userInfo=null
                this.userMenu=[]
                this.userPermission=[]
                clearLibrary()
                clearRememberMeLibrary()
            }else {
                const {id,name,sex,phone,address,roles,menus,permissions,email,profile,rolesName} =res.data;
                this.userInfo={id,name,sex,phone,address,roles,email,profile,rolesName}
                this.userPermission=permissions

                let menu=filterAsyncRouter(routerList,menus)

                menu= menu.concat(afterRouter)
                this.userMenu=menu
                menu.forEach(item=>{
                    router.addRoute(item)
                })
            }
        },
        async logout() {
            let res= await logout()
            if (res.data) {
                this.userInfo= null
                this.userMenu=[]
                this.userPermission=[]
                clearLibrary()
                clearRememberMeLibrary()
            }
            return res.data;
        },
    }
})

