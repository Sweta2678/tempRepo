package com.tapestry.moic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan(basePackages = {"com.tapestry.moic"})
@SpringBootApplication
@EnableScheduling
public class MoicApiApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MoicApiApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MoicApiApplication.class);
	}
}
