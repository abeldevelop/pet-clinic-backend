package com.abeldevelop.petclinic.services.customers.objectmother;

import java.time.LocalDate;

import com.abeldevelop.petclinic.services.customers.generated.entity.PetEntity;
import com.abeldevelop.petclinic.services.customers.generated.resource.pet.PetRequestResource;

public class PetObjectMother {

	private PetObjectMother() {
		
	}
	
	public static PetRequestResource generatePetRequestResource() {
		return PetRequestResource.builder()
				.name("namePetRequestResource")
				.birthDate(LocalDate.of(2020, 4, 15))
				.typeId(1)
				.build();
	}
	
	public static PetEntity generatePetEntity() {
		return PetEntity.builder()
			    .id(1)
			    .name("nameEntity")
			    .birthDate(LocalDate.of(2020, 4, 16))
			    .type(PetTypeObjectMother.generatePetTypeEntity())
			    .customer(CustomerObjectMother.generateCustomerEntity())
				.build();
	}
	
	public static PetRequestResource generatePetRequestResourceFromPetEntity(PetEntity petEntity) {
		return PetRequestResource.builder()
				.name(petEntity.getName() + "Updated")
				.birthDate(petEntity.getBirthDate())
				.build();
	}
	
}
