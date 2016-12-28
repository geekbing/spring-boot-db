# spring-boot-db

这是一个关于Spring Boot的数据库访问的演示Demo.

主要使用到以下技术或改进的地方：

1. 使用Druid数据库连接池，并且配置开启监控。
2. 扩展了JPA，增加了14个自定义的方法，更加方便了数据库的操作。
3. 使用Redis作为缓存。并且对Redis的RedisTemplate使用范型进行了封装，使得对Redis的操作极其简单。
