<template>
  <div>
    <Layout class="layout">
      <Sider class="side-bar" :collapsed-width="78">
        <Menu theme="light" :width="auto" @on-select="onSelect" accordion>
          <div class="user-icon">
            <!-- <div class="user-img">
              <img src="static/img/head.png" />
            </div> -->
            <p>Gavin</p>
          </div>
          <MenuItem name="info">
            <Icon type="md-person"></Icon>
            <span>个人信息</span>
          </MenuItem>
          <Submenu name="address">
            <template slot="title">
              <Icon type="md-locate"></Icon>
              <span>收货地址</span>
            </template>
            <MenuItem name="myAddress">我的收货地址</MenuItem>
            <MenuItem name="addAddress">添加收货地址</MenuItem>
          </Submenu>
          <MenuItem name="order">
            <Icon type="md-clipboard"></Icon>
            <span>购物订单</span>
          </MenuItem>
          <MenuItem name="shoppingCar">
            <Icon type="ios-cart"></Icon>
            <span>购物车</span>
          </MenuItem>
        </Menu>
      </Sider>
      <Layout class="layout">
        <Header :style="{ background: '#fff' }">
          <h2>{{ activeTitle }}</h2>
        </Header>
        <Content class="content">
          <transition mode="out-in">
            <router-view></router-view>
          </transition>
        </Content>
        <Footer class="layout-footer-center">2018 &copy; Gavin</Footer>
      </Layout>
    </Layout>
  </div>
</template>

<script>
export default {}
</script>

<style scoped>
.side-bar {
  height: "calc(100vh - 35px)";
  background: "#fff";
  overflow: "auto";
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
.content {
  margin: 15px;
  background-color: #fff;
  padding: 15px;
}
.layout-footer-center {
  padding: 0px 15px;
  padding-bottom: 15px;
  text-align: center;
}
</style>
