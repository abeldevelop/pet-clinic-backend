package com.abeldevelop.petclinic.services.customers.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abeldevelop.petclinic.services.customers.exception.NotFoundException;
import com.abeldevelop.petclinic.services.customers.generated.entity.PetEntity;
import com.abeldevelop.petclinic.services.customers.generated.resource.PetRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.PetResponseResource;
import com.abeldevelop.petclinic.services.customers.mapper.PetMapper;
import com.abeldevelop.petclinic.services.customers.repository.PetRepository;
import com.abeldevelop.petclinic.services.customers.service.OwnerService;
import com.abeldevelop.petclinic.services.customers.service.PetService;
import com.abeldevelop.petclinic.services.customers.service.PetTypeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PetServiceImpl implements PetService {
	
	private final PetRepository petRepository;
	private final PetMapper petMapper;

	private final OwnerService ownerService;
	private final PetTypeService petTypeService;
	
	@Override
	@Transactional
	public PetResponseResource create(Integer ownerId, PetRequestResource petRequestResource) {

		PetEntity petEntity = petMapper.map(petRequestResource);
		petEntity.setOwner(ownerService.findOwnerById(ownerId));
		petEntity.setType(petTypeService.findPetTypeById(petRequestResource.getTypeId()));
		
		return petMapper.map(petRepository.save(petEntity));
	}

	@Override
	@Transactional
	public void update(Integer ownerId, Integer petId, PetRequestResource petRequestResource) {
		PetEntity petEntity = findPetById(petId);
		
		petMapper.map(petEntity, petRequestResource);
		petEntity.setOwner(ownerService.findOwnerById(ownerId));
		petEntity.setType(petTypeService.findPetTypeById(petRequestResource.getTypeId()));
		
		petMapper.map(petRepository.save(petEntity));
	}

	@Override
	@Transactional(readOnly = true)
	public PetResponseResource findById(Integer ownerId, Integer petId) {
		return petMapper.map(petRepository.findByIdAndOwner(petId, ownerService.findOwnerById(ownerId)).orElseThrow(() -> new NotFoundException("No exist Pet with ID: '" + petId + "' for Owner with ID: '" + ownerId + "'")));
	}
	
	@Override
	@Transactional(readOnly = true)
	public PetEntity findPetById(Integer petId) {
		return petRepository.findById(petId).orElseThrow(() -> new NotFoundException("No exist Pet with ID: '" + petId + "'"));
	}

	@Override
	@Transactional(readOnly = true)
	public List<PetResponseResource> findAll(Integer ownerId) {
		return petRepository.findByOwner(ownerService.findOwnerById(ownerId)).stream().map(petMapper::map).collect(Collectors.toList());
	}

}
