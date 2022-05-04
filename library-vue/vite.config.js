import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'



// https://vitejs.dev/config/
export default defineConfig({
    plugins: [
        vue(),
    ],
    server: {
        port: 3000,
        proxy: {
            '/api': {
                target: 'http://localhost:8081',//后端的端口号
                changeOrigin: true,//将请求头POST设置为target
                rewrite: (path) => path.replace(/^\/api/, '')
            },
            '/dou': {
                target: 'https://images.weserv.nl/?url=',//后端的端口号
                changeOrigin: true,//将请求头POST设置为target
                rewrite: (path) => path.replace(/^\/dou/, '')
            },
        }
    }
})
