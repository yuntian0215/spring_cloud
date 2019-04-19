# spring_cloud
spring cloud技术集合

长期不间断维护

主要操作是：整合插件，有趣的功能业务等

现有：

1，spring cloud微服务分布式架构搭建，公共类的引用等

2，spring cloud服务之间通信

3，eureka服务注册

4,spring-eureka-server为eureka客户端；spring-application1和spring-applicaton2集成了eureka服务端,所以要运行spring-application1和spring-applicaton2注意先启动spring-eureka-server

5,spring-application1集成了mybatis

6,spring-shiro集成了权限控制插件shiro,并实现了登陆的权限拦截；sessionId使用Redis来存储

7,spring-rabbitmq集成rabbitmq;hello包实现单生产者单消费者，单生产者多消费者；user包实现实体类传输；
	topic包实现的是交换机绑定binding_key进行绑定匹配传输；fanout包实现的交换机指定routing_key传输；
	callback包+config/RabbitConfig+topic/topicMessagesReceiver实现消息发送回调
	
8,spring-elasticsearch集成独立的elasticsearch集群，只是简单的实现查询，其他条件查询继续...

9,spring-weixin是针对微信公众号的开发

10,spring-mongodb集成mongodb的常用方法

11,spring-kafka集成了kafka，注意配置文件hosts名称需要和服务器hosts名称对应上
