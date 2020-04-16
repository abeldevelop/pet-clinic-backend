package com.abeldevelop.petclinic.services.customers.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.abeldevelop.petclinic.services.customers.generated.entity.OwnerEntity;
import com.abeldevelop.petclinic.services.customers.generated.resource.OwnerRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.OwnerResponseResource;
import com.abeldevelop.petclinic.services.customers.objectmother.OwnerObjectMother;

public class OwnerMapperTest {

	private OwnerMapper ownerMapper;
	
	@BeforeEach
	public void setUp() {
		ownerMapper = new OwnerMapper();
	}
	
	@Test
	public void mapOwnerRequestResourceToOwnerEntity() {
		//given
		OwnerRequestResource ownerRequestResource = OwnerObjectMother.generateOwnerRequestResource();
		
		//when
		OwnerEntity ownerEntity = ownerMapper.map(ownerRequestResource);
		
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
		OwnerEntity ownerEntity = OwnerObjectMother.generateOwnerEntity();
		
		//when
		OwnerResponseResource ownerResponseResource = ownerMapper.map(ownerEntity);
		
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
		OwnerEntity ownerEntity = OwnerObjectMother.generateOwnerEntity();
		OwnerRequestResource ownerRequestResource = OwnerObjectMother.generateOwnerRequestResource();
		
		//when
		OwnerEntity ownerEntityResult = ownerMapper.map(OwnerObjectMother.generateOwnerEntity(), ownerRequestResource);
		
		//then
		assertEquals(ownerEntity.getId(), ownerEntityResult.getId());
		assertEquals(ownerRequestResource.getFirstName(), ownerEntityResult.getFirstName());
		assertEquals(ownerRequestResource.getLastName(), ownerEntityResult.getLastName());
		assertEquals(ownerRequestResource.getAddress(), ownerEntityResult.getAddress());
		assertEquals(ownerRequestResource.getCity(), ownerEntityResult.getCity());
		assertEquals(ownerRequestResource.getTelephone(), ownerEntityResult.getTelephone());
	}
	
}
