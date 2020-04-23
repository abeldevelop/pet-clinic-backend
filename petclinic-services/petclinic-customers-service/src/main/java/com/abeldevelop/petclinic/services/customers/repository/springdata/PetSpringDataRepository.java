package com.abeldevelop.petclinic.services.customers.repository.springdata;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abeldevelop.petclinic.services.customers.generated.entity.CustomerEntity;
import com.abeldevelop.petclinic.services.customers.generated.entity.PetEntity;

public interface PetSpringDataRepository extends JpaRepository<PetEntity, Integer> {

	public Optional<PetEntity> findByIdAndCustomer(Integer id, CustomerEntity customer);
	
	public List<PetEntity> findByCustomer(CustomerEntity customer);
	
}