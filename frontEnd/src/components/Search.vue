<template>
  <div>
    <Navi></Navi>
    <!-- 筛选 -->
    <div class="item-class-show w">
      <Row
        class="item-class-group"
        v-for="(items, index) in tagsInfo"
        :key="index"
      >
        <i-col class="item-class-name" span="3">{{ items.tagName }}</i-col>
        <i-col class="item-class-select" span="21">
          <span
            v-for="(item, subIndex) in items.tags"
            :key="subIndex"
            @click="searchItems(index, item)"
            >{{ item }}</span
          >
        </i-col>
      </Row>
    </div>
    <!-- 商品展示容器 -->
    <div class="goods-box">
      <div class="goods-list-box w">
        <div class="goods-list-tool">
          <ul>
            <li
              v-for="(item, index) in isBuy"
              :key="index"
              @click="selectBuyOrSell(item.value, index)"
            >
              <span :class="{ 'goods-list-tool-active': isSelected[index] }"
                >{{ item.label }}
              </span>
            </li>
          </ul>
        </div>
        <div class="goods-list">
          <div
            class="goods-show-info"
            v-for="(item, index) in searchList"
            :key="index"
            @click="viewDetails(item)"
          >
            <div class="goods-show-img">
              <img :src="item.img" />
            </div>
            <div class="goods-show-price">
              <span>
                <Icon type="logo-yen" class="number"></Icon>
                <span class="number">{{ item.price }}</span>
              </span>
            </div>
            <div class="goods-show-detail">
              <span>{{ item.intro }}</span>
            </div>
            <div class="goods-show-num">
              已有<span>{{ item.remarks }}</span
              >人评价
            </div>
            <div class="goods-show-seller">
              <span>{{ item.shopName }}</span>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="goods-page w">
      <Page :total="100" show-sizer></Page>
    </div>
  </div>
</template>

<script>
import Navi from '../components/Navi'
import { mapActions, mapState } from 'vuex'
export default {
  data () {
    return {
      tagsInfo: [
        {
          tagName: '物品类型',
          tags: ['电子类', '日常用品类', '书籍类']
        },
        {
          tagName: '价格区间',
          tags: ['0-100', '100-1000', '1000-10000']
        }
      ],
      selectedType: '',
      selectedPrice: '',
      selectedBuy: true,
      isSelected: [true, false],
      isBuy: [
        {
          label: '我要买',
          value: true
        },
        {
          label: '我要卖',
          value: false
        }
      ]
    }
  },
  methods: {
    ...mapActions(['loadSearchList']),
    searchItems (index, item) {
      if (index === 0) this.selectedType = item
      else this.selectedPrice = item
    },
    selectBuyOrSell (isBuy, index) {
      this.isSelected = [false, false]
      this.isSelected[index] = true
      this.selectBuy = isBuy
      // this.$Message.success(isBuy ? '我要买' : '我要卖')
    },
    viewDetails (item) {
      // this.$Message.success('查看详情')
      this.$router.push('/detail')
    }
  },
  computed: {
    ...mapState(['searchList'])
  },
  created () {
    this.loadSearchList()
  },
  components: {
    Navi
  }
}
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
.goods-page {
  margin: 20px auto 20px;
  display: flex;
  justify-content: flex-end;
}
</style>
