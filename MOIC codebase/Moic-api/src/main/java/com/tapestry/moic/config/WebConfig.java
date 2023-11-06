package com.tapestry.moic.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The Class WebConfig.
 *
 * @version 0.0.1
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

	/**
	 * Method addCorsMappings.
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {

		registry.addMapping("/**").allowedMethods("GET", "POST").allowedOrigins("*")
				.allowedHeaders("*");

	}

}