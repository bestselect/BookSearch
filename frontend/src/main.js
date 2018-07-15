import Vue from 'vue'
import App from './App'
import router from 'src/router'
import axios from 'src/custom/vue_axios'
import store from 'src/store'
import BootstrapVue from 'bootstrap-vue'

Vue.use(BootstrapVue)

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  axios,
  store,
  template: '<App/>',
  components: { App }
})
