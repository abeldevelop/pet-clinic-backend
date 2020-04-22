package com.abeldevelop.petclinic.services.customers.service;

import com.abeldevelop.petclinic.services.customers.generated.entity.PetEntity;
import com.abeldevelop.petclinic.services.customers.generated.resource.PetPaginationResponseResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.PetRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.PetResponseResource;

public interface PetService {

    public PetResponseResource create(Integer ownerId, PetRequestResource petRequestResource);
	
    public void update(Integer ownerId, Integer petId, PetRequestResource petRequestResource);

    public PetResponseResource findById(Integer ownerId, Integer petId);

    public PetEntity findPetById(Integer petId);
    
    public PetPaginationResponseResource findAll(Integer ownerId);
}
