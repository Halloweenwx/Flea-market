<template>
  <div class="goods-list-container">
    <Alert show-icon class="tips-box">
      小提示
      <Icon type="md-bulb" slot="icon"></Icon>
      <template slot="desc">请点击商品前的选择框，选择购物车中的商品，点击付款即可。</template>
    </Alert>
    <Table
      border
      ref="cart"
      :columns="columns"
      :data="shoppingCart"
      size="large"
      no-data-text="您的购物车没有商品，请先添加商品到购物车再点击购买"
      @on-selection-change="select"
    >
      <template slot-scope="{ row, index }" slot="price">
        <iInput
          type="text"
          v-model="row.dealPrice"
          v-if="row.priceFixed === true"
          @on-change="update(row, index)"
        ></iInput>
        <span v-else>{{ row.dealPrice }}</span>
      </template>

      <!-- <template slot-scope="{ row, index }" slot="count">
        <iInput type="text" v-model="row.count" @on-change="update(row, index)"></iInput>
      </template>-->
    </Table>
    <div class="pay-container">
      <div class="pay-box">
        <p>
          <span>应付总额（不含中介费）：</span>
          <span class="money">
            <Icon type="logo-yen"></Icon>
            {{ this.totalPrice.toFixed(2) }}
          </span>
        </p>
        <div class="pay-btn">
          <router-link to="/pay">
            <Button type="error" size="large" @click="submit" :disabled="this.payDisable">支付订单</Button>
          </router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapMutations } from "vuex";
export default {
  beforeRouteEnter(to, from, next) {
    window.scrollTo(0, 0);
    next();
  },
  data() {
    return {
      payDisable:true,
      goodsCheckList: [],
      columns: [
        {
          type: "selection",
          width: 58,
          align: "center"
        },
        {
          title: "图片",
          key: "img",
          width: 86,
          render: (h, params) => {
            console.log(params);
            return h("div", [
              h("img", {
                attrs: {
                  src: params.row.img,
                  width: 86
                }
              })
            ]);
          },
          align: "center"
        },
        {
          title: "名称",
          key: "name",
          align: "center"
        },
        {
          title: "描述",
          key: "description",
          align: "center"
        },
        {
          title: "类型",
          width: 198,
          key: "type",
          align: "center"
        },
        {
          title: "当前所有者",
          key: "owner",
          width: 100,
          align: "center"
          // slot: "count"
        },
        {
          title: "价格",
          width: 90,
          key: "dealPrice",
          align: "center",
          slot: "price"
        }
      ],
      shoppingCart: [
        // {
        //   goods_id: "1529931938150",
        //   isSelected: true,
        //   count: 1,
        //   img: require("../assets/img/1.jpg"),
        //   type: "竞价",
        //   price: 28,
        //   title: "苹果8/7手机壳iPhone7 Plus保护壳全包防摔磨砂硬外壳"
        // },
        // {
        //   goods_id: "1529931938150",
        //   isSelected: true,
        //   count: 1,
        //   img: require("../assets/img/1.jpg"),
        //   type: "一口价",
        //   price: 28,
        //   title: "苹果8/7手机壳iPhone7 Plus保护壳全包防摔磨砂硬外壳"
        // }
      ],
      shoppingCartTable: [
        // {
        //   goods_id: "1529931938150",
        //   isSelected: true,
        //   count: 1,
        //   img: "static/img/goodsDetail/pack/1.jpg",
        //   type: "竞价",
        //   price: 28,
        //   title: "苹果8/7手机壳iPhone7 Plus保护壳全包防摔磨砂硬外壳"
        // },
        // {
        //   goods_id: "1529931938150",
        //   isSelected: true,
        //   count: 1,
        //   img: "static/img/goodsDetail/pack/1.jpg",
        //   type: "一口价",
        //   price: 28,
        //   title: "苹果8/7手机壳iPhone7 Plus保护壳全包防摔磨砂硬外壳"
        // }
      ],
      remarks: "",
      // totalPrice: 0,
      selected: []
    };
  },
  computed: {
    totalPrice() {
      let price = 0;
      this.shoppingCartTable.forEach(value => {
        if (value.isSelected) {
          price += value.dealPrice;
        }
      });
      return price;
    }
  },
  methods: {
    ...mapMutations(["changeCartCount"]),
    select(selection) {
      // var cnt = 0;
      console.log(this.$refs.cart.objData);
      for (var i in this.$refs.cart.objData) {
        if (this.$refs.cart.objData[i]._isChecked) {
          this.shoppingCartTable[i].isSelected = true;
          // cnt += 1;
        } else this.shoppingCartTable[i].isSelected = false;
      }
      this.payDisable = this.totalPrice === 0;
      // this.changeCartCount(cnt);
    },
    async update(row, index) {
      const oldPrice = this.shoppingCartTable[index].price;
      this.shoppingCartTable[index].price = row.price;
      const { data: res } = await this.$http.post(
        "cart/item/update",
        this.shoppingCartTable
      );
      if (res.code !== 200) {
        this.shoppingCartTable[index].price = oldPrice;
        return this.$Message.error("更新购物车失败");
      }
      // this.shoppingCartTable[index].count = row.count;
    },
    async getshoppingCart() {
      const { data: res } = await this.$http.get("cart");
      if (res.code !== 200) return this.$Message.error("获取购物车信息失败");
      for (var i in res.data) {
        res.data[i].type = res.data[i].category.cnCategory;
        res.data[i].owner = res.data[i].belong.username;
        res.data[i].isSelected = false;
        res.data[i].dealPrice = res.data[i].startPrice;
        if (res.data[i].pictures) res.data[i].img = res.data[i].pictures[0].url;
      }
      this.shoppingCart = this.shoppingCartTable = res.data;
    },
    async submit() {
      var buyItems = [];
      for (var i in this.shoppingCartTable) {
        if (this.shoppingCartTable[i].isSelected) {
          buyItems.push(this.shoppingCartTable[i]);
        }
      }
      console.log(buyItems);
      const { data: res } = await this.$http.post("/cart/buy", buyItems);
      if (res.code !== 200) return this.$Message.error("提交订单失败");
      this.$Message.success("提交订单成功");
    }
  },
  created() {
    this.getshoppingCart();
  }
  // mounted() {
  //   this.$refs.cart.selectAll(true);
  // }
};
</script>

<style scoped>
.goods-list-container {
  margin: 15px auto;
  width: 1000px;
}
.tips-box {
  margin-bottom: 15px;
}
.address-container {
  margin-top: 15px;
}
.address-box {
  margin-top: 15px;
  padding: 15px;
  border: 1px #ccc dotted;
}
.address-check {
  margin: 15px 0px;
  height: 36px;
  display: flex;
  align-items: center;
}
.address-check-name {
  width: 120px;
  display: flex;
  justify-content: center;
  align-content: center;
  background-color: #ccc;
}
.address-check-name span {
  width: 120px;
  height: 36px;
  font-size: 14px;
  line-height: 36px;
  text-align: center;
  color: #fff;
  background-color: #f90013;
}
.address-detail {
  padding-left: 15px;
  font-size: 14px;
  color: #999999;
}
.remarks-container {
  margin: 15px 0px;
}
.remarks-input {
  margin-top: 15px;
}
.invoices-container p {
  font-size: 12px;
  line-height: 30px;
  color: #999;
}
.pay-container {
  margin: 15px;
  display: flex;
  justify-content: flex-end;
}
.pay-box {
  font-size: 18px;
  font-weight: bolder;
  color: #495060;
}
.money {
  font-size: 26px;
  color: #f90013;
}
.pay-btn {
  margin: 15px 0px;
  display: flex;
  justify-content: flex-end;
}
</style>
