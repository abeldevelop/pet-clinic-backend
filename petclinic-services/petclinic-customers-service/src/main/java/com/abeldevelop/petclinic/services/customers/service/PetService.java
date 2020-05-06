package com.abeldevelop.petclinic.services.customers.service;

import com.abeldevelop.petclinic.services.customers.generated.resource.pet.PetPaginationResponseResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.pet.PetRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.pet.PetResponseResource;

public interface PetService {

    public PetResponseResource executeCreate(Integer customerId, PetRequestResource petRequestResource);
	
    public void executeUpdate(Integer customerId, Integer petId, PetRequestResource petRequestResource);

    public void executeDeleteById(Integer customerId, Integer petId);
    
    public PetResponseResource executeFindByIdAndCustomer(Integer customerId, Integer petId);

    public PetPaginationResponseResource executeFindAll(Integer customerId, Integer page, Integer size, String name);
    
}
