2019秋季学期Web开发技术实践作业
基于Spring、Structs、Hibernate/Mybatis等开源框架开发，实现一个小买卖中介平台原型系统，数据库采用开源的mysql，要求涉及至少2种开源框架的使用。   
一、系统用户
1）普通用户 可用功能需求
* 注册用户基本信息；
* 修改用户联系电话、密码信息；
* 登录系统验证密码的正确性；
* 可以是卖二手物品身份（卖家）：填写发布物品信息，上传物品照片；查询自己发布的物品信息，对于有竞价该物品的用户可点击同意或拒绝，如果同意则平台按照规则计算中介费并提交数据库存储；修改自己已发布未成交的自己物品信息；删除已发布未成交的自己物品信息；查询所有在售物品信息；查询所有物品需求信息。

* 可以是买物品身份（买家）：填写想购买的二手物品信息；查询或修改自己发布的购买物品需求信息；删除已发布的购买物品需求信息；查询所有购买物品需求信息；查询所有在售物品信息。点击某一具体物品可看到明细信息，点击购买可模拟竞价购买的请求。
2）管理员 可用功能需求
可以查询当前所有用户基本信息；查询一定条件的物品及其状态信息，点击某一物品标识可显示货主信息；查询购物需求信息，点击某一标识可显示求购用户基本信息；查询一定条件下当前已经成交物品的累计中介费收益信息。
二、系统功能模块

1. 用户注册功能；
2. 用户登录功能；在首页输入用户名、密码，验证后登录系统；
3. 用户信息管理模块：用户可以浏览自己注册的基本信息，只能修改联系电话和登录密码。
4. 物品信息查询功能模块：默认显示所有物品信息，可以输入物品类型、物品名称、价格区间条件，显示满足条件的物品信息，注意货主姓名、电话号码信息不可见。
5. 求购物品信息查询功能模块：默认显示所有求购物品信息，可以输入物品类型、物品名称、价格区间条件，显示满足条件的求购物品信息，注意求购用户姓名、电话号码信息不可见。
6. 我要卖功能模块：默认显示当前自己已发布的所有物品信息；发布新的物品信息；修改自己已发布未成交物品信息；删除自己已发布未成交物品信息；
7. 我要买功能模块：默认显示当前已发布的所有物品信息；发布自己新的物品购买需求信息；修改自己已发布未成交的购买需求信息；删除已发布未成交的购买需求信息；
8. 统计分析功能模块：显示起始年月、终止年月、某个地域不同物品类型成交中介费的明细，并按月以折线图的方式展示每月累计成交笔数、中介费金额的变化趋势。

三、开发业务模型及对应的后台数据库表简要说明

1. 创建用户类：用户标识、用户名、登录密码、用户类型（系统管理员/普通用户）、用户姓名、手机号码(11位数字)、用户级别（钻石级、重要、一般）、注册城市、注册时间、修改时间。在后台数据库至少要建立一个管理员用户,用户名：admin,密码admin。
2. 创建出售物品类：物品标识、所属用户标识、物品类型（电子类、日常用品类、书籍类）、物品名称、物品最低价格（元）、是否一口价、竞价结束日期、物品描述、物品照片、创建时间、修改时间、状态（已售、待售）。
3. 创建求购物品类：求购请求标识、求购用户标识、物品类型（电子类、日常用品类、书籍类）、物品名称、求购最低价格（元）、求购最高价格（元）、物品描述、物品照片、创建时间、修改时间、状态（已购、待购）。
4. 物品成交明细表：物品标识、出售用户标识、购买用户标识、成交日期、成交价格、买家支付中介费（成交价格\*0.02）、卖家支付中介费（成交价格\*0.01）。
5. 中介收益汇总表：日期（YYYYMMDD）、地域（省-市）、物品类型、成交笔数、中介费收入金额。
6. 注：开始可以直接在数据库中插入些符合业务逻辑的测试数据。数据库表可以涵盖上述要求基础上扩展其它内容。

四、开发视图层页面基本要求
1. 用户注册页面：输入用户基本信息及密码。（要求密码不少于6位，必须含有两个数字，不能与原来的密码相似，不能都为大写或小写）
2. 用户登录页面，输入用户名、密码，验证通过后登录系统。用户名、密码可以事先插入到数据库中。
3. 修改用户信息页面：显示所有基本信息，但只能修改手机号码和密码（同前要求）。
4. 我要卖信息新增发布、查询、修改、删除功能页面；
5. 我要买信息新增发布、查询、修改、删除功能页面；
6. 用户详细信息展示页面；
7. 物品信息显示页面；
8. 成交订单确认页面；
9. 物品买卖成交明细及统计信息查询页面；

* 可输入起始年月日、终止年月日、某个地域条件，按条件查询显示物品成交中介费的明细列表，并按日以折线图的方式展示每日累计成交笔数、中介费金额的变化趋势。
* 可支持按照累计成交笔数、累计中介费排序的功能。

五、在完成基本功能基础之上，可扩展其它功能。如根据用户的级别进行不同比例中介费的收取规则，用户可以上传物品展示的视频文件等。

六、程序验收说明：
1. 最多3人一组，20周(2019年1月6日)前提交文件：姓名1_姓名2_姓名3.rar。包含三部分内容：
（1）工程源代码
（2）建表及测试数据insert语句文件,命名为sql.txt
（3）针对题目需求的工程说明文件：包含运行环境配置说明、已实现功能、未实现功能、额外添加的功能,关键界面截图及说明。
（4）小组分工说明，说明每个同学的贡献百分比及主要工作。
2. 18周上课时检查大作业完成情况，要求带着部署好程序的笔记本演示并讲解程序的运行过程，要求能正确回答老师提出的问题，如18周未完成，请在19周结束前提交文档，和老师单独约时间检查。

