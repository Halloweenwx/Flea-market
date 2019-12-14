<template>
  <div class="info-form">
    <Form
      ref="formValidate"
      :model="formValidate"
      :label-width="80"
      :rules="ruleValidate"
    >
      <FormItem label="手机号" prop="phone">
        <i-input
          v-model="formValidate.phone"
          clearable
          size="large"
          placeholder="请输入手机号"
        ></i-input>
      </FormItem>
      <FormItem label="验证码" prop="checkNum">
        <i-input
          v-model="formValidate.checkNum"
          size="large"
          placeholder="请输入验证码"
        >
          <Button slot="append" @click="getcheckNum">获取验证码</Button>
        </i-input>
      </FormItem>
      <FormItem>
        <Button type="error" size="large" @click="handleSubmit('formValidate')"
          >验证手机号</Button
        >
      </FormItem>
    </Form>
  </div>
</template>

<script>
import { mapMutations } from 'vuex'
export default {
  data () {
    return {
      formValidate: {
        phone: '13852897754',
        checkNum: '1234'
      },
      ruleValidate: {
        phone: [
          { required: true, message: '手机号不能为空', trigger: 'blur' },
          {
            type: 'string',
            pattern: /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/,
            message: '请输入合法的手机号',
            trigger: 'blur'
          }
        ],
        checkNum: [
          { required: true, message: '必须填写验证码', trigger: 'blur' },
          {
            type: 'string',
            min: 4,
            max: 4,
            message: '验证码长度错误',
            trigger: 'blur'
          }
        ]
      }
    }
  },
  methods: {
    ...mapMutations(['moveOn']),
    async getcheckNum () {
      console.log()
      const { data: res } = await this.$http.post('register/phone', {
        phone: this.formValidate.phone
      })
      if (res.code !== 200) return this.$Message.error('获取验证码失败')
      this.$Message.success({
        content: '验证码为: ' + res.data.checkNum,
        duration: 6,
        closable: true
      })
    },
    handleSubmit (name) {
      // 提交验证
      this.$refs[name].validate(valid => {
        if (valid) {
          this.moveOn()
          this.$router.push({
            path: '/inputInfo',
            query: { phone: this.formValidate.phone }
          })
        } else {
          this.$Message.error({
            content: '请正确填写信息',
            duration: 6,
            closable: true
          })
        }
      })
    }
  }
  // computed: {
  //   ...mapState(['formValidate'])
  // }
}
</script>

<style scoped>
.info-form {
  width: 90% !important;
}
</style>
