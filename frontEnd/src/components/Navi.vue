<template>
  <div>
    <!-- header start -->
    <div class="header w">
      <!-- logo -->
      <div class="logo">
        <h1>
          <a href="/" title="TET">TET</a>
        </h1>
      </div>
      <!-- search -->
      <div class="search">
        <Input 
          enter-button="搜索"
          placeholder="请输入内容"
          class="box"
          v-model="searchInfo.query"
          search
          auto-complete
          @on-search="search"
        />
        
        <!-- <input type="text" class="text" value="请搜索" />
        <button class="btn">搜索</button>-->
      </div>
      <!-- hotwords -->
      <div class="hotwords">
        <a href="#" class="style-red">优惠购</a>
        <a href="#">百亿补贴</a>
        <a href="#">1元购</a>
        <a href="#">满100-100</a>
        <a href="#">想不出来了</a>
      </div>
      <!-- shopcar -->
      <div class="shopcar">
        <a href="/#/shoppingcar">
          <i class="car"></i>
          我的购物车
          <i class="arrow"></i>
          <!-- <i class="count">{{this.cartCount}}</i> -->
        </a>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  data(){
    return{
      searchInfo:{
        query:''
      },
      itemList:[],
      cartCount:0
    }
  },
  methods: {
    changeResult() {
      console.log(this.itemList)
      this.$emit("resultChanged",this.itemList);
    },
    async getCartCnt(){
      const { data: res } = await this.$http.get("/cart/num");
      if (res.code !== 200) return this.$Message.error(res.msg);
      console.log(res.data)
      this.cartCount = res.data.cartNum;
    },
    async search(key, index) {
      if (key === "type") this.searchInfo.category = this.tags.value[index];
      const { data: res } = await this.$http.get("/fore/search", {
        params: this.searchInfo
      });
      console.log(res);
      if (res.code !== 200) return this.$Message.error(res.msg);
      this.itemList = res.data.content;
      this.changeResult();
      if (this.$route.path !== "/search"){
        this.$router.push({
          path:"/search",
          query:{
            list: this.itemList
          }
        })
      }
    },
    mounted() {
      this.getCartCnt();
    }
  },
  computed: {
    ...mapState(["cartCount"])
  }
};
</script>

<style scoped>
@import "../assets/css/base.css";
/* header区域 */
.header {
  position: relative;
  height: 120px;
}
.logo {
  position: absolute;
  top: 0px;
  left: 50px;
  width: 114px;
  height: 114px;
}
.logo a {
  display: block;
  width: 114px;
  height: 114px;
  background: url("../assets/logo.jpg");
  font-size: 0;
}
.search {
  position: absolute;
  top: 25px;
  left: 460px;
}
.search .box {
  width: 300px;
}
.text {
  float: left;
  width: 445px;
  height: 32px;
  border: 2px solid #b1191a;
  padding-left: 10px;
  color: #ccc;
}
.btn {
  float: left;
  width: 82px;
  height: 32px;
  background-color: #b1191a;
  border: 0;
  font-size: 16px;
  color: #fff;
}
.hotwords {
  position: absolute;
  top: 65px;
  left: 450px;
}
.hotwords a {
  margin: 0 10px;
}
.shopcar {
  position: absolute;
  top: 25px;
  right: 80px;
  width: 138px;
  height: 34px;
  border: 1px solid #dfdfdf;
  background-color: #f7f7f7;
  line-height: 34px;
  font-size: 12px;
}
.car {
  font-family: "icomoon";
  margin-left: 13px;
  margin-right: 5px;
  color: #da5555;
}
.arrow {
  font-family: "icomoon";
}
.count {
  position: absolute;
  top: -5px;
  left: 100px;
  background-color: #e60012;
  height: 14px;
  padding: 0 3px;
  line-height: 14px;
  color: #fff;
  border-radius: 7px 7px 7px 0;
}
</style>
