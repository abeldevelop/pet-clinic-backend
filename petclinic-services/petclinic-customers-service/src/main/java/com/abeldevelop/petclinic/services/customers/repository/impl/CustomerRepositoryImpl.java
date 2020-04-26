package com.abeldevelop.petclinic.services.customers.repository.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
	public Page<CustomerEntity> executeFindAll(Pageable page) {
		return customerSpringDataRepository.findAll(page);
	}
	
	@Override
	public Page<CustomerEntity> executeFindAll(Specification<CustomerEntity> spec, Pageable pageable) {
		return customerSpringDataRepository.findAll(spec, pageable);
	}
}
