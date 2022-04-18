import {createApp} from 'vue'
import App from './App.vue'
import {createPinia} from "pinia";
import router from "./router";

import Element from 'element-plus'
import 'element-plus/dist/index.css'
import 'element-plus/theme-chalk/display.css'
import zhCn from 'element-plus/es/locale/lang/zh-cn'
import 'element-plus/theme-chalk/display.css'

import directives from "./directives";
import * as filters from './utils/filters'

import './assets/css/reset.css'
import 'animate.css'
import './assets/css/elementReset.css'
import './assets/css/common.css'
import './permission'

const app = createApp(App)
app.config.globalProperties.$filters=filters


app
    .use(directives)
    .use(Element,{
        locale: zhCn,
    })
    .use(createPinia())
    .use(router)
    .mount('#app')
