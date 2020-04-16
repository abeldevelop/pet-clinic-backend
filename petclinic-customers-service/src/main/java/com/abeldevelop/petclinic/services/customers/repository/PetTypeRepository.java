package com.abeldevelop.petclinic.services.customers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.abeldevelop.petclinic.services.customers.generated.entity.PetTypeEntity;

public interface PetTypeRepository extends JpaRepository<PetTypeEntity, Integer> {

}
