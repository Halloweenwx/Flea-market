<template>
  <div>
    <div class="footer">
      <div class="w">
        <!-- mod_service -->
        <div class="mod_service">
          <ul>
            <li>
              <i class="mod_service-icon mod_service_zheng"></i>
              <div class="mod_service_tit">
                <h5>正品保障</h5>
                <p>正品保障，提供发票</p>
              </div>
            </li>
            <li>
              <i class="mod_service-icon mod_service_kuai"></i>
              <div class="mod_service_tit">
                <h5>正品保障</h5>
                <p>正品保障，提供发票</p>
              </div>
            </li>
            <li>
              <i class="mod_service-icon mod_service_bao"></i>
              <div class="mod_service_tit">
                <h5>正品保障</h5>
                <p>正品保障，提供发票</p>
              </div>
            </li>
            <li>
              <i class="mod_service-icon mod_service_bao"></i>
              <div class="mod_service_tit">
                <h5>正品保障</h5>
                <p>正品保障，提供发票</p>
              </div>
            </li>
            <li>
              <i class="mod_service-icon mod_service_bao"></i>
              <div class="mod_service_tit">
                <h5>正品保障</h5>
                <p>正品保障，提供发票</p>
              </div>
            </li>
          </ul>
        </div>
        <!-- mod_help -->
        <div class="mod_help">
          <dl class="mod_help_item">
            <dt>购物指南</dt>
            <dd><a href="#">购物流程</a></dd>
            <dd><a href="#">会员介绍</a></dd>
          </dl>
          <dl class="mod_help_item">
            <dt>购物指南</dt>
            <dd><a href="#">购物流程</a></dd>
            <dd><a href="#">会员介绍</a></dd>
          </dl>
          <dl class="mod_help_item">
            <dt>购物指南</dt>
            <dd><a href="#">购物流程</a></dd>
            <dd><a href="#">会员介绍</a></dd>
          </dl>
          <dl class="mod_help_item">
            <dt>购物指南</dt>
            <dd><a href="#">购物流程</a></dd>
            <dd><a href="#">会员介绍</a></dd>
          </dl>
          <dl class="mod_help_item">
            <dt>购物指南</dt>
            <dd><a href="#">购物流程</a></dd>
            <dd><a href="#">会员介绍</a></dd>
          </dl>
          <dl class="mod_help_item mod_help_app">
            <dt>帮助中心</dt>
            <dd>
              <img src="img/wx_cz.jpg" alt="" />
              <p>品优购客户端</p>
            </dd>
          </dl>
        </div>
        <!-- mod copyright -->
        <div class="mod_copyright">
          <p class="mod_copyright_links">
            关于我们 | 联系我们 | 联系客服
          </p>
          <p class="mod_copyright_info">
            地址：北京市海淀区西土城路
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {}
</script>

<style>
@import "../assets/css/common.css";
/* footer */
.footer {
  height: 386px;
  background-color: #f5f5f5;
  padding-top: 30px;
}
.mod_service {
  height: 100px;
  border-bottom: 1px solid #ccc;
}
.mod_service li {
  float: left;
  width: 240px;
  height: 79px;
}
.mod_service-icon {
  float: left;
  width: 73px;
  height: 74px;
  /* background: url(../img/icons.png) no-repeat; */
  margin-left: 35px;
}
.mod_service_zheng {
  /* background-position: -253px -3px; */
  background: url(https://icon.fengniao.com/fn_mall/images/home-v2/guarantee-icon1.png);
}
.mod_service_tit {
  float: left;
  margin-left: 5px;
}
.mod_service_tit h5 {
  margin: 5px 0;
}
.mod_service_kuai {
  /* background-position: -255px -54px; */
  background: url(https://icon.fengniao.com/fn_mall/images/home-v2/guarantee-icon2.png);
}
.mod_service_bao {
  /* background-position: -257px -105px; */
  background: url(https://icon.fengniao.com/fn_mall/images/home-v2/guarantee-icon3.png);
}
.mod_help {
  height: 187px;
  border-bottom: 1px solid #ccc;
}
.mod_help_item {
  float: left;
  width: 150px;
  padding: 20px 0 0 50px;
}
.mod_help_item dt {
  height: 25px;
  font-size: 16px;
}
.mod_help_item dd {
  height: 22px;
}
.mod_help_app dt,
.mod_help_app p {
  padding-left: 12px;
}
.mod_help_app img {
  margin: 7px 0;
}
.mod_copyright {
  text-align: center;
}
.mod_copyright_links {
  margin: 20px 0 15px 0;
}
.mod_copyright_info {
  line-height: 18px;
}

</style>
