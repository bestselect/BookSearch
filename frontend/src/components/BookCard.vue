<template>
  <b-card
    class="bookcard"
    align="center">
    <b-card-img 
      :src=thumbnailPost
      alt="Image"
      class="thumbnail" />
    <p class="card-text">{{$props.title}}</p>
    <b-button :href=url
      variant="primary">구매하기</b-button>
    <b-button variant="warning" v-if="isBookMarked" @click="removeBookMark">★</b-button>
    <b-button variant="warning" v-if="!isBookMarked" @click="addBookMark">☆</b-button>
    <div slot="footer">
        <b-badge>{{$props.priceText}}</b-badge>
        <b-badge>{{$props.status}}</b-badge>
        <b-badge>{{$props.publisher}}</b-badge>
        <b-badge v-for="author in authorList" v-bind:key="author">{{author}}</b-badge>
    </div>
  </b-card>
</template>
<script>
import { mapGetters } from 'vuex'
import { findBookMark } from 'src/store'

export default {
  name: 'bookcard',
  props: [
    'isbn',
    'title',
    'price',
    'url',
    'authors',
    'barcode',
    'category',
    'contents',
    'datetime',
    'ebook_barcode',
    'publisher',
    'sale_price',
    'sale_yn',
    'status',
    'thumbnail',
    'translators'
  ],
  computed: {
    priceText: function () {
      return this.sale_price + '원'
    },
    authorList: function () {
      return this.$props.authors.split(',')
    },
    thumbnailPost: function () {
      return this.$props.thumbnail ? this.$props.thumbnail : 'https://placekitten.com/144/200'
    },
    ...mapGetters({ bookmarks: 'bookmarks' }),
    isBookMarked: function () {
      return findBookMark(this.bookmarks, this.$props)
    }
  },
  methods: {
    addBookMark () {
      this.$store.dispatch('addBookMark', {
        book: this.$props
      })
    },
    removeBookMark () {
      this.$store.dispatch('removeBookMark', {
        book: this.$props
      })
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