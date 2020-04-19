package com.abeldevelop.petclinic.services.customers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PetclinicCustomersServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetclinicCustomersServiceApplication.class, args);
	}

}
