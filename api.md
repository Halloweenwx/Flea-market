接口


1. 用户登录/注册
    1. /login POST
        1. req
            1. string username
            2. string password
            3. boolean isCustomer 0/1 用户/管理员
        2. res
            1. 200 登陆成功
                1. string token
            2. Bad Request 用户名密码错误
    2. /register POST
        1. req
            1. /register/phone POST
                1. string phone （主键）
            2. /register/info POST
                1. string username
                2. string name
                3. string email
                4. string password
                5. string city
        2. res
            1. string checkNum
    3. /city
        1. req
            1. /city/first GET
            2. /city/second GET
            3. /city/third GET
        2. res
            1. Array 
                1. string value: code 
                2. string label : city
            2. Array
                1. string value: code 
                2. string label : city
            3. Array
                1. string value: code 
                2. string label : city
2. 个人主页
    1. /home GET
        1. req
            1. /home/info GET
                1. token
            2. /home/info/ POST
                1. token
                2. string phone
                3. string email
                4. string oldPassword
                5. string newPassword
        2. res
            1. Object data
                1. string username
                2. string name
                3. string email
                4. string city 
                5. string memberLevel
            2. Object data
                1. 200
    2. /home/demand 
        1. req
            1. /home/demand/query GET
                1. Object queryInfo
                    1. string iid 
                    2. string query 查名字，为空返回所有
                    3. int pagenum 页号 1
                    4. int pagesize 页大小 1
            2. /home/demand/add POST 增
                1. Object
                    1. string category
                    2. string name
                    3. double highestPrice
                    4. double lowestPrice 
                    5. string description
                    6. Array [Object] pictures
                        1. pid
                        2. string filename
            3. /home/demand/modify POST 改
                1. Object
                    1. string iid
                    2. string category
                    3. string name
                    4. double highestPrice
                    5. double lowestPrice 
                    6. string description
                    7. Array [Object] pictures
                        1. pid
                        2. string filename
        2. res
            1. Object data
                1. Array[Object] items
                    1. string iid 
                    2. string category
                    3. string name
                    4. double highestPrice
                    5. double lowestPrice 
                    6. string/boolean status 
                    7. Array[Object] pictures
                        1. pid
                        2. string filename
                2. string total
            2. data
                1. 200
            3. data
                1. 200
    3. /home/idle
        1. req
            1. /home/idle/query GET
                1. Object queryInfo
                    1. string iid 
                    2. string query 查名字，为空返回所有
                    3. int pagenum 页号 1
                    4. int pagesize 页大小 1
            2. /home/idle/add POST 增
                1. Object
                    1. string category
                    2. string name
                    3. boolean isFixed
                    4. double lowestPrice
                    5. Date endDate 
                    6. string description
                    7. Array [Object] pictures
                        1. pid
                        2. string filename
            3. /home/idle/modify POST 改
                1. Object
                    1. string iid
                    2. string category
                    3. string name
                    4. boolean isFixed
                    5. double lowestPrice
                    6. Date endDate 
                    7. string description
                    8. Array [Object] pictures
                        1. pid
                        2. string filename
        2. res
            1. Object data
                1. Array[Object] items
                    1. string iid 
                    2. string category
                    3. string name
                    4. boolean isFixed
                    5. double lowestPrice
                    6. Date endDate 
                    7. string description
                    8. Array [Object] pictures
                        1. pid
                        2. string filename
                2. string total
            2. data
                1. 200
            3. data
                1. 200
        3. 
3. 照片
    1. 数据库更改，picture{pid filename binary}
    2. /picture（处理图片）
        1. req
            1. /picture/upload POST 
                1. filename
                2. Form data 二进制文件
            2. /picture/fetch/{pid} GET
        2. res
            1. 200
            2. Object（这个要试一下）
                1. binary （）
