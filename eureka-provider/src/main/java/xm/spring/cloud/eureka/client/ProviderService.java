package xm.spring.cloud.eureka.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
@RestController
public class ProviderService {
	@Autowired
	DiscoveryClient client;

	@RequestMapping("/hello")
	@HystrixCommand(fallbackMethod = "fallback")
	public String helloProvider() {
		ServiceInstance localInstance = client.getLocalServiceInstance();
		return "Hello World: "+ localInstance.getServiceId()+":"+localInstance.getHost()+":"+localInstance.getPort();
	}
	
	public String fallback(){
        return "some exception occur call fallback method.";
    }
	
}