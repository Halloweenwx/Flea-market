<template>
  <div>
    <!-- 展示个人信息 -->
    <div class="container">
      <div class="title">
        <em>个人信息</em>
      </div>
      <div class="info">
        <List>
          <template slot="header" class="header">
            <div class="header">
              <Button type="error" @click="editDialogVisable = true">编辑</Button>
            </div>
          </template>
          <ListItem>
            <ListItemMeta
              avatar="https://dev-file.iviewui.com/userinfoPDvn9gKWYihR24SpgC319vXY8qniCqj4/avatar"
              title="用户名"
              :description="userInfo.username"
            />
          </ListItem>
          <ListItem>
            <ListItemMeta
              avatar="https://dev-file.iviewui.com/userinfoPDvn9gKWYihR24SpgC319vXY8qniCqj4/avatar"
              title="姓名"
              :description="userInfo.realName"
            />
          </ListItem>
          <ListItem>
            <ListItemMeta
              avatar="https://dev-file.iviewui.com/userinfoPDvn9gKWYihR24SpgC319vXY8qniCqj4/avatar"
              title="手机号"
              :description="userInfo.phone"
            />
          </ListItem>
          <!-- <ListItem>
            <ListItemMeta
              avatar="https://dev-file.iviewui.com/userinfoPDvn9gKWYihR24SpgC319vXY8qniCqj4/avatar"
              title="邮箱"
              :description="userInfo.email"
            />
          </ListItem>-->
          <ListItem>
            <ListItemMeta
              avatar="https://dev-file.iviewui.com/userinfoPDvn9gKWYihR24SpgC319vXY8qniCqj4/avatar"
              title="所在地区"
              :description="userInfo.city"
            />
          </ListItem>
        </List>
      </div>
    </div>
    <!-- 修改个人信息对话框 -->
    <Modal v-model="editDialogVisable" title="修改个人信息" @on-ok="editUserInfo">
      <Form ref="editFormRef" :model="editForm" :rules="formRules">
        <FormItem label="用户名" prop="username">
          <iInput type="text" v-model="editForm.username" placeholder="请输入你的用户名" clearable disabled>
            <Icon type="ios-person-outline" slot="prepend"></Icon>
          </iInput>
        </FormItem>
        <FormItem label="姓名" prop="realName">
          <iInput type="text" v-model="editForm.realName" placeholder="请输入你的姓名" clearable disabled>
            <Icon type="ios-person-outline" slot="prepend"></Icon>
          </iInput>
        </FormItem>
        <FormItem label="所在城市" prop="city">
          <iInput type="text" v-model="editForm.city" placeholder="请输入你所在的城市" clearable disabled>
            <Icon type="ios-home-outline" slot="prepend"></Icon>
          </iInput>
        </FormItem>
        <FormItem label="手机号" prop="phone">
          <iInput type="text" v-model="editForm.phone" placeholder="请输入你的手机号" clearable>
            <Icon type="ios-call-outline" slot="prepend"></Icon>
          </iInput>
        </FormItem>
        <!-- <FormItem label="邮箱" prop="email">
          <iInput
            type="text"
            v-model="editForm.email"
            placeholder="请输入你的邮箱"
            clearable
          >
            <Icon type="ios-mail-outline" slot="prepend"></Icon>
          </iInput>
        </FormItem>-->
        <FormItem label="密码" prop="password">
          <i-input type="password" v-model="editForm.password" clearable placeholder="请输入你的密码">
            <Icon type="ios-key-outline" slot="prepend"></Icon>
          </i-input>
        </FormItem>
        <FormItem label="确认密码" prop="repassword">
          <i-input type="password" v-model="editForm.repassword" clearable placeholder="请再次输入你的密码">
            <Icon type="ios-key-outline" slot="prepend"></Icon>
          </i-input>
        </FormItem>
      </Form>
    </Modal>
  </div>
</template>

<script>
export default {
  data() {
    //   验证邮箱
    // var checkEmail = (rule, value, cb) => {
    //   const regEmail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/

    //   if (regEmail.test(value)) return cb()
    //   cb(new Error('请输入合法的邮箱'))
    // }
    // 验证手机号
    var checkMobile = (rule, value, cb) => {
      const regMobile = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;

      if (regMobile.test(value)) return cb();
      cb(new Error("请输入合法的手机号"));
    };
    // 验证密码
    var validatePassCheck = (rule, value, callback) => {
      if (value !== this.editForm.password) {
        callback(new Error("两次输入的密码不一样"));
      } else {
        callback();
      }
    };

    return {
      // 用户信息
      userInfo: {},
      // 编辑用户信息
      editForm: {
        username: "wxl1999",
        realName: "王晓磊",
        city: "北京",
        phone: "18810312968",
        // email: '',
        password: "",
        repassword: ""
      },
      // 编辑对话框是否可见
      editDialogVisable: false,
      formRules: {
        phone: [
          { required: true, message: '请填写手机号', trigger: 'blur' },
          { validator: checkMobile, trigger: "blur" }
        ],

        password: [
          { required: true, message: '密码不能为空', trigger: 'blur' },
          {
            type: "string",
            min: 6,
            message: "密码长度不能小于6",
            trigger: "blur"
          },
          {
            type: "string",
            pattern: /^.*?\d.*?\d.*?$/,
            message: "密码中至少包含两个数字",
            trigger: "blur"
          },
          {
            type: "string",
            pattern: /(?=.*[A-Z])(?=.*[a-z])|\d+/,
            message: "密码不能均为大写或小写",
            trigger: "blur"
          }
        ],
        repassword: [
          // { required: true, message: '密码不能为空', trigger: 'blur' },
          { validator: validatePassCheck, trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getUserInfo();
  },
  methods: {
    // 提交编辑的用户信息
    editUserInfo() {
      // this.$Message.success('更新用户信息成功')
      this.$refs.editFormRef.validate(async valid => {
        if (!valid) return this.$Message.error("更新用户信息失败");
        const { data: res } = await this.$http.post("home/update", {
          phone: this.editForm.phone,
          password: this.editForm.password
        });
        if (res.code !== 200) return this.$message.error(res.msg);
        // 刷新数据列表
        this.getUserInfo();
        // 提示修改成功
        this.$Message.success("修改成功，请重新登录");
        this.$router.push("/login");
      });
    },
    async getUserInfo() {
      const { data: res } = await this.$http.get("/home/info");
      if (res.code !== 200) return this.$Message.error(res.msg);
      this.userInfo = this.editForm = res.data;
      this.userInfo.city = this.editForm.city = res.data.city.label;
      this.$message.success(res.msg)
      console.log(res.data)
    }
  }
};
</script>

<style scoped>
.container {
  width: 75%;
  margin: 0 auto;
}
.title {
  margin: 50px auto;
  width: 100%;
  text-align: center;
  font-size: 32px;
}
.info {
  margin: 0 auto;
  width: 100%;
}
.header {
  position: relative;
  height: 30px;
}
button {
  position: absolute;
  right: 0;
}
</style>
