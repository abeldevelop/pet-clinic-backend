package com.abeldevelop.petclinic.services.customers.mapper;

import org.springframework.stereotype.Component;

import com.abeldevelop.petclinic.services.customers.generated.entity.CustomerEntity;
import com.abeldevelop.petclinic.services.customers.generated.resource.customer.CustomerCreateRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.customer.CustomerResponseResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.customer.CustomerUpdateRequestResource;

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
	
	public CustomerResponseResource map(CustomerEntity customerEntity) {
		return CustomerResponseResource.builder()
			    .id(customerEntity.getId())
			    .identificationDocument(customerEntity.getIdentificationDocument())
			    .firstName(customerEntity.getFirstName())
			    .lastName(customerEntity.getLastName())
			    .address(customerEntity.getAddress())
			    .city(customerEntity.getCity())
			    .telephone(customerEntity.getTelephone())
				.build();
	}
	
	public CustomerEntity map(CustomerEntity customerEntity, CustomerUpdateRequestResource customerUpdateRequestResource) {
		customerEntity.setFirstName(customerUpdateRequestResource.getFirstName());
		customerEntity.setLastName(customerUpdateRequestResource.getLastName());
		customerEntity.setAddress(customerUpdateRequestResource.getAddress());
		customerEntity.setCity(customerUpdateRequestResource.getCity());
		customerEntity.setTelephone(customerUpdateRequestResource.getTelephone());
		return customerEntity;
	}
}
