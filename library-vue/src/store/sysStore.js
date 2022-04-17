import {defineStore} from 'pinia'
import Router from "../router";


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
                if (index === -1) {
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