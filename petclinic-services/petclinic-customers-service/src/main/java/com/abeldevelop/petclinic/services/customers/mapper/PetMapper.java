package com.abeldevelop.petclinic.services.customers.mapper;

import org.springframework.stereotype.Component;

import com.abeldevelop.petclinic.services.customers.generated.entity.PetEntity;
import com.abeldevelop.petclinic.services.customers.generated.resource.PetRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.PetResponseResource;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class PetMapper {

	public PetEntity map(PetRequestResource petRequestResource) {
		return PetEntity.builder()
			    .name(petRequestResource.getName())
			    .birthDate(petRequestResource.getBirthDate())
				.build();
	}
	
	public PetResponseResource map(PetEntity petEntity) {
		return PetResponseResource.builder()
			   .id(petEntity.getId())
			   .name(petEntity.getName())
			   .birthDate(petEntity.getBirthDate())
			   .petTypeId(petEntity.getType().getId())
			   .ownerId(petEntity.getOwner().getId())
			   .build();
	}
	
	public PetEntity map(PetEntity petEntity, PetRequestResource petRequestResource) {
		petEntity.setName(petRequestResource.getName());
		petEntity.setBirthDate(petRequestResource.getBirthDate());
		return petEntity;
	}
}
