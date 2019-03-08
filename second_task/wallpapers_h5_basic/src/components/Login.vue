<template>
  <div class="mh-form-box">
    <div class="vux-demo">
      <img class="logo" src="../assets/logo.png">
      <h1>{{ msg }}</h1>
    </div>
    <group>
      <x-input title="账户" v-model="user_name" placeholder="请输入账号"></x-input>
      <x-input title="密码" v-model="password" type="password" placeholder="密码"></x-input>
    </group>
    <group>
      <x-button @click.native="handleSubmit" type="primary">立即登录</x-button>
    </group>
  </div>
</template>

<script>
import Vue from 'vue'
import { Alert, XInput, Group, XButton, Cell } from 'vux'
import qs from 'qs'
import axios from 'axios'

export default {
  components: {
    XInput,
    XButton,
    Group,
    Cell,
    Alert
  },
  mounted () {
  },
  data () {
    return {
      msg: '壁纸网',
      user_name: 'turbozero',
      password: 'trust40s'
    }
  },
  methods: {
    handleSubmit () {
      let that = this
      let qsLogin = qs.stringify({
        loginId: this.user_name,
        loginPassword: this.password
      })
      axios.get('http://127.0.0.1:8080/auth?' + qsLogin)
        .then(function (response) {
          console.log(response)
          if (response.data.status === 200) {
            localStorage.setItem('token', response.data.token)
            that.$router.push({
              name: 'wallpaper_list'
            })
          } else {
            Vue.$vux.alert.show({
              title: '错误提示',
              content: response.data.message
            })
          }
        })
        .catch(function (error) {
          console.log(error)
        })
    }
  }
}
</script>

<style>
.vux-demo {
  margin: 20px;
  text-align: center;
}
.logo {
  width: 100px;
  height: 100px;
}
</style>
