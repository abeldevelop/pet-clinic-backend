package com.abeldevelop.petclinic.services.customers.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.abeldevelop.petclinic.services.customers.generated.entity.CustomerEntity;
import com.abeldevelop.petclinic.services.customers.generated.resource.CustomerCreateRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.CustomerResponseResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.CustomerUpdateRequestResource;
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
		CustomerCreateRequestResource customerRequestResource = CustomerObjectMother.generateCustomerCreateRequestResource();
		
		//when
		CustomerEntity customerEntity = customerMapper.map(customerRequestResource);
		
		//then
		assertEquals(customerRequestResource.getFirstName(), customerEntity.getFirstName());
		assertEquals(customerRequestResource.getLastName(), customerEntity.getLastName());
		assertEquals(customerRequestResource.getAddress(), customerEntity.getAddress());
		assertEquals(customerRequestResource.getCity(), customerEntity.getCity());
		assertEquals(customerRequestResource.getTelephone(), customerEntity.getTelephone());
	}
	
	@Test
	public void mapCustomerEntityToCustomerResponseResource() {
		//given
		CustomerEntity customerEntity = CustomerObjectMother.generateCustomerEntity();
		
		//when
		CustomerResponseResource customerResponseResource = customerMapper.map(customerEntity);
		
		//then
		assertEquals(customerEntity.getId(), customerResponseResource.getId());
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
		CustomerUpdateRequestResource customerRequestResource = CustomerObjectMother.generateCustomerUpdateRequestResource();
		
		//when
		CustomerEntity customerEntityResult = customerMapper.map(CustomerObjectMother.generateCustomerEntity(), customerRequestResource);
		
		//then
		assertEquals(customerEntity.getId(), customerEntityResult.getId());
		assertEquals(customerRequestResource.getFirstName(), customerEntityResult.getFirstName());
		assertEquals(customerRequestResource.getLastName(), customerEntityResult.getLastName());
		assertEquals(customerRequestResource.getAddress(), customerEntityResult.getAddress());
		assertEquals(customerRequestResource.getCity(), customerEntityResult.getCity());
		assertEquals(customerRequestResource.getTelephone(), customerEntityResult.getTelephone());
	}
	
}
