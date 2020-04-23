package com.abeldevelop.petclinic.services.customers.repository.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.abeldevelop.petclinic.services.customers.generated.entity.PetTypeEntity;
import com.abeldevelop.petclinic.services.customers.repository.PetTypeRepository;
import com.abeldevelop.petclinic.services.customers.repository.springdata.PetTypeSpringDataRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class PetTypeRepositoryImpl implements PetTypeRepository {

	private final PetTypeSpringDataRepository petTypeSpringDataRepository;

	@Override
	public List<PetTypeEntity> executeFindAll() {
		return petTypeSpringDataRepository.findAll();
	}
	
	@Override
	public Optional<PetTypeEntity> executeFindById(Integer petTypeId) {
		return petTypeSpringDataRepository.findById(petTypeId);
	}
}
