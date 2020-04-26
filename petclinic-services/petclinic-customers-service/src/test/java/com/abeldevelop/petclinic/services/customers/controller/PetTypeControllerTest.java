package com.abeldevelop.petclinic.services.customers.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import com.abeldevelop.petclinic.services.customers.generated.entity.PetTypeEntity;
import com.abeldevelop.petclinic.services.customers.generated.resource.pettype.PetTypePaginationResponseResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.pettype.PetTypeRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.pettype.PetTypeResponseResource;
import com.abeldevelop.petclinic.services.customers.mapper.PetTypeMapper;
import com.abeldevelop.petclinic.services.customers.objectmother.PetTypeObjectMother;
import com.abeldevelop.petclinic.services.customers.repository.springdata.CustomerSpringDataRepository;
import com.abeldevelop.petclinic.services.customers.repository.springdata.PetTypeSpringDataRepository;
import com.abeldevelop.petclinic.services.customers.util.constants.PetTypeConstants;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PetTypeControllerTest extends CommonTest {

	@Autowired
	private CustomerSpringDataRepository customerSpringDataRepository;
	
	@Autowired
	private PetTypeSpringDataRepository petTypeSpringDataRepository;
	
	@Autowired
	private PetTypeMapper petTypeMapper;
	
	@Autowired
	private MessageFormatter messageFormatter;
	
	@Test
	public void testCreatePetTypeEndpointOk() throws Exception {
		//given
		customerSpringDataRepository.deleteAll();
		petTypeSpringDataRepository.deleteAll();
		
		PetTypeRequestResource petTypeRequestResource = PetTypeObjectMother.generatePetTypeRequestResource();
		
		//when
		ResponseCall<PetTypeResponseResource> response = makePostCall(RequestCall.builder()
				.endpoint("/pet-types")
				.body(petTypeRequestResource)
				.build(), 
				PetTypeResponseResource.class);
		
		//then
		assertEquals(HttpStatus.CREATED, response.getStatus());
	}
	
	@Test
	public void testCreatePetTypeEndpointNameAlreadyExist() throws Exception {
		//given
		customerSpringDataRepository.deleteAll();
		petTypeSpringDataRepository.deleteAll();

		PetTypeRequestResource petTypeRequestResource = PetTypeObjectMother.generatePetTypeRequestResource();
		petTypeSpringDataRepository.save(petTypeMapper.map(petTypeRequestResource));
		
		//when
		ResponseCall<ErrorResponseResource> response = makePostCall(RequestCall.builder()
				.endpoint("/pet-types")
				.body(petTypeRequestResource)
				.build(), 
				ErrorResponseResource.class);
		
		//then
		String expectedErrorMessage = messageFormatter.format(PetTypeConstants.PET_TYPE_WITH_NAME_ALREADY_EXIST_ERROR_MESSAGE, Arrays.asList(petTypeRequestResource.getName()));
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatus());
		assertEquals(PetTypeConstants.PET_TYPE_WITH_NAME_ALREADY_EXIST_ERROR_CODE, response.getBody().getCode());
		assertEquals(expectedErrorMessage, response.getBody().getMessage());
	}
	
	@Test
	public void testCreatePetTypesEndpointNameIsMandatory() throws Exception {
		//given
		customerSpringDataRepository.deleteAll();
		petTypeSpringDataRepository.deleteAll();
		
		PetTypeRequestResource petTypeRequestResource = PetTypeObjectMother.generatePetTypeRequestResource();
		petTypeRequestResource.setName(null);
		
		//when
		ResponseCall<ErrorResponseResource> response = makePostCall(RequestCall.builder()
				.endpoint("/pet-types")
				.body(petTypeRequestResource)
				.build(), 
				ErrorResponseResource.class);
		
		//then
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatus());
		assertEquals(PetTypeConstants.NAME_VALIDATION_NOT_NULL_ERROR_CODE, response.getBody().getCode());
		assertEquals(PetTypeConstants.NAME_VALIDATION_NOT_NULL_ERROR_MESSAGE, response.getBody().getMessage());
	}
	
	@Test
	public void testCreatePetTypesEndpointNameLength() throws Exception {
		//given
		customerSpringDataRepository.deleteAll();
		petTypeSpringDataRepository.deleteAll();
		
		PetTypeRequestResource petTypeRequestResource = PetTypeObjectMother.generatePetTypeRequestResource();
		petTypeRequestResource.setName("012345678901234567890123456789012345678901234567890123456789012345678901234567890");
		
		//when
		ResponseCall<ErrorResponseResource> response = makePostCall(RequestCall.builder()
				.endpoint("/pet-types")
				.body(petTypeRequestResource)
				.build(), 
				ErrorResponseResource.class);
		
		//then
		String expectedErrorMessage = messageFormatter.format(PetTypeConstants.NAME_VALIDATION_MAX_LENGTH_ERROR_MESSAGE, Arrays.asList(PetTypeConstants.NAME_VALIDATION_MAX_LENGTH));
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatus());
		assertEquals(PetTypeConstants.NAME_VALIDATION_MAX_LENGTH_ERROR_CODE, response.getBody().getCode());
		assertEquals(expectedErrorMessage, response.getBody().getMessage());
	}
	
	@Test
	public void testUpdatePetTypesEndpointOk() throws Exception {
		//given
		customerSpringDataRepository.deleteAll();
		petTypeSpringDataRepository.deleteAll();
		PetTypeEntity petTypeEntity = petTypeSpringDataRepository.save(PetTypeObjectMother.generatePetTypeEntity());
		PetTypeRequestResource petTypeRequestResource = PetTypeObjectMother.generatePetTypeRequestResource();
		
		//when
		ResponseCall<Void> response = makePutCall(RequestCall.builder()
				.endpoint("/pet-types/{petTypeId}")
				.pathParam(petTypeEntity.getId())
				.body(petTypeRequestResource)
				.build(), 
				Void.class);
		
		//then
		assertEquals(HttpStatus.NO_CONTENT, response.getStatus());
	}
	
	@Test
	public void testUpdatePetTypesEndpointSameName() throws Exception {
		//given
		customerSpringDataRepository.deleteAll();
		petTypeSpringDataRepository.deleteAll();
		PetTypeEntity petTypeEntity = petTypeSpringDataRepository.save(PetTypeObjectMother.generatePetTypeEntity());
		PetTypeRequestResource petTypeRequestResource = PetTypeObjectMother.generatePetTypeRequestResource();
		petTypeRequestResource.setName(petTypeEntity.getName());
		
		//when
		ResponseCall<Void> response = makePutCall(RequestCall.builder()
				.endpoint("/pet-types/{petTypeId}")
				.pathParam(petTypeEntity.getId())
				.body(petTypeRequestResource)
				.build(), 
				Void.class);
		
		//then
		assertEquals(HttpStatus.NO_CONTENT, response.getStatus());
	}
	
	@Test
	public void testDeletePetTypesEndpointOk() throws Exception {
		//given
		customerSpringDataRepository.deleteAll();
		petTypeSpringDataRepository.deleteAll();
		PetTypeEntity petTypeEntity = petTypeSpringDataRepository.save(PetTypeObjectMother.generatePetTypeEntity());
		
		//when
		ResponseCall<Void> response = makeDeleteCall(RequestCall.builder()
				.endpoint("/pet-types/{petTypeId}")
				.pathParam(petTypeEntity.getId())
				.build(), 
				Void.class);
		
		//then
		assertEquals(HttpStatus.NO_CONTENT, response.getStatus());
	}
	
	@Test
	public void testFindPetTypeByIdEndpointOk() throws Exception {
		//given
		customerSpringDataRepository.deleteAll();
		petTypeSpringDataRepository.deleteAll();
		PetTypeEntity petTypeEntity = petTypeSpringDataRepository.save(PetTypeObjectMother.generatePetTypeEntity());
		
		//when
		ResponseCall<PetTypeResponseResource> response = makeGetCall(RequestCall.builder()
				.endpoint("/pet-types/{petTypeId}")
				.pathParam(petTypeEntity.getId())
				.build(), 
				PetTypeResponseResource.class);
		
		//then
		assertEquals(HttpStatus.OK, response.getStatus());
	}
	
	@Test
	public void testFindPetTypeByIdEndpointNotFound() throws Exception {
		//given
		customerSpringDataRepository.deleteAll();
		petTypeSpringDataRepository.deleteAll();
		Integer petTypeId = 1;
		
		//when
		ResponseCall<ErrorResponseResource> response = makeGetCall(RequestCall.builder()
				.endpoint("/pet-types/{petTypeId}")
				.pathParam(petTypeId)
				.build(), 
				ErrorResponseResource.class);
		
		//then
		String expectedErrorMessage = messageFormatter.format(PetTypeConstants.PET_TYPE_WITH_ID_NOT_FOUND_ERROR_MESSAGE, Arrays.asList(petTypeId));
		assertEquals(HttpStatus.NOT_FOUND, response.getStatus());
		assertEquals(PetTypeConstants.PET_TYPE_WITH_ID_NOT_FOUND_ERROR_CODE, response.getBody().getCode());
		assertEquals(expectedErrorMessage, response.getBody().getMessage());
	}
	
	@Test
	public void testFindAllPetTypesEndpointOk() throws Exception {
		//given
		customerSpringDataRepository.deleteAll();
		petTypeSpringDataRepository.deleteAll();
		petTypeSpringDataRepository.save(PetTypeObjectMother.generatePetTypeEntity());
		
		//when
		ResponseCall<PetTypePaginationResponseResource> response = makeGetCall(RequestCall.builder()
				.endpoint("/pet-types")
				.build(), 
				PetTypePaginationResponseResource.class);
		
		//then
		assertEquals(HttpStatus.OK, response.getStatus());
		assertEquals(1, response.getBody().getPetTypes().size());
	}
	
}
