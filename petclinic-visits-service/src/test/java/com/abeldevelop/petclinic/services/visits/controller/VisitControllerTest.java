package com.abeldevelop.petclinic.services.visits.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.abeldevelop.petclinic.library.test.CommonTest;
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
		Integer ownerId = 1;
		Integer petId = 1;
		
		//when
		MockHttpServletResponse mockHttpServletResponse = mvc.perform(
				post("/owners/{ownerId}/pets/{petId}/visits", ownerId, petId)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.content(convertObjectToJsonAsString(visitRequestResource))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andReturn()
				.getResponse();
		mockHttpServletResponse.setCharacterEncoding("UTF-8");
		
		VisitResponseResource visitResponseResource = convertJsonAsStringToObject(mockHttpServletResponse.getContentAsString(), VisitResponseResource.class);
		
		//then
		assertNotNull(visitResponseResource.getId());
		assertEquals(visitRequestResource.getDate(), visitResponseResource.getDate());
		assertEquals(visitRequestResource.getDescription(), visitResponseResource.getDescription());
		assertEquals(petId, visitResponseResource.getPetId());
		
	}
	
	@Test
	public void testFindAllEndpoint() throws Exception {
		//given
		visitRepository.deleteAll();
		visitRepository.save(VisitObjectMother.generateVisitEntity());
		Integer ownerId = 1;
		Integer petId = 1;
		
		//when
		MockHttpServletResponse mockHttpServletResponse = mvc.perform(
				get("/owners/{ownerId}/pets/{petId}/visits", ownerId, petId)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn()
				.getResponse();
		
		mockHttpServletResponse.setCharacterEncoding("UTF-8");

		VisitPaginationResponseResource result = convertJsonAsStringToObject(mockHttpServletResponse.getContentAsString(), VisitPaginationResponseResource.class);
		
		//then
		assertEquals(1, result.getVisits().size());
	}
	
}
