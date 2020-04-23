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

	private CustomerMapper ownerMapper;
	
	@BeforeEach
	public void setUp() {
		ownerMapper = new CustomerMapper();
	}
	
	@Test
	public void mapOwnerRequestResourceToOwnerEntity() {
		//given
		CustomerCreateRequestResource ownerRequestResource = CustomerObjectMother.generateCustomerCreateRequestResource();
		
		//when
		CustomerEntity ownerEntity = ownerMapper.map(ownerRequestResource);
		
		//then
		assertEquals(ownerRequestResource.getFirstName(), ownerEntity.getFirstName());
		assertEquals(ownerRequestResource.getLastName(), ownerEntity.getLastName());
		assertEquals(ownerRequestResource.getAddress(), ownerEntity.getAddress());
		assertEquals(ownerRequestResource.getCity(), ownerEntity.getCity());
		assertEquals(ownerRequestResource.getTelephone(), ownerEntity.getTelephone());
	}
	
	@Test
	public void mapOwnerEntityToOwnerResponseResource() {
		//given
		CustomerEntity ownerEntity = CustomerObjectMother.generateCustomerEntity();
		
		//when
		CustomerResponseResource ownerResponseResource = ownerMapper.map(ownerEntity);
		
		//then
		assertEquals(ownerEntity.getId(), ownerResponseResource.getId());
		assertEquals(ownerEntity.getFirstName(), ownerResponseResource.getFirstName());
		assertEquals(ownerEntity.getLastName(), ownerResponseResource.getLastName());
		assertEquals(ownerEntity.getAddress(), ownerResponseResource.getAddress());
		assertEquals(ownerEntity.getCity(), ownerResponseResource.getCity());
		assertEquals(ownerEntity.getTelephone(), ownerResponseResource.getTelephone());
	}
	
	@Test
	public void mapOwnerEntityAndOwnerRequestResourceToOwnerEntity() {
		//given
		CustomerEntity ownerEntity = CustomerObjectMother.generateCustomerEntity();
		CustomerUpdateRequestResource ownerRequestResource = CustomerObjectMother.generateCustomerUpdateRequestResource();
		
		//when
		CustomerEntity ownerEntityResult = ownerMapper.map(CustomerObjectMother.generateCustomerEntity(), ownerRequestResource);
		
		//then
		assertEquals(ownerEntity.getId(), ownerEntityResult.getId());
		assertEquals(ownerRequestResource.getFirstName(), ownerEntityResult.getFirstName());
		assertEquals(ownerRequestResource.getLastName(), ownerEntityResult.getLastName());
		assertEquals(ownerRequestResource.getAddress(), ownerEntityResult.getAddress());
		assertEquals(ownerRequestResource.getCity(), ownerEntityResult.getCity());
		assertEquals(ownerRequestResource.getTelephone(), ownerEntityResult.getTelephone());
	}
	
}
