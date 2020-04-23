package com.abeldevelop.petclinic.services.customers.service.impl;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abeldevelop.petclinic.library.common.exception.NotFoundException;
import com.abeldevelop.petclinic.services.customers.generated.entity.CustomerEntity;
import com.abeldevelop.petclinic.services.customers.generated.resource.CustomerCreateRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.CustomerPaginationResponseResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.CustomerResponseResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.CustomerUpdateRequestResource;
import com.abeldevelop.petclinic.services.customers.mapper.CustomerMapper;
import com.abeldevelop.petclinic.services.customers.repository.CustomerRepository;
import com.abeldevelop.petclinic.services.customers.service.CustomerDomainService;
import com.abeldevelop.petclinic.services.customers.service.CustomerService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService, CustomerDomainService {

	private final CustomerRepository customerRepository;
	private final CustomerMapper customerMapper;
	
	@Override
	@Transactional
	public CustomerResponseResource executeCreate(CustomerCreateRequestResource customerCreateRequestResource) {
		return customerMapper.map(customerRepository.executeSave(customerMapper.map(customerCreateRequestResource)));
	}

	@Override
	@Transactional
	public void executeUpdate(String identificationDocument, CustomerUpdateRequestResource customerUpdateRequestResource) {
		CustomerEntity customerEntity = findByIdentificationDocument(identificationDocument);
		customerRepository.executeSave(customerMapper.map(customerEntity, customerUpdateRequestResource));
	}

	@Override
	@Transactional
	public void executeDelete(String identificationDocument) {
		customerRepository.executeDelete(findByIdentificationDocument(identificationDocument));
		
	}

	@Override
	@Transactional(readOnly = true)
	public CustomerResponseResource executeFindByIdentificationDocument(String identificationDocument) {
		return customerMapper.map(findByIdentificationDocument(identificationDocument));
	}

	@Override
	@Transactional(readOnly = true)
	public CustomerEntity findByIdentificationDocument(String identificationDocument) {
		return customerRepository.executeFindByIdentificationDocument(identificationDocument).orElseThrow(() -> new NotFoundException("No exist Customer with Identification Document: '" + identificationDocument + "'"));
	}
	
	@Override
	@Transactional(readOnly = true)
	public CustomerPaginationResponseResource executeFindAll() {
		return CustomerPaginationResponseResource.builder()
				.customers(customerRepository.executeFindAll().stream().map(customerMapper::map).collect(Collectors.toList()))
				.build();
	}

	
}
