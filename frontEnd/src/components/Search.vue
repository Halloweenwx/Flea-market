<template>
  <div>
    <Navi v-on:resultChanged="updateResult"></Navi>
    <!-- 筛选 -->
    <div class="item-class-show w">
      <!-- <Row class="item-class-group" v-for="(items, index) in tagsInfo" :key="index">
        <i-col class="item-class-name" span="3">{{ items.tagName }}</i-col>
        <i-col class="item-class-select" span="21">
          <span
            v-for="(item, subIndex) in items.tags"
            :key="subIndex"
            @click="search(index, item)"
          >{{ item }}</span>
        </i-col>
      </Row>-->
      <Row class="item-class-group">
        <iCol class="item-class-name" span="3">物品类型</iCol>
        <i-col class="item-class-select" span="21">
          <span
            v-for="(item, index) in tags.label"
            :key="index"
            @click="search('type', index)"
          >{{ item }}</span>
        </i-col>
      </Row>
      <Row class="item-class-group" type="flex" align="middle">
        <iCol class="item-class-name" span="3">价格区间</iCol>
        <iCol class="lprice" span="1">
          <iInput v-model="searchInfo.lprice" />
        </iCol>—
        <iCol class="rprice" span="1">
          <iInput v-model="searchInfo.rprice" />
        </iCol>
        <iCol class="priceConfirm" span="1">
          <Button type="error" @click="search('price')">确定</Button>
        </iCol>
        <!-- <span></span>
        <iInput v-model="this.searchInfo.rprice" />>-->
      </Row>
    </div>
    <!-- 商品展示容器 -->
    <div class="goods-box">
      <div class="goods-list-box w">
        <div class="goods-list-tool">
          <!-- 我要买or我要卖 -->
          <ul>
            <li
              v-for="(item, index) in buyOrSell"
              :key="index"
              @click="selectBuyOrSell(item.value, index)"
            >
              <span :class="{ 'goods-list-tool-active': isSelected[index] }">{{ item.label }}</span>
            </li>
          </ul>
        </div>
        <div class="goods-list">
          <div class="goods-show-info" v-for="(item, index) in itemList" :key="index">
            <div class="goods-show-img" v-if="item.pictures">
              <img :src="item.pictures[0].url" />
            </div>
            <div class="goods-show-name">
              <span>{{ item.name }}</span>
            </div>
            <div class="goods-show-price">
              <span v-if="item.startPrice!==undefined">
                <Icon type="logo-yen" class="number"></Icon>
                <span class="number">{{ item.startPrice }}</span>
              </span>
              <span v-else>
                <Icon type="logo-yen" class="number"></Icon>
                <span class="number">{{ item.lowestPrice }}（心理价）</span>
              </span>
            </div>
            <div class="goods-show-detail">
              <span>{{ item.description }}</span>
            </div>
            <div class="goods-show-seller" v-if="item.belong">
              <span>所有者：{{ item.belong.username }}</span>
            </div>
            <div class="action">
              <Button type="info" @click="viewDetail(index)" class="goods-detail">查看详情</Button>
              <Button type="error" @click="addToCart(index)" class="goods-add">加入购物车</Button>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- 翻页 -->
    <!-- <div class="goods-page w">
      <Page :total="100" show-sizer></Page>
    </div>-->
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
  </div>
</template>

<script>
import Navi from "../components/Navi";
// import { mapActions, mapState } from "vuex";
export default {
  data() {
    return {
      // tagsInfo: [
      //   {
      //     tagName: "物品类型",
      //     tags: ["电子类", "日常用品类", "书籍类"]
      //   },
      //   {
      //     tagName: "价格区间",
      //     tags: ["0-100", "100-1000", "1000-10000"]
      //   }
      // ],
      tags: {
        label: ["电子类", "日常用品类", "书籍类"],
        value: ["ele", "daily", "book"]
      },
      searchInfo: {
        query:"",
        category: "",
        isIdle: true,
        lprice: "",
        rprice: ""
      },
      isSelected: [true, false],
      buyOrSell: [
        {
          label: "我要买",
          value: true
        },
        {
          label: "我要卖",
          value: false
        }
      ],
      itemList: [
        {
          img: "",
          price: "",
          intro: "",
          remarks: "",
          shopName: "",
          sale: ""
        }
      ],
      detailVisiable: false,
      detail: {}
    };
  },
  methods: {
    updateResult(list){
      this.itemList = list;
      console.log(list)
    },
    // ...mapActions(["loadSearchList"]),
    async search(key, index) {
      console.log(this.searchInfo.query)
      if (key === "type") this.searchInfo.category = this.tags.value[index];
      console.log(this.searchInfo);
      const { data: res } = await this.$http.get("/fore/search", {
        params: this.searchInfo
      });
      console.log(res);
      if (res.code !== 200) return this.$Message.error(res.msg);
      this.itemList = res.data.content;
      console.log(this.itemList);
    },

    selectBuyOrSell(isBuy, index) {
      this.isSelected = [false, false];
      this.isSelected[index] = true;
      this.searchInfo.isIdle = isBuy;
      this.search();
    },
    viewDetail(index) {
      this.detail = this.itemList[index];
      this.detailVisiable = true;
    },
    async addToCart(index) {
      const { data: res } = await this.$http.post("/cart/item/add", {
        iid: this.itemList[index].iid
      });
      console.log(res);
      if (res.code !== 200) return this.$Message.error(res.msg);
      this.$Message.success("成功加入购物车");
    }
  },
  // computed: {
  //   ...mapState(["itemList"])
  // },
  created() {
    this.itemList = this.$route.query.list;
  },
  components: {
    Navi
  }
};
</script>

<style scoped>
.item-class-show {
  margin: 15px auto;
}
.item-class-group {
  margin-top: 1px;
  height: 45px;
  border-bottom: 1px solid #ccc;
}
.item-class-group:first-child {
  border-top: 1px solid #ccc;
}
.item-class-name {
  padding-left: 15px;
  line-height: 44px;
  color: #666;
  font-weight: bold;
  background-color: #f3f3f3;
}
.item-class-name:first-child {
  line-height: 43px;
}
.item-class-select span {
  margin-left: 15px;
  width: 160px;
  color: #005aa0;
  line-height: 45px;
  cursor: pointer;
}
.lprice {
  margin: 0 10px;
}
.rprice {
  margin: 0 10px;
}
/* 搜索结果 */
.goods-box {
  display: flex;
}
/* ---------------商品栏开始------------------- */
.goods-list-tool {
  width: 100%;
  height: 38px;
  border: 1px solid #ccc;
  background-color: #f1f1f1;
}
.goods-list-tool ul {
  padding-left: 15px;
  list-style: none;
}
.goods-list-tool li {
  cursor: pointer;
  float: left;
}
.goods-list-tool span {
  padding: 5px 8px;
  border: 1px solid #ccc;
  border-left: none;
  line-height: 36px;
  background-color: #fff;
}
.goods-list-tool span:hover {
  border: 1px solid #e4393c;
}
.goods-list-tool i:hover {
  color: #e4393c;
}
.goods-list-tool-active {
  color: #fff;
  border-left: 1px solid #ccc;
  background-color: #e4393c !important;
}

.goods-list {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}
.goods-show-info {
  width: 240px;
  padding: 10px;
  margin: 15px 0px;
  border: 1px solid #fff;
  cursor: pointer;
}
.goods-show-info:hover {
  border: 1px solid #ccc;
  box-shadow: 0px 0px 15px #ccc;
}
.goods-show-price {
  margin-top: 6px;
}
.number {
  margin-right: 5px;
  font-size: 18px;
  font-weight: bold;
  color: #a94442;
}
.goods-show-img {
  width: 160px;
  height: 160px;
  margin: 0px auto;
  overflow: hidden;
}
.goods-show-name {
  font-size: 20px;
  margin: 6px 0px;
}
.goods-show-detail {
  font-size: 12px;
  margin: 6px 0px;
}
.goods-show-num {
  font-size: 12px;
  margin-bottom: 6px;
  color: #009688;
}
.goods-show-num span {
  color: #005aa0;
  font-weight: bold;
}
.goods-show-seller {
  font-size: 12px;
  color: #e4393c;
}
.action {
  margin-top: 10px;
  text-align: center;
}
.goods-detail {
  margin-right: 10px;
}
.goods-add {
  margin-left: 10px;
}
.goods-page {
  margin: 20px auto 20px;
  display: flex;
  justify-content: flex-end;
}
</style>
