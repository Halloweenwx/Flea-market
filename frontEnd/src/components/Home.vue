<template>
  <div>
    <Layout class="layout">
      <Sider class="side-bar" :collapsed-width="100" style="">
        <Menu theme="light" width="auto" accordion>
          <div class="user-icon">
            <div class="user-img">
              <img src="../assets/img/qrcode.png" />
            </div>
            <p>{{ this.username }}</p>
          </div>
          <MenuItem name="info" :to="`/info`">
            <Icon type="md-person"></Icon>
            <span>个人信息</span>
          </MenuItem>
          <MenuItem name="buy" :to="`/buy`">
            <Icon type="ios-pricetag"></Icon>
            <span>我要买</span>
          </MenuItem>
          <MenuItem name="sell" :to="`/sell`">
            <Icon type="ios-pricetag"></Icon>
            <span>我要卖</span>
          </MenuItem>
          <!-- <Submenu name="address">
            <template slot="title">
              <Icon type="md-locate"></Icon>
              <span>收货地址</span>
            </template>
            <MenuItem name="Address" :to="`/address`">我的收货地址</MenuItem>
            <MenuItem name="addAddress" :to="`/address/add`"
              >添加收货地址</MenuItem
            >
          </Submenu>
          <MenuItem name="order" :to="`/order`">
            <Icon type="md-clipboard"></Icon>
            <span>交易明细</span>
          </MenuItem> -->
        </Menu>
      </Sider>
      <Content class="content">
        <router-view></router-view>
      </Content>
    </Layout>
  </div>
</template>

<script>
export default {
  data () {
    return {
      username: 'admin'
    }
  },
  methods: {}
}
</script>

<style scoped>
.side-bar {
  height: 600px;
  background: #fff;
  overflow: auto;
}
.side-bar a {
  color: #232323;
}
.user-icon {
  height: 200px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.user-icon span {
  font-size: 96px;
}
.user-img {
  margin-bottom: 15px;
  width: 96px;
  height: 96px;
  border-radius: 48px;
  overflow: hidden;
}
.user-img img {
  width: 100%;
}
.Header p {
  height: 50px;
  text-align: center;
}
content {
  width: 100%;
  margin: 0 auto;
  display: flex;
}
/* .content {
  height: 600px;
  margin: 0 0 15px 0;
  padding: 15px;
} */
.layout-footer-center {
  padding: 0px 15px;
  padding-bottom: 15px;
  text-align: center;
}
.content {
  margin: 10px 10px 0 10px;
}
</style>
