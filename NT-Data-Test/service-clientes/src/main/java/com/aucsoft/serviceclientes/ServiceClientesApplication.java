package com.aucsoft.serviceclientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ServiceClientesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceClientesApplication.class, args);
	}

}
