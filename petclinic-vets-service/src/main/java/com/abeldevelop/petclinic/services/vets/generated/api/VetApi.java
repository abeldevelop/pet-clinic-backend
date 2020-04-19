package com.abeldevelop.petclinic.services.vets.generated.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abeldevelop.petclinic.services.vets.generated.resource.VetPaginationResponseResult;

@RequestMapping("/vets")
public interface VetApi {

	@GetMapping
    public VetPaginationResponseResult findAll();
	
}
