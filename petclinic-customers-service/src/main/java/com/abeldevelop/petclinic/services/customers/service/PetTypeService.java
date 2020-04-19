package com.abeldevelop.petclinic.services.customers.service;

import java.util.List;

import com.abeldevelop.petclinic.services.customers.generated.entity.PetTypeEntity;
import com.abeldevelop.petclinic.services.customers.generated.resource.PetTypeResponseResource;

public interface PetTypeService {

	public List<PetTypeResponseResource> findAll();
	
	public PetTypeEntity findPetTypeById(Integer petTypeId);
}
