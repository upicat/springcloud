package xm.spring.cloud.eureka.client;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
@RestController
public class ConsumerService {

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/discovery")
    public String doDiscoveryService(){
        StringBuilder buf = new StringBuilder();
        List<String> serviceIds = discoveryClient.getServices();
        if(!CollectionUtils.isEmpty(serviceIds)){
            for(String s : serviceIds){
                System.out.println("serviceId:" + s);
                List<ServiceInstance> serviceInstances =  discoveryClient.getInstances(s);
                if(!CollectionUtils.isEmpty(serviceInstances)){
                    for(ServiceInstance si:serviceInstances){
                        buf.append("["+si.getServiceId() +" host=" +si.getHost()+" port="+si.getPort()+" uri="+si.getUri()+"]");
                    }
                }else{
                    buf.append("no service.");
                }
            }
        }

        return buf.toString();
    }

    @Autowired
	HelloClient client;

	@RequestMapping("/")
	@HystrixCommand(fallbackMethod = "fallback")
	public String hello() {
		return client.hello();
	}
	public String fallback(){
        return "some exception occur call fallback method.";
    }
	
	@RequestMapping("/call")
	public String call() {
		return "call.....";
	}
	
	
//	@FeignClient("${provider.name:provider}")
	@FeignClient("provider")
	interface HelloClient {
//		@RequestMapping(value = "/", method = GET)
		@RequestMapping(value = "/hello", method = GET)
		String hello();
	}
}