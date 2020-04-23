package com.abeldevelop.petclinic.services.customers.repository;

import java.util.List;
import java.util.Optional;

import com.abeldevelop.petclinic.services.customers.generated.entity.PetTypeEntity;

public interface PetTypeRepository {

	public List<PetTypeEntity> executeFindAll();
	
	public Optional<PetTypeEntity> executeFindById(Integer petTypeId);
	
}
