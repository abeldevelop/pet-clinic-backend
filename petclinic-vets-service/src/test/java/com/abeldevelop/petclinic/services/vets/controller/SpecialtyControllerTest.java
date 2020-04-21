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

import com.abeldevelop.petclinic.library.common.resources.ErrorResponseResource;
import com.abeldevelop.petclinic.services.vets.generated.entity.VetEntity;
import com.abeldevelop.petclinic.services.vets.generated.resource.SpecialtyPaginationResponseResult;
import com.abeldevelop.petclinic.services.vets.objectmother.VetObjectMother;
import com.abeldevelop.petclinic.services.vets.repository.VetRepository;
import com.abeldevelop.petclinic.services.vets.util.CommonTest;

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
		MockHttpServletResponse mockHttpServletResponse = mvc.perform(
				get("/vets/{vetId}/specialties", vetEntity.getId())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn()
				.getResponse();
		
		mockHttpServletResponse.setCharacterEncoding("UTF-8");

		SpecialtyPaginationResponseResult result = convertJsonAsStringToObject(mockHttpServletResponse.getContentAsString(), SpecialtyPaginationResponseResult.class);
		
		//then
		assertEquals(1, result.getSpecialties().size());
		
	}
	
	@Test
	public void testfindAllEndpointVetNotFound() throws Exception {
		//given
		vetRepository.deleteAll();
		Integer vetId = 1;
		
		//when
		MockHttpServletResponse mockHttpServletResponse = mvc.perform(
				get("/vets/{vetId}/specialties", vetId)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andReturn()
				.getResponse();
		
		mockHttpServletResponse.setCharacterEncoding("UTF-8");

		ErrorResponseResource result = convertJsonAsStringToObject(mockHttpServletResponse.getContentAsString(), ErrorResponseResource.class);
		
		//then
		assertEquals("No exist vet with ID: '" + vetId + "'", result.getMessage());
		
	}
}
