package com.tech.microservice.productstockservice.techproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableEurekaClient
public class TechMicroserviceProductStockServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechMicroserviceProductStockServiceApplication.class, args);
	}
	@Bean
	public PasswordEncoder encoder() {
	    return NoOpPasswordEncoder.getInstance();
	}

}
