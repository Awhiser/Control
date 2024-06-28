import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  server: {
    host: 'localhost',  // 此前端项目的IP地址
    port: 5173,  // 此前端项目的端口号
    open: true,  //表示在启动开发服务器时，会自动打开浏览器并访问指定的地址
    proxy: {
      '/api': {
        target: 'http://localhost:8088', //接口域名
        changeOrigin: true,       //是否跨域
        ws: true,            //是否代理 websockets
        secure: true,          //是否https接口
        pathRewrite: {
          '^/api': '/'  //假如我们的地址是 /api/member/getToken 会转化为 /member/getToken
        }
      }
    }
  }


})
