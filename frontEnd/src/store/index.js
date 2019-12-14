import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    registerStep: 0,
    sellList: [],
    buyList: [],
    searchList: []
  },
  mutations: {
    moveOn (state) {
      state.registerStep += 1
    },
    done (state) {
      state.registerStep = 0
    },
    SET_SECKILLS_INFO (state, sellList) {
      state.sellList = sellList
      state.buyList = sellList
    },
    SET_GOODS_LIST (state, data) {
      state.searchList = data
    }
  },
  actions: {
    loadSellList ({ commit }) {
      return new Promise((resolve, reject) => {
        const data = [
          {
            intro: '【赠小风扇】维他 柠檬茶250ml*32盒 礼品装 整箱',
            img: 'static/img/index/seckill/seckill-item1.jpg',
            price: 71.9,
            realPrice: 89.6
          },
          {
            intro: 'Kindle Paperwhite 全新升级版6英寸护眼非反光电子墨水',
            img: 'static/img/index/seckill/seckill-item2.jpg',
            price: 989.0,
            realPrice: 1299.0
          },
          {
            intro: '粮悦 大吃兄糯米锅巴 安徽特产锅巴糯米原味400g*2盒',
            img: 'static/img/index/seckill/seckill-item3.jpg',
            price: 21.8,
            realPrice: 49.0
          },
          {
            intro: '【京东超市】清风（APP）抽纸 原木纯品金装系列 3层',
            img: 'static/img/index/seckill/seckill-item4.jpg',
            price: 49.9,
            realPrice: 59.0
          },
          {
            intro: 'NIKE耐克 男子休闲鞋 AIR MAX 90 ESSENTIAL 气垫',
            img: 'static/img/index/seckill/seckill-item5.jpg',
            price: 559.9,
            realPrice: 759.9
          }
        ]
        commit('SET_SECKILLS_INFO', data)
      })
    },
    loadSearchList ({ commit }) {
      return new Promise((resolve, reject) => {
        setTimeout(() => {
          const data = [
            {
              img: 'static/img/goodsList/item-show-1.jpg',
              price: 36.6,
              intro:
                'SKSK 苹果7/7plus手机壳 iPhone 7 Plus保护套全包硬壳男女磨砂防摔 火影红(苹',
              remarks: 6160,
              shopName: '元亨利配件专营店',
              sale: 9900
            },
            {
              img: 'static/img/goodsList/item-show-2.jpg',
              price: 28.0,
              intro:
                '蒙奇奇 苹果6s手机壳磨砂防摔保护套 适用于iphone6/6s/6sPlus/6plus 6/6s 4.7英',
              remarks: 3000,
              shopName: 'monqiqi旗舰店',
              sale: 9600
            },
            {
              img: 'static/img/goodsList/item-show-3.jpg',
              price: 15.0,
              intro:
                'BIAZE 苹果6/6s手机壳 苹果iphone6s 4.7英寸透明手机套 清爽系列',
              remarks: 2800,
              shopName: 'BIAZE官方旗舰店',
              sale: 8900
            },
            {
              img: 'static/img/goodsList/item-show-4.jpg',
              price: 29.9,
              intro:
                '慕臣 苹果6s手机壳全包防摔磨砂软保护套男女 适用于iPhone6splus',
              remarks: 9000,
              shopName: '歌乐力手配专营店',
              sale: 8600
            },
            {
              img: 'static/img/goodsList/item-show-5.jpg',
              price: 15.0,
              intro:
                'BIAZE 苹果6/6s手机壳 苹果iphone6s 4.7英寸透明手机套 清爽系列',
              remarks: 6160,
              shopName: 'BIAZE官方旗舰店',
              sale: 8560
            },
            {
              img: 'static/img/goodsList/item-show-6.jpg',
              price: 28.0,
              intro:
                '慕臣 苹果6s手机壳全包防摔磨砂软保护套男女 适用于iPhone6splus',
              remarks: 9006,
              shopName: '歌乐力手配专营店',
              sale: 8530
            },
            {
              img: 'static/img/goodsList/item-show-7.jpg',
              price: 15.0,
              intro:
                'BIAZE 苹果6/6s手机壳 苹果iphone6s 4.7英寸透明手机套 清爽系列',
              remarks: 8666,
              shopName: 'BIAZE官方旗舰店',
              sale: 8360
            },
            {
              img: 'static/img/goodsList/item-show-8.jpg',
              price: 29.9,
              intro:
                '慕臣 苹果6s手机壳全包防摔磨砂软保护套男女 适用于iPhone6splus',
              remarks: 6080,
              shopName: '歌乐力手配专营店',
              sale: 7560
            },
            {
              img: 'static/img/goodsList/item-show-1.jpg',
              price: 36.6,
              intro:
                'SKSK 苹果7/7plus手机壳 iPhone 7 Plus保护套全包硬壳男女磨砂防摔 火影红(苹',
              remarks: 6160,
              shopName: '元亨利配件专营店',
              sale: 7360
            },
            {
              img: 'static/img/goodsList/item-show-2.jpg',
              price: 28.0,
              intro:
                '蒙奇奇 苹果6s手机壳磨砂防摔保护套 适用于iphone6/6s/6sPlus/6plus 6/6s 4.7英',
              remarks: 3000,
              shopName: 'monqiqi旗舰店',
              sale: 6960
            },
            {
              img: 'static/img/goodsList/item-show-3.jpg',
              price: 15.0,
              intro:
                'BIAZE 苹果6/6s手机壳 苹果iphone6s 4.7英寸透明手机套 清爽系列',
              remarks: 2800,
              shopName: 'BIAZE官方旗舰店',
              sale: 6560
            },
            {
              img: 'static/img/goodsList/item-show-4.jpg',
              price: 29.9,
              intro:
                '慕臣 苹果6s手机壳全包防摔磨砂软保护套男女 适用于iPhone6splus',
              remarks: 9000,
              shopName: '歌乐力手配专营店',
              sale: 6360
            },
            {
              img: 'static/img/goodsList/item-show-5.jpg',
              price: 15.0,
              intro:
                'BIAZE 苹果6/6s手机壳 苹果iphone6s 4.7英寸透明手机套 清爽系列',
              remarks: 6160,
              shopName: 'BIAZE官方旗舰店',
              sale: 5530
            },
            {
              img: 'static/img/goodsList/item-show-6.jpg',
              price: 28.0,
              intro:
                '慕臣 苹果6s手机壳全包防摔磨砂软保护套男女 适用于iPhone6splus',
              remarks: 9006,
              shopName: '歌乐力手配专营店',
              sale: 5560
            },
            {
              img: 'static/img/goodsList/item-show-7.jpg',
              price: 15.0,
              intro:
                'BIAZE 苹果6/6s手机壳 苹果iphone6s 4.7英寸透明手机套 清爽系列',
              remarks: 8666,
              shopName: 'BIAZE官方旗舰店',
              sale: 5260
            },
            {
              img: 'static/img/goodsList/item-show-8.jpg',
              price: 29.9,
              intro:
                '慕臣 苹果6s手机壳全包防摔磨砂软保护套男女 适用于iPhone6splus',
              remarks: 6080,
              shopName: '歌乐力手配专营店',
              sale: 3560
            }
          ]
          commit('SET_GOODS_LIST', data)
        })
      })
    }
  },
  modules: {}
})
