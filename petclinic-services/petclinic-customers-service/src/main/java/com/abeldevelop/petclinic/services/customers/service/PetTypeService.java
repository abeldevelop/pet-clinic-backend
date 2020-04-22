package com.abeldevelop.petclinic.services.customers.service;

import com.abeldevelop.petclinic.services.customers.generated.entity.PetTypeEntity;
import com.abeldevelop.petclinic.services.customers.generated.resource.PetTypePaginationResponseResource;

public interface PetTypeService {

	public PetTypePaginationResponseResource findAll();
	
	public PetTypeEntity findPetTypeById(Integer petTypeId);
}
