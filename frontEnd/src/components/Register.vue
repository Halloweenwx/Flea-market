<template>
  <div>
    <Row class="container w">
      <iCol span="12" class="signup-img-box">
        <img src="../assets/img/signup-sale.png" alt="" />
      </iCol>
      <iCol span="10" offset="1" class="box">
        <div class="sign-up-title">
          <h1>欢迎注册账号</h1>
        </div>
        <div class="sing-up-step-box">
          <Steps :current="this.$store.state.registerStep">
            <Step title="验证手机号" icon="md-phone-portrait"></Step>
            <Step title="填写账号信息" icon="md-person-add"></Step>
            <Step title="注册成功" icon="md-checkmark-circle-outline"></Step>
          </Steps>
          <div class="sign-up-box">
            <transition mode="out-in">
              <router-view></router-view>
            </transition>
          </div>
        </div>
      </iCol>
    </Row>
  </div>
</template>

<script>
export default {
  data () {
    return {
    }
  }
}
</script>

<style scoped>
.container {
  margin: 15px auto 0;
  overflow: hidden;
}
.signup-img-box {
  height: 500px;
  display: flex;
  align-items: center;
  justify-content: center;
}
.signup-img-box img {
  height: 80%;
}
.sign-up-title {
  width: 430px;
  margin: 10px auto 25px;
}
.sing-up-step-box {
  margin: 0px auto;
  width: 430px;
  height: 400px;
}
.sign-up-box {
  margin: 30px 25px;
  width: 350px;
  display: flex;
  align-items: center;
}
</style>
