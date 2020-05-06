package com.abeldevelop.petclinic.services.customers.service.impl;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abeldevelop.petclinic.library.common.exception.NotFoundException;
import com.abeldevelop.petclinic.services.customers.generated.entity.CustomerEntity;
import com.abeldevelop.petclinic.services.customers.generated.entity.PetEntity;
import com.abeldevelop.petclinic.services.customers.generated.resource.pet.PetPaginationResponseResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.pet.PetRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.pet.PetResponseResource;
import com.abeldevelop.petclinic.services.customers.mapper.PetMapper;
import com.abeldevelop.petclinic.services.customers.repository.impl.PetRepositoryImpl;
import com.abeldevelop.petclinic.services.customers.service.CustomerDomainService;
import com.abeldevelop.petclinic.services.customers.service.PetDomainService;
import com.abeldevelop.petclinic.services.customers.service.PetService;
import com.abeldevelop.petclinic.services.customers.service.PetTypeDomainService;
import com.abeldevelop.petclinic.services.customers.util.constants.PetConstants;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PetServiceImpl implements PetService, PetDomainService {
	
	private final PetRepositoryImpl petRepository;
	private final PetMapper petMapper;

	private final CustomerDomainService customerDomainService;
	private final PetTypeDomainService petTypeDomainService;
	
	@Override
	@Transactional
	public PetResponseResource executeCreate(Integer customerId, PetRequestResource petRequestResource) {

		PetEntity petEntity = petMapper.map(petRequestResource);
		petEntity.setCustomer(customerDomainService.findById(customerId));
		petEntity.setType(petTypeDomainService.findPetTypeById(petRequestResource.getPetTypeId()));
		
		return petMapper.map(petRepository.executeSave(petEntity));
	}

	@Override
	@Transactional
	public void executeUpdate(Integer customerId, Integer petId, PetRequestResource petRequestResource) {
		CustomerEntity customerEntity = customerDomainService.findById(customerId);
		PetEntity petEntity = findByIdAndCustomer(customerEntity, petId);
		
		petMapper.map(petEntity, petRequestResource);
		petEntity.setCustomer(customerEntity);
		petEntity.setType(petTypeDomainService.findPetTypeById(petRequestResource.getPetTypeId()));
		
		petMapper.map(petRepository.executeSave(petEntity));
	}

	
	@Override
	@Transactional
	public void executeDeleteById(Integer customerId, Integer petId) {
		PetEntity petEntity = findByIdAndCustomer(customerDomainService.findById(customerId), petId);
		petRepository.executeDelete(petEntity);
	}
	
	@Override
	@Transactional(readOnly = true)
	public PetResponseResource executeFindByIdAndCustomer(Integer customerId, Integer petId) {
		return petMapper.map(findByIdAndCustomer(customerDomainService.findById(customerId), petId));
	}
	
	@Override
	@Transactional(readOnly = true)
	public PetEntity findByIdAndCustomer(CustomerEntity customerEntity, Integer petId) {
		return petRepository.executeFindByIdAndCustomer(petId, customerEntity)
				.orElseThrow(() -> new NotFoundException(PetConstants.PET_AND_CUSTOMER_NOT_FOUND_ERROR_CODE, PetConstants.PET_AND_CUSTOMER_NOT_FOUND_ERROR_MESSAGE, petId, customerEntity.getId()));
	}

	@Override
	@Transactional(readOnly = true)
	public PetPaginationResponseResource executeFindAll(Integer customerId, Integer page, Integer size, String name) {
		return PetPaginationResponseResource.builder()
				.pets(petRepository.executeFindByCustomer(customerDomainService.findById(customerId)).stream().map(petMapper::map).collect(Collectors.toList()))
				.build();
	}

}
