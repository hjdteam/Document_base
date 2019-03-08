<template>
  <div>
    
    <group :title="item.copyright" v-for="(item, i) in items" :key="i">
      <cell :value="item.title" is-link></cell>
      <div class="vux-demo">
      <img class="logo" :src="item.url">
      <h1></h1>
    </div>
    </group>
  </div>
</template>

<script>
import { Group, Cell } from 'vux'
import axios from 'axios'
import qs from 'qs'

export default {
  components: {
    Group,
    Cell
  },
  data () {
    return {
      // note: changing this line won't causes changes
      // with hot-reload because the reloaded component
      // preserves its current state and we are modifying
      // its initial state.
      msg: 'Hello World!',
      page: 1,
      pageSize: 10,
      items: []
    }
  },
  mounted: function () {
    this.getData()
  },
  methods: {
    getData () {
      var that = this
      let params = qs.stringify({
        token: localStorage.getItem('token'),
        page: this.page,
        rows: this.pageSize
      })
      axios.get('http://127.0.0.1:8080/list?' + params)
        .then(function (response) {
          console.log(response)
          if (response.data.status === 200) {
            let result = response.data
            for (let idata = 0; idata < result.rows.length; idata++) {
              result.rows[idata].url = 'http://www.bing.com/' + result.rows[idata].url
            }
            that.items = result.rows
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
  text-align: center;
}
.logo {
  width: 90%;
  height: 90%;
}
</style>
