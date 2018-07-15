<template>
    <div class="container-fluid search-wrapper">
      <navigation />
      <form @submit.prevent="search">
          <div class="input-group">
              <input ref='keyword' type="text" class="form-control" aria-label="Text input with segmented dropdown button" v-model="keyword">
              <div class="input-group-append">
                  <button type="submit" class="btn btn-outline-secondary">Search</button>
                  <button type="button" class="btn btn-outline-secondary dropdown-toggle dropdown-toggle-split" @click="toggleMode">
                  <span class="sr-only">Toggle Dropdown</span>
                  </button>
              </div>
          </div>
          <div class="list-group" v-if="mode==='history'">
              <div v-for="keyword in keywords">
                  <div class="row history-row">
                    <a href="#" class="list-group-item list-group-item-action col-sm" @click="selectHistory(keyword)">{{keyword}}</a>
                    <button type="button" class="btn btn-light col-sm-1" @click="removeHistory(keyword)">x</button>
                  </div>
              </div>
          </div>
      </form>
    </div>
</template>
<script>
import { mapGetters } from 'vuex'
import Navigation from 'src/components/Navigation'

export default {
  name: 'Search',
  components: {
    Navigation
  },
  computed: {
    ...mapGetters({ keywords: 'keywords' })
  },
  data () {
    return {
      keyword: '',
      mode: 'typing'
    }
  },
  methods: {
    search () {
      this.$store.dispatch('search', {
        keyword: this.keyword,
        page: 1
      })
    },
    toggleMode () {
      this.mode = this.mode === 'history' ? 'typing' : 'history'
    },
    selectHistory (keyword) {
      this.mode = 'typing'
      this.keyword = keyword
      this.$refs.keyword.focus()
    },
    removeHistory (keyword) {
      this.$store.dispatch('removeKeyword', {
        keyword: keyword
      })
    }
  }
}
</script>
<style lang="css">
.search-wrapper {
  width: 70%;
  margin: 5% auto;
}
.history-row {
  margin-right: 0px;
  margin-left: 0px;
}
</style>