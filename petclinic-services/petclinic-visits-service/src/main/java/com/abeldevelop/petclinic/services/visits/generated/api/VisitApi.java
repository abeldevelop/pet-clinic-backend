package com.abeldevelop.petclinic.services.visits.generated.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.abeldevelop.petclinic.services.visits.generated.resource.VisitPaginationResponseResource;
import com.abeldevelop.petclinic.services.visits.generated.resource.VisitRequestResource;
import com.abeldevelop.petclinic.services.visits.generated.resource.VisitResponseResource;

@RequestMapping("/owners/{ownerId}/pets/{petId}/visits")
public interface VisitApi {

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public VisitResponseResource create(@PathVariable("ownerId") Integer ownerId, @PathVariable("petId") Integer petId, @RequestBody VisitRequestResource visitRequestResource);
	
	@GetMapping
	public VisitPaginationResponseResource findAll(@PathVariable("ownerId") Integer ownerId, @PathVariable("petId") Integer petId);
	
}
