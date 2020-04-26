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

import com.abeldevelop.petclinic.services.customers.generated.resource.pettype.PetTypePaginationResponseResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.pettype.PetTypeRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.pettype.PetTypeResponseResource;

@RequestMapping("/pet-types")
public interface PetTypeApi {

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PetTypeResponseResource executeCreatePetType(@RequestBody PetTypeRequestResource petTypeRequestResource);
	
	@PutMapping("/{petTypeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void executeUpdatePetType(@PathVariable("petTypeId") Integer petTypeId, @RequestBody PetTypeRequestResource petTypeRequestResource);
	
	@DeleteMapping("/{petTypeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void executeDeletePetTypeById(@PathVariable("petTypeId") Integer petTypeId);
	
	@GetMapping("/{petTypeId}")
	@ResponseStatus(HttpStatus.OK)
	public PetTypeResponseResource executeFindPetTypeById(@PathVariable("petTypeId") Integer petTypeId);
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
    public PetTypePaginationResponseResource executeFindAllPetTypes(@RequestParam(name = "page", required = false) Integer page, @RequestParam(name = "size", required = false) Integer size);
	
}
