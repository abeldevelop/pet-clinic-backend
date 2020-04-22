package com.abeldevelop.petclinic.services.customers.generated.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abeldevelop.petclinic.services.customers.generated.resource.PetTypePaginationResponseResource;

@RequestMapping("/petTypes")
public interface PetTypeApi {

	@GetMapping
    public PetTypePaginationResponseResource findAll();
	
}
