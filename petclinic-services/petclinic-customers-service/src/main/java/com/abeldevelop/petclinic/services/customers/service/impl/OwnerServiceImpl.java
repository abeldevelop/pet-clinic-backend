package com.abeldevelop.petclinic.services.customers.service.impl;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abeldevelop.petclinic.library.common.exception.NotFoundException;
import com.abeldevelop.petclinic.services.customers.generated.entity.OwnerEntity;
import com.abeldevelop.petclinic.services.customers.generated.resource.OwnerPaginationResponseResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.OwnerRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.OwnerResponseResource;
import com.abeldevelop.petclinic.services.customers.mapper.OwnerMapper;
import com.abeldevelop.petclinic.services.customers.repository.OwnerRepository;
import com.abeldevelop.petclinic.services.customers.service.OwnerService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OwnerServiceImpl implements OwnerService {

	private final OwnerRepository ownerRepository;
	private final OwnerMapper ownerMapper;
	
	@Override
	@Transactional
	public OwnerResponseResource create(OwnerRequestResource ownerRequestResource) {
		return ownerMapper.map(ownerRepository.save(ownerMapper.map(ownerRequestResource)));
	}

	@Override
	@Transactional
	public void update(Integer ownerId, OwnerRequestResource ownerRequestResource) {
		OwnerEntity ownerEntity = findOwnerById(ownerId);
		ownerRepository.save(ownerMapper.map(ownerEntity, ownerRequestResource));
	}

	@Override
	@Transactional
	public void delete(Integer ownerId) {
		OwnerEntity ownerEntity = findOwnerById(ownerId);
		ownerRepository.delete(ownerEntity);
		
	}

	@Override
	@Transactional(readOnly = true)
	public OwnerResponseResource findById(Integer ownerId) {
		return ownerMapper.map(findOwnerById(ownerId));
	}

	@Override
	@Transactional(readOnly = true)
	public OwnerEntity findOwnerById(Integer ownerId) {
		return ownerRepository.findById(ownerId).orElseThrow(() -> new NotFoundException("No exist Owner with ID: '" + ownerId + "'"));
	}
	
	@Override
	@Transactional(readOnly = true)
	public OwnerPaginationResponseResource findAll() {
		return OwnerPaginationResponseResource.builder()
				.owners(ownerRepository.findAll().stream().map(ownerMapper::map).collect(Collectors.toList()))
				.build();
	}

	
}
