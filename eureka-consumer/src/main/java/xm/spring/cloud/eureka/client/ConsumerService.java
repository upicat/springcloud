package xm.spring.cloud.eureka.client;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;
import com.netflix.loadbalancer.ZoneAwareLoadBalancer;

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
	
	@Autowired
	private SpringClientFactory clientFactory;

	@RequestMapping("/ribbon")
	public String getServerList() throws Exception {
		ZoneAwareLoadBalancer<Server> lb = (ZoneAwareLoadBalancer<Server>) clientFactory.getLoadBalancer("consumer");
		ServerList<Server> serverList = lb.getServerListImpl();

		List<Server> serverDetailList = serverList.getInitialListOfServers();
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		if (!CollectionUtils.isEmpty(serverDetailList)) {
			for (Server s : serverDetailList) {
				pw.println(s.getHost() + "," + s.getPort()+"; ");
			}
		} else {
			pw.println("no service");
		}
		return sw.toString();
	}
}