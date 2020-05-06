package com.abeldevelop.petclinic.services.customers.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import com.abeldevelop.petclinic.library.common.component.MessageFormatter;
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
import com.abeldevelop.petclinic.services.customers.util.constants.PetConstants;

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
	
	@Autowired
	private MessageFormatter messageFormatter;
	
	@Test
	public void testCreatePetEndpoint() throws Exception {
		//given
		customerSpringDataRepository.deleteAll();
		petTypeSpringDataRepository.deleteAll();
		CustomerEntity customerEntity = customerSpringDataRepository.save(CustomerObjectMother.generateCustomerEntity());
		PetTypeEntity petTypeEntity = petTypeSpringDataRepository.save(PetTypeObjectMother.generatePetTypeEntity());
		PetRequestResource petRequestResource = PetObjectMother.generatePetRequestResource();
		petRequestResource.setPetTypeId(petTypeEntity.getId());
		
		//when
		ResponseCall<PetResponseResource> response = makePostCall(RequestCall.builder()
				.endpoint("/customers/{customerId}/pets")
				.pathParam(customerEntity.getId())
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
		petRequestResource.setPetTypeId(petTypeEntity.getId());
		
		//when
		ResponseCall<ErrorResponseResource> response = makePostCall(RequestCall.builder()
				.endpoint("/customers/{customerId}/pets")
				.pathParam(customerEntity.getId())
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
		petRequestResource.setPetTypeId(petTypeEntity.getId());
		
		//when
		ResponseCall<Void> response = makePutCall(RequestCall.builder()
				.endpoint("/customers/{customerId}/pets/{petId}")
				.pathParam(customerEntity.getId()).pathParam(petEntity.getId())
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
				.endpoint("/customers/{customerId}/pets/{petId}")
				.pathParam(customerEntity.getId()).pathParam(petEntity.getId())
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
		Integer petId = 1;
		
		//when
		ResponseCall<ErrorResponseResource> response = makeGetCall(RequestCall.builder()
				.endpoint("/customers/{customerId}/pets/{petId}")
				.pathParam(customerEntity.getId()).pathParam(petId)
				.build(), 
				ErrorResponseResource.class);
		
		//then
		String expectedErrorMessage = messageFormatter.format(PetConstants.PET_AND_CUSTOMER_NOT_FOUND_ERROR_MESSAGE, Arrays.asList(petId, customerEntity.getId()));
		assertEquals(HttpStatus.NOT_FOUND, response.getStatus());
		assertEquals(expectedErrorMessage, response.getBody().getMessage());
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
				.endpoint("/customers/{customerId}/pets")
				.pathParam(customerEntity.getId())
				.build(), 
				PetPaginationResponseResource.class);
		
		//then
		assertEquals(HttpStatus.OK, response.getStatus());
		assertEquals(1, response.getBody().getPets().size());
	}
}
