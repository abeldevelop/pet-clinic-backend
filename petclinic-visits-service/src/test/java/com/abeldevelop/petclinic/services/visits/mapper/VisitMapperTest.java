package com.abeldevelop.petclinic.services.visits.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.abeldevelop.petclinic.services.visits.generated.entity.VisitEntity;
import com.abeldevelop.petclinic.services.visits.generated.resource.VisitRequestResource;
import com.abeldevelop.petclinic.services.visits.generated.resource.VisitResponseResource;
import com.abeldevelop.petclinic.services.visits.objectmother.VisitObjectMother;

public class VisitMapperTest {

	private VisitMapper visitMapper;
	
	@BeforeEach
	public void setUp() {
		visitMapper = new VisitMapper();
	}
	
	@Test
	public void testMapVisitRequestResourceToVisitEntity() {
		//given
		VisitRequestResource visitRequestResource = VisitObjectMother.generateVisitRequestResource();
		
		//when
		VisitEntity visitEntity = visitMapper.map(visitRequestResource);
		
		//then
		assertEquals(visitRequestResource.getDate(), visitEntity.getDate());
		assertEquals(visitRequestResource.getDescription(), visitEntity.getDescription());
	}
	
	@Test
	public void testMapVisitEntityToVisitResponseResource() {
		//given
		VisitEntity visitEntity = VisitObjectMother.generateVisitEntity();
		
		//when
		VisitResponseResource visitResponseResource = visitMapper.map(visitEntity);
		
		//then
		assertEquals(visitEntity.getId(), visitResponseResource.getId());
		assertEquals(visitEntity.getDate(), visitResponseResource.getDate());
		assertEquals(visitEntity.getDescription(), visitResponseResource.getDescription());
		assertEquals(visitEntity.getPetId(), visitResponseResource.getPetId());
	}
	
	@Test
	public void testMapVisitEntityAndVisitRequestResourceToVisitEntity() {
		//given
		VisitEntity visitEntity = VisitObjectMother.generateVisitEntity();
		VisitRequestResource visitRequestResource = VisitObjectMother.generateVisitRequestResource();
		
		//when
		visitMapper.map(visitEntity, visitRequestResource);
		
		//then
		assertEquals(visitEntity.getId(), visitEntity.getId());
		assertEquals(visitEntity.getDate(), visitEntity.getDate());
		assertEquals(visitRequestResource.getDescription(), visitEntity.getDescription());
		assertEquals(visitEntity.getPetId(), visitEntity.getPetId());
	}
}
