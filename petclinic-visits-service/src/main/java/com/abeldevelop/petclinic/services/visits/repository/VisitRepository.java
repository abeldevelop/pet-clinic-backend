package com.abeldevelop.petclinic.services.visits.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abeldevelop.petclinic.services.visits.generated.entity.VisitEntity;

public interface VisitRepository extends JpaRepository<VisitEntity, Integer> {

	public List<VisitEntity> findByPetId(Integer petId);
	
}
