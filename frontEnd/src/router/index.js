import Vue from 'vue'
import VueRouter from 'vue-router'
import Add from '../components/Add.vue'
import Address from '../components/Address.vue'
import Buy from '../components/Buy.vue'
import CheckPhone from '../components/CheckPhone.vue'
import Detail from '../components/Detail.vue'
import Home from '../components/Home.vue'
import Index from '../components/Index.vue'
import Info from '../components/Info.vue'
import InputInfo from '../components/InputInfo.vue'
import Login from '../components/Login.vue'
import Order from '../components/Order.vue'
import Pay from '../components/Pay.vue'
import PayDone from '../components/PayDone.vue'
import Register from '../components/Register.vue'
import RegisterDone from '../components/RegisterDone.vue'
import Search from '../components/Search.vue'
import Sell from '../components/Sell.vue'
import ShoppingCart from '../components/ShoppingCart.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/index',
    component: Index
  },
  {
    path: '/',
    redirect: '/index'
  },
  {
    path: '/search',
    component: Search
  },
  {
    path: '/detail',
    component: Detail
  },
  {
    path: '/register',
    component: Register,
    children: [
      {
        path: '/',
        component: CheckPhone
      },
      {
        path: '/checkphone',
        component: CheckPhone
      },
      {
        path: '/inputinfo',
        component: InputInfo
      },
      {
        path: '/registerdone',
        component: RegisterDone
      }
    ]
  },
  {
    path: '/login',
    component: Login
  },
  {
    path: '/home',
    name: 'home',
    component: Home,
    redirect: '/info',
    children: [
      { path: '/info', name: 'info', component: Info },
      { path: '/address', name: 'address', component: Address },
      { path: '/address/add', name: 'add', component: Add },
      { path: '/order', name: 'order', component: Order },
      { path: '/buy', name: 'buy', component: Buy },
      { path: '/sell', name: 'sell', component: Sell }
    ]
  },
  {
    path: '/shoppingcar',
    component: ShoppingCart
  },
  {
    path: '/pay',
    component: Pay
  },
  {
    path: '/paydone',
    component: PayDone
  }
]

const router = new VueRouter({
  routes
})

export default router
