import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import ElementPlus from './plugins/element'
import axios from "axios"
// 自定义全局css
import './assets/css/global.css'
import locale from 'element-plus/lib/locale/lang/zh-cn' 

const app = createApp(App)

app.config.globalProperties.$http = axios
app.use(ElementPlus,{ locale })
app.use(router).mount('#app')