package com.abeldevelop.petclinic.services.customers.service;

import com.abeldevelop.petclinic.services.customers.generated.entity.PetEntity;

public interface PetDomainService {

    public PetEntity findPetById(Integer petId);
    
}
