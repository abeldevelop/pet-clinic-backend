package com.abeldevelop.petclinic.services.customers.repository.springdata;

import java.util.List;
import java.util.Optional;

import com.abeldevelop.petclinic.library.common.extend.CommonSpringDataRepositoryRepository;
import com.abeldevelop.petclinic.services.customers.generated.entity.CustomerEntity;
import com.abeldevelop.petclinic.services.customers.generated.entity.PetEntity;

public interface PetSpringDataRepository extends CommonSpringDataRepositoryRepository<PetEntity, Integer> {

	public Optional<PetEntity> findByIdAndCustomer(Integer id, CustomerEntity customer);
	
	public List<PetEntity> findByCustomer(CustomerEntity customer);
	
}