package com.abeldevelop.petclinic.services.customers.repository;

import java.util.List;
import java.util.Optional;

import com.abeldevelop.petclinic.services.customers.generated.entity.CustomerEntity;
import com.abeldevelop.petclinic.services.customers.generated.entity.PetEntity;

public interface PetRepository {

	public PetEntity executeSave(PetEntity petEntity);
	
	public Optional<PetEntity> executeFindById(Integer petId);
	
	public Optional<PetEntity> executeFindByIdAndCustomer(Integer petId, CustomerEntity customerEntity);
	
	public List<PetEntity> executeFindByCustomer(CustomerEntity customerEntity);
	
}