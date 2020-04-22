package com.abeldevelop.petclinic.services.vets.service;

import com.abeldevelop.petclinic.services.vets.generated.resource.SpecialtyPaginationResponseResult;

public interface SpecialtyService {

	public SpecialtyPaginationResponseResult findAll(Integer vetId);
	
}
