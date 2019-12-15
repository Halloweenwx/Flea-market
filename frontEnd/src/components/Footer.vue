<template>
  <div>
    <div class="footer w">
      <div>
        <!-- mod_service -->
        <div class="mod_service">
          <ul>
            <li>
              <i class="mod_service-icon mod_service_baozhang"></i>
              <div class="mod_service_tit">
                <span>精选商品</span>
                <!-- <p>垃圾商品过滤</p> -->
                <!-- <ul>
                  <li>垃圾商品过滤</li>
                  <li>全程客服辅助</li>
                </ul> -->
              </div>
            </li>
            <li>
              <i class="mod_service-icon mod_service_fukuan"></i>
              <div class="mod_service_tit">
                <span>安全支付</span>
                <!-- <p>正品保障，提供发票</p> -->
              </div>
            </li>
            <li>
              <i class="mod_service-icon mod_service_fangxin"></i>
              <div class="mod_service_tit">
                <span>货款放心</span>
                <!-- <p>正品保障，提供发票</p> -->
              </div>
            </li>
            <li>
              <i class="mod_service-icon mod_service_jiufen"></i>
              <div class="mod_service_tit">
                <span>纠纷维权</span>
                <!-- <p>正品保障，提供发票</p> -->
              </div>
            </li>
          </ul>
        </div>
        <!-- mod_help -->
        <div class="mod_help w">
          <dl class="mod_help_item">
            <dt>新手上路</dt>
            <dd><a href="/">购物流程</a></dd>
            <dd><a href="/">免费注册</a></dd>
            <dd><a href="/">发布商品</a></dd>
            <dd><a href="/">交易安全</a></dd>
            <dd><a href="/">联系客服</a></dd>
          </dl>
          <dl class="mod_help_item">
            <dt>支付方式</dt>
            <dd><a href="/">支付宝</a></dd>
            <dd><a href="/">网银支付</a></dd>
            <dd><a href="/">信用卡</a></dd>
            <dd><a href="/">公司转账</a></dd>
          </dl>
          <dl class="mod_help_item">
            <dt>购物指南</dt>
            <dd><a href="/">购物流程</a></dd>
            <dd><a href="/">会员介绍</a></dd>
          </dl>
          <dl class="mod_help_item">
            <dt>售后保障</dt>
            <dd><a href="/">保障范围</a></dd>
            <dd><a href="/">退换货流程</a></dd>
            <dd><a href="/">信息举报</a></dd>
            <dd><a href="/">退款说明</a></dd>
          </dl>
          <dl class="mod_help_item mod_help_app">
            <dt>帮助中心</dt>
            <dd>
              <img src="../assets/img/qrcode.png" alt="" />
              <p>生活不易<br>打赏一下</p>
            </dd>
          </dl>
        </div>
        <!-- mod copyright -->
        <div class="mod_copyright">
          <p class="mod_copyright_links">
            关于我们 | 联系我们 | 联系客服
          </p>
          <p class="mod_copyright_info">
            地址：北京市海淀区
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {}
</script>

<style scoped>
@import '../assets/css/common.css';
/* footer */
.footer {
  width: 100%;
  background-color: #eaeaea;
  padding-top: 30px;
}
.mod_service {
  height: 100px;
  border-bottom: 1px solid #b2dfdb;
}
.mod_service li {
  float: left;
  width: 25%;
  height: 74px;
}
.mod_service-icon {
  float: left;
  width: 73px;
  height: 74px;
  margin-left: 75px;
}
.mod_service_baozhang {
  background: url('../assets/img/guarantee-icon1.png');
}
.mod_service_tit {
  float: left;
  margin-left: 5px;
}
.mod_service_tit span {
  margin: 0 10px;
  color: #444444;
  font-size: 18px;
  font-weight: bold;
  line-height: 74px;
}
.mod_service_fukuan {
  background: url('../assets/img/guarantee-icon2.png');
}
.mod_service_fangxin {
  background: url('../assets/img/guarantee-icon3.png');
}
.mod_service_jiufen {
  background: url('../assets/img/guarantee-icon4.png');
}
.mod_help {
  width: 100%;
  height: 200px;
  border-bottom: 1px solid #b2dfdb;
  background-color: #fff;
}
.mod_help_item {
  float: left;
  width: 20%;
  padding: 25px 0 0 80px;
}
.mod_help_item dt {
  color: #444444;
  height: 25px;
  font-size: 16px;
  margin-bottom: 10px;
}

.mod_help_app dt,
.mod_help_app p {
  padding-left: 12px;
}
.mod_help_app dd img{
  width: auto;
  height: auto;
  max-width: 50%;
  max-height: 50%;
}
.mod_help_app dd p {
  margin-top: 5px;
  margin-left: 10px;
  width: 100px;
}
.mod_copyright {
  padding-top: 10px;
  padding-bottom: 20px;
  text-align: center;
  background-color: #000;
  color: #fff;
}
.mod_copyright_links {
  margin: 0px 0 15px 0;
}
.mod_copyright_info {
  line-height: 18px;
}
</style>
