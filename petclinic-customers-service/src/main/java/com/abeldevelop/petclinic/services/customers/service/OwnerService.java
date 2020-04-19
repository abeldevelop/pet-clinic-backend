package com.abeldevelop.petclinic.services.customers.service;

import java.util.List;

import com.abeldevelop.petclinic.services.customers.generated.entity.OwnerEntity;
import com.abeldevelop.petclinic.services.customers.generated.resource.OwnerRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.OwnerResponseResource;

public interface OwnerService {

    public OwnerResponseResource create(OwnerRequestResource ownerResource);
	
    public void update(Integer ownerId, OwnerRequestResource ownerResource);
	
    public void delete(Integer ownerId);
	
    public OwnerResponseResource findById(Integer ownerId);
    
    public OwnerEntity findOwnerById(Integer ownerId);
	
    public List<OwnerResponseResource> findAll();
	
}
