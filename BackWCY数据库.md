1. users 表（用户表）
   - id: Long (主键，自增)
   - username: String (非空，唯一)
   - password: String (非空)
   - created_at: LocalDateTime (创建时间)


2. devices 表（设备表）
   - id: Long (主键，自增)
   - name: String (非空)
   - created_at: LocalDateTime (创建时间)


3. device_data 表（设备数据表）
   - id: Long (主键，自增)
   - device_id: Long (外键，关联到 devices 表的 id)
   - value: Double (非空)
   - timestamp: LocalDateTime (非空)


现在，让我们详细解释每个表的作用和字段含义：

1. users 表：
   - 用途：存储用户信息
   - id：唯一标识每个用户的主键
   - username：用户的登录名，必须唯一
   - password：用户的登录密码（注意：实际应用中应该存储加密后的密码）
   - created_at：用户账号创建的时间

2. devices 表：
   - 用途：存储设备信息
   - id：唯一标识每个设备的主键
   - name：设备的名称
   - created_at：设备添加到系统的时间

3. device_data 表：
   - 用途：存储设备产生的数据
   - id：唯一标识每条数据记录的主键
   - device_id：关联到 devices 表的外键，表示这条数据属于哪个设备
   - value：设备记录的数值
   - timestamp：数据记录的时间戳

这个数据库结构设计允许系统：

1. 管理多个用户
2. 跟踪多个设备
3. 为每个设备记录时间序列数据

通过 device_id 外键，我们可以轻松查询特定设备的所有数据记录。同时，timestamp 字段允许我们按时间顺序检索和分析数据。