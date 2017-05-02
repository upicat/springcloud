package xm.spring.cloud.zuul;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.netflix.zuul.FilterFileManager;
import com.netflix.zuul.FilterLoader;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.ContextLifecycleFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.filters.FilterRegistry;
import com.netflix.zuul.groovy.GroovyCompiler;
import com.netflix.zuul.groovy.GroovyFileFilter;
import com.netflix.zuul.http.ZuulServlet;
import com.netflix.zuul.monitoring.MonitoringHelper;

@Component
public class MyCommandLineRunner implements CommandLineRunner {
	@Override
	public void run(String... args) throws Exception {
		MonitoringHelper.initMocks();
		initJavaFilters();
		FilterLoader.getInstance().setCompiler(new GroovyCompiler());
		try {
			FilterFileManager.setFilenameFilter(new GroovyFileFilter());
			FilterFileManager.init(10, "groovy");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Autowired
	ZuulFilter myFilter;

	private void initJavaFilters() {
		final FilterRegistry r = FilterRegistry.instance();

		//r.put("javaPreFilter", myFilter);

		 r.put("javaPreFilter", new ZuulFilter() {
		 @Override
		 public int filterOrder() {
		 return 50000;
		 }
		
		 @Override
		 public String filterType() {
		 return "pre";
		 }
		
		 @Override
		 public boolean shouldFilter() {
		 return true;
		 }
		
		 @Override
		 public Object run() {
		 System.out.println("running javaPreFilter!!!!!!!!!!!!");
		 return null;
		 }
		 });

	}

}