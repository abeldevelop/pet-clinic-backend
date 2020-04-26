package com.abeldevelop.petclinic.services.customers.repository.springdata;

import java.util.Optional;

import com.abeldevelop.petclinic.library.common.extend.CommonSpringDataRepositoryRepository;
import com.abeldevelop.petclinic.services.customers.generated.entity.PetTypeEntity;

public interface PetTypeSpringDataRepository extends CommonSpringDataRepositoryRepository<PetTypeEntity, Integer> {

	public Optional<PetTypeEntity> findByName(String name);

}
