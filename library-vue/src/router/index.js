import {createRouter, createWebHistory} from "vue-router";
import {shallowRef} from 'vue'
import Home from "../views/Home.vue";
import layout from '../views/layout/Index.vue'

export const baseRouters = [
    {
        path: '/'
        , name: 'home'
        , component: Home
        , meta: {title: 'my图书馆'}
        , hidden: true
    },
    {
        path: '/login'
        , name: '登录'
        , component: () => import('../views/Login.vue')
        , meta: {title: '登录'}
        , hidden: true
    }

]

const router = createRouter({
    routes: baseRouters,
    history: createWebHistory()
})

export default router

export const routerList = [

    {
        path: '/admin'
        , name: 'admin-dashboard'
        , component: layout
        , meta: {title: '控制台'}
        , redirect: '/admin/home'
        , children: [
            {
                path: '/admin/home'
                , name: '首页'
                , component: () => import('../views/admin/Index.vue')
                , meta: {title: '首页', icon: 'password'}
            },
            {
                path: '/admin/dashboard'
                , name: '控制台'
                , component: () => import('../views/admin/Dashboard.vue')
                , meta: {title: '控制台'}
                , menu: 'dashboard'
            }
        ]
    }

    , {
        path: '/2'
        , name: 'admin-book'
        , redirect: '/admin/book'
        , component: layout
        , meta: {title: '图书管理', icon: 'tree'}
        , children: [
            {
                path: '/admin/book'
                , name: '书籍管理'
                , component: () => import('../views/admin/Book.vue')
                , meta: {title: '书籍管理'}
                , menu: 'book'
            }
            , {
                path: '/admin/bookType'
                , name: '书籍类型管理'
                , component: () => import('../views/admin/BookType.vue')
                , meta: {title: '书籍类型管理'}
                , menu: 'bookType'
            }
            , {
                path: '/admin/bookTag'
                , name: '书籍标签管理'
                , component: () => import('../views/admin/BookTag.vue')
                , meta: {title: '书籍标签管理'}
                , menu: 'bookTag'
            }
        ]
    }
    , {
        path: '/3'
        , name: 'admin-borrow'
        , component: layout
        , meta: {title: '借阅管理'}
        , redirect: '/admin/borrow'
        , children: [
            {
                path: '/admin/borrow'
                , name: '借阅管理'
                , component: () => import('../views/admin/Borrow.vue')
                , meta: {title: '借阅管理'}
                , menu: 'borrow'

            }
        ]
    }
    , {
        path: '/5'
        , name: 'admin-role'
        , component: layout
        , redirect: '/admins/role'
        , meta: {title: '系统管理', icon: 'table'}
        , children: [
            {
                path: '/admin/user'
                , name: '用户管理'
                , component: () => import('../views/admin/User.vue')
                , meta: {title: '用户管理'}
                , menu: 'user'
            },
            {
                path: '/admin/permiss'
                , name: '权限管理'
                , component: () => import('../views/admin/Permiss.vue')
                , meta: {title: '权限管理', icon: 'password'}
                , menu: 'permiss'
            },
        ]
    }
]
export const afterRouter = [
    {
        path: '/:pathMatch(.*)*'
        , name: 'NotFound'
        , component: () => import('../views/404.vue')
        , meta: {title: '404'}
        , hidden: true
    }
]