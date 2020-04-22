package com.abeldevelop.petclinic.services.customers.generated.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abeldevelop.petclinic.services.customers.generated.resource.PetTypeResponseResource;

@RequestMapping("/petTypes")
public interface PetTypeApi {

	@GetMapping
    public List<PetTypeResponseResource> findAll();
	
}
