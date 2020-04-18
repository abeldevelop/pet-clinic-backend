package com.abeldevelop.petclinic.services.vets.mapper;

import org.springframework.stereotype.Component;

import com.abeldevelop.petclinic.services.vets.generated.entity.VetEntity;
import com.abeldevelop.petclinic.services.vets.generated.resource.VetResponseResult;

@Component
public class VetMapper {

	public VetResponseResult map(VetEntity vetEntity) {
		return VetResponseResult.builder()
				.id(vetEntity.getId())
				.firstName(vetEntity.getFirstName())
				.lastName(vetEntity.getLastName())
				.build();
	}
	
}
