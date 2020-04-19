package com.abeldevelop.petclinic.services.vets.generated.api;

import org.springframework.web.bind.annotation.GetMapping;

import com.abeldevelop.petclinic.services.vets.generated.resource.VetPaginationResponseResult;

public interface VetApi {

	@GetMapping
    public VetPaginationResponseResult findAll();
	
}
