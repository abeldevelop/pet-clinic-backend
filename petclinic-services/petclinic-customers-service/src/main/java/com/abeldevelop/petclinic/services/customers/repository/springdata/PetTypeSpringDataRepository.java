package com.abeldevelop.petclinic.services.customers.repository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abeldevelop.petclinic.services.customers.generated.entity.PetTypeEntity;

public interface PetTypeSpringDataRepository extends JpaRepository<PetTypeEntity, Integer> {

}
