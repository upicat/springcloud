package xm.spring.cloud.zuul;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=ZuulApplication.class)
@DirtiesContext
public class ZuulApplicationTests {

	@Autowired
	DiscoveryClient client;

	@Test
	public void contextLoads() throws Exception {
		assertNotNull(this.client);
	}
	
	@Test
	public void discoveryClientIsEureka() {
		assertTrue("discoveryClient is wrong type", client instanceof EurekaDiscoveryClient);
	}

}