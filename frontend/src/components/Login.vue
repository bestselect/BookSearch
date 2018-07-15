<template>
  <div class="login-wrapper border border-light">
    <div class="text-center">
      <h2 class="form-signin-heading">책검색 서비스를 이용하기위해</h2>
      <h2 class="form-signin-heading">카카오아이디로 로그인해주세요.</h2>
      <a id="kakao-login-btn"></a>
      <h2 class="form-signin-heading" v-if="error !== ''">{{error}}</h2>
    </div>
  </div>
</template>
<script>
import Kakao from 'kakao'

export default {
  name: 'Login',
  data () {
    return {
      error: ''
    }
  },
  created () {
    Kakao.cleanup()
    Kakao.init('31c589b611dc053afed70e572aace08d')
  },
  mounted () {
    let token = Kakao.Auth.getAccessToken()
    if (token) {
      this.$store.dispatch('login', {access_token: token})
    } else {
      let app = this
      Kakao.Auth.createLoginButton({
        container: '#kakao-login-btn',
        success: function (authObj) {
          this.error = ''
          app.$store.dispatch('login', {access_token: authObj.access_token})
        },
        fail: function (err) {
          this.error = JSON.stringify(err)
        }
      })
    }
  },
  methods: {
  }
}
</script>

<style lang="css">
.login-wrapper {
  width: 70%;
  margin: 12% auto;
}
.form-signin {
  max-width: 440px;
  padding: 5% 15px;
  margin: 0 auto;
}
.form-signin .form-signin-heading {
  margin-bottom: 10px;
}
</style>
