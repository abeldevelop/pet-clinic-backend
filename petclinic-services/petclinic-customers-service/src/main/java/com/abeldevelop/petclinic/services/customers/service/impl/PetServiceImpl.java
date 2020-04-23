package com.abeldevelop.petclinic.services.customers.service.impl;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abeldevelop.petclinic.library.common.exception.NotFoundException;
import com.abeldevelop.petclinic.services.customers.generated.entity.PetEntity;
import com.abeldevelop.petclinic.services.customers.generated.resource.PetPaginationResponseResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.PetRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.PetResponseResource;
import com.abeldevelop.petclinic.services.customers.mapper.PetMapper;
import com.abeldevelop.petclinic.services.customers.repository.impl.PetRepositoryImpl;
import com.abeldevelop.petclinic.services.customers.service.CustomerDomainService;
import com.abeldevelop.petclinic.services.customers.service.PetDomainService;
import com.abeldevelop.petclinic.services.customers.service.PetService;
import com.abeldevelop.petclinic.services.customers.service.PetTypeDomainService;

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
	public PetResponseResource executeCreate(String identificationDocument, PetRequestResource petRequestResource) {

		PetEntity petEntity = petMapper.map(petRequestResource);
		petEntity.setCustomer(customerDomainService.findByIdentificationDocument(identificationDocument));
		petEntity.setType(petTypeDomainService.findPetTypeById(petRequestResource.getTypeId()));
		
		return petMapper.map(petRepository.executeSave(petEntity));
	}

	@Override
	@Transactional
	public void executeUpdate(String identificationDocument, Integer petId, PetRequestResource petRequestResource) {
		PetEntity petEntity = findPetById(petId);
		
		petMapper.map(petEntity, petRequestResource);
		petEntity.setCustomer(customerDomainService.findByIdentificationDocument(identificationDocument));
		petEntity.setType(petTypeDomainService.findPetTypeById(petRequestResource.getTypeId()));
		
		petMapper.map(petRepository.executeSave(petEntity));
	}

	@Override
	@Transactional(readOnly = true)
	public PetResponseResource executeFindById(String identificationDocument, Integer petId) {
		return petMapper.map(petRepository.executeFindByIdAndCustomer(petId, customerDomainService.findByIdentificationDocument(identificationDocument)).orElseThrow(() -> new NotFoundException("No exist Pet with ID: '" + petId + "' for Customer with Identification Document: '" + identificationDocument + "'")));
	}
	
	@Override
	@Transactional(readOnly = true)
	public PetEntity findPetById(Integer petId) {
		return petRepository.executeFindById(petId).orElseThrow(() -> new NotFoundException("No exist Pet with ID: '" + petId + "'"));
	}

	@Override
	@Transactional(readOnly = true)
	public PetPaginationResponseResource executeFindAll(String identificationDocument) {
		return PetPaginationResponseResource.builder()
				.pets(petRepository.executeFindByCustomer(customerDomainService.findByIdentificationDocument(identificationDocument)).stream().map(petMapper::map).collect(Collectors.toList()))
				.build();
	}

}
