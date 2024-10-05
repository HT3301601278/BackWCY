# BackWcy 项目

## 项目简介

BackWcy 是一个基于 Spring Boot 的后端项目,主要用于管理用户、设备和设备数据。该项目提供了用户注册、登录、设备管理以及设备数据记录等功能。

## 项目结构

项目的主要结构如下:

```
src/main/java/org/example/backwcy/
├── config/
├── controller/
├── dao/
├── dto/
├── entity/
├── exception/
├── service/
│   └── impl/
└── BackWcyApplication.java
```

### 主要组件说明

1. **config**: 包含项目的配置类,如安全配置等。
2. **controller**: 包含所有的 REST API 控制器。
3. **dao**: 包含数据访问对象(Repository)接口。
4. **dto**: 包含数据传输对象。
5. **entity**: 包含所有的实体类。
6. **exception**: 包含自定义异常类。
7. **service**: 包含服务接口和实现类。

## 主要功能

### 1. 用户管理

- 用户注册
- 用户登录
- 获取用户信息

### 2. 设备管理

- 添加新设备
- 获取所有设备列表
- 根据ID获取设备信息

### 3. 设备数据管理

- 添加设备数据
- 获取特定设备的数据列表

## 关键类说明

### 实体类

1. **User**: 用户实体类
   - 文件位置: `src/main/java/org/example/backwcy/entity/User.java`
   - 主要属性: id, username, password, createdAt

2. **Device**: 设备实体类
   - 文件位置: `src/main/java/org/example/backwcy/entity/Device.java`
   - 主要属性: id, name, createdAt

3. **DeviceData**: 设备数据实体类
   - 文件位置: `src/main/java/org/example/backwcy/entity/DeviceData.java`
   - 主要属性: id, device, value, timestamp

### 服务接口及实现

1. **UserService** 和 **UserServiceImpl**: 用户相关的业务逻辑
   - 文件位置: 
     - `src/main/java/org/example/backwcy/service/UserService.java`
     - `src/main/java/org/example/backwcy/service/impl/UserServiceImpl.java`
   - 主要方法: registerUser, loginUser, getUserById

2. **DeviceService** 和 **DeviceServiceImpl**: 设备相关的业务逻辑
   - 文件位置:
     - `src/main/java/org/example/backwcy/service/DeviceService.java`
     - `src/main/java/org/example/backwcy/service/impl/DeviceServiceImpl.java`
   - 主要方法: addDevice, getAllDevices, getDeviceById

3. **DeviceDataService** 和 **DeviceDataServiceImpl**: 设备数据相关的业务逻辑
   - 文件位置:
     - `src/main/java/org/example/backwcy/service/DeviceDataService.java`
     - `src/main/java/org/example/backwcy/service/impl/DeviceDataServiceImpl.java`
   - 主要方法: addDeviceData, getDeviceData

### 控制器

1. **UserController**: 处理用户相关的 HTTP 请求
   - 文件位置: `src/main/java/org/example/backwcy/controller/UserController.java`
   - 主要接口: 
     - POST /api/users/register: 用户注册
     - POST /api/users/login: 用户登录

2. **DeviceController**: 处理设备相关的 HTTP 请求
   - 文件位置: `src/main/java/org/example/backwcy/controller/DeviceController.java`
   - 主要接口:
     - POST /api/devices: 添加新设备
     - GET /api/devices: 获取所有设备
     - GET /api/devices/{deviceId}: 获取特定设备

3. **DeviceDataController**: 处理设备数据相关的 HTTP 请求
   - 文件位置: `src/main/java/org/example/backwcy/controller/DeviceDataController.java`
   - 主要接口:
     - POST /api/device-data: 添加设备数据
     - GET /api/device-data/device/{deviceId}: 获取特定设备的数据

### 异常处理

项目使用了全局异常处理器 `GlobalExceptionHandler` 来统一处理各种异常,提供了统一的错误响应格式。

- 文件位置: `src/main/java/org/example/backwcy/exception/GlobalExceptionHandler.java`

## 数据库配置

项目使用 MySQL 数据库,配置信息位于 `src/main/resources/application.properties` 文件中。

## API文档

### 基础URL
所有API都以 `http://localhost:8080/api` 为基础URL。

### 1. 用户管理

#### 1.1 注册用户
- **URL**: `/users/register`
- **方法**: POST
- **参数**:
  - `username`: 用户名（字符串）
  - `password`: 密码（字符串）
- **成功响应**: 
  - 状态码: 200 OK
  - 内容: 用户对象（JSON）
- **错误响应**:
  - 状态码: 400 Bad Request（无效输入）
  - 状态码: 409 Conflict（用户名已存在）

**测试用例**:
1. 正常注册：
   ```
   POST /api/users/register?username=testuser&password=testpass
   ```
   预期结果：返回状态码200和新创建的用户对象

2. 重复用户名：
   ```
   POST /api/users/register?username=testuser&password=testpass
   ```
   预期结果：返回状态码409和错误信息

3. 空用户名或密码：
   ```
   POST /api/users/register?username=&password=testpass
   ```
   预期结果：返回状态码400和错误信息

#### 1.2 用户登录
- **URL**: `/users/login`
- **方法**: POST
- **参数**:
  - `username`: 用户名（字符串）
  - `password`: 密码（字符串）
- **成功响应**: 
  - 状态码: 200 OK
  - 内容: 用户对象（JSON）
- **错误响应**:
  - 状态码: 400 Bad Request（无效输入）
  - 状态码: 401 Unauthorized（密码错误）
  - 状态码: 404 Not Found（用户不存在）

**测试用例**:
1. 正常登录：
   ```
   POST /api/users/login?username=testuser&password=testpass
   ```
   预期结果：返回状态码200和用户对象

2. 错误密码：
   ```
   POST /api/users/login?username=testuser&password=wrongpass
   ```
   预期结果：返回状态码401和错误信息

3. 不存在的用户：
   ```
   POST /api/users/login?username=nonexistentuser&password=testpass
   ```
   预期结果：返回状态码404和错误信息

### 2. 设备管理

#### 2.1 添加设备
- **URL**: `/devices`
- **方法**: POST
- **参数**:
  - `name`: 设备名称（字符串）
- **成功响应**: 
  - 状态码: 200 OK
  - 内容: 设备对象（JSON）
- **错误响应**:
  - 状态码: 400 Bad Request（无效输入）
  - 状态码: 409 Conflict（设备名称已存在）

**测试用例**:
1. 正常添加设备：
   ```
   POST /api/devices?name=testdevice
   ```
   预期结果：返回状态码200和新创建的设备对象

2. 重复设备名称：
   ```
   POST /api/devices?name=testdevice
   ```
   预期结果：返回状态码409和错误信息

3. 空设备名称：
   ```
   POST /api/devices?name=
   ```
   预期结果：返回状态码400和错误信息

#### 2.2 获取所有设备
- **URL**: `/devices`
- **方法**: GET
- **参数**: 无
- **成功响应**: 
  - 状态码: 200 OK
  - 内容: 设备对象数组（JSON）

**测试用例**:
1. 获取所有设备：
   ```
   GET /api/devices
   ```
   预期结果：返回状态码200和设备对象数组

#### 2.3 根据ID获取设备
- **URL**: `/devices/{deviceId}`
- **方法**: GET
- **参数**:
  - `deviceId`: 设备ID（路径参数，整数）
- **成功响应**: 
  - 状态码: 200 OK
  - 内容: 设备对象（JSON）
- **错误响应**:
  - 状态码: 404 Not Found（设备不存在）

**测试用例**:
1. 获取存在的设备：
   ```
   GET /api/devices/1
   ```
   预期结果：返回状态码200和设备对象

2. 获取不存在的设备：
   ```
   GET /api/devices/999
   ```
   预期结果：返回状态码404和错误信息

### 3. 设备数据管理

#### 3.1 添加设备数据
- **URL**: `/device-data`
- **方法**: POST
- **参数**:
  - `deviceId`: 设备ID（整数）
  - `value`: 数据值（浮点数）
- **成功响应**: 
  - 状态码: 200 OK
  - 内容: 设备数据对象（JSON）
- **错误响应**:
  - 状态码: 400 Bad Request（无效输入）
  - 状态码: 404 Not Found（设备不存在）

**测试用例**:
1. 正常添加设备数据：
   ```
   POST /api/device-data?deviceId=1&value=23.5
   ```
   预期结果：返回状态码200和新创建的设备数据对象

2. 设备不存在：
   ```
   POST /api/device-data?deviceId=999&value=23.5
   ```
   预期结果：返回状态码404和错误信息

3. 无效的数据值：
   ```
   POST /api/device-data?deviceId=1&value=invalid
   ```
   预期结果：返回状态码400和错误信息

#### 3.2 获取设备数据
- **URL**: `/device-data/device/{deviceId}`
- **方法**: GET
- **参数**:
  - `deviceId`: 设备ID（路径参数，整数）
- **成功响应**: 
  - 状态码: 200 OK
  - 内容: 设备数据对象数组（JSON）
- **错误响应**:
  - 状态码: 404 Not Found（设备不存在）

**测试用例**:
1. 获取存在设备的数据：
   ```
   GET /api/device-data/device/1
   ```
   预期结果：返回状态码200和设备数据对象数组

2. 获取不存在设备的数据：
   ```
   GET /api/device-data/device/999
   ```
   预期结果：返回状态码404和错误信息

### 错误处理

所有API都使用统一的错误响应格式：

```json
{
  "errorCode": "ERROR_CODE",
  "message": "错误描述",
  "details": "详细错误信息"
}
```

常见错误代码：
- `USER_NOT_FOUND`: 用户不存在
- `DEVICE_NOT_FOUND`: 设备不存在
- `USERNAME_ALREADY_EXISTS`: 用户名已存在
- `INVALID_PASSWORD`: 密码错误
- `INVALID_INPUT`: 输入无效
- `DEVICE_NAME_ALREADY_EXISTS`: 设备名称已存在
- `INTERNAL_ERROR`: 内部服务器错误
- `UNEXPECTED_ERROR`: 意外错误

### 测试环境设置

1. 确保MySQL数据库已启动，并创建了名为`backwcy`的数据库。
2. 检查`application.properties`文件中的数据库连接信息是否正确：


```1:13:src/main/resources/application.properties
spring.datasource.url=jdbc:mysql://localhost:3305/backwcy?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect

spring.jpa.open-in-view=true

server.port=8080

```


3. 运行`BackWcyApplication.java`启动Spring Boot应用。
4. 使用Postman或其他API测试工具进行测试。

## 如何运行项目

1. 确保你的开发环境中已安装 Java 和 Maven。
2. 克隆项目到本地。
3. 在项目根目录下运行 `mvn spring-boot:run` 命令。
4. 项目将在 `http://localhost:8080` 上运行。

## 学习建议

1. 首先阅读 `BackWcyApplication.java` 文件,了解项目的入口点。
2. 然后查看 `application.properties` 文件,了解项目的基本配置。
3. 依次学习实体类、服务接口及实现、控制器的代码,理解它们之间的关系。
4. 尝试使用 Postman 或其他 API 测试工具调用项目的各个接口,观察返回结果。
5. 研究 `GlobalExceptionHandler` 类,了解项目是如何统一处理异常的。
6. 最后,可以尝试添加新的功能或修改现有功能,加深对项目的理解。