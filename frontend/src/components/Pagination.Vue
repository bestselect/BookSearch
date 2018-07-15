<template>
  <div>
    <b-pagination ref="pageController" class="pagination" align="center" size="lg" :total-rows="currentBookListSize" @input="onChangePage" :per-page="currentSearchOption.size">
    </b-pagination>
  </div>    
</template>
<script>
import { mapGetters } from 'vuex'

export default {
  name: 'pagination',
  computed: {
    ...mapGetters({ currentSearchOption: 'currentSearchOption' }),
    ...mapGetters({ currentBookListSize: 'currentBookListSize' }),
    ...mapGetters({ booklistSource: 'booklistSource' })
  },
  methods: {
    onChangePage (page) {
      this.$store.dispatch('search', {
        page: page
      })
    }
  }
}
</script>

<style>
.pagination {
    margin-top: 3em;
}
</style>
