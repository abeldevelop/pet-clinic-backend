package com.abeldevelop.petclinic.services.customers.generated.api;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.abeldevelop.petclinic.services.customers.generated.resource.PetPaginationResponseResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.PetRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.PetResponseResource;

@RequestMapping("/owners/{ownerId}/pets")
public interface PetApi {

	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PetResponseResource create(@PathVariable("ownerId") Integer ownerId, @Valid @RequestBody PetRequestResource petRequestResource);
	
	@PutMapping("/{petId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("ownerId") Integer ownerId, @PathVariable("petId") Integer petId, @Valid @RequestBody PetRequestResource petRequestResource);

    @GetMapping("/{petId}")
    public PetResponseResource findById(@PathVariable("ownerId") Integer ownerId, @PathVariable("petId") Integer petId);

    @GetMapping
    public PetPaginationResponseResource findAll(@PathVariable("ownerId") Integer ownerId);
    
}
