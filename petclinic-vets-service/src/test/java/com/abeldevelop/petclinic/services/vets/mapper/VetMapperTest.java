package com.abeldevelop.petclinic.services.vets.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.abeldevelop.petclinic.services.vets.generated.entity.VetEntity;
import com.abeldevelop.petclinic.services.vets.generated.resource.VetResponseResult;
import com.abeldevelop.petclinic.services.vets.objectmother.VetObjectMother;

public class VetMapperTest {

	private VetMapper vetMapper;
	
	@BeforeEach
	public void setUp() {
		vetMapper = new VetMapper();
	}
	
	@Test
	public void testMap() {
		//given
		VetEntity vetEntity = VetObjectMother.generateVetEntity();
		
		//when
		VetResponseResult vetResponseResult = vetMapper.map(vetEntity);
		
		//then
		assertEquals(vetEntity.getId(), vetResponseResult.getId());
		assertEquals(vetEntity.getFirstName(), vetResponseResult.getFirstName());
		assertEquals(vetEntity.getLastName(), vetResponseResult.getLastName());
		
	}
}
