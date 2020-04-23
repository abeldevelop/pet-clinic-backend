package com.abeldevelop.petclinic.services.customers.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.abeldevelop.petclinic.services.customers.generated.entity.PetEntity;
import com.abeldevelop.petclinic.services.customers.generated.resource.PetRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.PetResponseResource;
import com.abeldevelop.petclinic.services.customers.objectmother.PetObjectMother;

public class PetMapperTest {

	private PetMapper petMapper;
	
	@BeforeEach
	public void setUp() {
		petMapper = new PetMapper();
	}
	
	@Test
	public void mapPetRequestResourceToPetEntity() {
		//given
		PetRequestResource petRequestResource = PetObjectMother.generatePetRequestResource();
		
		//when
		PetEntity petEntity = petMapper.map(petRequestResource);
		
		//then
		assertEquals(petRequestResource.getName(), petEntity.getName());
		assertEquals(petRequestResource.getBirthDate(), petEntity.getBirthDate());
	}
	
	@Test
	public void mapPetEntityToPetResponseResource() {
		//given
		PetEntity petEntity = PetObjectMother.generatePetEntity();
		
		//when
		PetResponseResource petResponseResource = petMapper.map(petEntity);
		
		//then
		assertEquals(petEntity.getId(), petResponseResource.getId());
		assertEquals(petEntity.getName(), petResponseResource.getName());
		assertEquals(petEntity.getBirthDate(), petResponseResource.getBirthDate());
		assertEquals(petEntity.getType().getId(), petResponseResource.getPetTypeId());
		assertEquals(petEntity.getCustomer().getId(), petResponseResource.getCustomerId());
		
	}
	
	@Test
	public void mapPetEntityAndPetRequestResourceToPetEntity() {
		//given
		PetRequestResource petRequestResource = PetObjectMother.generatePetRequestResource();
		PetEntity petEntity = PetObjectMother.generatePetEntity();
		
		//when
		PetEntity petEntityResult = petMapper.map(petEntity, PetObjectMother.generatePetRequestResource());
		
		//then
		assertEquals(petEntity.getId(), petEntityResult.getId());
		assertEquals(petRequestResource.getName(), petEntityResult.getName());
		assertEquals(petRequestResource.getBirthDate(), petEntityResult.getBirthDate());
	}
	

}
