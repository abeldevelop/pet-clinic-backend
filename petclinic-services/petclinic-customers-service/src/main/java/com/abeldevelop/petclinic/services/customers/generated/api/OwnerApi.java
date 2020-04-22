package com.abeldevelop.petclinic.services.customers.generated.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.abeldevelop.petclinic.services.customers.generated.resource.OwnerPaginationResponseResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.OwnerRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.OwnerResponseResource;

@RequestMapping("/owners")
public interface OwnerApi {

	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OwnerResponseResource create(@RequestBody OwnerRequestResource ownerResource);
	
	@PutMapping("/{ownerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("ownerId") Integer ownerId, @RequestBody OwnerRequestResource ownerResource);
	
	@DeleteMapping("/{ownerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("ownerId") Integer ownerId);
	
	@GetMapping(value = "/{ownerId}")
    public OwnerResponseResource findById(@PathVariable("ownerId") Integer ownerId);
	
	@GetMapping
    public OwnerPaginationResponseResource findAll();
	
}
