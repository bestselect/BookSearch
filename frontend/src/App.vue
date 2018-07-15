<template>
  <div id="app">
    <div class="container-flow">
      <router-view></router-view>
    </div>
    <div class="container-flow content-wrapper">
      <book-list />
    </div>
  </div>
</template>

<script>
import BookList from 'src/components/BookList'

export default {
  name: 'app',
  components: {
    BookList
  }
}
</script>

<style>
.navigation-wrapper {
  width: 70%;
  margin: 3% auto -3% auto;
}
.content-wrapper {
  width: 70%;
  margin: 5% auto;
}
</style>
