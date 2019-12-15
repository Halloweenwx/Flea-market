<template>
  <div class="login_container">
    <div class="login_box">
      <!-- 头像区域 -->
      <div class="avatar_box">
        <img src="../../assets/logo.png" />
      </div>
      <!-- 登录表单区 -->
      <Form
        ref="loginFormRef"
        :model="loginForm"
        :rules="loginFormRules"
        :label-width="0"
        class="login_form"
      >
        <!-- 用户名 -->
        <FormItem prop="username">
          <iInput v-model="loginForm.username" prefix="md-contact"></iInput>
        </FormItem>
        <!-- 密码 -->
        <FormItem prop="password">
          <iInput
            v-model="loginForm.password"
            prefix="ios-key"
            type="password"
          ></iInput>
        </FormItem>
        <!-- 按钮区域 -->
        <FormItem class="btns">
          <iButton type="primary" @click="login">登录</iButton>
          <iButton type="info" @click="resetLoginForm">重置</iButton>
        </FormItem>
      </Form>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      // 登陆表单的数据绑定对象
      loginForm: {
        username: 'admin',
        password: '123456'
      },
      // 表单的验证规则对象
      loginFormRules: {
        username: [
          { required: true, message: '请输入登录名称', trigger: 'blur' },
          { min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入登录密码', trigger: 'blur' },
          { min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    // 点击重置按钮，重置登陆表单
    resetLoginForm () {
      // console.log(this)
      this.$refs.loginFormRef.resetFields()
    },
    login () {
      this.$refs.loginFormRef.validate(async valid => {
        if (!valid) return
        // const { data: res } = await this.$http.post('login', this.loginForm)
        // if (res.meta.status !== 200) return this.$message.error('登陆失败')
        // this.$message.success('登陆成功')
        // console.log(res)
        // window.sessionStorage.setItem('token', res.data.token)
        this.$router.push('/admin/home')
      })
    }
  }
}
</script>

<style scoped>
.login_container {
  background-color: #2b4b6b;
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  min-height: 100%;
  height: auto;
}

.login_box {
  width: 450px;
  height: 300px;
  background-color: #fff;
  border-radius: 3px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}

.avatar_box {
  height: 130px;
  width: 130px;
  border: 1px solid #eee;
  border-radius: 50%;
  padding: 10px;
  box-shadow: 0 0 10px #ddd;
  position: absolute;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: #fff;
}

.avatar_box img {
  width: 100%;
  height: 100%;
  border-radius: 50%;
  background-color: #eee;
}

.login_form {
  position: absolute;
  bottom: 0;
  width: 100%;
  padding: 0 20px;
  box-sizing: border-box;
}

.btns {
  display: flex;
  justify-content: flex-end;
}
</style>
