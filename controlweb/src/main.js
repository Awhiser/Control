import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'
import i18n from '@/i18n/index'
//import { persistedstate } from 'pinia-plugin-persistedstate'


import App from './App.vue'
import router from './router'

import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/reset.css';

const app = createApp(App)

app.use(createPinia())
app.use(router)
app.use(Antd)
app.use(i18n)
app.mount('#app')

