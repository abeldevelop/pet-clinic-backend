package com.abeldevelop.petclinic.server.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class PetclinicDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetclinicDiscoveryServerApplication.class, args);
	}

}
