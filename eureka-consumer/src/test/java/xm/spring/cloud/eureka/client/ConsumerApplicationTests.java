package xm.spring.cloud.eureka.client;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, properties = "feign.hystrix.enabled=false")
@DirtiesContext
public class ConsumerApplicationTests {

	@Autowired
	private EurekaDiscoveryClient client;

	@Test
	public void contextLoads() throws Exception {
		assertNotNull(this.client);
	}
	
	@Value("${local.server.port}")
	int port;

	@Test
	public void clientConnects() {
		ResponseEntity<String> response = new TestRestTemplate()
				.getForEntity("http://localhost:" + port, String.class);
		assertNotNull("response was null", response);
//		assertEquals("Wrong status code", HttpStatus.OK, response.getStatusCode());
//		assertTrue(response.getBody().contains("<html"));
	}

}