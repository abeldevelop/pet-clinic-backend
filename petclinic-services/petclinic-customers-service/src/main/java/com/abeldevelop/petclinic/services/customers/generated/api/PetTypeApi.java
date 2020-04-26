package com.abeldevelop.petclinic.services.customers.generated.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.abeldevelop.petclinic.services.customers.generated.resource.pettype.PetTypePaginationResponseResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.pettype.PetTypeRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.pettype.PetTypeResponseResource;

@RequestMapping("/pet-types")
public interface PetTypeApi {

	@PostMapping
	public PetTypeResponseResource executeCreatePetType(@RequestBody PetTypeRequestResource petTypeRequestResource);
	
	@PutMapping("/{petTypeId}")
	public void executeUpdatePetType(@PathVariable("petTypeId") Integer petTypeId, @RequestBody PetTypeRequestResource petTypeRequestResource);
	
	@DeleteMapping("/{petTypeId}")
	public void executeDeletePetTypeById(@PathVariable("petTypeId") Integer petTypeId);
	
	@GetMapping("/{petTypeId}")
	public PetTypeResponseResource executeFindPetTypeById(@PathVariable("petTypeId") Integer petTypeId);
	
	@GetMapping
    public PetTypePaginationResponseResource executeFindAllPetTypes(@RequestParam(name = "page", required = false) Integer page, @RequestParam(name = "size", required = false) Integer size);
	
}
