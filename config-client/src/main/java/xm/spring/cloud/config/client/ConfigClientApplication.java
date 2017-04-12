package xm.spring.cloud.config.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;

@EnableAutoConfiguration
@ComponentScan
@RestController
@RefreshScope // POST http://localhost:${port}/refresh 
public class ConfigClientApplication {

	@Value("${name:World!}") String name ;

    @RequestMapping("/")
    public String home(){
        return "Hello " + name;
    }

	
	public static void main(String[] args) {
		SpringApplication.run(ConfigClientApplication.class, args);
	}

}
