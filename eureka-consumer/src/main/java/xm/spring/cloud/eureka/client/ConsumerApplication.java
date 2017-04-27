package xm.spring.cloud.eureka.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
//@RibbonClient(name = "hello", configuration = HelloRibbonClientConfiguration.class)
public class ConsumerApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ConsumerApplication.class, args);
	}

//	@Configuration
//	class HelloRibbonClientConfiguration {
//
//		@Bean
//		public ILoadBalancer ribbonLoadBalancer() {
//			//because of this, it doesn't use eureka to lookup the server,
//			// but the classpath is tested
//			BaseLoadBalancer balancer = new BaseLoadBalancer();
//			balancer.setServersList(Arrays.asList(new Server("example.com", 80)));
//			return balancer;
//		}
//
//	}
}
