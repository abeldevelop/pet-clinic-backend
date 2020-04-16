package com.abeldevelop.petclinic.services.customers.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.abeldevelop.petclinic.services.customers.generated.entity.OwnerEntity;
import com.abeldevelop.petclinic.services.customers.generated.entity.PetEntity;
import com.abeldevelop.petclinic.services.customers.generated.entity.PetTypeEntity;
import com.abeldevelop.petclinic.services.customers.generated.resource.PetRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.PetResponseResource;
import com.abeldevelop.petclinic.services.customers.objectmother.OwnerObjectMother;
import com.abeldevelop.petclinic.services.customers.objectmother.PetObjectMother;
import com.abeldevelop.petclinic.services.customers.objectmother.PetTypeObjectMother;
import com.abeldevelop.petclinic.services.customers.repository.OwnerRepository;
import com.abeldevelop.petclinic.services.customers.repository.PetRepository;
import com.abeldevelop.petclinic.services.customers.repository.PetTypeRepository;
import com.abeldevelop.petclinic.services.customers.util.CommonTest;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class PetControllerTest extends CommonTest {

	@Autowired
	private OwnerRepository ownerRepository;
	
	@Autowired
	private PetRepository petRepository;
	
	@Autowired
	private PetTypeRepository petTypeRepository;
	
	@Test
	public void testCreatePetEndpoint() throws Exception {
		//given
		ownerRepository.deleteAll();
		petTypeRepository.deleteAll();
		OwnerEntity ownerEntity = ownerRepository.save(OwnerObjectMother.generateOwnerEntity());
		PetTypeEntity petTypeEntity = petTypeRepository.save(PetTypeObjectMother.generatePetTypeEntity());
		PetRequestResource petRequestResource = PetObjectMother.generatePetRequestResource();
		petRequestResource.setTypeId(petTypeEntity.getId());
		
		//when
		MockHttpServletResponse mockHttpServletResponse = mvc.perform(
				post("/owners/{ownerId}/pets", ownerEntity.getId())
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.content(convertObjectToJsonAsString(petRequestResource))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andReturn()
				.getResponse();
		mockHttpServletResponse.setCharacterEncoding("UTF-8");
		
		PetResponseResource petResponseResource = convertJsonAsStringToObject(mockHttpServletResponse.getContentAsString(), PetResponseResource.class);
		
		//then
		assertNotNull(petResponseResource.getId());
		assertEquals(petRequestResource.getName(), petResponseResource.getName());
		assertEquals(petRequestResource.getBirthDate(), petResponseResource.getBirthDate());
		assertEquals(ownerEntity.getId(), petResponseResource.getOwnerId());
		assertEquals(petTypeEntity.getId(), petResponseResource.getPetTypeId());
	}
	
	@Test
	public void testUpdatePetEndpoint() throws Exception {
		//given
		ownerRepository.deleteAll();
		petTypeRepository.deleteAll();
		
		OwnerEntity ownerEntity = ownerRepository.save(OwnerObjectMother.generateOwnerEntity());
		PetTypeEntity petTypeEntity = petTypeRepository.save(PetTypeObjectMother.generatePetTypeEntity());
		PetEntity petEntity = PetObjectMother.generatePetEntity();
		petEntity.setOwner(ownerEntity);
		petEntity.setType(petTypeEntity);
		petEntity = petRepository.save(petEntity);
		PetRequestResource petRequestResource = PetObjectMother.generatePetRequestResourceFromPetEntity(petEntity);
		petRequestResource.setTypeId(petTypeEntity.getId());
		
		//when
		mvc.perform(
				put("/owners/{ownerId}/pets/{petId}", ownerEntity.getId(), petEntity.getId())
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.content(convertObjectToJsonAsString(petRequestResource))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNoContent());
		
		//then
		PetEntity petEntityInDataBase = petRepository.findById(petEntity.getId()).orElseThrow(() -> new Exception("Error in Test"));
		assertEquals(petEntity.getId(), petEntityInDataBase.getId());
		assertEquals(petEntity.getName() + "Updated", petEntityInDataBase.getName());
	}
	
	@Test
	public void testFindPetByIdEndpoint() throws Exception {
		//given
		ownerRepository.deleteAll();
		petTypeRepository.deleteAll();
		OwnerEntity ownerEntity = ownerRepository.save(OwnerObjectMother.generateOwnerEntity());
		PetTypeEntity petTypeEntity = petTypeRepository.save(PetTypeObjectMother.generatePetTypeEntity());
		PetEntity petEntity = PetObjectMother.generatePetEntity();
		petEntity.setOwner(ownerEntity);
		petEntity.setType(petTypeEntity);
		petEntity = petRepository.save(petEntity);
		
		//when
		MockHttpServletResponse mockHttpServletResponse = mvc.perform(
				get("/owners/{ownerId}/pets/{petId}", ownerEntity.getId(), petEntity.getId())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn()
				.getResponse();
		mockHttpServletResponse.setCharacterEncoding("UTF-8");
		
		PetResponseResource petResponseResource = convertJsonAsStringToObject(mockHttpServletResponse.getContentAsString(), PetResponseResource.class);
		
		//then
		assertEquals(petEntity.getId(), petResponseResource.getId());
		assertEquals(petEntity.getName(), petResponseResource.getName());
		assertEquals(petEntity.getBirthDate(), petResponseResource.getBirthDate());
		assertEquals(ownerEntity.getId(), petResponseResource.getOwnerId());
		assertEquals(petEntity.getType().getId(), petResponseResource.getPetTypeId());
	}
	
	@Test
	public void testFindAllPetsEndpoint() throws Exception {
		//given
		ownerRepository.deleteAll();
		petTypeRepository.deleteAll();
		OwnerEntity ownerEntity = ownerRepository.save(OwnerObjectMother.generateOwnerEntity());
		PetTypeEntity petTypeEntity = petTypeRepository.save(PetTypeObjectMother.generatePetTypeEntity());
		PetEntity petEntity = PetObjectMother.generatePetEntity();
		petEntity.setOwner(ownerEntity);
		petEntity.setType(petTypeEntity);
		petEntity = petRepository.save(petEntity);
		
		//when
		MockHttpServletResponse mockHttpServletResponse = mvc.perform(
				get("/owners/{ownerId}/pets", petEntity.getOwner().getId())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn()
				.getResponse();
		mockHttpServletResponse.setCharacterEncoding("UTF-8");
		
		List<PetResponseResource> result = Arrays.asList(convertJsonAsStringToObject(mockHttpServletResponse.getContentAsString(), PetResponseResource[].class));
		
		//then
		assertEquals(1, result.size());
	}
}
