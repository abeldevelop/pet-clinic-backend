package com.abeldevelop.petclinic.services.customers.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.abeldevelop.petclinic.library.common.resources.ErrorResponseResource;
import com.abeldevelop.petclinic.library.test.CommonTest;
import com.abeldevelop.petclinic.services.customers.generated.entity.OwnerEntity;
import com.abeldevelop.petclinic.services.customers.generated.resource.OwnerRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.OwnerResponseResource;
import com.abeldevelop.petclinic.services.customers.objectmother.OwnerObjectMother;
import com.abeldevelop.petclinic.services.customers.repository.OwnerRepository;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class OwnerControllerTest extends CommonTest {

	@Autowired
	private OwnerRepository ownerRepository;
	
	@Test
	public void testCreateOwnerEndpoint() throws Exception {
		//given
		ownerRepository.deleteAll();
		OwnerRequestResource ownerRequestResource = OwnerObjectMother.generateOwnerRequestResource();
		
		//when
		MockHttpServletResponse mockHttpServletResponse = mvc.perform(
				post("/owners")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.content(convertObjectToJsonAsString(ownerRequestResource))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andReturn()
				.getResponse();
		mockHttpServletResponse.setCharacterEncoding("UTF-8");
		
		OwnerResponseResource ownerResponseResource = convertJsonAsStringToObject(mockHttpServletResponse.getContentAsString(), OwnerResponseResource.class);
		
		//then
		assertNotNull(ownerResponseResource.getId());
		assertEquals(ownerRequestResource.getFirstName(), ownerResponseResource.getFirstName());
		assertEquals(ownerRequestResource.getLastName(), ownerResponseResource.getLastName());
		assertEquals(ownerRequestResource.getAddress(), ownerResponseResource.getAddress());
		assertEquals(ownerRequestResource.getCity(), ownerResponseResource.getCity());
		assertEquals(ownerRequestResource.getTelephone(), ownerResponseResource.getTelephone());
	}
	
	@Test
	public void testUpdateOwnerEndpoint() throws Exception {
		//given
		ownerRepository.deleteAll();
		OwnerEntity ownerEntity = ownerRepository.save(OwnerObjectMother.generateOwnerEntity());
		OwnerRequestResource ownerRequestResource = OwnerObjectMother.generateOwnerRequestResourceFromOwnerEntity(ownerEntity);
		
		//when
		mvc.perform(
				put("/owners/{ownerId}", ownerEntity.getId())
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.content(convertObjectToJsonAsString(ownerRequestResource))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNoContent());
		
		//then
		OwnerEntity ownerEntityInDataBase = ownerRepository.findById(ownerEntity.getId()).orElseThrow(() -> new Exception("Error in Test"));
		assertEquals(ownerEntity.getId(), ownerEntityInDataBase.getId());
		assertEquals(ownerEntity.getFirstName() + "Updated", ownerEntityInDataBase.getFirstName());
		assertEquals(ownerEntity.getLastName() + "Updated", ownerEntityInDataBase.getLastName());
		assertEquals(ownerEntity.getAddress() + "Updated", ownerEntityInDataBase.getAddress());
		assertEquals(ownerEntity.getCity() + "Updated", ownerEntityInDataBase.getCity());
		assertEquals(ownerEntity.getTelephone(), ownerEntityInDataBase.getTelephone());
	}
	
	@Test
	public void testDeleteOwnerEndpoint() throws Exception {
		//given
		ownerRepository.deleteAll();
		OwnerEntity ownerEntity = ownerRepository.save(OwnerObjectMother.generateOwnerEntity());
		
		//when
		mvc.perform(
	            delete("/owners/{ownerId}", ownerEntity.getId())
	            .accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNoContent());
		
		//then
		Optional<OwnerEntity> ownerEntityInDataBase = ownerRepository.findById(ownerEntity.getId());
		assertEquals(false, ownerEntityInDataBase.isPresent());
	}
	
	@Test
	public void testFindOwnerByIdEndpoint() throws Exception {
		//given
		ownerRepository.deleteAll();
		OwnerEntity ownerEntity = ownerRepository.save(OwnerObjectMother.generateOwnerEntity());
		
		//when
		MockHttpServletResponse mockHttpServletResponse = mvc.perform(
				get("/owners/{ownerId}", ownerEntity.getId())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn()
				.getResponse();
		mockHttpServletResponse.setCharacterEncoding("UTF-8");
		
		OwnerResponseResource ownerResponseResource = convertJsonAsStringToObject(mockHttpServletResponse.getContentAsString(), OwnerResponseResource.class);
		
		//then
		assertEquals(ownerEntity.getId(), ownerResponseResource.getId());
		assertEquals(ownerEntity.getFirstName(), ownerResponseResource.getFirstName());
		assertEquals(ownerEntity.getLastName(), ownerResponseResource.getLastName());
		assertEquals(ownerEntity.getAddress(), ownerResponseResource.getAddress());
		assertEquals(ownerEntity.getCity(), ownerResponseResource.getCity());
		assertEquals(ownerEntity.getTelephone(), ownerResponseResource.getTelephone());
	}
	
	@Test
	public void testFindOwnerByIdEndpointNotFound() throws Exception {
		//given
		ownerRepository.deleteAll();
		int ownerId = 1;
		
		//when
		MockHttpServletResponse mockHttpServletResponse = mvc.perform(
				get("/owners/{ownerId}", ownerId)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andReturn()
				.getResponse();
		mockHttpServletResponse.setCharacterEncoding("UTF-8");
		
		ErrorResponseResource errorResponseResource = convertJsonAsStringToObject(mockHttpServletResponse.getContentAsString(), ErrorResponseResource.class);
		
		//then
		assertEquals("No exist Owner with ID: '" + ownerId + "'", errorResponseResource.getMessage());
	}
	
	@Test
	public void testFindAllOwnersEndpoint() throws Exception {
		//given
		ownerRepository.deleteAll();
		ownerRepository.save(OwnerObjectMother.generateOwnerEntity());
		
		//when
		MockHttpServletResponse mockHttpServletResponse = mvc.perform(
				get("/owners")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn()
				.getResponse();
		mockHttpServletResponse.setCharacterEncoding("UTF-8");
		
		List<OwnerResponseResource> result = Arrays.asList(convertJsonAsStringToObject(mockHttpServletResponse.getContentAsString(), OwnerResponseResource[].class));
		
		//then
		assertEquals(1, result.size());
	}
	
	
}
