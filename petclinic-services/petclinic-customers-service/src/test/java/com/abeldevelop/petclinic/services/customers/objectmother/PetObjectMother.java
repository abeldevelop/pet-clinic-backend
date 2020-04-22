package com.abeldevelop.petclinic.services.customers.objectmother;

import java.time.LocalDate;

import com.abeldevelop.petclinic.services.customers.generated.entity.PetEntity;
import com.abeldevelop.petclinic.services.customers.generated.resource.PetRequestResource;

public class PetObjectMother {

	private PetObjectMother() {
		
	}
	
	public static PetRequestResource generatePetRequestResource() {
		return PetRequestResource.builder()
				.name("namePetRequestResource")
				.birthDate(LocalDate.of(2020, 4, 15))
				.build();
	}
	
	public static PetEntity generatePetEntity() {
		return PetEntity.builder()
			    .id(1)
			    .name("nameEntity")
			    .birthDate(LocalDate.of(2020, 4, 16))
			    .type(PetTypeObjectMother.generatePetTypeEntity())
			    .owner(OwnerObjectMother.generateOwnerEntity())
				.build();
	}
	
	public static PetRequestResource generatePetRequestResourceFromPetEntity(PetEntity petEntity) {
		return PetRequestResource.builder()
				.name(petEntity.getName() + "Updated")
				.birthDate(petEntity.getBirthDate())
				.build();
	}
	
}
