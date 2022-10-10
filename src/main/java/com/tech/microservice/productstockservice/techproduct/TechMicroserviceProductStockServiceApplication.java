package com.tech.microservice.productstockservice.techproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
public class TechMicroserviceProductStockServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechMicroserviceProductStockServiceApplication.class, args);
	}

}
