<template>
  <div>
    <book-deck v-for="n in deckSize" v-bind:key="n" v-bind:startIndex="n - 1" />
    <pagination v-if="booklistSource === 'search' && deckSize > 0" />
  </div>
</template>
<script>
import { mapGetters } from 'vuex'
import BookDeck from 'src/components/BookDeck'
import Pagination from 'src/components/Pagination'

export default {
  name: 'booklist',
  components: {
    BookDeck,
    Pagination
  },
  computed: {
    ...mapGetters({ booklist: 'booklist' }),
    ...mapGetters({ booklistSource: 'booklistSource' }),
    deckSize: function () {
      return Math.ceil(this.booklist.length / 3)
    }
  }
}
</script>
