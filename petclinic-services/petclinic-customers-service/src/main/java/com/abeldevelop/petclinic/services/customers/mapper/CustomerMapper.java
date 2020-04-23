package com.abeldevelop.petclinic.services.customers.mapper;

import org.springframework.stereotype.Component;

import com.abeldevelop.petclinic.services.customers.generated.entity.CustomerEntity;
import com.abeldevelop.petclinic.services.customers.generated.resource.CustomerCreateRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.CustomerResponseResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.CustomerUpdateRequestResource;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CustomerMapper {

	public CustomerEntity map(CustomerCreateRequestResource customerRequestResource) {
		return CustomerEntity.builder()
				.identificationDocument(customerRequestResource.getIdentificationDocument())
			    .firstName(customerRequestResource.getFirstName())
			    .lastName(customerRequestResource.getLastName())
			    .address(customerRequestResource.getAddress())
			    .city(customerRequestResource.getCity())
			    .telephone(customerRequestResource.getTelephone())
				.build();
	}
	
	public CustomerResponseResource map(CustomerEntity ownerEntity) {
		return CustomerResponseResource.builder()
			    .id(ownerEntity.getId())
			    .identificationDocument(ownerEntity.getIdentificationDocument())
			    .firstName(ownerEntity.getFirstName())
			    .lastName(ownerEntity.getLastName())
			    .address(ownerEntity.getAddress())
			    .city(ownerEntity.getCity())
			    .telephone(ownerEntity.getTelephone())
				.build();
	}
	
	public CustomerEntity map(CustomerEntity ownerEntity, CustomerUpdateRequestResource customerUpdateRequestResource) {
		ownerEntity.setFirstName(customerUpdateRequestResource.getFirstName());
		ownerEntity.setLastName(customerUpdateRequestResource.getLastName());
		ownerEntity.setAddress(customerUpdateRequestResource.getAddress());
		ownerEntity.setCity(customerUpdateRequestResource.getCity());
		ownerEntity.setTelephone(customerUpdateRequestResource.getTelephone());
		return ownerEntity;
	}
}
