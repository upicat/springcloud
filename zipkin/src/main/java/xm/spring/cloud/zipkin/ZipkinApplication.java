package xm.spring.cloud.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@RestController
@EnableZipkinServer
public class ZipkinApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ZipkinApplication.class, args);
	}

	@RequestMapping("/call")
	public String call() {
		return "call Zuul.";
	}
}
