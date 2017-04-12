package xm.spring.cloud.eureka.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 *
 * @author Gunnar Hillert
 *
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class EurekaClientApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(EurekaClientApplication.class, args);
	}

}
