package com.abeldevelop.petclinic.services.customers.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.abeldevelop.petclinic.services.customers.generated.entity.CustomerEntity;

public interface CustomerRepository {

	public CustomerEntity executeSave(CustomerEntity customerEntity);
	
	public void executeDelete(CustomerEntity customerEntity);
	
	public Optional<CustomerEntity> executeFindByIdentificationDocument(String identificationDocument);
	
	public Page<CustomerEntity> executeFindAll(Pageable page);

	public Page<CustomerEntity> executeFindAll(Specification<CustomerEntity> spec, Pageable pageable);

}
