<template>
  <div>
    <div>
      <Row class="container">
        <iCol span="13" offset="1" class="img_box">
          <img src="../assets/img/sale.jpg" alt="" />
        </iCol>
        <iCol span="9" offset="1" class="login_box">
          <div class="login_container">
            <div class="login_header">
              <p>欢迎登录</p>
            </div>
            <div class="form_box">
              <Form ref="loginFormRef" :model="loginForm" :rules="loginRuleRef">
                <FormItem prop="username">
                  <iInput
                    type="text"
                    v-model="loginForm.username"
                    clearable
                    size="large"
                    placeholder="用户名"
                  >
                    <Icon type="ios-person-outline" slot="prepend"></Icon>
                  </iInput>
                </FormItem>
                <FormItem prop="password">
                  <iInput
                    type="password"
                    v-model="loginForm.password"
                    clearable
                    size="large"
                    placeholder="密码"
                  >
                    <Icon type="ios-lock-outline" slot="prepend"> </Icon>
                  </iInput>
                </FormItem>
                <FormItem>
                  <Button
                    type="error"
                    size="large"
                    @click="login('loginFormRef')"
                    long
                    >登录</Button
                  >
                </FormItem>
              </Form>
            </div>
          </div>
        </iCol>
      </Row>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      loginForm: {
        username: '',
        password: ''
      },
      loginRuleRef: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { type: 'string', min: 6, message: '密码不能少于6位', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    login (name) {
      this.$refs[name].validate(async valid => {
        if (!valid) return
        // const { data: res } = await this.$http.post('login', this.loginForm)
        // if (res.meta.status !== 200) return this.$message.error('登陆失败')
        this.$Message.success('登陆成功')
        // window.sessionStorage.setItem('token', res.data.token)
        this.$router.push('/')
      })
    }
  }
}
</script>

<style scoped>
.container {
  width: 1200px;
  margin-top: 30px;
  height: 485px;
  background-color: #fff;
}
.img_box {
  height: 485px;
  overflow: hidden;
  display: flex;
  align-items: center;
  justify-content: center;
}
.img_box img {
  width: 75%;
}
.login_box {
  height: 485px;
  display: flex;
  align-items: center;
  justify-content: center;
}
.login_container {
  width: 80%;
  height: 280px;
  border: #ED3F14 solid 1px;
}
.login_header {
  height: 50px;
  font-size: 20px;
  text-align: center;
  line-height: 50px;
  letter-spacing: 5px;
  color: #fff;
  background-color: #ED3F14;
}
.form_box {
  width: 80%;
  margin: 30px auto;
}
</style>
