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
            <img src="static/img/index/clock.png" />
          </div>
          <div class="items-text">
            <span class="items-title">二手闲置</span>
            <span class="items-remarks">总有你想不到的低价</span>
          </div>
        </div>
        <!-- 内容 -->
        <div class="items-content">
          <div
            class="items-item"
            v-for="(item, index) in sellList"
            :key="index"
          >
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
            <img src="static/img/index/clock.png" />
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
import Navi from '../components/Navi'
import { mapActions, mapState } from 'vuex'
export default {
  data () {
    return {}
  },
  methods: {
    ...mapActions(['loadSellList']),
    searchItems (value) {
      this.$router.push('/search')
    }
  },
  created () {
    this.loadSellList()
  },
  computed: {
    ...mapState(['sellList', 'buyList'])
  },
  components: {
    Navi
  }
}
</script>

<style>
@import '../assets/css/base.css';
/* header区域 */
.header {
  position: relative;
  height: 105px;
}
.logo {
  position: absolute;
  top: 25px;
  left: 50px;
  width: 175px;
  height: 56px;
}
.logo a {
  display: block;
  width: 175px;
  height: 56px;
  background: url('../assets/img/logo.png');
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
  font-family: 'icomoon';
  margin-left: 13px;
  margin-right: 5px;
  color: #da5555;
}
.arrow {
  font-family: 'icomoon';
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
