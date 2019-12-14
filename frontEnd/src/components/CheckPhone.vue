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
        phone: '',
        checkNum: ''
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
    getcheckNum () {
      this.$Message.success({
        content: '验证码为：1234',
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
            path: '/inputInfo'
            // query: { phone: this.formValidate.phone }
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
}
</script>

<style scoped>
.info-form {
  width: 90% !important;
}
</style>
