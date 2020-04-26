package com.abeldevelop.petclinic.services.customers.mapper;

import org.springframework.stereotype.Component;

import com.abeldevelop.petclinic.services.customers.generated.entity.PetTypeEntity;
import com.abeldevelop.petclinic.services.customers.generated.resource.pettype.PetTypeRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.pettype.PetTypeResponseResource;

@Component
public class PetTypeMapper {

	public PetTypeEntity map(PetTypeRequestResource petTypeRequestResource) {
		return PetTypeEntity.builder()
				.name(petTypeRequestResource.getName())
				.build();
	}
	
	public PetTypeResponseResource map(PetTypeEntity petTypeEntity) {
		return PetTypeResponseResource.builder()
				.id(petTypeEntity.getId())
				.name(petTypeEntity.getName())
				.build();
	}
	
	public PetTypeEntity map(PetTypeEntity petTypeEntity, PetTypeRequestResource petTypeRequestResource) {
		petTypeEntity.setName(petTypeRequestResource.getName());
		return petTypeEntity;
	}
	
}
