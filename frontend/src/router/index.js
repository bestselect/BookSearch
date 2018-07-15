import Vue from 'vue'
import Router from 'vue-router'
import Login from 'src/components/Login'
import Search from 'src/components/Search'
import store from 'src/store'

Vue.use(Router)

const requireLogin = () => (from, to, next) => {
  if (store.getters.isLoggedIn) {
    return next()
  } else {
    next('/')
  }
}

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/search',
      name: 'Search',
      component: Search,
      beforeEnter: requireLogin()
    }
  ]
})

