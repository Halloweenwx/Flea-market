<template>
  <div>
    <!-- 面包屑导航区域 -->
    <Breadcrumb class="navi">
      <BreadcrumbItem to="/home">首页</BreadcrumbItem>
      <BreadcrumbItem>我要买</BreadcrumbItem>
    </Breadcrumb>

    <!-- 卡片视图 -->
    <Card>
      <!-- 搜索与添加区 -->
      <Row :gutter="50">
        <!-- <iCol :span="7">
          <iInput placeholder="搜索商品名" v-model="queryInfo.query" @on-clear="getItemList">
            <iButton slot="append" icon="ios-search-outline" @click="getItemList"></iButton>
          </iInput>
        </iCol> -->
        <iCol :offset="18" :span="4">
          <iButton type="primary" @click="addDialogVisible = true">发布求购信息</iButton>
        </iCol>
      </Row>

      <!-- 求购物品列表区域 -->
      <Table
        :data="itemList"
        :columns="columns"
        class="itemList"
        border
        stripe
        no-data-text="这里空空如也~"
      >
        <template slot-scope="{ row, index }" slot="action">
          <!-- 详情 -->
          <Tooltip effect="dark" content="详情" placement="top">
            <iButton
              content="详情"
              type="primary"
              icon="md-cube"
              size="default"
              class="modify"
              @click="showDetail(index)"
            ></iButton>
          </Tooltip>
          <!-- 修改 -->
          <Tooltip effect="dark" content="修改" placement="top">
            <iButton
              content="修改"
              type="primary"
              icon="ios-create-outline"
              size="default"
              class="modify"
              :disabled="!itemList[index].canChange"
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
              :disabled="!itemList[index].canChange"
              @click="delItem(index)"
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

    <!-- 发布求购的对话框 -->
    <Modal title="发布求购信息" v-model="addDialogVisible" @close="addDialogClosed">
      <!-- 内容主体区 -->
      <Form ref="addFormRef" :model="addForm" :rules="addFormRules" :label-width="125">
        <FormItem label="物品类型" prop="category">
          <Select v-model="addForm.category" style="width:200px" clearable>
            <Option v-for="item in typeList" :value="item.value" :key="item.value">{{ item.label }}</Option>
          </Select>
        </FormItem>
        <FormItem label="物品名称" prop="name">
          <iInput v-model="addForm.name" clearable></iInput>
        </FormItem>
        <FormItem label="物品最低价格" prop="lowestPrice">
          <InputNumber :max="this.addForm.highestPrice" v-model="addForm.lowestPrice" clearable/>
        </FormItem>
        <FormItem label="物品最高价格" prop="highestPrice">
          <InputNumber :min="this.addForm.lowestPrice" v-model="addForm.highestPrice"/>
        </FormItem>
        <FormItem label="物品描述" prop="description">
          <iInput v-model="addForm.description" clearable></iInput>
        </FormItem>
        <FormItem label="物品图片" prop="pictures">
          <!-- <iInput v-model="addForm.photo"></iInput> -->
          <Upload
            multiple
            type="drag"
            :format="['jpg', 'jpeg', 'png']"
            action= "http://10.128.205.27:8080/picture/upload"
            :on-success="upLoadImg"
            
          >
            <div style="padding: 20px 0">
              <Icon type="ios-cloud-upload" size="52" style="color: #3399ff"></Icon>
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

    <!-- 详情的对话框 -->
    <Modal title="查看物品详情" v-model="detailDialogVisible">
      <List>
        <ListItem>
          <ListItemMeta
            avatar="https://dev-file.iviewui.com/userinfoPDvn9gKWYihR24SpgC319vXY8qniCqj4/avatar"
            title="物品类型"
            :description="detail.category"
          />
        </ListItem>
        <ListItem>
          <ListItemMeta
            avatar="https://dev-file.iviewui.com/userinfoPDvn9gKWYihR24SpgC319vXY8qniCqj4/avatar"
            title="物品名称"
            :description="detail.name"
          />
        </ListItem>
        <ListItem>
          <ListItemMeta
            avatar="https://dev-file.iviewui.com/userinfoPDvn9gKWYihR24SpgC319vXY8qniCqj4/avatar"
            title="物品最低价格"
            :description="detail.lowestPrice"
          />
        </ListItem>
        <ListItem>
          <ListItemMeta
            avatar="https://dev-file.iviewui.com/userinfoPDvn9gKWYihR24SpgC319vXY8qniCqj4/avatar"
            title="物品最高价格"
            :description="detail.highestPrice"
          />
        </ListItem>
        <ListItem>
          <ListItemMeta
            avatar="https://dev-file.iviewui.com/userinfoPDvn9gKWYihR24SpgC319vXY8qniCqj4/avatar"
            title="物品描述"
            :description="detail.description"
          />
        </ListItem>
        <ListItem>
          <ListItemMeta
            avatar="https://dev-file.iviewui.com/userinfoPDvn9gKWYihR24SpgC319vXY8qniCqj4/avatar"
            title="创建时间"
            :description="detail.createTime"
          />
        </ListItem>
        <ListItem>
          <ListItemMeta
            avatar="https://dev-file.iviewui.com/userinfoPDvn9gKWYihR24SpgC319vXY8qniCqj4/avatar"
            title="修改时间"
            :description="detail.modifiedTime"
          />
        </ListItem>
      </List>
    </Modal>

    <!-- 修改求购的对话框 -->
    <Modal title="修改求购物品的信息" v-model="editDialogVisible" @close="editDialogClosed">
      <Form
        :model="editForm"
        :rules="editFormRules"
        ref="editFormRef"
        :label-width="125"
      >
        <FormItem label="物品类型" prop="category">
          <Select v-model="editForm.category" style="width:200px" clearable>
            <Option v-for="item in typeList" :value="item.value" :key="item.value">{{ item.label }}</Option>
          </Select>
        </FormItem>
        <FormItem label="物品名称" prop="name">
          <iInput v-model="editForm.name"></iInput>
        </FormItem>
        <FormItem label="物品最低价格" prop="lowestPrice">
          <input-number :max="this.addForm.highestPrice" v-model="editForm.lowestPrice"/>
        </FormItem>
        <FormItem label="物品最高价格" prop="highestPrice">
          <input-number :min="this.addForm.lowestPrice" v-model="editForm.highestPrice"/>
        </FormItem>
        <FormItem label="物品描述" prop="description">
          <iInput v-model="editForm.description"></iInput>
        </FormItem>
        <FormItem label="物品图片" prop="photo">
          <!-- <iInput v-model="editForm.photo"></iInput> -->
          <Upload
            multiple
            type="drag"
            :format="['jpg', 'jpeg', 'png']"
            action= "http://10.128.205.27:8080/picture/upload"
            @on-success="upLoadImg"
          >
            <div style="padding: 20px 0">
              <Icon type="ios-cloud-upload" size="52" style="color: #3399ff"></Icon>
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
  data() {
    return {
      // 获取物品列表的参数对象
      queryInfo: {
        query: "",
        // 当前的页数
        pageNum: 1,
        // 当前每页显示多少条数据
        pageSize: 10
      },
      itemList: [
        {
          // category: "电子类",
          // name: "笔记本",
          // lowestPrice: 10,
          // highestPrice: 20,
          // description: "abook",
          // status: "待购",
          // pictures: null
        }
      ],
      total: 100,
      //  控制添加对话框的显示与隐藏
      addDialogVisible: false,
      columns: [
        {
          type: "index"
        },
        {
          title: "物品类型",
          key: "category"
        },
        {
          title: "物品名称",
          key: "name"
        },
        {
          title: "最低价",
          key: "lowestPrice"
        },
        {
          title: "最高价",
          key: "highestPrice"
        },
        {
          title: "状态",
          key: "status"
        },
        {
          title: "操作",
          slot: "action",
          width: 175
        }
      ],
      //   添加的表单数据
      addForm: {
        // category: "电子类",
        // name: "笔记本",
        // lowestPrice: 0,
        // highestPrice: 0,
        // description: "abook",
        // status: "待购",
        // pictures: []
      },
      //   添加表单的验证规则对象
      addFormRules: {
        category: [
          { required: true, message: "请选择物品类型", trigger: "blur" }
        ],
        name: [{ required: true, message: "请输入物品名称", trigger: "blur" }],
        lowestPrice: [
          {
            type: "number",
            required: true,
            message: "请输入物品的最低求购价格",
            trigger: "blur"
          },
        ],
        highestPrice: [
          {
            type: "number",
            required: true,
            message: "请输入物品的最高求购价格",
            trigger: "blur"
          },
        ]
      },
      detailDialogVisible: false,
      detail: {},
      editDialogVisible: false,
      //   查询到的物品对象
      editForm: {
        category: "",
        name: "",
        lowestPrice: 0,
        highestPrice: 0,
        description: "",
        pictures: []
      },
      // 修改表单的验证规则对象
      editFormRules: {
        category: [
          { required: true, message: "请选择物品类型", trigger: "blur" }
        ],
        name: [{ required: true, message: "请输入物品名称", trigger: "blur" }],
        lowestPrice: [
          {
            type: "number",
            required: true,
            message: "请输入物品的最低求购价格",
            trigger: "blur"
          }
        ],
        highestPrice: [
          {
            type: "number",
            required: true,
            message: "请输入物品的最高求购价格",
            trigger: "blur"
          }
        ]
      },
      typeList: [
        {
          value: "ele",
          label: "电子类"
        },
        {
          value: "daily",
          label: "日常用品类"
        },
        {
          value: "book",
          label: "书籍类"
        }
      ]
    };
  },
  created() {
    this.getItemList();
  },
  methods: {
    // 获取求购物品列表
    async getItemList() {
      const { data: res } = await this.$http.get("home/item/demand", {
        params: this.queryInfo
      });
      console.log(this.queryInfo)
      if (res.code !== 200) return this.$message.error("获取用户列表失败!");
      console.log(res)
      for(var i in res.data.content){
        res.data.content[i].category = res.data.content[i].category.cnCategory;
        res.data.content[i].status = res.data.content[i].itemStatus.cnStatus;
        res.data.content[i].canChange = res.data.content[i].itemStatus.enStatus === 'on';
      }
      this.itemList = res.data.content;
      this.total = res.data.totalPages;
    },
    // 监听pagesize改变的事件
    handleSizeChange(newSize) {
      this.queryInfo.pageSize = newSize;
      this.getItemList();
    },
    // 监听页码值改变的事件
    handleCurrentChange(newPage) {
      this.queryInfo.pageNum = newPage;
      this.getItemList();
    },
    // 监听添加对话框的关闭
    addDialogClosed() {
      this.$refs.addFormRef.resetFields();
    },
    upLoadImg(response, file, fileList) {
      // console.log(response);
      // console.log(file);
      console.log(fileList);
      this.addForm.pictures = [];
      for (var i = 0; i < fileList.length; i++) {
        this.addForm.pictures.push({ pid: fileList[i].response.data.pid });
      }
    },
    // 点击按钮添加求购信息
    addItem() {
      this.$refs.addFormRef.validate(async valid => {
        if (!valid) return;
        this.addForm.status = "on";
        console.log(this.addForm)
        const { data: res } = await this.$http.post("item/demand/add",
          this.addForm
        );

        if (res.code !== 200) this.$message.error("添加失败");

        this.getItemList();
        this.addDialogVisible = false;
        this.$message.success("添加成功");
      });
    },
    showDetail(id) {
      this.itemDetail = this.itemList[id];
      this.detailDialogVisible = true;
    },
    // 展示编辑物品信息的对话框
    showEditDialog(id) {
      this.editForm = this.itemList[id];
      // const { data: res } = await this.$http.get('users/' + id)
      // if (res.meta.status !== 200) {
      //   return this.$message.error('查询用户信息失败！')
      // }
      // this.editForm = res.data
      this.editDialogVisible = true;
    },
    // 监听修改用户对话框的关闭事件
    editDialogClosed() {
      this.$refs.editFormRef.resetFields();
    },
    // 修改物品信息并提交
    editItem() {
      this.$refs.editFormRef.validate(async valid => {
        if (!valid) return;
        console.log(this.editForm)
        const { data: res } = await this.$http.post("item/demand/update", 
          this.editForm
        );
        console.log(res);
        // if (res.code !== 200) return this.$message.error("更新失败");
        // 刷新数据列表
        this.getItemList();
        // 关闭对话框
        this.editDialogVisible = false;
        // 提示修改成功
        // this.$message.success("更新成功");
      });
    },
    async delItem(index) {
      const { data: res } = await this.$http.delete("/item/demand/delete/"+ this.itemList[index].iid);
      if (res.code !== 200) return this.$Message.error("删除失败");
      this.getItemList();
      // this.itemList.splice(index, 1);
      this.$Message.success("删除成功");
    }
  }
};
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
