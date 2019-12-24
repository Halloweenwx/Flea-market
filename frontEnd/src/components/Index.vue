<template>
  <div>
    <Navi></Navi>
    <!-- 商品显示区域 -->
    <!-- 卖 -->
    <div class="content">
      <div class="items">
        <!-- 头部 -->
        <div class="items-head">
          <div class="items-icon">
            <img src="../assets/img/clock.png" />
          </div>
          <div class="items-text">
            <span class="items-title">二手闲置</span>
            <span class="items-remarks">总有你想不到的低价</span>
          </div>
        </div>
        <!-- 内容 -->
        <div class="items-content">
          <div class="items-item" v-for="(item, index) in sellList" :key="index">
            <div class="items-item-img">
              <router-link to="/detail">
                <img :src="item.img" />
              </router-link>
            </div>
            <div class="items-item-info">
              <p>{{ item.intro }}</p>
              <p>
                <span class="items-price text-danger">
                  <Icon type="social-yen"></Icon>
                  {{ item.price }}
                </span>
                <span class="items-old-price">
                  <s>{{ item.realPrice }}</s>
                </span>
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- 买 -->
    <div class="content">
      <div class="items">
        <!-- 头部 -->
        <div class="items-head">
          <div class="items-icon">
            <img src="@/assets/img/clock.png" />
          </div>
          <div class="items-text">
            <span class="items-title">求购信息</span>
            <span class="items-remarks">你身边的小伙伴正需要</span>
          </div>
        </div>
        <!-- 内容 -->
        <div class="items-content">
          <div class="items-item" v-for="(item, index) in buyList" :key="index">
            <div class="items-item-img">
              <router-link to="/detail">
                <img :src="item.img" />
              </router-link>
            </div>
            <div class="items-item-info">
              <p>{{ item.intro }}</p>
              <p>
                <span class="items-price text-danger">
                  <Icon type="social-yen"></Icon>
                  {{ item.price }}
                </span>
                <span class="items-old-price">
                  <s>{{ item.realPrice }}</s>
                </span>
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Navi from "../components/Navi";
// import { mapActions, mapState } from 'vuex'
export default {
  data() {
    return {
      buyList: null,
      sellList: null
    };
  },
  methods: {
    // ...mapActions(['loadSellList']),
    loadSellList() {
      const { data: res } = this.$http.get("fore/item/idle");
      if (res.code !== 200) return this.$Message.error("获取待售物品失败");
      this.sellList = res.data;
    },
    loadBuyList() {
      const { data: res } = this.$http.get("fore/item/demand");
      if (res.code !== 200) return this.$Message.error("获取求购物品失败");
      this.buyList = res.data;
    },
    searchItems(value) {
      this.$router.push("/search");
    }
  },
  created() {
    this.loadSellList();
  },
  // computed: {
  //   ...mapState(['sellList', 'buyList'])
  // },
  components: {
    Navi
  }
};
</script>

<style scoped>
@import "../assets/css/base.css";
/* itemList start */
.content {
  width: 1008px;
  margin: 0px auto 50px;
}
/*物品展示*/
.items {
  width: 100%;
  height: 330px;
  margin: 15px auto;
  background-color: #fff;
}
.items-head {
  width: 100%;
  height: 50px;
  background-color: #e01222;
}
.items-icon {
  width: 68px;
  height: 100%;
  float: left;
}
.items-icon img {
  width: 35px;
  height: 35px;
  margin-top: 6px;
  margin-left: 15px;
  animation-name: shake-clock;
  animation-duration: 0.3s;
  animation-iteration-count: infinite; /*设置无线循环*/
}
/*定义闹钟震动动画关键帧*/
@keyframes shake-clock {
  0% {
    transform: rotate(-8deg);
  }
  50% {
    transform: rotate(8deg);
  }
  100% {
    transform: rotate(-8deg);
  }
}
.items-text {
  width: 300px;
  height: 100%;
  float: left;
}
.items-text .items-title {
  font-size: 22px;
  line-height: 50px;
  color: #fff;
}
.items-text .items-remarks {
  margin-left: 5px;
  font-size: 10px;
  color: #fff;
}
.items-content {
  margin-top: 10px;
  width: 100%;
  height: 280px;
}
.items-item {
  width: 183px;
  height: 250px;
  margin-top: 15px;
  margin-left: 15px;
  box-shadow: 0px 0px 8px #ccc;
  cursor: pointer;
  float: left;
}
.items-item-img {
  width: 160px;
  height: 160px;
  margin: 0px auto;
  overflow: hidden;
  border-bottom: 1px solid #ccc;
  background-color: #fff;
}
.items-item-img img {
  width: 130px;
  height: 130px;
  margin-left: 15px;
  margin-top: 15px;
  transition: margin-top 0.3s;
}
.items-item-img:hover img {
  margin-top: 6px;
  transition: margin-top 0.3s;
}
.items-item-info {
  padding: 5px;
  padding-left: 15px;
  padding-right: 15px;
  font-size: 12px;
  color: #009688;
}
.items-item-info i:first-child {
  font-size: 14px;
}
.items-price {
  margin-right: 5px;
  font-size: 25px;
  font-weight: bold;
}
</style>
