package com.abeldevelop.petclinic.services.customers.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

import com.abeldevelop.petclinic.library.test.CommonTest;
import com.abeldevelop.petclinic.library.test.domain.RequestCall;
import com.abeldevelop.petclinic.library.test.domain.ResponseCall;
import com.abeldevelop.petclinic.services.customers.generated.resource.PetTypePaginationResponseResource;
import com.abeldevelop.petclinic.services.customers.objectmother.PetTypeObjectMother;
import com.abeldevelop.petclinic.services.customers.repository.OwnerRepository;
import com.abeldevelop.petclinic.services.customers.repository.PetTypeRepository;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PetTypeControllerTest extends CommonTest {

	@Autowired
	private OwnerRepository ownerRepository;
	
	@Autowired
	private PetTypeRepository petTypeRepository;
	
	@Test
	public void testFindAllPetTypesEndpoint() throws Exception {
		//given
		ownerRepository.deleteAll();
		petTypeRepository.deleteAll();
		petTypeRepository.save(PetTypeObjectMother.generatePetTypeEntity());
		
		//when
		ResponseCall<PetTypePaginationResponseResource> response = makeGetCall(RequestCall.builder()
				.endpoint("/petTypes")
				.build(), 
				PetTypePaginationResponseResource.class);
		
		//then
		assertEquals(1, response.getBody().getPetTypes().size());
	}
	
}