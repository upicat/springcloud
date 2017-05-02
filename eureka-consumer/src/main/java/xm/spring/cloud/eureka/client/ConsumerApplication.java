package xm.spring.cloud.eureka.client;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;
import com.netflix.loadbalancer.ZoneAwareLoadBalancer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
//@RibbonClients
@EnableHystrix
//@RibbonClient(name = "provider", configuration = TestRibbonConfig.class)
@ComponentScan(
		// Exclude @Configuration classes that should be included only in sub contexts created
		// by Ribbon's SpringClientFactory.
		excludeFilters = { @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*RibbonConfig") })
public class ConsumerApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ConsumerApplication.class, args);
	}
	
//	@Autowired
//	private SpringClientFactory clientFactory;

	
	/**
	 * Throws exception if the SpringClientFactory doesn't return a balancer with a server
	 * list of the expected type.
	 */
//	@PostConstruct
//	public void test() throws Exception {
//		@SuppressWarnings("unchecked")
//		ZoneAwareLoadBalancer<Server> lb = (ZoneAwareLoadBalancer<Server>) this.clientFactory.getLoadBalancer("baz");
//
//		ServerList<Server> serverList = lb.getServerListImpl();
//		if (!(serverList instanceof TestRibbonConfig.BazServiceList)) {
//			throw new Exception("wrong server list type");
//		}
//	}
}
