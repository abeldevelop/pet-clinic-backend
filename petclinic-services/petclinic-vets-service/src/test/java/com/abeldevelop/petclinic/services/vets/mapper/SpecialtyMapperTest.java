package com.abeldevelop.petclinic.services.vets.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.abeldevelop.petclinic.services.vets.generated.entity.SpecialtyEntity;
import com.abeldevelop.petclinic.services.vets.generated.resource.SpecialtyResponseResult;
import com.abeldevelop.petclinic.services.vets.objectmother.SpecialtyObjectMother;

public class SpecialtyMapperTest {

	private SpecialtyMapper specialtyMapper;
	
	@BeforeEach
	public void setUp() {
		specialtyMapper = new SpecialtyMapper();
	}
	
	@Test
	public void testMap() {
		//given
		SpecialtyEntity specialtyEntity = SpecialtyObjectMother.generateSpecialtyEntity();
		
		//when
		SpecialtyResponseResult specialtyResponseResult = specialtyMapper.map(specialtyEntity);
		
		//then
		assertEquals(specialtyEntity.getId(), specialtyResponseResult.getId());
		assertEquals(specialtyEntity.getName(), specialtyResponseResult.getName());
		
	}
	
}
