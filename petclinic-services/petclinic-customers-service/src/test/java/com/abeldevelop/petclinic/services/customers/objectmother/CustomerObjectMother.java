package com.abeldevelop.petclinic.services.customers.objectmother;

import com.abeldevelop.petclinic.services.customers.generated.entity.CustomerEntity;
import com.abeldevelop.petclinic.services.customers.generated.resource.customer.CustomerCreateRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.customer.CustomerUpdateRequestResource;

public class CustomerObjectMother {

	private CustomerObjectMother() {
		
	}
	
	public static CustomerCreateRequestResource generateCustomerCreateRequestResource() {
		return CustomerCreateRequestResource.builder()
				.identificationDocument("123456")
			    .firstName("firstNameCreate")
			    .lastName("lastNameCreate")
			    .address("addressCreate")
			    .city("cityCreate")
			    .telephone("987654321")
				.build();
	}
	
	public static CustomerUpdateRequestResource generateCustomerUpdateRequestResource() {
		return CustomerUpdateRequestResource.builder()
			    .firstName("firstNameUpdate")
			    .lastName("lastNameUpdate")
			    .address("addressUpdate")
			    .city("cityUpdate")
			    .telephone("567891234")
				.build();
	}
	
	public static CustomerEntity generateCustomerEntity() {
		return CustomerEntity.builder()
				.id(1)
				.identificationDocument("54321")
			    .firstName("firstNameEntity")
			    .lastName("lastNameEntity")
			    .address("addressEntity")
			    .city("cityEntity")
			    .telephone("123456789")
				.build();
	}
	
	public static CustomerUpdateRequestResource generateCustomerUpdateRequestResourceFromCustomerEntity(CustomerEntity customerEntity) {
		return CustomerUpdateRequestResource.builder()
			    .firstName(customerEntity.getFirstName() + "Modify")
			    .lastName(customerEntity.getLastName() + "Modify")
			    .address(customerEntity.getAddress() + "Modify")
			    .city(customerEntity.getCity() + "Modify")
			    .telephone(customerEntity.getTelephone())
				.build();
	}
	
	
}
