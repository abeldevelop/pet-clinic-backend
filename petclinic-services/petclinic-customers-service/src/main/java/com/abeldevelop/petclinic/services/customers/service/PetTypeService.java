package com.abeldevelop.petclinic.services.customers.service;

import com.abeldevelop.petclinic.services.customers.generated.resource.pettype.PetTypePaginationResponseResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.pettype.PetTypeRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.pettype.PetTypeResponseResource;

public interface PetTypeService {

	public PetTypeResponseResource executeCreate(PetTypeRequestResource petTypeRequestResource);
	
	public void executeUpdate(Integer petTypeId, PetTypeRequestResource petTypeRequestResource);
	
	public void executeDeleteById(Integer petTypeId);
	
	public PetTypeResponseResource executeFindById(Integer petTypeId);
	
	public PetTypePaginationResponseResource executeFindAll(Integer page, Integer size);
	
}
