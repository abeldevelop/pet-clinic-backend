package com.abeldevelop.petclinic.services.vets.mapper;

import org.springframework.stereotype.Component;

import com.abeldevelop.petclinic.services.vets.generated.entity.SpecialtyEntity;
import com.abeldevelop.petclinic.services.vets.generated.resource.SpecialtyResponseResult;

@Component
public class SpecialtyMapper {

	public SpecialtyResponseResult map(SpecialtyEntity specialtyEntity) {
		return SpecialtyResponseResult.builder()
				.id(specialtyEntity.getId())
				.name(specialtyEntity.getName())
				.build();
	}
	
}
