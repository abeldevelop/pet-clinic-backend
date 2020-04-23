package com.abeldevelop.petclinic.services.customers.service;

import com.abeldevelop.petclinic.services.customers.generated.entity.PetTypeEntity;

public interface PetTypeDomainService {

	public PetTypeEntity findPetTypeById(Integer petTypeId);
	
}
