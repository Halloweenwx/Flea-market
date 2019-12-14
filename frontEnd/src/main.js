import axios from 'axios'
import Vue from 'vue'
import App from './App.vue'
import './assets/css/base.css'
import './plugins/iview.js'
import router from './router'
import store from './store'
// 配置请求的根路径
axios.defaults.baseURL = 'http://127.0.0.1:8888/api/private/v1/'
axios.interceptors.request.use(config => {
  // console.log(config)
  config.headers.Authorization = window.sessionStorage.getItem('token')
  return config
})
Vue.prototype.$http = axios

Vue.config.productionTip = false

router.beforeEach((to, from, next) => {
  Vue.prototype.$Loading.start()
  next()
})

router.afterEach(route => {
  Vue.prototype.$Loading.finish()
})

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
