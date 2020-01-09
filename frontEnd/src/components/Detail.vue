<template>
<!-- 详情对话框 -->
    <Modal v-model="detailVisiable" title="商品详情">
      <List>
        <ListItem>
          <ListItemMeta
            avatar="https://dev-file.iviewui.com/userinfoPDvn9gKWYihR24SpgC319vXY8qniCqj4/avatar"
            title="卖家用户名"
            :description="detail.username"
          />
        </ListItem>
        <ListItem>
          <ListItemMeta
            avatar="https://dev-file.iviewui.com/userinfoPDvn9gKWYihR24SpgC319vXY8qniCqj4/avatar"
            title="物品类型"
            :description="detail.category"
          />
        </ListItem>
        <ListItem>
          <ListItemMeta
            avatar="https://dev-file.iviewui.com/userinfoPDvn9gKWYihR24SpgC319vXY8qniCqj4/avatar"
            title="物品名称"
            :description="detail.name"
          />
        </ListItem>
        <ListItem>
          <ListItemMeta
            avatar="https://dev-file.iviewui.com/userinfoPDvn9gKWYihR24SpgC319vXY8qniCqj4/avatar"
            title="物品最低价格"
            :description="detail.lowestPrice"
          />
        </ListItem>
        <ListItem>
          <ListItemMeta
            avatar="https://dev-file.iviewui.com/userinfoPDvn9gKWYihR24SpgC319vXY8qniCqj4/avatar"
            title="是否一口价"
            :description="detail.priceFixed"
          />
        </ListItem>
        <ListItem>
          <ListItemMeta
            avatar="https://dev-file.iviewui.com/userinfoPDvn9gKWYihR24SpgC319vXY8qniCqj4/avatar"
            title="竞价结束日期"
            :description="detail.endDate"
          />
        </ListItem>
        <ListItem>
          <ListItemMeta
            avatar="https://dev-file.iviewui.com/userinfoPDvn9gKWYihR24SpgC319vXY8qniCqj4/avatar"
            title="物品描述"
            :description="detail.description"
          />
        </ListItem>
      </List>
    </Modal>
  <!-- <div>
    <Navi></Navi>
    <Row class="w" type="flex" align="middle">
      <iCol span="12">
        <div class="itemImg">
          <img src="../assets/img/goodsList/item-show-1.jpg" />
        </div>
      </iCol>
      <iCol span="12">
        <div class="detail">
          <List size="large" border>
            <template slot="footer">
              <div class="add">
                <iButton type="error" @click="addToCart">添加至购物车</iButton>
              </div>
            </template>
            <ListItem>
              <ListItemMeta
                avatar="https://dev-file.iviewui.com/userinfoPDvn9gKWYihR24SpgC319vXY8qniCqj4/avatar"
                title="所有者"
                :description="item.owner"
              />
            </ListItem>
            <ListItem>
              <ListItemMeta
                avatar="https://dev-file.iviewui.com/userinfoPDvn9gKWYihR24SpgC319vXY8qniCqj4/avatar"
                title="商品名称"
                :description="item.name"
              />
            </ListItem>
            <ListItem>
              <ListItemMeta
                avatar="https://dev-file.iviewui.com/userinfoPDvn9gKWYihR24SpgC319vXY8qniCqj4/avatar"
                title="价格"
                :description="item.price"
              />
            </ListItem>
          </List>
        </div>
      </iCol>
    </Row>
  </div> -->
</template>

<!--
<script>
import Navi from "./Navi.vue";
export default {
  data() {
    return {
      item: {
        owner: "侯瀛",
        name: "手机壳",
        price: "12"
      }
    };
  },
  methods: {
    addToCart() {}
  },
  components: {
    Navi
  }
};
</script>

<style scoped>
.w {
  width: 1200px;
  height: 485px;
  margin: 0 auto;
}
.itemImg {
  margin: 0 auto;
  text-align: center;
}
.itemImg img {
  width: 50%;
  height: 50%;
  max-width: 100%;
  max-height: 100%;
}
.detail {
  margin: 0 auto;
}
.add {
  text-align: right;
}
</style>
-->