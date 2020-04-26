package com.abeldevelop.petclinic.services.customers.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.abeldevelop.petclinic.services.customers.generated.entity.CustomerEntity;
import com.abeldevelop.petclinic.services.customers.generated.resource.customer.CustomerCreateRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.customer.CustomerResponseResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.customer.CustomerUpdateRequestResource;
import com.abeldevelop.petclinic.services.customers.objectmother.CustomerObjectMother;

public class CustomerMapperTest {

	private CustomerMapper customerMapper;
	
	@BeforeEach
	public void setUp() {
		customerMapper = new CustomerMapper();
	}
	
	@Test
	public void mapCustomerRequestResourceToCustomerEntity() {
		//given
		CustomerCreateRequestResource customerCreateRequestResource = CustomerObjectMother.generateCustomerCreateRequestResource();
		
		//when
		CustomerEntity customerEntity = customerMapper.map(customerCreateRequestResource);
		
		//then
		assertEquals(customerCreateRequestResource.getIdentificationDocument(), customerEntity.getIdentificationDocument());
		assertEquals(customerCreateRequestResource.getFirstName(), customerEntity.getFirstName());
		assertEquals(customerCreateRequestResource.getLastName(), customerEntity.getLastName());
		assertEquals(customerCreateRequestResource.getAddress(), customerEntity.getAddress());
		assertEquals(customerCreateRequestResource.getCity(), customerEntity.getCity());
		assertEquals(customerCreateRequestResource.getTelephone(), customerEntity.getTelephone());
	}
	
	@Test
	public void mapCustomerEntityToCustomerResponseResource() {
		//given
		CustomerEntity customerEntity = CustomerObjectMother.generateCustomerEntity();
		
		//when
		CustomerResponseResource customerResponseResource = customerMapper.map(customerEntity);
		
		//then
		assertEquals(customerEntity.getId(), customerResponseResource.getId());
		assertEquals(customerEntity.getIdentificationDocument(), customerResponseResource.getIdentificationDocument());
		assertEquals(customerEntity.getFirstName(), customerResponseResource.getFirstName());
		assertEquals(customerEntity.getLastName(), customerResponseResource.getLastName());
		assertEquals(customerEntity.getAddress(), customerResponseResource.getAddress());
		assertEquals(customerEntity.getCity(), customerResponseResource.getCity());
		assertEquals(customerEntity.getTelephone(), customerResponseResource.getTelephone());
	}
	
	@Test
	public void mapCustomerEntityAndCustomerRequestResourceToCustomerEntity() {
		//given
		CustomerEntity customerEntity = CustomerObjectMother.generateCustomerEntity();
		CustomerUpdateRequestResource customerUpdateRequestResource = CustomerObjectMother.generateCustomerUpdateRequestResource();
		
		//when
		CustomerEntity customerEntityResult = customerMapper.map(CustomerObjectMother.generateCustomerEntity(), customerUpdateRequestResource);
		
		//then
		assertEquals(customerEntity.getId(), customerEntityResult.getId());
		assertEquals(customerUpdateRequestResource.getFirstName(), customerEntityResult.getFirstName());
		assertEquals(customerUpdateRequestResource.getLastName(), customerEntityResult.getLastName());
		assertEquals(customerUpdateRequestResource.getAddress(), customerEntityResult.getAddress());
		assertEquals(customerUpdateRequestResource.getCity(), customerEntityResult.getCity());
		assertEquals(customerUpdateRequestResource.getTelephone(), customerEntityResult.getTelephone());
	}
	
}
