package com.abeldevelop.petclinic.services.customers.service;

import com.abeldevelop.petclinic.services.customers.generated.resource.PetPaginationResponseResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.PetRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.PetResponseResource;

public interface PetService {

    public PetResponseResource executeCreate(String identificationDocument, PetRequestResource petRequestResource);
	
    public void executeUpdate(String identificationDocument, Integer petId, PetRequestResource petRequestResource);

    public PetResponseResource executeFindById(String identificationDocument, Integer petId);

    public PetPaginationResponseResource executeFindAll(String identificationDocument);
    
}
