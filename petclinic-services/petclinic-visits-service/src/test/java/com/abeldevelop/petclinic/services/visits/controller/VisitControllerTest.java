package com.abeldevelop.petclinic.services.visits.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import com.abeldevelop.petclinic.library.test.CommonTest;
import com.abeldevelop.petclinic.library.test.domain.RequestCall;
import com.abeldevelop.petclinic.library.test.domain.ResponseCall;
import com.abeldevelop.petclinic.services.visits.generated.resource.VisitPaginationResponseResource;
import com.abeldevelop.petclinic.services.visits.generated.resource.VisitRequestResource;
import com.abeldevelop.petclinic.services.visits.generated.resource.VisitResponseResource;
import com.abeldevelop.petclinic.services.visits.objectmother.VisitObjectMother;
import com.abeldevelop.petclinic.services.visits.repository.VisitRepository;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class VisitControllerTest extends CommonTest {

	@Autowired
	private VisitRepository visitRepository;
	
	@Test
	public void testCreateEndpoint() throws Exception {
		//given
		visitRepository.deleteAll();
		VisitRequestResource visitRequestResource = VisitObjectMother.generateVisitRequestResource();
		String identificationDocument = "1";
		Integer petId = 1;
		
		//when
		ResponseCall<VisitResponseResource> response = makePostCall(RequestCall.builder()
				.endpoint("/customers/{identificationDocument}/pets/{petId}/visits")
				.pathParam(identificationDocument).pathParam(petId)
				.body(visitRequestResource)
				.build(), 
				VisitResponseResource.class);
		
		//then
		assertEquals(HttpStatus.CREATED, response.getStatus());
		assertNotNull(response.getBody().getId());
		assertEquals(visitRequestResource.getDate(), response.getBody().getDate());
		assertEquals(visitRequestResource.getDescription(), response.getBody().getDescription());
		assertEquals(petId, response.getBody().getPetId());
		
	}
	
	@Test
	public void testFindAllEndpoint() throws Exception {
		//given
		visitRepository.deleteAll();
		visitRepository.save(VisitObjectMother.generateVisitEntity());
		String identificationDocument = "1";
		Integer petId = 1;
		
		//when
		ResponseCall<VisitPaginationResponseResource> response = makeGetCall(RequestCall.builder()
				.endpoint("/customers/{identificationDocument}/pets/{petId}/visits")
				.pathParam(identificationDocument).pathParam(petId)
				.build(), 
				VisitPaginationResponseResource.class);
		
		//then
		assertEquals(HttpStatus.OK, response.getStatus());
		assertEquals(1, response.getBody().getVisits().size());
	}
	
}
