package com.abeldevelop.petclinic.services.customers.repository;

import java.util.List;
import java.util.Optional;

import com.abeldevelop.petclinic.services.customers.generated.entity.CustomerEntity;

public interface CustomerRepository {

	public CustomerEntity executeSave(CustomerEntity customerEntity);
	
	public void executeDelete(CustomerEntity customerEntity);
	
	public Optional<CustomerEntity> executeFindByIdentificationDocument(String identificationDocument);
	
	public List<CustomerEntity> executeFindAll();
	
}
