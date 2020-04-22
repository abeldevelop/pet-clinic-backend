package com.abeldevelop.petclinic.services.customers.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abeldevelop.petclinic.services.customers.generated.entity.OwnerEntity;
import com.abeldevelop.petclinic.services.customers.generated.entity.PetEntity;

public interface PetRepository extends JpaRepository<PetEntity, Integer> {

	public Optional<PetEntity> findByIdAndOwner(Integer id, OwnerEntity owner);
	
	public List<PetEntity> findByOwner(OwnerEntity owner);
	
}