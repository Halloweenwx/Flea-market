<template>
  <div>
    <!-- 面包屑导航区域 -->
    <Breadcrumb class="navi">
      <BreadcrumbItem to="/home">首页</BreadcrumbItem>
      <BreadcrumbItem>我要卖</BreadcrumbItem>
    </Breadcrumb>

    <!-- 卡片视图 -->
    <Card>
      <!-- 搜索与添加区 -->
      <Row :gutter="50">
        <iCol :span="7">
          <iInput
            placeholder="请输入内容"
            v-model="queryInfo.query"
            clearable
            @on-clear="getItemList"
          >
            <iButton
              slot="append"
              icon="ios-search-outline"
              @click="getItemList"
            ></iButton>
          </iInput>
        </iCol>
        <iCol :offset="13" :span="4">
          <iButton type="primary" @click="addDialogVisible = true"
            >我有东西要卖</iButton
          >
        </iCol>
      </Row>

      <!-- 出售物品列表区域 -->
      <Table
        :data="itemList"
        :columns="columns"
        class="itemList"
        border
        stripe
        no-data-text="这里空空如也~"
      >
        <template slot-scope="{ row, index }" slot="action">
          <!-- 修改 -->
          <Tooltip effect="dark" content="修改" placement="top">
            <iButton
              content="修改"
              type="primary"
              icon="ios-create-outline"
              size="default"
              class="modify"
              @click="showEditDialog(index)"
            ></iButton>
          </Tooltip>
          <!-- 删除 -->
          <Tooltip effect="dark" content="删除" placement="top">
            <iButton
              content="删除"
              type="error"
              icon="ios-trash-outline"
              size="default"
            ></iButton>
          </Tooltip>
        </template>
      </Table>

      <!-- 分页区域 -->
      <Page
        class="page"
        :current="queryInfo.pagenum"
        :page-size-opts="[1, 2, 5, 10]"
        :page-size="queryInfo.pagesize"
        :total="total"
        show-total
        show-elevator
        show-sizer
        @on-page-size-change="handleSizeChange"
        @on-change="handleCurrentChange"
      ></Page>
    </Card>

    <!-- 出售物品的对话框 -->
    <Modal
      title="我有东西要卖"
      v-model="addDialogVisible"
      @close="addDialogClosed"
    >
      <!-- 内容主体区 -->
      <Form
        ref="addFormRef"
        :model="addForm"
        :rules="addFormRules"
        :label-width="125"
      >
        <FormItem label="物品类型" prop="type">
          <Select v-model="addForm.type" style="width:200px" clearable>
            <Option
              v-for="item in typeList"
              :value="item.value"
              :key="item.value"
              >{{ item.label }}</Option
            >
          </Select>
        </FormItem>
        <FormItem label="物品名称" prop="name">
          <iInput v-model="addForm.name" clearable></iInput>
        </FormItem>
        <FormItem label="物品最低价格" prop="lowestPrice">
          <iInput v-model="addForm.lowestPrice" clearable></iInput>
        </FormItem>
        <FormItem label="是否一口价" prop="isFixed">
          <iSwitch v-model="addForm.isFixed" />
        </FormItem>
        <FormItem label="竞价结束日期" prop="endDate">
          <DatePicker
            type="date"
            v-model="addForm.endDate"
            clearable
            placeholder="请选择竞价结束日期"
            style="width: 200px"
          ></DatePicker>
        </FormItem>
        <FormItem label="物品描述" prop="description">
          <iInput v-model="addForm.description" clearable></iInput>
        </FormItem>
        <FormItem label="物品照片" prop="photo">
          <iInput v-model="addForm.photo"></iInput>
          <Upload
            multiple
            type="drag"
            :format="['jpg', 'jpeg', 'png']"
            action="//jsonplaceholder.typicode.com/posts/"
            @on-success="upLoadImg"
          >
            <div style="padding: 20px 0">
              <Icon
                type="ios-cloud-upload"
                size="52"
                style="color: #3399ff"
              ></Icon>
              <p>您可以点击选择图片或者直接拖拽</p>
            </div>
          </Upload>
        </FormItem>
      </Form>
      <!-- 底部区 -->
      <span slot="footer" class="dialog-footer">
        <iButton @click="addDialogVisible = false">取 消</iButton>
        <iButton type="primary" @click="addItem">确 定</iButton>
      </span>
    </Modal>

    <!-- 修改出售物品的对话框 -->
    <Modal title="修改出售物品的信息" v-model="editDialogVisible">
      <Form
        :model="editForm"
        :rules="editFormRules"
        ref="editFormRef"
        :label-width="125"
        @close="editDialogClosed"
      >
        <FormItem label="物品类型" prop="type">
          <Select v-model="editForm.type" style="width:200px" clearable>
            <Option
              v-for="item in typeList"
              :value="item.value"
              :key="item.value"
              >{{ item.label }}</Option
            >
          </Select>
        </FormItem>
        <FormItem label="物品名称" prop="name">
          <iInput v-model="editForm.name"></iInput>
        </FormItem>
        <FormItem label="物品最低价格" prop="lowestPrice">
          <iInput v-model="editForm.lowestPrice"></iInput>
        </FormItem>
        <FormItem label="是否一口价" prop="isFixed">
          <iSwitch v-model="editForm.isFixed" />
        </FormItem>
        <FormItem label="竞价结束日期" prop="endDate">
          <DatePicker
            type="date"
            v-model="editForm.endDate"
            clearable
            placeholder="请选择竞价结束日期"
            style="width: 200px"
          ></DatePicker>
        </FormItem>
        <FormItem label="物品描述" prop="description">
          <iInput v-model="editForm.description"></iInput>
        </FormItem>
        <FormItem label="物品照片" prop="photo">
          <iInput v-model="editForm.photo"></iInput>
          <Upload
            multiple
            type="drag"
            :format="['jpg', 'jpeg', 'png']"
            action="//jsonplaceholder.typicode.com/posts/"
            @on-success="upLoadImg"
          >
            <div style="padding: 20px 0">
              <Icon
                type="ios-cloud-upload"
                size="52"
                style="color: #3399ff"
              ></Icon>
              <p>您可以点击选择图片或者直接拖拽</p>
            </div>
          </Upload>
        </FormItem>
      </Form>
      <span slot="footer" class="dialog-footer">
        <iButton @click="editDialogVisible = false">取 消</iButton>
        <iButton type="primary" @click="editItem">确 定</iButton>
      </span>
    </Modal>
  </div>
</template>

<script>
export default {
  data () {
    return {
      // 获取物品列表的参数对象
      queryInfo: {
        query: '',
        // 当前的页数
        pagenum: 1,
        // 当前每页显示多少条数据
        pagesize: 2
      },
      itemList: [
        {
          type: '电子类',
          name: '笔记本',
          lowestPrice: 10,
          isFixed: '是',
          state: '待售'
        }
      ],
      total: 1,
      //  控制添加对话框的显示与隐藏
      addDialogVisible: false,
      columns: [
        {
          type: 'index'
        },
        {
          title: '物品类型',
          key: 'type'
        },
        {
          title: '物品名称',
          key: 'name'
        },
        {
          title: '最低价',
          key: 'lowestPrice'
        },
        {
          title: '是否一口价',
          key: 'isFixed'
        },
        {
          title: '状态',
          key: 'state'
        },
        {
          title: '操作',
          slot: 'action'
        }
      ],
      //   添加的表单数据
      addForm: {
        type: '',
        name: '',
        lowestPrice: '',
        isFixed: false,
        endDate: '',
        description: '',
        photo: ''
      },
      upLoadImg (response, file, fileList) {
        console.log(response)
      },
      //   添加表单的验证规则对象
      addFormRules: {
        type: [{ required: true, message: '请选择物品类型', trigger: 'blur' }],
        name: [{ required: true, message: '请输入物品名称', trigger: 'blur' }],
        lowestPrice: [
          {
            required: true,
            message: '请输入物品的最低求购价格',
            trigger: 'blur'
          }
        ],
        isFixed: [
          { required: true, message: '请选择是否为一口价', trigger: 'blur' }
        ],
        endDate: [
          { required: true, message: '请选择竞价结束日期', trigger: 'blur' }
        ]
      },
      editDialogVisible: false,
      //   查询到的物品
      editForm: {
        type: '',
        name: '',
        lowestPrice: '',
        isFixed: false,
        endDate: '',
        description: '',
        photo: ''
      },
      // 修改表单的验证规则对象
      editFormRules: {
        type: [{ required: true, message: '请选择物品类型', trigger: 'blur' }],
        name: [{ required: true, message: '请输入物品名称', trigger: 'blur' }],
        lowestPrice: [
          {
            required: true,
            message: '请输入物品的最低求购价格',
            trigger: 'blur'
          }
        ],
        isFixed: [
          { required: true, message: '请选择是否为一口价', trigger: 'blur' }
        ],
        endDate: [
          { required: true, message: '请选择竞价结束日期', trigger: 'blur' }
        ]
      },
      typeList: [
        {
          value: '电子类',
          label: '电子类'
        },
        {
          value: '日常用品类',
          label: '日常用品类'
        },
        {
          value: '书籍类',
          label: '书籍类'
        }
      ]
    }
  },
  created () {
    this.getItemList()
  },
  methods: {
    // 获取物品列表
    async getItemList () {
      // const { data: res } = await this.$http.get('users', {
      //   params: this.queryInfo
      // })
      // if (res.meta.status !== 200) {
      //   return this.$message.error('获取用户列表失败!')
      // }
      // this.userList = res.data.users
      // this.total = res.data.total
      // console.log(res)
    },
    // 监听pagesize改变的事件
    handleSizeChange (newSize) {
      this.queryInfo.pagesize = newSize
      this.getItemList()
    },
    // 监听页码值改变的事件
    handleCurrentChange (newPage) {
      this.queryInfo.pagenum = newPage
      this.getItemList()
    },
    // 监听添加对话框的关闭
    addDialogClosed () {
      this.$refs.addFormRef.resetFields()
    },
    // 点击按钮添加求购信息
    addItem () {
      this.$refs.addFormRef.validate(async valid => {
        if (!valid) return
        // 发起请求
        const { data: res } = await this.$http.post('users', this.addForm)

        if (res.status !== 201) {
          this.$message.error('添加用户失败')
        }

        this.$message.success('添加用户成功')
        this.addDialogVisible = false
        this.getItemList()
      })
    },
    // 展示编辑物品信息的对话框
    async showEditDialog (id) {
      // const { data: res } = await this.$http.get('users/' + id)
      // if (res.meta.status !== 200) {
      //   return this.$message.error('查询用户信息失败！')
      // }
      // this.editForm = res.data
      this.editDialogVisible = true
    },
    // 监听修改用户对话框的关闭事件
    editDialogClosed () {
      this.$refs.editFormRef.resetFields()
    },
    // 修改物品信息并提交
    editItem () {
      this.$refs.editFormRef.validate(valid => {
        if (!valid) return
        const { data: res } = this.$http.put('users/' + this.editForm.id, {
          email: this.editFrom.email,
          mobile: this.editForm.mobile
        })

        if (res.meta.status !== 200) {
          return this.$message.error('更新用户信息失败')
        }

        // 关闭对话框
        this.editDialogVisible = false
        // 刷新数据列表
        this.getItemList()
        // 提示修改成功
        this.$message.success('更新用户信息成功')
      })
    }
  }
}
</script>

<style scoped>
.navi {
  margin-bottom: 10px;
  font-size: 12px;
  margin-left: 5px;
}
.itemList {
  margin-top: 20px;
}
.page {
  margin: 20px 0 20px 1px;
  font-size: 12px;
}
.modify {
  margin-right: 20px;
}
</style>
