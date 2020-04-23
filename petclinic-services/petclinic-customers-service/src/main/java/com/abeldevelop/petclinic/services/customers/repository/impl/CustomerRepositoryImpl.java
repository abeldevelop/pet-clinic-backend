package com.abeldevelop.petclinic.services.customers.repository.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.abeldevelop.petclinic.services.customers.generated.entity.CustomerEntity;
import com.abeldevelop.petclinic.services.customers.repository.CustomerRepository;
import com.abeldevelop.petclinic.services.customers.repository.springdata.CustomerSpringDataRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

	private final CustomerSpringDataRepository customerSpringDataRepository;

	@Override
	public CustomerEntity executeSave(CustomerEntity customerEntity) {
		return customerSpringDataRepository.save(customerEntity);
	}
	
	@Override
	public void executeDelete(CustomerEntity customerEntity) {
		customerSpringDataRepository.delete(customerEntity);
	}
	
	@Override
	public Optional<CustomerEntity> executeFindByIdentificationDocument(String identificationDocument) {
		return customerSpringDataRepository.findByIdentificationDocument(identificationDocument);
	}
	
	@Override
	public List<CustomerEntity> executeFindAll() {
		return customerSpringDataRepository.findAll();
	}
}
