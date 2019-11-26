<template>
  <div>
    <!-- header start -->
    <div class="header w">
      <!-- logo -->
      <div class="logo">
        <h1><a href="index.html" title="品优购">品优购</a></h1>
      </div>
      <!-- search -->
      <div class="search">
        <input type="text" class="text" value="请搜索" />
        <button class="btn">搜索</button>
      </div>
      <!-- hotwords -->
      <div class="hotwords">
        <a href="#" class="style-red">优惠购</a>
        <a href="#">百亿补贴</a>
        <a href="#">1元购</a>
        <a href="#">满100-100</a>
        <a href="#">想不出来了</a>
      </div>
      <!-- shopcar -->
      <div class="shopcar">
        <i class="car"></i>
        我的购物车
        <i class="arrow"></i>
        <i class="count">8</i>
      </div>
    </div>
    <!-- header end -->
    <!-- nav start -->
    <div class="nav">
      <div class="w">
        <div class="dropdown fl">
          <div class="dt">全部商品分类</div>
          <div class="dd" style="display: none;">
            <ul>
              <li class="menu_item"><a href="#">家用电器</a><i></i></li>
              <li class="menu_item">
                <a href="#">手机 / 运营商 / 数码</a><i></i>
              </li>
            </ul>
          </div>
        </div>
        <!-- 右侧导航 -->
        <div class="navitems fl">
          <ul>
            <li><a href="#">服装</a></li>
            <li><a href="#">美妆</a></li>
            <li><a href="#">笔记本</a></li>
            <li><a href="#">二手书</a></li>
            <li><a href="#">家具</a></li>
            <li><a href="#">美食</a></li>
          </ul>
        </div>
      </div>
    </div>
    <!-- nav end -->
  </div>
</template>

<script>
export default {}
</script>

<style>
@import "../assets/css/base.css";
/* header区域 */
.header {
  position: relative;
  height: 105px;
}
.logo {
  position: absolute;
  top: 25px;
  left: 0px;
  width: 175px;
  height: 56px;
}
.logo a {
  display: block;
  width: 175px;
  height: 56px;
  background: url("../assets/img/logo.png");
  font-size: 0;
}
.search {
  position: absolute;
  top: 25px;
  left: 348px;
}
.text {
  float: left;
  width: 445px;
  height: 32px;
  border: 2px solid #b1191a;
  padding-left: 10px;
  color: #ccc;
}
.btn {
  float: left;
  width: 82px;
  height: 36px;
  background-color: #b1191a;
  border: 0;
  font-size: 16px;
  color: #fff;
}
.hotwords {
  position: absolute;
  top: 65px;
  left: 348px;
}
.hotwords a {
  margin: 0 10px;
}
.shopcar {
  position: absolute;
  top: 25px;
  right: 32px;
  width: 138px;
  height: 34px;
  border: 1px solid #dfdfdf;
  background-color: #f7f7f7;
  line-height: 34px;
  font-size: 12px;
}
.car {
  font-family: "icomoon";
  margin-left: 13px;
  margin-right: 5px;
  color: #da5555;
}
.arrow {
  font-family: "icomoon";
}
.count {
  position: absolute;
  top: -5px;
  left: 100px;
  background-color: #e60012;
  height: 14px;
  padding: 0 3px;
  line-height: 14px;
  color: #fff;
  border-radius: 7px 7px 7px 0;
}
/* nav start */
.nav {
  height: 45px;
  border-bottom: 2px solid #b1191a;
}
.dropdown {
  width: 209px;
  height: 45px;
}
.dropdown .dt {
  width: 100%;
  height: 100%;
  background-color: #b1191a;
  font-size: 16px;
  text-align: center;
  line-height: 45px;
  color: #fff;
}
.dropdown .dd {
  height: 465px;
  background-color: #c81623;
  margin-top: 2px;
}
.menu_item {
  height: 31px;
  line-height: 31px;
  margin-left: 1px;
  padding: 0 10px;
  transition: all 0.5s;
}
.menu_item:hover {
  background-color: #fff;
  padding-left: 20px;
}
.menu_item:hover a {
  color: #c81623;
}
.menu_item a {
  font-size: 14px;
  color: #fff;
}
.menu_item i {
  float: right;
  font-family: "icomoon";
  font-size: 18px;
  color: #fff;
}
.navitems {
  margin-left: 10px;
}
.navitems li {
  float: left;
}
.navitems li a {
  display: block;
  height: 100%;
  line-height: 45px;
  padding: 0 25px;
  font-size: 16px;
  color: #333;
}
</style>
