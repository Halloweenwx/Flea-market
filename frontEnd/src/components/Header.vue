<template>
  <!-- 顶部快捷导航 -->
  <div class="shortcut">
    <div class="w">
      <div class="fl">
        <ul>
          <li>TET欢迎您！</li>
          <li>
            <router-link to="/login">请登录 | </router-link>
            <router-link to="/register" class="style-red">免费注册</router-link>
          </li>
        </ul>
      </div>
      <div class="fr">
        <ul>
          <li><router-link to="/shoppingcar">我的购物车</router-link></li>
          <li class="spacer"></li>
          <li>
            <router-link to="/info">我的TET</router-link>
            <!-- <i class="icomoon"></i> -->
          </li>
          <li class="spacer"></li>
          <li><router-link to="/">网站导航</router-link></li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
export default {}
</script>

<style scoped>
@import '../assets/css/common.css';
/* 顶部快捷导航 */
.shortcut {
  height: 31px;
  background-color: #f1f1f1;
  line-height: 31px;
}
.shortcut li {
  float: left;
}
</style>
