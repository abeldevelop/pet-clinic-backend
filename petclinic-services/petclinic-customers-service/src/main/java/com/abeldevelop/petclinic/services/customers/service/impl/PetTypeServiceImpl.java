package com.abeldevelop.petclinic.services.customers.service.impl;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abeldevelop.petclinic.library.common.exception.NotFoundException;
import com.abeldevelop.petclinic.services.customers.generated.entity.PetTypeEntity;
import com.abeldevelop.petclinic.services.customers.generated.resource.PetTypePaginationResponseResource;
import com.abeldevelop.petclinic.services.customers.mapper.PetTypeMapper;
import com.abeldevelop.petclinic.services.customers.repository.PetTypeRepository;
import com.abeldevelop.petclinic.services.customers.service.PetTypeDomainService;
import com.abeldevelop.petclinic.services.customers.service.PetTypeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PetTypeServiceImpl implements PetTypeService, PetTypeDomainService {
	
	private final PetTypeRepository petTypeRepository;
	private final PetTypeMapper petTypeMapper;
	
	@Override
	@Transactional(readOnly = true)
	public PetTypePaginationResponseResource executeFindAll() {
		return PetTypePaginationResponseResource.builder()
				.petTypes(petTypeRepository.executeFindAll().stream().map(petTypeMapper::map).collect(Collectors.toList()))
				.build();
	}

	@Override
	@Transactional(readOnly = true)
	public PetTypeEntity findPetTypeById(Integer petTypeId) {
		return petTypeRepository.executeFindById(petTypeId).orElseThrow(() -> new NotFoundException("No exist Pet Type with ID: '" + petTypeId + "'"));
	}

}
