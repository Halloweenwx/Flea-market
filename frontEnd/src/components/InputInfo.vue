<template>
  <div class="info-form">
    <Form
      ref="formValidate"
      :model="formValidate"
      :rules="ruleValidate"
      :label-width="80"
    >
      <FormItem label="用户名" prop="username">
        <i-input
          v-model="formValidate.username"
          clearable
          size="large"
          placeholder="请输入你的用户名"
        ></i-input>
      </FormItem>
      <FormItem label="姓名" prop="name">
        <i-input
          v-model="formValidate.name"
          clearable
          size="large"
          placeholder="请输入你的姓名"
        ></i-input>
      </FormItem>
      <!-- <FormItem label="邮箱" prop="email">
        <i-input
          v-model="formValidate.email"
          clearable
          size="large"
          placeholder="请输入你的邮箱"
        ></i-input>
      </FormItem> -->
      <FormItem label="城市" prop="city">
        <Cascader
          :data="cityList"
          v-model="formValidate.city"
          trigger="hover"
          clearable
        ></Cascader>
      </FormItem>
      <FormItem label="密码" prop="password">
        <i-input
          type="password"
          v-model="formValidate.password"
          clearable
          size="large"
          placeholder="请输入你的密码"
        ></i-input>
      </FormItem>
      <FormItem label="确认密码" prop="repassword">
        <i-input
          type="password"
          v-model="formValidate.repassword"
          clearable
          size="large"
          placeholder="请再次输入你的密码"
        ></i-input>
      </FormItem>
      <FormItem>
        <Button type="error" size="large" @click="handleSubmit('formValidate')"
          >注册</Button
        >
      </FormItem>
    </Form>
  </div>
</template>

<script>
import { mapMutations } from 'vuex'
export default {
  data () {
    const validatePassCheck = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.formValidate.password) {
        callback(new Error('两次输入的密码不一样'))
      } else {
        callback()
      }
    }
    return {
      cityList: [
        {
          value: 'beijing',
          label: '北京',
          children: [
            {
              value: 'gugong',
              label: '故宫'
            },
            {
              value: 'tiantan',
              label: '天坛'
            },
            {
              value: 'wangfujing',
              label: '王府井'
            }
          ]
        },
        {
          value: 'jiangsu',
          label: '江苏',
          children: [
            {
              value: 'nanjing',
              label: '南京',
              children: [
                {
                  value: 'fuzimiao',
                  label: '夫子庙'
                }
              ]
            },
            {
              value: 'suzhou',
              label: '苏州',
              children: [
                {
                  value: 'zhuozhengyuan',
                  label: '拙政园'
                },
                {
                  value: 'shizilin',
                  label: '狮子林'
                }
              ]
            }
          ]
        }
      ],
      formValidate: {
        username: '',
        name: '',
        // email: '',
        city: '',
        password: '',
        repassword: ''
      },
      ruleValidate: {
        username: [
          { required: true, message: '用户名不能为空', trigger: 'blur' }
        ],
        name: [{ required: true, message: '姓名不能为空', trigger: 'blur' }],
        city: [{ required: true, message: '城市不能为空', trigger: 'blur' }],
        // mail: [
        //   { required: true, message: '邮箱不能为空', trigger: 'blur' },
        //   { type: 'email', message: '邮箱格式错误', trigger: 'blur' }
        // ],
        password: [
          { required: true, message: '密码不能为空', trigger: 'blur' },
          {
            type: 'string',
            min: 6,
            message: '密码长度不能小于6',
            trigger: 'blur'
          },
          {
            type: 'string',
            pattern: /^.*?\d.*?\d.*?$/,
            message: '密码中至少包含两个数字',
            trigger: 'blur'
          },
          {
            type: 'string',
            pattern: /(?=.*[A-Z])(?=.*[a-z])|\d+/,
            message: '密码不能均为大写或小写',
            trigger: 'blur'
          }
        ],
        repassword: [
          { required: true, message: '密码不能为空', trigger: 'blur' },
          { validator: validatePassCheck, trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    ...mapMutations(['moveOn']),
    handleSubmit (name) {
      this.$refs[name].validate(valid => {
        if (valid) {
          this.$Message.success('注册成功')
          //   const userinfo = {
          //     username: this.formValidate.username,
          //     name: this.formValidate.name,
          //     password: this.formValidate.password,
          //     mail: this.formValidate.mail,
          //     phone: this.$route.query.phone
          //   }
          this.moveOn()
          this.$router.push({ path: '/done' })
        } else {
          this.$Message.error('注册失败')
        }
      })
    }
  }
}
</script>

<style scoped>
.info-form {
  width: 90% !important;
}
</style>
