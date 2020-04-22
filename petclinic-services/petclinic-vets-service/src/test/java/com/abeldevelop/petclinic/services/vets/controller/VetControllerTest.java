package com.abeldevelop.petclinic.services.vets.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import com.abeldevelop.petclinic.services.vets.generated.resource.VetPaginationResponseResult;
import com.abeldevelop.petclinic.services.vets.objectmother.VetObjectMother;
import com.abeldevelop.petclinic.services.vets.repository.VetRepository;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class VetControllerTest extends CommonTest {

	@Autowired
	private VetRepository vetRepository;
	
	@Test
	public void testfindAllEndpoint() throws Exception {
		//given
		vetRepository.deleteAll();
		vetRepository.save(VetObjectMother.generateVetEntityWithoutId());
		
		//when
		ResponseCall<VetPaginationResponseResult> response = makeGetCall(RequestCall.builder()
				.endpoint("/vets")
				.build(), 
				VetPaginationResponseResult.class);
		
		//then
		assertEquals(HttpStatus.OK, response.getStatus());
		assertEquals(1, response.getBody().getVets().size());
		
	}
	
}
