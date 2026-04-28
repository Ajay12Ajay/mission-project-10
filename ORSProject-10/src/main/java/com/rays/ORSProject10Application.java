package com.rays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Main class for Spring Boot application. It bootstraps the application and
 * configures global settings like CORS.
 * 
 * @author Ajay Pratap Kerketta
 */
@SpringBootApplication
public class ORSProject10Application {

	/**
	 * Main method to run the Spring Boot application.
	 * 
	 * @param args Command-line arguments passed during application startup
	 * @return void
	 */
	public static void main(String[] args) {
		SpringApplication.run(ORSProject10Application.class, args);
	}

	/**
	 * Bean configuration for handling CORS (Cross-Origin Resource Sharing). This
	 * allows frontend applications (like Angular running on localhost:4200) to
	 * communicate with backend APIs.
	 * 
	 * @return WebMvcConfigurer - Configured CORS settings
	 */
	@Bean
	public WebMvcConfigurer corsConfig() {

		/**
		 * Anonymous implementation of WebMvcConfigurer
		 */
		WebMvcConfigurer w = new WebMvcConfigurer() {

			/**
			 * Configure CORS mappings for the application.
			 * 
			 * @param registry CorsRegistry object used to define CORS rules
			 * @return void
			 */
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:4200", "http://localhost:8080")
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS").allowedHeaders("*")
						.allowCredentials(true);
			}
		};

		return w;
	}

}