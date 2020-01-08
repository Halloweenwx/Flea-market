<template>
  <div>
    <!-- 面包屑导航区域 -->
    <Breadcrumb class="navi">
      <BreadcrumbItem to="/admin/home">首页</BreadcrumbItem>
      <BreadcrumbItem>用户信息</BreadcrumbItem>
    </Breadcrumb>

    <!-- 卡片视图 -->
    <Card>
      <!-- 搜索与添加区 -->
      <Row :gutter="50">
        <iCol :span="7">
          <iInput placeholder="请输入内容" v-model="queryInfo.query" @on-clear="getUserList">
            <iButton slot="append" icon="ios-search-outline" @click="getUserList"></iButton>
          </iInput>
        </iCol>
      </Row>

      <!-- 求购物品列表区域 -->
      <Table
        :data="userList"
        :columns="columns"
        class="userList"
        border
        stripe
        no-data-text="这里空空如也~"
      ></Table>

      <!-- 分页区域 -->
      <Page
        class="page"
        :current="queryInfo.pagenum"
        :page-size-opts="[1, 2, 5, 10]"
        :page-size="queryInfo.pageSize"
        :total="total"
        show-total
        show-elevator
        show-sizer
        @on-page-size-change="handleSizeChange"
        @on-change="handleCurrentChange"
      ></Page>
    </Card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 获取物品列表的参数对象
      queryInfo: {
        query: "",
        // 当前的页数
        pagenum: 1,
        // 当前每页显示多少条数据
        pageSize: 2
      },
      userList: [],
      total: 100,
      columns: [
        {
          type: "index"
        },
        {
          title: "用户名",
          key: "username"
        },
        {
          title: "姓名",
          key: "realName"
        },
        {
          title: "手机号",
          key: "phone"
        },
        {
          title: "注册城市",
          key: "city"
        },
        {
          title: "用户级别",
          key: "memberLevel"
        },
        {
          title: "注册时间",
          key: "registerTime"
        },
        {
          title: "修改时间",
          key: "modifiedTime"
        }
      ]
    };
  },
  methods: {
    async getUserList() {
      const { data: res } = await this.$http.get("admin/user", {
        params: this.queryInfo
      });
      if (res.code !== 200) return this.$Message.error(res.msg);
      this.userList = res.data.content;
      for (var i in this.userList) {
        if (this.userList[i].city)
          this.userList[i].city = this.userList[i].city.label;
      }
      this.total = res.data.total;
    },
    // 监听pagesize改变的事件
    handleSizeChange(newSize) {
      this.queryInfo.pageSize = newSize;
      this.getItemList();
    },
    // 监听页码值改变的事件
    handleCurrentChange(newPage) {
      this.queryInfo.pageNum = newPage;
      this.getItemList();
    }
  },
  created() {
    this.getUserList();
  }
};
</script>

<style scoped>
.navi {
  margin-bottom: 10px;
  font-size: 12px;
  margin-left: 5px;
}
.userList {
  margin-top: 20px;
}
.page {
  margin: 20px 0 20px 1px;
  font-size: 12px;
}
.modify {
  margin-right: 20px;
}
</style>
