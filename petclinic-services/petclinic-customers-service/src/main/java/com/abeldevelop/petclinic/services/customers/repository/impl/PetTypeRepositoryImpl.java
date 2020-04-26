package com.abeldevelop.petclinic.services.customers.repository.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public PetTypeEntity executeSave(PetTypeEntity petTypeEntity) {
		return petTypeSpringDataRepository.save(petTypeEntity);
	}
	
	@Override
	public void executeDelete(PetTypeEntity petTypeEntity) {
		petTypeSpringDataRepository.delete(petTypeEntity);
	}
	
	@Override
	public Page<PetTypeEntity> executeFindAll(Pageable page) {
		return petTypeSpringDataRepository.findAll(page);
	}
	
	@Override
	public Optional<PetTypeEntity> executeFindById(Integer petTypeId) {
		return petTypeSpringDataRepository.findById(petTypeId);
	}
	
	@Override
	public Optional<PetTypeEntity> executeFindByName(String name) {
		return petTypeSpringDataRepository.findByName(name);
	}
}
