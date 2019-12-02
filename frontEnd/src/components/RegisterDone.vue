<template>
  <div class="container">
    <Icon type="ios-checkmark-circle-outline"></Icon>
    <p>注册成功</p>
    <p>{{ countDownTime }}秒钟后跳转至首页</p>
  </div>
</template>

<script>
import { mapMutations } from 'vuex'
export default {
  data () {
    return {
      countDownTime: 5
    }
  },
  created () {
    this.countDown()
  },
  methods: {
    ...mapMutations(['done']),
    countDown () {
      this.interval = setInterval(() => {
        this.countDownTime--
        if (this.countDownTime === 0) {
          clearInterval(this.interval)
          this.done()
          this.$router.push('/')
        }
      }, 1000)
    }
  }
}
</script>

<style scoped>
.container {
  width: 100%;
  font-size: 90px;
  color: #2d8cf0;
  text-align: center;
}
.btn-success {
  margin-top: 30px;
}
p {
  font-size: 20px;
}
</style>
