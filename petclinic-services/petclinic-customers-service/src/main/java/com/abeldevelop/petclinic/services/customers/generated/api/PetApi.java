package com.abeldevelop.petclinic.services.customers.generated.api;

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

@RequestMapping("/customers/{identificationDocument}/pets")
public interface PetApi {

	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PetResponseResource executeCreatePet(@PathVariable("identificationDocument") String identificationDocument, @RequestBody PetRequestResource petRequestResource);
	
	@PutMapping("/{petId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void executeUpdatePet(@PathVariable("identificationDocument") String identificationDocument, @PathVariable("petId") Integer petId, @RequestBody PetRequestResource petRequestResource);

    @GetMapping("/{petId}")
    public PetResponseResource executeFindPetById(@PathVariable("identificationDocument") String identificationDocument, @PathVariable("petId") Integer petId);

    @GetMapping
    public PetPaginationResponseResource executeFindAllPets(@PathVariable("identificationDocument") String identificationDocument);
    
}
