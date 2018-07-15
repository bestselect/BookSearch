<template>
  <div>
    <b-card-group deck>
      <book-card v-for="n in remainCount" v-bind:key="(startIndex * 3) + n - 1" v-bind="booklist[(startIndex * 3) + n - 1]" />
    </b-card-group>
  </div>
</template>
<script>
import { mapGetters } from 'vuex'
import BookCard from 'src/components/BookCard'

export default {
  name: 'bookdeck',
  props: ['startIndex'],
  components: {
    BookCard
  },
  computed: {
    ...mapGetters({ booklist: 'booklist' }),
    remainCount: function () {
      return Math.min(3, this.booklist.length - (this.startIndex * 3))
    }
  }
}
</script>
<style lang='css'>
.thumbnail {
    max-width: 6em;
}
.bookcard {
    margin-top: 15px;
}
</style>
