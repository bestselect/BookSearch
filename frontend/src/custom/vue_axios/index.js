import Vue from 'vue'
import VueAxios from 'vue-axios'
import axios from './axios'

Vue.use(VueAxios, axios)

import VueLocalStorage from 'vue-ls'

let options = {
  namespace: 'vuejs__'
}

Vue.use(VueLocalStorage, options)
