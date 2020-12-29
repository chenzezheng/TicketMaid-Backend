[toc]

# Ticket Maid API文档

**基础说明**

___

- 基础URL: "http://39.108.211.7:8123/api"
- 参数和返回均为**JSON**格式
- ***VERSION:1 ***

___

## 用户

### 注册

- URL

  > `/user/register`

- 请求方式

  > POST

- 参数

  ```json
  {
      "username": "root",
      "password": "root"
  }
  ```

- 返回示例

  ```json
  {
      "code": 0,						   // 状态码（通常>=0表示成功，<0表示失败）
      "data": {
          "id": 11,				    	// 用户id
          "username": "test",				// 用户名
          "nickname": "test",			 	// 昵称
          "sex": 2,					   // 性别
          "email": null,				   // 邮箱
          "is_admin": 0				   // 是否管理员
      },
      "desc": "注册成功"					// 返回描述信息
  }
  ```

  

### 登录

- URL

> `/user/login`

- 请求方式

> POST

- 参数

  ```json
  {
      "username": "root",
      "password": "root"
  }
  ```

- 返回示例

  ```json
  {
      "code": 0,
      "data": {
          "access_token": "xxxxxxxxx",	// 登录token
          "refresh_token": "xxxxxxxx",	// 刷新token
          "user": {
              "id": 9,
              "username": "root",
              "nickname": "root",
              "sex": 2,
              "email": null,
              "is_admin": 1
          }
      },
      "desc": "登录成功"
  }
  ```

### 修改信息

- URL

  > `user/modify/profile`

- 请求头

  ```json
  {
      "access_token": "xxxxxxxx"
  }
  ```

- 请求方式

  > PUT

- 参数

  ```json
  {
      "nickname": "test",
      "sex": 2,
      "email": null
  }
  ```

- 返回示例

  ```json
  {
      "code": 0,
      "data": null,
      "desc": "修改资料成功"
  }
  ```

  

### 修改密码

- URL

  > `user/modify/password`

- 请求头

  ```json
  {
      "access_token": "xxxxxxxx"
  }
  ```

- 请求方式

  > PUT

- 参数

  ```json
  {
      "password": "test"
  }
  ```

- 返回示例

  ```json
  {
      "code": 0,
      "data": null,
      "desc": "修改密码成功"
  }
  ```



## Token

### 刷新Token

- URL

  > token/refresh

- 请求头

  ```json
  {
      "refresh_token": "xxxxxxxx"
  }
  ```

- 请求方式

  > GET

- 返回示例

  ```json
  {
      "code": 0,
      "data": {
          "access_token": "xxxxxxxxx",	// 登录token
          "refresh_token": "xxxxxxxx",	// 刷新token
      },
      "desc": "刷新成功"
  }
  ```



## 地址

### 添加收货地址

- URL

  > `address/add`

- 请求头

  ```json
  {
      "access_token": "xxxxxxxx"
  }
  ```

- 请求方式

  > POST

- 参数

  ```json
  {
      "name": "chen zezheng",
      "address": "Wuhan University",
      "phone": "18988888888"
  }
  ```

- 返回示例

  ```json
  {
      "code": 0,
      "data": {
          "id": 2,
          "user_id": 3,
          "address": "Wuhan University",
          "name": "czz",
          "phone": "18938768888"
      },
      "desc": "加入地址信息成功"
  }
  ```

### 查询用户收货地址

- URL

  > `address/query`

- 请求头

  ```json
  {
      "access_token": "xxxxxxxx"
  }
  ```

- 请求方式

  > GET

- 参数

  ```json
  {
      "pageNo": 1,		// （可选）页码
      "pageSize": 8		// （可选）页大小，若为0则不分页
  }
  ```

- 返回示例

  ```json
  {
      "code": 0,
      "data": [
          {
              "id": 1,
              "user_id": 3,
              "address": "wuhan",
              "name": "chenzezheng",
              "phone": "18938888888"
          },
          {
              "id": 2,
              "user_id": 3,
              "address": "Wuhan University",
              "name": "czz",
              "phone": "18938768888"
          }
      ],
      "desc": "查询成功"
  }
  ```

### 删除地址

- URL

  > `address/remove`

- 请求头

  ```json
  {
      "access_token": "xxxxxxxx"
  }
  ```

- 请求方式

  > DELETE

- 参数

  ```json
  {
      "id": 1		// 地址id
  }
  ```

- 返回示例

  ```json
  {
      "code": 0,
      "data": null,
      "desc": "删除成功"
  }
  ```

### 修改地址

- URL

  > `address/modify`

- 请求头

  ```json
  {
      "access_token": "xxxxxxxx"
  }
  ```

- 请求方式

  > PUT

- 参数

  ```json
  {
      "name": "chen zezheng",
      "address": "Wuhan University",
      "phone": "18988888888"
  }
  ```

- 返回示例

  ```json
  {
      "code": 0,
      "data": {
          "id": 2,
          "user_id": 3,
          "address": "Wuhan University",
          "name": "czz",
          "phone": "18938768888"
      },
      "desc": "修改成功"
  }
  ```



## 活动

### 添加活动

- URL

  > `event/add`

- 请求头

  ```json
  {
      "access_token": "xxxxxxxx"
  }
  ```

- 请求方式

  > POST

- 参数

  ```json
  {
      "name": "桃子星球音乐艺术节",			// 活动名称
      "quota": "10",						  // 活动名额
      "time": "2020-12-31 18:00:00",		   // 活动时间
      "location": "上海市杨浦区淞沪路",		// 活动地点
      "info": "暂无介绍",					   // 活动介绍
      "cover": "https://i.loli.net/2020/12/06/rlqEVfN387hmjp5.jpg",		// 活动封面链接
      "price": "120"						  // 活动票价
  }
  ```

- 返回示例

  ```json
  {
      "code": 0,
      "data": {
          "id": 11,
          "name": "桃子星球音乐艺术节",
          "host_id": 3,
          "time": "2020-12-31T10:00:00.000+00:00",
          "location": "上海市杨浦区淞沪路",
          "quota": 10,
          "info": "暂无介绍",
          "cover": "https://i.loli.net/2020/12/06/rlqEVfN387hmjp5.jpg",
          "price": 120.0
      },
      "desc": "添加活动成功"
  }
  ```

### 删除活动

- URL

  > `event/remove`

- 请求头

  ```json
  {
      "access_token": "xxxxxxxx"
  }
  ```

- 请求方式

  > DELETE

- 参数

  ```json
  {
      "id": 1		// 活动id
  }
  ```

- 返回示例

  ```json
  {
      "code": 0,
      "data": null,
      "desc": "删除活动成功"
  }
  ```

### 统计活动数量

- URL

  > `event/count`

- 请求方式

  > GET

- 返回示例

  ```json
  {
      "code": 0,
      "data": 3,			// 活动数量
      "desc": "计数成功"
  }
  ```

### 查询活动

- URL

  > `event/query`

- 请求方式

  > GET

- 参数

  ```json
  {
      "pageNo": 1,		// 页码
      "pageSize": 8		// 页大小
  }
  ```

- 返回示例

  ```json
  {
      "code": 0,
      "data": [
          {
              "id": 10,
              "name": "Bilibili Macro Link-Visual Release 2020",
              "host_id": 9,
              "time": "2020-12-30T10:00:00.000+00:00",
              "location": "上海 国家会展中心（上海）虹馆",
              "quota": 97,
              "info": "xxx",
              "cover": "https://i.loli.net/2020/12/22/ZXpcSQ3F7LkfaVN.jpg",
              "price": 280.0
          },
          {
              "id": 12,
              "name": "桃子星球音乐艺术节",
              "host_id": 9,
              "time": "2020-12-31T10:00:00.000+00:00",
              "location": "上海市杨浦区淞沪路",
              "quota": 1,
              "info": "暂无介绍",
              "cover": "https://i.loli.net/2020/12/06/rlqEVfN387hmjp5.jpg",
              "price": 120.0
          }
      ],
      "desc": "查询成功"
  }
  ```

### 搜索活动

- URL

  > `event/search`

- 请求方式

  > GET

- 参数

  ```json
  {
      "keyword": "测试1"		// 关键词
  }
  ```

- 返回示例

  ```json
  {
      "code": 0,
      "data": {
          "id": 8,
          "name": "测试1",
          "host_id": 3,
          "time": "2020-12-25T06:00:00.000+00:00",
          "location": "上海",
          "quota": 100,
          "info": null,
          "cover": "www.test.com/cover.png",
          "price": 12.0
      },
      "desc": "查询成功"
  }
  ```

### 修改活动

- URL

  > `event/modify`

- 请求头

  ```json
  {
      "access_token": "xxxxxxxx"
  }
  ```

- 请求方式

  > PUT

- 参数

  ```json
  {
      "name": "桃子星球音乐艺术节",			// 活动名称
      "quota": "10",						  // 活动名额
      "time": "2020-12-31 18:00:00",		   // 活动时间
      "location": "上海市杨浦区淞沪路",		// 活动地点
      "info": "暂无介绍",					   // 活动介绍
      "cover": "https://i.loli.net/2020/12/06/rlqEVfN387hmjp5.jpg",		// 活动封面链接
      "price": "120"						  // 活动票价
  }
  ```

- 返回示例

  ```json
  {
      "code": 0,
      "data": {
          "id": 11,
          "name": "桃子星球音乐艺术节",
          "host_id": 3,
          "time": "2020-12-31T10:00:00.000+00:00",
          "location": "上海市杨浦区淞沪路",
          "quota": 10,
          "info": "暂无介绍",
          "cover": "https://i.loli.net/2020/12/06/rlqEVfN387hmjp5.jpg",
          "price": 120.0
      },
      "desc": "修改活动信息成功"
  }
  ```

### 查询特定管理员发起的活动

- URL

  > `event/adminquery`

- 请求头

  ```json
  {
      "access_token": "xxxxxxxx"
  }
  ```

- 请求方式

  > GET

- 参数

  ```json
  {
      "pageNo": 1,
      "pageSize": 8
  }
  ```

- 返回示例

  ```json
  {
      "code": 0,
      "data": [
          {
              "id": 8,
              "name": "测试1",
              "host_id": 3,
              "time": "2020-12-25T06:00:00.000+00:00",
              "location": "上海",
              "quota": 100,
              "info": null,
              "cover": "www.test.com/cover.png",
              "price": 12.0
          },
          {
              "id": 11,
              "name": "桃子星球音乐艺术节",
              "host_id": 3,
              "time": "2020-12-31T10:00:00.000+00:00",
              "location": "上海市杨浦区淞沪路",
              "quota": 10,
              "info": "暂无介绍",
              "cover": "https://i.loli.net/2020/12/06/rlqEVfN387hmjp5.jpg",
              "price": 120.0
          }
      ],
      "desc": "查询成功"
  }
  ```



## 收藏

### 添加收藏

- URL

  > `favorite/add`

- 请求头

  ```json
  {
      "access_token": "xxxxxxxx"
  }
  ```

- 请求方式

  > POST

- 参数

  ```json
  {
      "event_id": 11		// 收藏活动id
  }
  ```

- 返回示例

  ```json
  {
      "code": 0,
      "data": {
          "id": 10,
          "user_id": 3,
          "event_id": 11,
          "time": "2020-12-21T22:07:14.535+00:00"
      },
      "desc": "收藏成功"
  }
  ```

### 删除收藏

- URL

  > `favorite/remove`

- 请求头

  ```json
  {
      "access_token": "xxxxxxxx"
  }
  ```

- 请求方式

  > DELETE

- 参数

  ```json
  {
      "id": 11		// 取消收藏活动id
  }
  ```

- 返回示例

  ```json
  {
      "code": 0,
      "data": null,
      "desc": "取消收藏成功"
  }
  ```

### 查询用户收藏

- URL

  > `favorite/query`

- 请求头

  ```json
  {
      "access_token": "xxxxxxxx"
  }
  ```

- 请求方式

  > GET

- 参数

  ```json
  {
      "pageNo": 1,
      "pageSize": 8
  }
  ```

- 返回示例

  ```json
  {
      "code": 0,
      "data": [
          {
              "id": 8,
              "user_id": 3,
              "event_id": 8,
              "time": "2020-12-12T04:03:05.000+00:00",
              "name": "测试1",
              "price": 12.0,
              "cover": "www.test.com/cover.png"
          },
          {
              "id": 9,
              "user_id": 3,
              "event_id": 9,
              "time": "2020-12-11T20:16:32.000+00:00",
              "name": "测试2",
              "price": 12.0,
              "cover": "www.test.com/cover.png"
          }
      ],
      "desc": "查询收藏成功"
  }
  ```



## 订单

### 创建订单

- URL

  > `order/add`

- 请求头

  ```json
  {
      "access_token": "xxxxxxxx"
  }
  ```

- 请求方式

  > POST

- 参数

  ```json
  {
      "name": "chenzezheng",		// 收件人姓名
      "address": "wuhan",			// 收件人地址
      "phone": "18938888888",		// 收件人手机号
      "event_id": 9,			   // 购买活动id
      "quantity": 1			   // 购买数量
  }
  ```

- 返回示例

  ```json
  {
      "code": 0,
      "data": {
          "id": 0,
          "user_id": 3,
          "event_id": 9,
          "time": "2020-12-21T22:16:57.564+00:00",
          "quantity": 1,
          "name": "chenzezheng",
          "address": "wuhan",
          "phone": "18938888888"
      },
      "desc": "排队中"
  }
  ```

### 删除订单

- URL

  > `order/remove`

- 请求头

  ```json
  {
      "access_token": "xxxxxxxx"
  }
  ```

- 请求方式

  > DELETE

- 参数

  ```json
  {
      "id": 1		// 订单id
  }
  ```

- 返回示例

  ```json
  {
      "code": 0,
      "data": null,
      "desc": "删除订单成功"
  }
  ```

### 查询订单

- URL

  > `order/query`

- 请求头

  ```json
  {
      "access_token": "xxxxxxxx"
  }
  ```

- 请求方式

  > GET

- 参数

  ```json
  {
      "pageNo": 1,
      "pageSize": 8
  }
  ```

- 返回示例

  ```json
  {
      "code": 0,
      "data": [
          {
              "id": 2,
              "name": "测试1",
              "cover": "www.test.com/cover.png",
              "price": 12.0,
              "quantity": 2
          },
          {
              "id": 3,
              "name": "测试2",
              "cover": "www.test.com/cover.png",
              "price": 12.0,
              "quantity": 10
          }
      ],
      "desc": "查询订单列表成功"
  }
  ```

### 查询订单详情

- URL

  > `order/info`

- 请求头

  ```json
  {
      "access_token": "xxxxxxxx"
  }
  ```

- 请求方式

  > GET

- 参数

  ```json
  {
      "id": 3,
  }
  ```

- 返回示例

  ```json
  {
      "code": 0,
      "data": {
          "id": 3,
          "event_name": "测试2",
          "event_time": "2020-12-25T06:00:00.000+00:00",
          "event_cover": "www.test.com/cover.png",
          "event_price": 12.0,
          "order_quantity": 10,
          "order_time": "2020-12-17T20:22:23.000+00:00",
          "user_name": "chenzezheng",
          "user_address": "wuhan",
          "user_phone": "18938888888"
      },
      "desc": "查询订单详情成功"
  }
  ```

### 检查订单状态

- URL

  > `order/check`

- 请求头

  ```json
  {
      "access_token": "xxxxxxxx"
  }
  ```

- 请求方式

  > GET

- 参数

  ```json
  {
      "event_id": 10,
  }
  ```

- 返回示例

  ```json
  {
      "code": 1,
      "data": null,
      "desc": "购买成功"
  }
  ```

  

  ```json
  {
      "code": 0,
      "data": null,
      "desc": "排队中"
  }
  ```

  

  ```json
  {
      "code": -1,
      "data": null,
      "desc": "名额不足"
  }
  ```

  