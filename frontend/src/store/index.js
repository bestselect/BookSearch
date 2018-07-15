import Vue from 'vue'
import Vuex from 'vuex'
import router from 'src/router'
import UrlBuilder from 'src/custom/urlbuilder'

Vue.use(Vuex)

const LOGIN = 'LOGIN'
const LOGOUT = 'LOGOUT'
const SETBOOKLIST = 'SETBOOKLIST'
const SETSEARCHOPTION = 'SETSEARCHOPTION'
const SETKEYWORD = 'SETKEYWORD'
const SETBOOKMARK = 'SETBOOKMARK'
const SETBOOKLISTSOURCE = 'SETBOOKLISTSOURCE'

export function findBookMark (bookmarks, data) {
  let bm = bookmarks.find(t => {
    for (let key of Object.keys(data)) {
      if (t[key] !== data[key]) {
        return false
      }
    }
    return true
  })
  return bm
}

function initialState () {
  return {
    booklistSource: 'search',
    booklist: undefined,
    keywords: [],
    seachOption: {
      keyword: '',
      size: 9,
      page: 1,
      sort: 'title',
      order: 'desc'
    },
    user: undefined,
    bookmarks: undefined
  }
}

export default new Vuex.Store({
  state: initialState(),
  mutations: {
    [SETSEARCHOPTION] (state, option) {
      state.seachOption = {
        ...state.seachOption,
        ...option
      }
    },
    [SETKEYWORD] (state, keywords) {
      state.keywords = keywords
    },
    [LOGIN] (state, user) {
      state.user = user
    },
    [LOGOUT] (state) {
      let obj = initialState()
      Object.keys(obj).forEach(key => {
        state[key] = obj[key]
      })
    },
    [SETBOOKLIST] (state, booklist) {
      state.booklist = booklist
    },
    [SETBOOKMARK] (state, bookmarks) {
      state.bookmarks = bookmarks
    },
    [SETBOOKLISTSOURCE] (state, source) {
      state.booklistSource = source
    }
  },
  actions: {
    async addKeyword ({commit, dispatch}, params) {
      let url = UrlBuilder.Create().AppendUser(this.state.user).AppendUrl('keyword').ToString()
      let result = await this._vm.$http.post(url, {content: params.keyword})
      return result.data
    },
    async removeKeyword ({commit, dispatch}, params) {
      let targets = this.state.keywords.filter(t => t.content === params.keyword)
      if (targets.length > 0) {
        let url = UrlBuilder.Create().AppendUser(this.state.user).AppendUrl('keywords').ToString()
        let result = await this._vm.$http({method: 'delete', url: url, data: {content: params.keyword}})
        commit(SETKEYWORD, result.data)
      }
    },
    async search ({commit, dispatch}, params) {
      if (params.keyword) {
        commit(SETKEYWORD, await dispatch('addKeyword', params))
      }
      commit(SETSEARCHOPTION, {
        ...params
      })
      commit(SETBOOKLISTSOURCE, 'search')
      commit(SETBOOKLIST, await dispatch('getBookList'))
    },
    async getKeywords ({commit, dispacth}) {
      let url = UrlBuilder.Create().AppendUser(this.state.user).AppendUrl('keywords').ToString()
      let result = await this._vm.$http.get(url)
      return result.data
    },
    async login ({commit, dispatch}, params) {
      let url = UrlBuilder.Create().AppendUrl('users').AppendQuery('access_token', params).ToString()
      let result = await this._vm.$http.put(url)
      commit(LOGIN, result.data)
      commit(SETKEYWORD, await dispatch('getKeywords', params))
      commit(SETBOOKMARK, await dispatch('getBookmarks', params))
      dispatch('redirect', {path: '/search'})
    },
    logout ({commit, dispatch}) {
      commit(LOGOUT)
      dispatch('redirect', {path: '/'})
    },
    redirect ({commit, dispatch}, params) {
      let route = router.app.$route
      router.replace(route.query.redirect || params.path)
    },
    async getBookList ({commit, dispatch}, params) {
      let url = UrlBuilder.Create().AppendUrl('search').AppendQuery('keyword', this.state.seachOption).ToString()
      let result = await this._vm.$http.get(url)
      return result.data
    },
    async getBookmarks ({commit, dispatch}, params) {
      let url = UrlBuilder.Create().AppendUser(this.state.user).AppendUrl('bookmark').ToString()
      let result = await this._vm.$http.get(url)
      return result.data
    },
    async addBookMark ({commit, dispatch}, params) {
      let url = UrlBuilder.Create().AppendUser(this.state.user).AppendUrl('bookmark').ToString()
      let result = await this._vm.$http.post(url, params.book)
      commit(SETBOOKMARK, result.data)
    },
    async removeBookMark ({commit, dispatch}, params) {
      let bm = findBookMark(this.state.bookmarks, params.book)
      if (bm) {
        let url = UrlBuilder.Create().AppendUser(this.state.user).AppendUrl('bookmark').AppendUrl(bm.id).ToString()
        let result = await this._vm.$http({method: 'delete', url: url})
        commit(SETBOOKMARK, result.data)
        if (this.state.booklistSource === 'bookmark') {
          dispatch('showBookMarks')
        }
      }
    },
    showBookMarks ({commit, dispatch}) {
      commit(SETBOOKLISTSOURCE, 'bookmark')
      let booklist = {
        books: this.getters.bookmarks,
        meta: {
          total_count: this.getters.bookmarks.length,
          is_end: false,
          pageable_count: 1000
        }
      }
      commit(SETBOOKLIST, booklist)
    }
  },
  getters: {
    keywords (state) {
      return state.keywords.map(t => t.content)
    },
    isLoggedIn (state) {
      return state.user !== undefined
    },
    currentUser (state) {
      return state.user
    },
    booklist (state) {
      return state.booklist === undefined ? [] : state.booklist.books
    },
    currentSearchOption (state) {
      return state.seachOption
    },
    currentBookListSize (state) {
      return state.booklist === undefined ? 0 : state.booklist.meta.total_count
    },
    bookmarks (state) {
      return state.bookmarks
    },
    booklistSource (state) {
      return state.booklistSource
    }
  }
})
