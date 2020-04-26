package com.abeldevelop.petclinic.services.customers.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.abeldevelop.petclinic.services.customers.generated.entity.PetTypeEntity;

public interface PetTypeRepository {

	public PetTypeEntity executeSave(PetTypeEntity petTypeEntity);
	
	public void executeDelete(PetTypeEntity petTypeEntity);
	
	public Page<PetTypeEntity> executeFindAll(Pageable page);
	
	public Optional<PetTypeEntity> executeFindById(Integer petTypeId);
	
	public Optional<PetTypeEntity> executeFindByName(String name);
	
}
