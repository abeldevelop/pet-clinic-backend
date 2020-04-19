package com.abeldevelop.petclinic.services.customers.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.abeldevelop.petclinic.services.customers.generated.entity.PetTypeEntity;
import com.abeldevelop.petclinic.services.customers.generated.resource.PetTypeResponseResource;
import com.abeldevelop.petclinic.services.customers.objectmother.PetTypeObjectMother;

public class PetTypeMapperTest {

	private PetTypeMapper petTypeMapper;
	
	@BeforeEach
	public void setUp() {
		petTypeMapper = new PetTypeMapper();
	}
	
	@Test
	public void mapPetTypeEntityToPetTypeResponseResource() {
		//given
		PetTypeEntity petTypeEntity = PetTypeObjectMother.generatePetTypeEntity();
		
		//when
		PetTypeResponseResource petTypeResponseResource = petTypeMapper.map(petTypeEntity);
		
		//then
		assertEquals(petTypeEntity.getId(), petTypeResponseResource.getId());
		assertEquals(petTypeEntity.getName(), petTypeResponseResource.getName());
	}
	
}
