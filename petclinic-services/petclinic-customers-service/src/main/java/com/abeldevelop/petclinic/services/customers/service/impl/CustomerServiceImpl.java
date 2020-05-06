package com.abeldevelop.petclinic.services.customers.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
	public void executeUpdate(Integer id, CustomerUpdateRequestResource customerUpdateRequestResource) {
		CustomerEntity customerEntity = findById(id);
		customerRepository.executeSave(customerMapper.map(customerEntity, customerUpdateRequestResource));
	}

	@Override
	@Transactional
	public void executeDeleteById(Integer id) {
		customerRepository.executeDelete(findById(id));
		
	}

	@Override
	@Transactional(readOnly = true)
	public CustomerResponseResource executeFindById(Integer id) {
		return customerMapper.map(findById(id));
	}

	@Override
	@Transactional(readOnly = true)
	public CustomerEntity findById(Integer id) {
		return customerRepository.executeFindById(id).orElseThrow(() -> new NotFoundException(CustomerConstants.CUSTOMER_WITH_IDENTIFICATION_DOCUMENT_NOT_FOUND_ERROR_CODE, CustomerConstants.CUSTOMER_WITH_IDENTIFICATION_DOCUMENT_NOT_FOUND_ERROR_MESSAGE, id));
	}
	
	@Override
	@Transactional(readOnly = true)
	public CustomerPaginationResponseResource executeFindAll(Integer page, Integer size, String identificationDocument, String firstName) {
		Page<CustomerEntity> dataBaseResults = findAll(page, size, identificationDocument, firstName);
		return CustomerPaginationResponseResource.builder()
				.pagination(paginationMapper.map(dataBaseResults))
				.customers(dataBaseResults.get().map(customerMapper::map).collect(Collectors.toList()))
				.build();
	}

	protected Page<CustomerEntity> findAll(Integer page, Integer size, String identificationDocument, String firstName) {
		Pageable pageable = paginationMapper.map(page, size, "firstName");
		List<Specification<CustomerEntity>> customerSpecificationList = getCustomerSpecification(identificationDocument, firstName);
		if(customerSpecificationList.isEmpty()) {
			return customerRepository.executeFindAll(pageable);
		} else {
			Specification<CustomerEntity> spec = null;
			for(int i = 0; i < customerSpecificationList.size(); i++) {
				if(i == 0) {
					spec = customerSpecificationList.get(i);
				} else {
					spec = spec.and(customerSpecificationList.get(i));
				}
			}
			return customerRepository.executeFindAll(spec, pageable);
		}
	}
	
	private List<Specification<CustomerEntity>> getCustomerSpecification(String identificationDocument, String firstName) {
		List<Specification<CustomerEntity>> customerSpecificationList = new ArrayList<>();
		if(!StringUtils.isEmpty(firstName)) {
			customerSpecificationList.add(customerSpecification.firstNameLike(firstName));
		}
		if(!StringUtils.isEmpty(identificationDocument)) {
			customerSpecificationList.add(customerSpecification.identificationDocumentLike(identificationDocument));
		}
		return customerSpecificationList;
	}
	
	protected void validateIfIdentificationDocumentAlreadyExist(String identificationDocument) {
		if(customerRepository.executeFindByIdentificationDocument(identificationDocument).isPresent()) {
			throw new BadRequestException(CustomerConstants.CUSTOMER_WITH_IDENTIFICATION_DOCUMENT_ALREADY_EXIST_ERROR_CODE, CustomerConstants.CUSTOMER_WITH_IDENTIFICATION_DOCUMENT_ALREADY_EXIST_ERROR_MESSAGE, identificationDocument);
		}
	}
}
