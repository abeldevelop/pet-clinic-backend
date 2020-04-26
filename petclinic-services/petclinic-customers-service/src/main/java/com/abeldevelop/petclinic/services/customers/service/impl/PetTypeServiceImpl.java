package com.abeldevelop.petclinic.services.customers.service.impl;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abeldevelop.petclinic.library.common.exception.BadRequestException;
import com.abeldevelop.petclinic.library.common.exception.NotFoundException;
import com.abeldevelop.petclinic.library.common.extend.CommonService;
import com.abeldevelop.petclinic.services.customers.generated.entity.PetTypeEntity;
import com.abeldevelop.petclinic.services.customers.generated.resource.pettype.PetTypePaginationResponseResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.pettype.PetTypeRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.pettype.PetTypeResponseResource;
import com.abeldevelop.petclinic.services.customers.mapper.PetTypeMapper;
import com.abeldevelop.petclinic.services.customers.repository.PetTypeRepository;
import com.abeldevelop.petclinic.services.customers.service.PetTypeDomainService;
import com.abeldevelop.petclinic.services.customers.service.PetTypeService;
import com.abeldevelop.petclinic.services.customers.util.constants.PetTypeConstants;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PetTypeServiceImpl extends CommonService implements PetTypeService, PetTypeDomainService {
	
	private final PetTypeRepository petTypeRepository;
	private final PetTypeMapper petTypeMapper;
	
	@Override
	@Transactional
	public PetTypeResponseResource executeCreate(PetTypeRequestResource petTypeRequestResource) {
		validateIfPetTypeNameAlreadyExist(petTypeRequestResource.getName());
		return petTypeMapper.map(petTypeRepository.executeSave(petTypeMapper.map(petTypeRequestResource)));
	}
	
	@Override
	@Transactional
	public void executeUpdate(Integer petTypeId, PetTypeRequestResource petTypeRequestResource) {
		PetTypeEntity petTypeEntity = findPetTypeById(petTypeId);
		if(!petTypeEntity.getName().equals(petTypeRequestResource.getName())) {
			validateIfPetTypeNameAlreadyExist(petTypeRequestResource.getName());
		}
		petTypeRepository.executeSave(petTypeMapper.map(petTypeEntity, petTypeRequestResource));
	}

	@Override
	@Transactional
	public void executeDeleteById(Integer petTypeId) {
		petTypeRepository.executeDelete(findPetTypeById(petTypeId));
	}

	@Override
	@Transactional(readOnly = true)
	public PetTypeResponseResource executeFindById(Integer petTypeId) {
		return petTypeMapper.map(findPetTypeById(petTypeId));
	}
	
	@Override
	@Transactional(readOnly = true)
	public PetTypePaginationResponseResource executeFindAll(Integer page, Integer size) {
		Page<PetTypeEntity> dataBaseResults = petTypeRepository.executeFindAll(paginationMapper.map(page, size, "name"));
		return PetTypePaginationResponseResource.builder()
				.pagination(paginationMapper.map(dataBaseResults))
				.petTypes(dataBaseResults.get().map(petTypeMapper::map).collect(Collectors.toList()))
				.build();
	}

	@Override
	@Transactional(readOnly = true)
	public PetTypeEntity findPetTypeById(Integer petTypeId) {
		return petTypeRepository.executeFindById(petTypeId).orElseThrow(() -> new NotFoundException(PetTypeConstants.PET_TYPE_WITH_ID_NOT_FOUND_ERROR_CODE, PetTypeConstants.PET_TYPE_WITH_ID_NOT_FOUND_ERROR_MESSAGE, petTypeId));
	}
	
	protected void validateIfPetTypeNameAlreadyExist(String name) {
		if(petTypeRepository.executeFindByName(name).isPresent()) {
			throw new BadRequestException(PetTypeConstants.PET_TYPE_WITH_NAME_ALREADY_EXIST_ERROR_CODE, PetTypeConstants.PET_TYPE_WITH_NAME_ALREADY_EXIST_ERROR_MESSAGE, name);
		}
	}

}
