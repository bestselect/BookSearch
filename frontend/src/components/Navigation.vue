<template>
  <nav class="navbar navbar-default">
    <h2>안녕하세요! {{currentUser.name}}님.</h2>
    <ul class="nav navbar-nav navbar-right">
      <li>
        <button class="btn btn-warning bookmark" @click="showBookMarks">북마크</button>
        <button class="btn btn-secondary logout" @click="logout">로그아웃</button>
      </li>
    </ul>
  </nav>
</template>

<script>
import { mapGetters } from 'vuex'
import Kakao from 'kakao'

export default {
  name: 'navigation',
  computed: {
    ...mapGetters({ currentUser: 'currentUser' })
  },
  methods: {
    logout () {
      let app = this
      Kakao.Auth.logout(function () {
        app.$store.dispatch('logout')
      })
    },
    showBookMarks () {
      this.$store.dispatch('showBookMarks')
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.navbar-right { margin-right: 0px !important}
.bookmark {
  width: 105px;
  margin: 5px 0 0 0;
}
.logout {
  width: 105px;
  margin: 5px -15px 0 0;
}
</style>
