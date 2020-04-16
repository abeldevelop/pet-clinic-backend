package com.abeldevelop.petclinic.services.customers.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.abeldevelop.petclinic.services.customers.generated.resource.PetTypeResponseResource;
import com.abeldevelop.petclinic.services.customers.objectmother.PetTypeObjectMother;
import com.abeldevelop.petclinic.services.customers.repository.OwnerRepository;
import com.abeldevelop.petclinic.services.customers.repository.PetTypeRepository;
import com.abeldevelop.petclinic.services.customers.util.CommonTest;

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
		MockHttpServletResponse mockHttpServletResponse = mvc.perform(
				get("/petTypes")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn()
				.getResponse();
		
		mockHttpServletResponse.setCharacterEncoding("UTF-8");
		
		List<PetTypeResponseResource> result = Arrays.asList(convertJsonAsStringToObject(mockHttpServletResponse.getContentAsString(), PetTypeResponseResource[].class));
		
		//then
		assertEquals(1, result.size());
	}
	
}
