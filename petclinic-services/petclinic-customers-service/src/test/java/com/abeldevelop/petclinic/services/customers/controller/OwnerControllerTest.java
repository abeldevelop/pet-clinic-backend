package com.abeldevelop.petclinic.services.customers.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

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
import com.abeldevelop.petclinic.services.customers.generated.entity.OwnerEntity;
import com.abeldevelop.petclinic.services.customers.generated.resource.OwnerPaginationResponseResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.OwnerRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.OwnerResponseResource;
import com.abeldevelop.petclinic.services.customers.objectmother.OwnerObjectMother;
import com.abeldevelop.petclinic.services.customers.repository.OwnerRepository;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
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
		ResponseCall<OwnerResponseResource> response = makePostCall(RequestCall.builder()
				.endpoint("/owners")
				.body(ownerRequestResource)
				.build(), 
				OwnerResponseResource.class);
		
		//then
		assertEquals(HttpStatus.CREATED, response.getStatus());
		assertNotNull(response.getBody().getId());
		assertEquals(ownerRequestResource.getFirstName(), response.getBody().getFirstName());
		assertEquals(ownerRequestResource.getLastName(), response.getBody().getLastName());
		assertEquals(ownerRequestResource.getAddress(), response.getBody().getAddress());
		assertEquals(ownerRequestResource.getCity(), response.getBody().getCity());
		assertEquals(ownerRequestResource.getTelephone(), response.getBody().getTelephone());
	}
	
	@Test
	public void testUpdateOwnerEndpoint() throws Exception {
		//given
		ownerRepository.deleteAll();
		OwnerEntity ownerEntity = ownerRepository.save(OwnerObjectMother.generateOwnerEntity());
		OwnerRequestResource ownerRequestResource = OwnerObjectMother.generateOwnerRequestResourceFromOwnerEntity(ownerEntity);
		
		//when
		ResponseCall<Void> response = makePutCall(RequestCall.builder()
				.endpoint("/owners/{ownerId}")
				.pathParam(ownerEntity.getId())
				.body(ownerRequestResource)
				.build(), 
				Void.class);
		
		//then
		assertEquals(HttpStatus.NO_CONTENT, response.getStatus());
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
		ResponseCall<Void> response = makeDeleteCall(RequestCall.builder()
				.endpoint("/owners/{ownerId}")
				.pathParam(ownerEntity.getId())
				.build(), 
				Void.class);
		
		//then
		Optional<OwnerEntity> ownerEntityInDataBase = ownerRepository.findById(ownerEntity.getId());
		assertEquals(HttpStatus.NO_CONTENT, response.getStatus());
		assertEquals(false, ownerEntityInDataBase.isPresent());
	}
	
	@Test
	public void testFindOwnerByIdEndpoint() throws Exception {
		//given
		ownerRepository.deleteAll();
		OwnerEntity ownerEntity = ownerRepository.save(OwnerObjectMother.generateOwnerEntity());
		
		//when
		ResponseCall<OwnerResponseResource> response = makeGetCall(RequestCall.builder()
				.endpoint("/owners/{ownerId}")
				.pathParam(ownerEntity.getId())
				.build(), 
				OwnerResponseResource.class);
		
		//then
		assertEquals(HttpStatus.OK, response.getStatus());
		assertEquals(ownerEntity.getId(), response.getBody().getId());
		assertEquals(ownerEntity.getFirstName(), response.getBody().getFirstName());
		assertEquals(ownerEntity.getLastName(), response.getBody().getLastName());
		assertEquals(ownerEntity.getAddress(), response.getBody().getAddress());
		assertEquals(ownerEntity.getCity(), response.getBody().getCity());
		assertEquals(ownerEntity.getTelephone(), response.getBody().getTelephone());
	}
	
	@Test
	public void testFindOwnerByIdEndpointNotFound() throws Exception {
		//given
		ownerRepository.deleteAll();
		int ownerId = 1;
		
		//when
		ResponseCall<ErrorResponseResource> response = makeGetCall(RequestCall.builder()
				.endpoint("/owners/{ownerId}")
				.pathParam(ownerId)
				.build(), 
				ErrorResponseResource.class);
		
		//then
		assertEquals(HttpStatus.NOT_FOUND, response.getStatus());
		assertEquals("No exist Owner with ID: '" + ownerId + "'", response.getBody().getMessage());
	}
	
	@Test
	public void testFindAllOwnersEndpoint() throws Exception {
		//given
		ownerRepository.deleteAll();
		ownerRepository.save(OwnerObjectMother.generateOwnerEntity());
		
		//when
		ResponseCall<OwnerPaginationResponseResource> response = makeGetCall(RequestCall.builder()
				.endpoint("/owners")
				.build(), 
				OwnerPaginationResponseResource.class);
		
		//then
		assertEquals(HttpStatus.OK, response.getStatus());
		assertEquals(1, response.getBody().getOwners().size());
	}
	
	
}
