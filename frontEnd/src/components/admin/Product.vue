<template>
  <div>
    <!-- 面包屑导航区域 -->
    <Breadcrumb class="navi">
      <BreadcrumbItem to="/admin/home">首页</BreadcrumbItem>
      <BreadcrumbItem>商品信息</BreadcrumbItem>
    </Breadcrumb>

    <!-- 卡片视图 -->
    <Card>
      <!-- 搜索与添加区 -->
      <Row :gutter="50">
        <iCol :span="7">
          <iInput
            placeholder="请输入内容"
            v-model="queryInfo.query"
            @on-clear="getItemList"
          >
            <iButton
              slot="append"
              icon="ios-search-outline"
              @click="getItemList"
            ></iButton>
          </iInput>
        </iCol>
      </Row>

      <!-- 求购物品列表区域 -->
      <Table
        :data="itemList"
        :columns="columns"
        class="itemList"
        border
        stripe
        no-data-text="这里空空如也~"
      >
        <template slot-scope="{ row, index }" slot="action">
          <!-- 查看 -->
          <Tooltip effect="dark" content="查看详情" placement="top">
            <iButton
              content="查看详情"
              type="primary"
              icon="md-list-box"
              @click="showDetail(index)"
            ></iButton>
          </Tooltip>
        </template>
      </Table>

      <!-- 分页区域 -->
      <Page
        class="page"
        :current="queryInfo.pagenum"
        :page-size-opts="[1, 2, 5, 10]"
        :page-size="queryInfo.pagesize"
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
        query: '',
        // 当前的页数
        pagenum: 1,
        // 当前每页显示多少条数据
        pagesize: 2
      },
      itemList: [
        {
          type: '电子类',
          name: '笔记本',
          lowestPrice: 10,
          isFixed: '是',
          state: '待售'
        }
      ],
      total: 100,
      columns: [
        {
          type: 'index'
        },
        {
          title: '物品类型',
          key: 'type'
        },
        {
          title: '物品名称',
          key: 'name'
        },
        {
          title: '最低价',
          key: 'lowestPrice'
        },
        {
          title: '是否一口价',
          key: 'isFixed'
        },
        {
          title: '状态',
          key: 'state'
        },
        {
          title: '操作',
          slot: 'action'
        }
      ]
    }
  },
  methods: {
    showDetail(index) {
      this.$Modal.info({
        title: '查看详情',
        content: '111<br/>111'
      })
    }
  }
}
</script>

<style scoped>
.navi {
  margin-bottom: 10px;
  font-size: 12px;
  margin-left: 5px;
}
.itemList {
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
