package com.abeldevelop.petclinic.services.customers.objectmother;

import com.abeldevelop.petclinic.services.customers.generated.entity.PetTypeEntity;
import com.abeldevelop.petclinic.services.customers.generated.resource.pettype.PetTypeRequestResource;

public class PetTypeObjectMother {

	private PetTypeObjectMother() {
		
	}
	
	public static PetTypeRequestResource generatePetTypeRequestResource() {
		return PetTypeRequestResource.builder()
				.name("nameRequestResource")
				.build();
	}
	
	public static PetTypeEntity generatePetTypeEntity() {
		return PetTypeEntity.builder()
				.id(1)
				.name("nameEntity")
				.build();
	}
	
}
