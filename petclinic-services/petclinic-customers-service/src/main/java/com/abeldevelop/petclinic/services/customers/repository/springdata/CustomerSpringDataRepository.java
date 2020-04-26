package com.abeldevelop.petclinic.services.customers.repository.springdata;

import java.util.Optional;

import com.abeldevelop.petclinic.library.common.extend.CommonSpringDataRepositoryRepository;
import com.abeldevelop.petclinic.services.customers.generated.entity.CustomerEntity;

public interface CustomerSpringDataRepository extends CommonSpringDataRepositoryRepository<CustomerEntity, Integer> {

	Optional<CustomerEntity> findByIdentificationDocument(String identificationDocument);
	
}
