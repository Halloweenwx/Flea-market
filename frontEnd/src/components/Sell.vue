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
        <!-- <iCol :span="7">
          <iInput placeholder="请输入内容" v-model="queryInfo.query" @on-clear="getItemList">
            <iButton slot="append" icon="ios-search-outline" @click="getItemList"></iButton>
          </iInput>
        </iCol> -->
        <iCol :offset="18" :span="4">
          <iButton type="primary" @click="addDialogVisible = true">我有东西要卖</iButton>
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
        :total="totalPages"
        show-total
        show-elevator
        show-sizer
        @on-page-size-change="handleSizeChange"
        @on-change="handleCurrentChange"
      ></Page>
    </Card>

    <!-- 出售物品的对话框 -->
    <Modal title="我有东西要卖" v-model="addDialogVisible" @close="addDialogClosed">
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
        <FormItem label="物品最低价格" prop="startPrice">
          <iInput v-model="addForm.startPrice" clearable></iInput>
        </FormItem>
        <FormItem label="是否一口价" prop="priceFixed">
          <iSwitch v-model="addForm.priceFixed" />
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
        <FormItem label="物品图片" prop="pictures">
          <!-- <iInput v-model="addForm.photo"></iInput> -->
          <Upload
            multiple
            type="drag"
            :format="['jpg', 'jpeg', 'png']"
            action="//localhost:8080/picture/upload"
            @on-success="upLoadImg"
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
            title="是否一口价"
            :description="detail.priceFixed"
          />
        </ListItem>
        <ListItem>
          <ListItemMeta
            avatar="https://dev-file.iviewui.com/userinfoPDvn9gKWYihR24SpgC319vXY8qniCqj4/avatar"
            title="竞价结束日期"
            :description="detail.endDate"
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

    <!-- 修改出售物品的对话框 -->
    <Modal title="修改出售物品的信息" v-model="editDialogVisible">
      <Form
        :model="editForm"
        :rules="editFormRules"
        ref="editFormRef"
        :label-width="125"
        @close="editDialogClosed"
      >
        <FormItem label="物品类型" prop="category">
          <Select v-model="editForm.category" style="width:200px" clearable>
            <Option v-for="item in typeList" :value="item.value" :key="item.value">{{ item.label }}</Option>
          </Select>
        </FormItem>
        <FormItem label="物品名称" prop="name">
          <iInput v-model="editForm.name"></iInput>
        </FormItem>
        <FormItem label="物品最低价格" prop="startPrice">
          <iInput v-model="editForm.startPrice"></iInput>
        </FormItem>
        <!-- <FormItem label="是否一口价" prop="priceFixed">
          <iSwitch v-model="editForm.priceFixed" />
        </FormItem>
        <FormItem label="竞价结束日期" prop="endDate">
          <DatePicker
            type="date"
            v-model="editForm.endDate"
            clearable
            placeholder="请选择竞价结束日期"
            style="width: 200px"
          ></DatePicker>
        </FormItem> -->
        <FormItem label="物品描述" prop="description">
          <iInput v-model="editForm.description"></iInput>
        </FormItem>
        <FormItem label="物品图片" prop="photo">
          <!-- <iInput v-model="editForm.photo"></iInput> -->
          <Upload
            multiple
            type="drag"
            :format="['jpg', 'jpeg', 'png']"
            action="//localhost:8080/picture/upload"
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
          category: "电子类",
          name: "笔记本",
          startPrice: 10,
          priceFixed: "是",
          status: "待售"
        }
      ],
      totalPages: 100,
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
          key: "startPrice"
        },
        {
          title: "是否一口价",
          key: "priceFixed"
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
        category: "",
        name: "",
        startPrice: "",
        priceFixed: false,
        endDate: "",
        description: "",
        pictures: ""
      },
      //   添加表单的验证规则对象
      addFormRules: {
        category: [
          { required: true, message: "请选择物品类型", trigger: "blur" }
        ],
        name: [{ required: true, message: "请输入物品名称", trigger: "blur" }],
        startPrice: [
          {
            required: true,
            message: "请输入物品的最低求购价格",
            trigger: "blur"
          }
        ],
        // priceFixed: [
        //   {
        //     required: true,
        //     type: "boolean",
        //     message: "请选择是否为一口价",
        //     trigger: "blur"
        //   }
        // ],
        // endDate: [
        //   {
        //     required: true,
        //     type: "date",
        //     message: "请选择竞价结束日期",
        //     trigger: "blur"
        //   }
        // ]
      },
      detailDialogVisible: false,
      detail: {},
      editDialogVisible: false,
      //   查询到的物品
      editForm: {
        category: "",
        name: "",
        startPrice: "",
        priceFixed: false,
        endDate: "",
        description: "",
        pictures: ""
      },
      // 修改表单的验证规则对象
      editFormRules: {
        category: [
          { required: true, message: "请选择物品类型", trigger: "blur" }
        ],
        name: [{ required: true, message: "请输入物品名称", trigger: "blur" }],
        startPrice: [
          {
            required: true,
            message: "请输入物品的最低求购价格",
            trigger: "blur"
          }
        ],
        priceFixed: [
          { required: true, message: "请选择是否为一口价", trigger: "blur" }
        ],
        endDate: [
          { required: true, message: "请选择竞价结束日期", trigger: "blur" }
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
    // 获取物品列表
    async getItemList() {
      console.log(this.queryInfo);
      const { data: res } = await this.$http.get("home/item/idle", {
        params: this.queryInfo
      });

      if (res.code !== 200) return this.$message.error("获取物品列表失败!");
      for(var i in res.data.content){
        res.data.content[i].category = res.data.content[i].category.cnCategory;
        res.data.content[i].status = res.data.content[i].itemStatus.cnStatus;
        res.data.content[i].canChange = res.data.content[i].itemStatus.enStatus === 'on';
      }
      this.itemList = res.data.content;
      this.totalPages = res.data.totalPages;
      console.log(res)
      console.log(this.itemList)
      // console.log(res);
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
      console.log(response);
    },
    // 点击按钮添加求购信息
    addItem() {
      this.$refs.addFormRef.validate(async valid => {
        if (!valid) return;
        console.log(this.addForm.endDate);
        console.log(typeof this.addForm.endDate);
        this.addForm.endDate = this.addForm.endDate.format(
          "yyyy-mm-dd hh:mm:ss"
        );
        console.log(this.addForm.endDate);
        // 发起请求
        this.addForm.status = "on";
        const { data: res } = await this.$http.post(
          "item/idle/add",
          this.addForm
        );

        if (res.code !== 200) return this.$message.error("添加失败");

        this.getItemList();
        this.addDialogVisible = false;
        this.$message.success("添加成功");
      });
    },
    // 展示编辑物品信息的对话框
    showEditDialog(id) {
      this.editForm = this.itemList[id];
      if (this.editForm.priceFixed === "是") this.editForm.priceFixed = true;
      else this.editForm.priceFixed = false;
      this.editDialogVisible = true;
    },
    showDetail(index) {
      this.itemDetail = this.itemList[index];
      // console.log(this.itemDetail);
      this.detailDialogVisible = true;
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
        const { data: res } = await this.$http.post("item/idle/update", 
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
      const { data: res } = await this.$http.delete("/item/idle/delete/"+ this.itemList[index].iid);
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
