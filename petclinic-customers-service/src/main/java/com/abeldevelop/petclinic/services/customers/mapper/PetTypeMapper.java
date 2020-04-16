package com.abeldevelop.petclinic.services.customers.mapper;

import org.springframework.stereotype.Component;

import com.abeldevelop.petclinic.services.customers.generated.entity.PetTypeEntity;
import com.abeldevelop.petclinic.services.customers.generated.resource.PetTypeResponseResource;

@Component
public class PetTypeMapper {

	public PetTypeResponseResource map(PetTypeEntity petTypeEntity) {
		return PetTypeResponseResource.builder()
				.id(petTypeEntity.getId())
				.name(petTypeEntity.getName())
				.build();
	}
	
}
