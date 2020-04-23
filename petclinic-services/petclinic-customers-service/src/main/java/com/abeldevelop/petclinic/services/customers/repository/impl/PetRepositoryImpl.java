package com.abeldevelop.petclinic.services.customers.repository.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.abeldevelop.petclinic.services.customers.generated.entity.CustomerEntity;
import com.abeldevelop.petclinic.services.customers.generated.entity.PetEntity;
import com.abeldevelop.petclinic.services.customers.repository.PetRepository;
import com.abeldevelop.petclinic.services.customers.repository.springdata.PetSpringDataRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class PetRepositoryImpl implements PetRepository {

	private final PetSpringDataRepository petSpringDataRepository;
	
	@Override
	public PetEntity executeSave(PetEntity petEntity) {
		return petSpringDataRepository.save(petEntity);
	}
	
	@Override
	public Optional<PetEntity> executeFindById(Integer petId) {
		return petSpringDataRepository.findById(petId);
	}
	
	@Override
	public Optional<PetEntity> executeFindByIdAndCustomer(Integer petId, CustomerEntity customerEntity) {
		return petSpringDataRepository.findByIdAndCustomer(petId, customerEntity);
	}
	
	@Override
	public List<PetEntity> executeFindByCustomer(CustomerEntity customerEntity){
		return petSpringDataRepository.findByCustomer(customerEntity);
	}
	
}