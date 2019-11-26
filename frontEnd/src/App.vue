<template>
  <div id="app">
    <Header></Header>
    <router-view />
    <!-- 部分路由不应该包含这个Footer -->
    <Footer v-if="excludeRoutes.indexOf($route.name) == -1"></Footer>
  </div>
</template>

<script>
import Header from '@/components/Header'
import Footer from '@/components/Footer'
export default {
  name: 'app',
  data () {
    return {
      excludeRoutes: [
      ]
    }
  },
  components: {
    Header,
    Footer
  }
}
</script>

<style>
</style>
