package xm.spring.cloud.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@EnableZuulProxy
public class ZuulApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ZuulApplication.class, args);
	}

	@RequestMapping("/call")
	public String call() {
		return "call Zuul.";
	}
}
