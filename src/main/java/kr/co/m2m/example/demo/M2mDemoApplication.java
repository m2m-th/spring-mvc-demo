package kr.co.m2m.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication(exclude = { RedisAutoConfiguration.class })
@ComponentScan(basePackages = { "kr.co.m2m.example" }, excludeFilters = { @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".+RedisHttpSession.*") })
// @MapperScan("kr.co.m2mglobal*") 
// @EntityScan("kr.co.m2mglobal*")
public class M2mDemoApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(M2mDemoApplication.class);
	}

	public static void main(String[] args) {
		String profile = System.getProperty("spring.profiles.active");
		if (profile == null) {
			System.setProperty("spring.profiles.active", "local");
		}
		
		SpringApplication.run(M2mDemoApplication.class, args);
	}

}
