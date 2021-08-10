# springcloud

SpringCloud2020项目
springcloud+springcloud alibaba


# springcloud

SpringCloud2020项目
springcloud+springcloud alibaba

```
springcloud学习步骤：
1、基础使用：
	1-cloud-provider-payment8001
	2-cloud-consumer-order80
		使用RestTemplate远程调用服务（RestTemplate提供了多种便捷访问远程Http服务的方法，是一种简单便捷的访问restful服务模板类，是Spring提供的用于访问Rest服务的客户端模板工具集）。

	3-cloud-api-commons【新增模块】
		优化，实体类提出到公用模块。

2、eureka为注册中心【单机版】、RestTemplate远程调用
	eureka融合了ribbon、loadbalancer、hystrix：spring-cloud-starter-netflix-eureka-client【spring-cloud-starter-netflix-ribbon、spring-cloud-starter-loadbalancer、spring-cloud-starter-netflix-hystrix】
	4-cloud-eureka-server7001
		新增注册中心服务
			eureka:
			  instance:
				hostname: eureka7001.com  #Eureka服务端的实例名称
			  client:
				#false表示不向注册中心注册自己。
				register-with-eureka: false
				#false表示自己端就是注册中心，我的职责就是维护服务实例，并不需要检索服务
				fetch-registry: false
				service-url:
				  #单机就是自己
				  defaultZone: http://eureka7001.com:7001/eureka/
	5-cloud-provider-payment8001
		主启动类添加@EnableEurekaClient + 
			eureka:
			  client:
				#表示是否将自己注册进Eureka Server 默认为true
				register-with-eureka: true
				#是否从Eureka Server抓取已有的注册信息，默认为true。单节点无所谓，集群必须配置为true才能配合ribbon使用负载均衡。
				fetch-registry: true
				service-url:
				  defaultZone: http://localhost:7001/eureka #单机版
	6-cloud-consumer-order80
		eureka:
		  instance:
			instance-id: order80
			prefer-ip-address: true #访问路径可以显示IP地址
		  client:
			#表示是否将自己注册进Eureka Server 默认为true
			register-with-eureka: true
			#是否从Eureka Server抓取已有的注册信息，默认为true。单节点无所谓，集群必须配置为true才能配合ribbon使用负载均衡。
			fetch-registry: true
			service-url:
			  defaultZone: http://localhost:7001/eureka #单机版

3、改造为注册中心eureka集群版、服务提供者为集群版、使用@LoadBalanced注解赋予RestTemplate负载均衡的能力
	7-cloud-eureka-server7002【新增模块】
		新增eureka注册中心
	8-cloud-provider-payment8002【新增模块】
		新增服务提供者
	9-cloud-consumer-order80
		RestTemplate配置类新增负载均衡能力：ApplicationContextConfig.getRestTemplate()添加注解@LoadBalanced
	
4、actuator微服务信息完善
	10-cloud-provider-payment8001
		eureka.instance.instance-id: payment8001 # 服务名称修改
		eureka.instance.prefer-ip-address: true #访问路径可以显示IP地址
	11-cloud-provider-payment8002
		eureka.instance.instance-id: payment8002 # 服务名称修改
		eureka.instance.prefer-ip-address: true #访问路径可以显示IP地址

5、服务发现Discovery-DiscoveryClient+@EnableDiscoveryClient
	12-cloud-provider-payment8001

6、eureka自我保护机制、客户端发送心跳机制设置
	13-cloud-eureka-server7001
		eureka.server.enable-self-preservation: false #关闭自我保护机制，保证不可用服务被及时删除；默认为true，开启自我保护机制，服务不可用是不会立即清除服务。
	14-cloud-provider-payment8001
		eureka.instance.lease-renewal-interval-in-seconds: 1 #Eureka客户端向服务端发送心跳的时间间隔，单位为妙（默认是30秒）
		eureka.instance.lease-expiration-duration-in-seconds: 2 #Eureka服务端在收到最后一次心跳后等待时间上限，单位为妙（默认是90秒），超时将剔除服务

7、使用Zookeeper代替eureka为注册中心【服务节点为临时节点】
	15-cloud-provider-payment8004【新增模块】
		主启动类添加@EnableDiscoveryClient//该注解用于向使用consul或者Zookeeper作为注册中心时注册服务 + 
			#服务别名----注册Zookeeper到注册中心名称
			spring:
			  application:
				name: cloud-provider-payment
			  cloud:
				zookeeper:
				  connect-string: 192.168.25.134:2181
	16-cloud-consumerzk-order80【新增模块】
		使用RestTemplate远程调用服务 + 
		主启动类添加@EnableDiscoveryClient//该注解用于向使用consul或者Zookeeper作为注册中心时注册服务 + 
			#8004表示注册到Zookeeper服务器的支付服务提供者端口号
			server:
			  port: 80
			#服务别名----注册Zookeeper到注册中心名称
			spring:
			  application:
				name: cloud-consumer-order
			  cloud:
				zookeeper:
				  connect-string: 192.168.25.134:2181

8、使用Consul为注册中心
	17-cloud-providerconsul-payment8006【新增模块，服务提供者】
		主启动类添加@EnableDiscoveryClient//该注解用于向使用consul或者Zookeeper作为注册中心时注册服务 +
		#consul服务端口号
		server:
		  port: 8006

		spring:
		  application:
			name: consul-provider-payment
			#consul注册中心地址
		  cloud:
			host:  localhost
			port: 8500
			discovery:
			  #hostname:127.0.0.1
			  service-name: ${spring.application.name}
	18-cloud-consumerconsul-order80【新增模块，服务消费者】
		主启动类添加@EnableDiscoveryClient//该注解用于向使用consul或者Zookeeper作为注册中心时注册服务 +
		server:
		  port: 80
		spring:
		  application:
			name: cloud-consumer-order
		  #consul注册中心地址
		  cloud:
			consul:
			  host: localhost
			  port: 8500
			  discovery:
				service-name: ${spring.application.name}

9、Ribbon负载均衡服务调用
	19-cloud-consumer-order80【修改负载均衡规则为随机方式】
		注意：com.atguigu.myrule.MySelfRule自定义规则类不能放在主启动类同一包下或子包下 + 
		主启动类添加@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = MySelfRule.class) + 
		RestTemplate注释掉负载均衡的注解@LoadBalanced即可
		
		根据ribbon原理自己手写一个负载均衡规则
			 LoadBalancer【新增接口】、MySelfLoadBalancer【新增实现类】、新增controller的方法getPaymentLB

10、openfegin服务接口调用
	20-cloud-consumerfeign-order80【新增模块】
		主启动类添加@EnableFeignClients + 新增接口【类似controller方式的接口】 + 把接口当做service服务直接调用即可
			@Component
			//如果只加注解@FeignClient不加value值则报异常
			@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
			public interface PaymentFeginService {
				@GetMapping(value = "/payment/get/{id}")
				public CommonResult<Payment> getPaymentBuId(@PathVariable("id") long id);
			}
	设置ribbon超时设置：
		#设置feign客户端超时时间（OpenFeign默认支持ribbon）
		ribbon:
		  #指的是建立连接所用的时间，适用于网络状况正常的情况下，两端连接所用的实际
		  ReadTimeout: 5000
		  #指的是建立连接后从服务器读取到可用资源所用的时间
		  ConnectTimeout: 5000
	openfegin日志打印功能：
		logging:
		  level:
			#feign日志以什么级别监控那个接口
			com.atguigu.springcloud.service.PaymentFeignService: debug
		+
		@Configuration
		public class FeignConfig {
			@Bean
			Logger.Level feignLoggerLevel(){
				return Logger.Level.FULL;// 级别有：NONE/BASIC/HEADERS/FULL
			}
		}

11、hystrix断路器：服务降级、服务熔断、服务限流等
	21-cloud-provider-hystrix-payment8001【新增模块】
		
	22-cloud-consumer-feign-hystrix-order80【新增模块】
	
	23-cloud-consumer-hystrix-dashboard9001【新增模块-hystrix仪表盘】
		主启动类添加@EnableHystrixDashboard + spring-cloud-starter-netflix-hystrix-dashboard依赖
	24-修改cloud-provider-hystrix-payment8001
		主启动类中添加：
			 /**
			 * 此配置是为了服务监控而配置，与服务容错本身无关，springcloud升级后的坑
			 * ServletRegistrationBean因为SpringBoot的默认路径不是 “/hystrix.stream"
			 * 只要在自己的项目里配置上下的servlet就可以了
			 */
			@Bean
			public ServletRegistrationBean getServlet() {
				HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet() ;
				ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
				registrationBean.setLoadOnStartup(1);
				registrationBean.addUrlMappings("/hystrix.stream");
				registrationBean.setName("HystrixMetricsStreamServlet");
				return  registrationBean;
			}
	
12、Gateway网关【请求代理+路由+过滤】
	25-cloud-gateway-gateway9527【新增模块】
```
