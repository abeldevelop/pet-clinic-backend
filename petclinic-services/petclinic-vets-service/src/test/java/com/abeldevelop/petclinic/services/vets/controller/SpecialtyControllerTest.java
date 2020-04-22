package com.abeldevelop.petclinic.services.vets.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import com.abeldevelop.petclinic.library.common.resources.ErrorResponseResource;
import com.abeldevelop.petclinic.library.test.CommonTest;
import com.abeldevelop.petclinic.library.test.domain.RequestCall;
import com.abeldevelop.petclinic.library.test.domain.ResponseCall;
import com.abeldevelop.petclinic.services.vets.generated.entity.VetEntity;
import com.abeldevelop.petclinic.services.vets.generated.resource.SpecialtyPaginationResponseResult;
import com.abeldevelop.petclinic.services.vets.objectmother.VetObjectMother;
import com.abeldevelop.petclinic.services.vets.repository.VetRepository;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SpecialtyControllerTest extends CommonTest {

	@Autowired
	private VetRepository vetRepository;
	
	@Test
	public void testfindAllEndpoint() throws Exception {
		//given
		vetRepository.deleteAll();
		VetEntity vetEntity = vetRepository.save(VetObjectMother.generateVetEntityWithoutId());
		
		//when
		ResponseCall<SpecialtyPaginationResponseResult> response = makeGetCall(RequestCall.builder()
				.endpoint("/vets/{vetId}/specialties")
				.pathParam(vetEntity.getId())
				.build(), 
				SpecialtyPaginationResponseResult.class);
		
		//then
		assertEquals(HttpStatus.OK, response.getStatus());
		assertEquals(1, response.getBody().getSpecialties().size());
		
	}
	
	@Test
	public void testfindAllEndpointVetNotFound() throws Exception {
		//given
		vetRepository.deleteAll();
		Integer vetId = 1;
		
		//when
		ResponseCall<ErrorResponseResource> response = makeGetCall(RequestCall.builder()
				.endpoint("/vets/{vetId}/specialties")
				.pathParam(vetId)
				.build(), 
				ErrorResponseResource.class);
		
		//then
		assertEquals(HttpStatus.NOT_FOUND, response.getStatus());
		assertEquals("No exist vet with ID: '" + vetId + "'", response.getBody().getMessage());
		
	}
}
