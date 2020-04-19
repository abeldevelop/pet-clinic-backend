package com.abeldevelop.petclinic.services.vets.objectmother;

import com.abeldevelop.petclinic.services.vets.generated.entity.SpecialtyEntity;
import com.abeldevelop.petclinic.services.vets.generated.resource.SpecialtyResponseResult;

public class SpecialtyObjectMother {

	public static SpecialtyEntity generateSpecialtyEntity() {
		return SpecialtyEntity.builder()
				.id(1)
				.name("specialtyEntity")
				.build();
	}
	
	public static SpecialtyResponseResult generateSpecialtyResponseResult() {
		return SpecialtyResponseResult.builder()
				.id(2)
				.name("specialtyResponseResult")
				.build();
	}
	
}
