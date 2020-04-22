package com.abeldevelop.petclinic.services.customers.objectmother;

import com.abeldevelop.petclinic.services.customers.generated.entity.OwnerEntity;
import com.abeldevelop.petclinic.services.customers.generated.resource.OwnerRequestResource;

public class OwnerObjectMother {

	private OwnerObjectMother() {
		
	}
	
	public static OwnerRequestResource generateOwnerRequestResource() {
		return OwnerRequestResource.builder()
			    .firstName("firstName")
			    .lastName("lastName")
			    .address("address")
			    .city("city")
			    .telephone("987654321")
				.build();
	}
	
	public static OwnerEntity generateOwnerEntity() {
		return OwnerEntity.builder()
				.id(1)
			    .firstName("firstNameEntity")
			    .lastName("lastNameEntity")
			    .address("addressEntity")
			    .city("cityEntity")
			    .telephone("123456789")
				.build();
	}
	
	public static OwnerRequestResource generateOwnerRequestResourceFromOwnerEntity(OwnerEntity ownerEntity) {
		return OwnerRequestResource.builder()
			    .firstName(ownerEntity.getFirstName() + "Updated")
			    .lastName(ownerEntity.getLastName() + "Updated")
			    .address(ownerEntity.getAddress() + "Updated")
			    .city(ownerEntity.getCity() + "Updated")
			    .telephone(ownerEntity.getTelephone())
				.build();
	}
	
	
}
