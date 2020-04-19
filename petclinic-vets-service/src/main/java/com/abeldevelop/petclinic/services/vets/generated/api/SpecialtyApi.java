package com.abeldevelop.petclinic.services.vets.generated.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abeldevelop.petclinic.services.vets.generated.resource.SpecialtyPaginationResponseResult;

@RequestMapping("/{vetId}/specialties")
public interface SpecialtyApi {

	@GetMapping
    public SpecialtyPaginationResponseResult findAll(@PathVariable("vetId") Integer vetId);
	
}
