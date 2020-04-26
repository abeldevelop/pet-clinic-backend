package com.abeldevelop.petclinic.services.customers.service.impl;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.abeldevelop.petclinic.library.common.exception.BadRequestException;
import com.abeldevelop.petclinic.library.common.exception.NotFoundException;
import com.abeldevelop.petclinic.library.common.extend.CommonService;
import com.abeldevelop.petclinic.services.customers.generated.entity.CustomerEntity;
import com.abeldevelop.petclinic.services.customers.generated.resource.customer.CustomerCreateRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.customer.CustomerPaginationResponseResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.customer.CustomerResponseResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.customer.CustomerUpdateRequestResource;
import com.abeldevelop.petclinic.services.customers.mapper.CustomerMapper;
import com.abeldevelop.petclinic.services.customers.repository.CustomerRepository;
import com.abeldevelop.petclinic.services.customers.repository.specification.CustomerSpecification;
import com.abeldevelop.petclinic.services.customers.service.CustomerDomainService;
import com.abeldevelop.petclinic.services.customers.service.CustomerService;
import com.abeldevelop.petclinic.services.customers.util.constants.CustomerConstants;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl extends CommonService implements CustomerService, CustomerDomainService {

	private final CustomerRepository customerRepository;
	private final CustomerMapper customerMapper;
	private final CustomerSpecification customerSpecification;
	
	@Override
	@Transactional
	public CustomerResponseResource executeCreate(CustomerCreateRequestResource customerCreateRequestResource) {
		validateIfIdentificationDocumentAlreadyExist(customerCreateRequestResource.getIdentificationDocument());
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
		return customerRepository.executeFindByIdentificationDocument(identificationDocument).orElseThrow(() -> new NotFoundException(CustomerConstants.CUSTOMER_WITH_IDENTIFICATION_DOCUMENT_NOT_FOUND_ERROR_CODE, CustomerConstants.CUSTOMER_WITH_IDENTIFICATION_DOCUMENT_NOT_FOUND_ERROR_MESSAGE, identificationDocument));
	}
	
	@Override
	@Transactional(readOnly = true)
	public CustomerPaginationResponseResource executeFindAll(Integer page, Integer size, String firstName) {
		Page<CustomerEntity> dataBaseResults = findAll(page, size, firstName);
		return CustomerPaginationResponseResource.builder()
				.pagination(paginationMapper.map(dataBaseResults))
				.customers(dataBaseResults.get().map(customerMapper::map).collect(Collectors.toList()))
				.build();
	}

	protected Page<CustomerEntity> findAll(Integer page, Integer size, String firstName) {
		Pageable pageable = paginationMapper.map(page, size, "firstName");
		if(StringUtils.isEmpty(firstName)) {
			return customerRepository.executeFindAll(pageable);
		} else {
			return customerRepository.executeFindAll(customerSpecification.firstNameLike(firstName), pageable);
		}
	}
	
	protected void validateIfIdentificationDocumentAlreadyExist(String identificationDocument) {
		if(customerRepository.executeFindByIdentificationDocument(identificationDocument).isPresent()) {
			throw new BadRequestException(CustomerConstants.CUSTOMER_WITH_IDENTIFICATION_DOCUMENT_ALREADY_EXIST_ERROR_CODE, CustomerConstants.CUSTOMER_WITH_IDENTIFICATION_DOCUMENT_ALREADY_EXIST_ERROR_MESSAGE, identificationDocument);
		}
	}
}
