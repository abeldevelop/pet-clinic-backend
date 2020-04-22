package com.abeldevelop.petclinic.services.vets.objectmother;

import java.util.Arrays;
import java.util.HashSet;

import com.abeldevelop.petclinic.services.vets.generated.entity.VetEntity;
import com.abeldevelop.petclinic.services.vets.generated.resource.VetResponseResult;

public class VetObjectMother {

	public static VetEntity generateVetEntity() {
		return VetEntity.builder()
			    .id(1)
			    .firstName("firstNameEntity")
			    .lastName("lastNameEntity")
			    .specialties(new HashSet<>(Arrays.asList(SpecialtyObjectMother.generateSpecialtyEntity())))
				.build();
	}
	
	public static VetEntity generateVetEntityWithoutId() {
		VetEntity vetEntity = generateVetEntity();
		vetEntity.setId(null);
		vetEntity.getSpecialties().forEach((specialtyEntity) -> specialtyEntity.setId(null));
		return vetEntity;
	}
	
	public static VetResponseResult generateVetResponseResult() {
		return VetResponseResult.builder()
			    .id(2)
	    		.firstName("firstNameResponseResult")
	    		.lastName("lastNameResponseResult")
				.build();
	}
	
}
