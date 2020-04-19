package com.abeldevelop.petclinic.services.customers.objectmother;

import com.abeldevelop.petclinic.services.customers.generated.entity.PetTypeEntity;

public class PetTypeObjectMother {

	private PetTypeObjectMother() {
		
	}
	
	public static PetTypeEntity generatePetTypeEntity() {
		return PetTypeEntity.builder()
				.id(1)
				.name("nameEntity")
				.build();
	}
}
