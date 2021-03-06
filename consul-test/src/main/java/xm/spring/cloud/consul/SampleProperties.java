package xm.spring.cloud.consul;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Spencer Gibb
 */
@ConfigurationProperties("sample")
@Data
public class SampleProperties {

	private String prop = "default value";
}