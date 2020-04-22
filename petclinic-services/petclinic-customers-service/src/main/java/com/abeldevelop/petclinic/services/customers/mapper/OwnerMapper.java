package com.abeldevelop.petclinic.services.customers.mapper;

import org.springframework.stereotype.Component;

import com.abeldevelop.petclinic.services.customers.generated.entity.OwnerEntity;
import com.abeldevelop.petclinic.services.customers.generated.resource.OwnerRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.OwnerResponseResource;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class OwnerMapper {

	public OwnerEntity map(OwnerRequestResource ownerRequestResource) {
		return OwnerEntity.builder()
			    .firstName(ownerRequestResource.getFirstName())
			    .lastName(ownerRequestResource.getLastName())
			    .address(ownerRequestResource.getAddress())
			    .city(ownerRequestResource.getCity())
			    .telephone(ownerRequestResource.getTelephone())
				.build();
	}
	
	public OwnerResponseResource map(OwnerEntity ownerEntity) {
		return OwnerResponseResource.builder()
			    .id(ownerEntity.getId())
			    .firstName(ownerEntity.getFirstName())
			    .lastName(ownerEntity.getLastName())
			    .address(ownerEntity.getAddress())
			    .city(ownerEntity.getCity())
			    .telephone(ownerEntity.getTelephone())
				.build();
	}
	
	public OwnerEntity map(OwnerEntity ownerEntity, OwnerRequestResource ownerRequestResource) {
		ownerEntity.setFirstName(ownerRequestResource.getFirstName());
		ownerEntity.setLastName(ownerRequestResource.getLastName());
		ownerEntity.setAddress(ownerRequestResource.getAddress());
		ownerEntity.setCity(ownerRequestResource.getCity());
		ownerEntity.setTelephone(ownerRequestResource.getTelephone());
		return ownerEntity;
	}
}
