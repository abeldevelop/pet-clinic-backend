package com.abeldevelop.petclinic.services.vets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

import com.abeldevelop.petclinic.services.vets.config.VetsProperties;

@EnableDiscoveryClient
@ComponentScan("com.abeldevelop.petclinic")
@EnableConfigurationProperties(VetsProperties.class)
@SpringBootApplication
public class PetclinicVetsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetclinicVetsServiceApplication.class, args);
	}

}
