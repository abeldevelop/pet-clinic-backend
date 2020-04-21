package com.abeldevelop.petclinic.services.vets.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.abeldevelop.petclinic.library.test.CommonTest;
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
		MockHttpServletResponse mockHttpServletResponse = mvc.perform(
				get("/vets")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn()
				.getResponse();
		
		mockHttpServletResponse.setCharacterEncoding("UTF-8");

		VetPaginationResponseResult result = convertJsonAsStringToObject(mockHttpServletResponse.getContentAsString(), VetPaginationResponseResult.class);
		
		//then
		assertEquals(1, result.getVets().size());
		
	}
	
}
