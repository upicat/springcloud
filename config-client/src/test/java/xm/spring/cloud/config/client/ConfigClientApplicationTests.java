package xm.spring.cloud.config.client;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.config.client.ConfigServicePropertySourceLocator;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=ConfigClientApplication.class)
@DirtiesContext
public class ConfigClientApplicationTests {

	@Autowired
	private ConfigServicePropertySourceLocator locator;

	@Test
	public void contextLoads() throws Exception {
		assertNotNull(this.locator);
	}

}
