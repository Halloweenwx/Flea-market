<template>
  <div>
    <!-- 顶部快捷导航start -->
    <div class="shortcut">
      <div class="w">
        <div class="fl">
          <ul>
            <li>品优购欢迎您！</li>
            <li>
              <router-link to="/login">请登录</router-link>
              <router-link to="/register" href="#" class="style-red"
                >免费注册</router-link
              >
            </li>
          </ul>
        </div>
        <div class="fr">
          <ul>
            <li><a href="#">我的订单</a></li>
            <li class="spacer"></li>
            <li>
              <a href="#">我的品优购</a>
              <i class="icomoon"></i>
            </li>
            <li class="spacer"></li>
            <li><a href="#">网站导航</a></li>
          </ul>
        </div>
      </div>
    </div>
    <!-- 顶部快捷导航end -->
  </div>
</template>

<script>
export default {}
</script>

<style scoped>
@import "../assets/css/common.css";
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
