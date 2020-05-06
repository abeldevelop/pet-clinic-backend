package com.abeldevelop.petclinic.services.customers.generated.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.abeldevelop.petclinic.services.customers.generated.resource.pet.PetPaginationResponseResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.pet.PetRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.pet.PetResponseResource;

@RequestMapping("/customers/{customerId}/pets")
public interface PetApi {

	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PetResponseResource executeCreatePet(@PathVariable("customerId") Integer customerId, @RequestBody PetRequestResource petRequestResource);
	
	@PutMapping("/{petId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void executeUpdatePet(@PathVariable("customerId") Integer customerId, @PathVariable("petId") Integer petId, @RequestBody PetRequestResource petRequestResource);

	@DeleteMapping("/{petId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void executeDeletePetById(@PathVariable("customerId") Integer customerId, @PathVariable("petId") Integer petId);
	
    @GetMapping("/{petId}")
    @ResponseStatus(HttpStatus.OK)
    public PetResponseResource executeFindPetById(@PathVariable("customerId") Integer customerId, @PathVariable("petId") Integer petId);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PetPaginationResponseResource executeFindAllPets(
    		@PathVariable("customerId") Integer customerId,
    		@RequestParam(name = "page", required = false) Integer page, 
    		@RequestParam(name = "size", required = false) Integer size,
    		@RequestParam(name = "name", required = false) String name);
    
}
