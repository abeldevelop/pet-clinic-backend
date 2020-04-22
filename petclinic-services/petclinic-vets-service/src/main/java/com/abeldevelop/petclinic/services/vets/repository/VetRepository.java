package com.abeldevelop.petclinic.services.vets.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abeldevelop.petclinic.services.vets.generated.entity.VetEntity;

public interface VetRepository extends JpaRepository<VetEntity, Integer> {
}