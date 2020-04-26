package com.abeldevelop.petclinic.services.customers.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
import com.abeldevelop.petclinic.services.customers.generated.entity.CustomerEntity;
import com.abeldevelop.petclinic.services.customers.generated.entity.PetEntity;
import com.abeldevelop.petclinic.services.customers.generated.entity.PetTypeEntity;
import com.abeldevelop.petclinic.services.customers.generated.resource.pet.PetPaginationResponseResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.pet.PetRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.pet.PetResponseResource;
import com.abeldevelop.petclinic.services.customers.objectmother.CustomerObjectMother;
import com.abeldevelop.petclinic.services.customers.objectmother.PetObjectMother;
import com.abeldevelop.petclinic.services.customers.objectmother.PetTypeObjectMother;
import com.abeldevelop.petclinic.services.customers.repository.springdata.CustomerSpringDataRepository;
import com.abeldevelop.petclinic.services.customers.repository.springdata.PetSpringDataRepository;
import com.abeldevelop.petclinic.services.customers.repository.springdata.PetTypeSpringDataRepository;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PetControllerTest extends CommonTest {

	@Autowired
	private CustomerSpringDataRepository customerSpringDataRepository;
	
	@Autowired
	private PetSpringDataRepository petSpringDataRepository;
	
	@Autowired
	private PetTypeSpringDataRepository petTypeSpringDataRepository;
	
	@Test
	public void testCreatePetEndpoint() throws Exception {
		//given
		customerSpringDataRepository.deleteAll();
		petTypeSpringDataRepository.deleteAll();
		CustomerEntity customerEntity = customerSpringDataRepository.save(CustomerObjectMother.generateCustomerEntity());
		PetTypeEntity petTypeEntity = petTypeSpringDataRepository.save(PetTypeObjectMother.generatePetTypeEntity());
		PetRequestResource petRequestResource = PetObjectMother.generatePetRequestResource();
		petRequestResource.setTypeId(petTypeEntity.getId());
		
		//when
		ResponseCall<PetResponseResource> response = makePostCall(RequestCall.builder()
				.endpoint("/customers/{identificationDocument}/pets")
				.pathParam(customerEntity.getIdentificationDocument())
				.body(petRequestResource)
				.build(), 
				PetResponseResource.class);
		
		//then
		assertEquals(HttpStatus.CREATED, response.getStatus());
		assertNotNull(response.getBody().getId());
		assertEquals(petRequestResource.getName(), response.getBody().getName());
		assertEquals(petRequestResource.getBirthDate(), response.getBody().getBirthDate());
		assertEquals(customerEntity.getId(), response.getBody().getCustomerId());
		assertEquals(petTypeEntity.getId(), response.getBody().getPetTypeId());
	}
	
	@Test
	public void testCreatePetEndpointPetTypeNotFound() throws Exception {
		//given
		customerSpringDataRepository.deleteAll();
		petTypeSpringDataRepository.deleteAll();
		CustomerEntity customerEntity = customerSpringDataRepository.save(CustomerObjectMother.generateCustomerEntity());
		PetTypeEntity petTypeEntity = PetTypeObjectMother.generatePetTypeEntity();
		PetRequestResource petRequestResource = PetObjectMother.generatePetRequestResource();
		petRequestResource.setTypeId(petTypeEntity.getId());
		
		//when
		ResponseCall<ErrorResponseResource> response = makePostCall(RequestCall.builder()
				.endpoint("/customers/{identificationDocument}/pets")
				.pathParam(customerEntity.getIdentificationDocument())
				.body(petRequestResource)
				.build(), 
				ErrorResponseResource.class);
		
		//then
		assertEquals(HttpStatus.NOT_FOUND, response.getStatus());
		assertEquals("Not found Pet Type with ID: '" + petTypeEntity.getId() + "'", response.getBody().getMessage());
	}
	
	@Test
	public void testUpdatePetEndpoint() throws Exception {
		//given
		customerSpringDataRepository.deleteAll();
		petTypeSpringDataRepository.deleteAll();
		
		CustomerEntity customerEntity = customerSpringDataRepository.save(CustomerObjectMother.generateCustomerEntity());
		PetTypeEntity petTypeEntity = petTypeSpringDataRepository.save(PetTypeObjectMother.generatePetTypeEntity());
		PetEntity petEntity = PetObjectMother.generatePetEntity();
		petEntity.setCustomer(customerEntity);
		petEntity.setType(petTypeEntity);
		petEntity = petSpringDataRepository.save(petEntity);
		PetRequestResource petRequestResource = PetObjectMother.generatePetRequestResourceFromPetEntity(petEntity);
		petRequestResource.setTypeId(petTypeEntity.getId());
		
		//when
		ResponseCall<Void> response = makePutCall(RequestCall.builder()
				.endpoint("/customers/{identificationDocument}/pets/{petId}")
				.pathParam(customerEntity.getIdentificationDocument()).pathParam(petEntity.getId())
				.body(petRequestResource)
				.build(), 
				Void.class);
		
		//then
		assertEquals(HttpStatus.NO_CONTENT, response.getStatus());
		PetEntity petEntityInDataBase = petSpringDataRepository.findById(petEntity.getId()).orElseThrow(() -> new Exception("Error in Test"));
		assertEquals(petEntity.getId(), petEntityInDataBase.getId());
		assertEquals(petEntity.getName() + "Updated", petEntityInDataBase.getName());
	}
	
	@Test
	public void testFindPetByIdEndpoint() throws Exception {
		//given
		customerSpringDataRepository.deleteAll();
		petTypeSpringDataRepository.deleteAll();
		CustomerEntity customerEntity = customerSpringDataRepository.save(CustomerObjectMother.generateCustomerEntity());
		PetTypeEntity petTypeEntity = petTypeSpringDataRepository.save(PetTypeObjectMother.generatePetTypeEntity());
		PetEntity petEntity = PetObjectMother.generatePetEntity();
		petEntity.setCustomer(customerEntity);
		petEntity.setType(petTypeEntity);
		petEntity = petSpringDataRepository.save(petEntity);
		
		//when
		ResponseCall<PetResponseResource> response = makeGetCall(RequestCall.builder()
				.endpoint("/customers/{identificationDocument}/pets/{petId}")
				.pathParam(customerEntity.getIdentificationDocument()).pathParam(petEntity.getId())
				.build(), 
				PetResponseResource.class);
		
		//then
		assertEquals(HttpStatus.OK, response.getStatus());
		assertEquals(petEntity.getId(), response.getBody().getId());
		assertEquals(petEntity.getName(), response.getBody().getName());
		assertEquals(petEntity.getBirthDate(), response.getBody().getBirthDate());
		assertEquals(customerEntity.getId(), response.getBody().getCustomerId());
		assertEquals(petEntity.getType().getId(), response.getBody().getPetTypeId());
	}
	
	@Test
	public void testFindPetByIdEndpointNotFound() throws Exception {
		//given
		customerSpringDataRepository.deleteAll();
		petTypeSpringDataRepository.deleteAll();
		CustomerEntity customerEntity = customerSpringDataRepository.save(CustomerObjectMother.generateCustomerEntity());
		
		//when
		ResponseCall<ErrorResponseResource> response = makeGetCall(RequestCall.builder()
				.endpoint("/customers/{identificationDocument}/pets/{petId}")
				.pathParam(customerEntity.getIdentificationDocument()).pathParam(1)
				.build(), 
				ErrorResponseResource.class);
		
		//then
		assertEquals(HttpStatus.NOT_FOUND, response.getStatus());
		assertEquals(true, response.getBody().getMessage().contains("No exist Pet with ID:"));
		assertEquals(true, response.getBody().getMessage().contains("for Customer with Identification Document:"));
	}
	
	@Test
	public void testFindAllPetsEndpoint() throws Exception {
		//given
		customerSpringDataRepository.deleteAll();
		petTypeSpringDataRepository.deleteAll();
		CustomerEntity customerEntity = customerSpringDataRepository.save(CustomerObjectMother.generateCustomerEntity());
		PetTypeEntity petTypeEntity = petTypeSpringDataRepository.save(PetTypeObjectMother.generatePetTypeEntity());
		PetEntity petEntity = PetObjectMother.generatePetEntity();
		petEntity.setCustomer(customerEntity);
		petEntity.setType(petTypeEntity);
		petEntity = petSpringDataRepository.save(petEntity);
		
		//when
		ResponseCall<PetPaginationResponseResource> response = makeGetCall(RequestCall.builder()
				.endpoint("/customers/{identificationDocument}/pets")
				.pathParam(customerEntity.getIdentificationDocument())
				.build(), 
				PetPaginationResponseResource.class);
		
		//then
		assertEquals(HttpStatus.OK, response.getStatus());
		assertEquals(1, response.getBody().getPets().size());
	}
}
