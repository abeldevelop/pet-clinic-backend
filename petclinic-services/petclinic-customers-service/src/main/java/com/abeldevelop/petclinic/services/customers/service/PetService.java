package com.abeldevelop.petclinic.services.customers.service;

import com.abeldevelop.petclinic.services.customers.generated.resource.pet.PetPaginationResponseResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.pet.PetRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.pet.PetResponseResource;

public interface PetService {

    public PetResponseResource executeCreate(String identificationDocument, PetRequestResource petRequestResource);
	
    public void executeUpdate(String identificationDocument, Integer petId, PetRequestResource petRequestResource);

    public PetResponseResource executeFindById(String identificationDocument, Integer petId);

    public PetPaginationResponseResource executeFindAll(String identificationDocument);
    
}
