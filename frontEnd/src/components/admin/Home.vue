<template>
  <Layout class="home-container">
    <Header class="header">
      <div>
        <span>后台管理系统</span>
      </div>
      <iButton type="info" @click="logout">退出</iButton>
    </Header>
    <!-- 页面主体区域 -->
    <Layout>
      <!-- 侧边栏 -->
      <Sider class="sider">
        <!-- 侧边栏菜单区域 -->
        <Menu class="menu" theme="dark" width="auto" accordion>
          <MenuItem name="userInfo" :to="`/admin/user`">
            <Icon type="md-person"></Icon>
            <span>用户信息</span>
          </MenuItem>
          <MenuItem name="product" :to="`/admin/product`">
            <Icon type="md-pricetag"></Icon>
            <span>商品信息</span>
          </MenuItem>
          <MenuItem name="demand" :to="`/admin/demand`">
            <Icon type="md-pricetag"></Icon>
            <span>求购信息</span>
          </MenuItem>
          <MenuItem name="agency" :to="`/admin/agency`">
            <Icon type="md-stats"></Icon>
            <span>统计分析</span>
          </MenuItem>
          <!-- <Submenu name="stat">
            <template slot="title">
              <Icon type="md-stats" />
              统计分析
            </template>
            <MenuItem name="agency" :to="`/admin/agency`">
              <Icon type="md-cash"></Icon>
              中介费
            </MenuItem>
          </Submenu> -->
        </Menu>
      </Sider>
      <!-- 右侧内容主体 -->
      <Content class="content">
        <!-- 路由占位符 -->
        <router-view></router-view>
      </Content>
    </Layout>
  </Layout>
</template>

<script>
export default {
  data () {
    return {}
  },
  methods: {
    logout () {
      window.sessionStorage.clear()
      this.$router.push('/admin/login')
    }
  }
}
</script>

<style scoped>
.home-container {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  min-height: 100%;
  height: auto;
}

.header {
  background-color: #373d41;
  display: flex;
  justify-content: space-between;
  padding-left: 0;
  align-items: center;
  color: #fff;
  font-size: 20px;
}

.header span {
  display: flex;
  align-items: center;
  margin-left: 25px;
}

.sider {
  background-color: #515a6e;
}

.menu {
  width: 100%;
  border: none;
}

.content {
  /* background-color: #eaedf1; */
  margin: 10px 10px 0 10px;
}
</style>
