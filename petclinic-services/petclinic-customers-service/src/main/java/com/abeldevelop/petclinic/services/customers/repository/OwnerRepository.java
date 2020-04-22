package com.abeldevelop.petclinic.services.customers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abeldevelop.petclinic.services.customers.generated.entity.OwnerEntity;

public interface OwnerRepository extends JpaRepository<OwnerEntity, Integer> {

}
