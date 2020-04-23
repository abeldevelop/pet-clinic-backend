package com.abeldevelop.petclinic.services.customers.repository.springdata;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abeldevelop.petclinic.services.customers.generated.entity.CustomerEntity;

public interface CustomerSpringDataRepository extends JpaRepository<CustomerEntity, Integer> {

	Optional<CustomerEntity> findByIdentificationDocument(String identificationDocument);
	
}
