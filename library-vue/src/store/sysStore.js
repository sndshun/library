import {defineStore} from 'pinia'
import Router from "../router";
import {baseRouters} from "../router";

export const sysStore = defineStore('sys', {
    state: () => {
        return {
            //main动画
            mainAni: 'left',
            //记录上一次的位置
            mainIndex: 0,
            //面包屑
            breadcrumb: [
                {
                    name: '首页',
                    path: '/admin/home',
                    open: true
                }
            ],
        }
    },
    actions: {
        setBreadcrumb(breadcrumb) {
            if (breadcrumb.name) {
                //判断在第几位
                const index = this.breadcrumb.findIndex(item => {
                    if (item.name === breadcrumb.name) {
                        return true
                    }
                })
                if (this.mainIndex <= index) {
                    this.mainAni = 'right'
                } else {
                    this.mainAni = 'left'
                }
                //禁止添加白名单路由
                const flag= baseRouters.some(item => {
                    if (item.path === breadcrumb.path) {
                        return true
                    }
                })
                if (index === -1&&!flag) {
                    this.breadcrumb.push(breadcrumb)
                } else {
                    this.mainIndex = index
                }
            }
        },
        //删除面包屑
        delBreadcrumb(index) {
            //使用route获取当前的路由
            const route = Router.currentRoute
            if (route.value.path == this.breadcrumb[index].path) {
                this.breadcrumb.splice(index, 1);
                Router.push({
                    path: this.breadcrumb[index - 1].path
                });
            } else {
                this.breadcrumb.splice(index, 1);
            }
        },
    },
})