// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import FastClick from 'fastclick'
import VueRouter from 'vue-router'
import App from './App'
import Login from './components/Login'
import Home from './components/HelloFromVux'

Vue.use(VueRouter)

const routes = [
  {
    name: 'login',
    path: '/',
    component: Login,
    meta: { title: '登录' }
  },
  {
    name: 'wallpaper_list',
    path: '/wallpaper_list',
    component: Home,
    meta: { title: '壁纸列表' }
  }
]

const router = new VueRouter({
  routes
})

FastClick.attach(document.body)

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  router,
  render: h => h(App)
}).$mount('#app-box')
