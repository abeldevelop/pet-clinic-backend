package com.abeldevelop.petclinic.services.customers.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.Optional;

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
import com.abeldevelop.petclinic.services.customers.generated.resource.customer.CustomerCreateRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.customer.CustomerPaginationResponseResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.customer.CustomerResponseResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.customer.CustomerUpdateRequestResource;
import com.abeldevelop.petclinic.services.customers.objectmother.CustomerObjectMother;
import com.abeldevelop.petclinic.services.customers.repository.springdata.CustomerSpringDataRepository;
import com.abeldevelop.petclinic.services.customers.util.constants.CustomerConstants;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CustomerControllerTest extends CommonTest {

	@Autowired
	private CustomerSpringDataRepository customerSpringDataRepository;
	
	@Autowired
	private MessageFormatter messageFormatter;
	
	@Test
	public void testCreateCustomerEndpoint() throws Exception {
		//given
		customerSpringDataRepository.deleteAll();
		CustomerCreateRequestResource customerCreateRequestResource = CustomerObjectMother.generateCustomerCreateRequestResource();
		
		//when
		ResponseCall<CustomerResponseResource> response = makePostCall(RequestCall.builder()
				.endpoint("/customers")
				.body(customerCreateRequestResource)
				.build(), 
				CustomerResponseResource.class);
		
		//then
		assertEquals(HttpStatus.CREATED, response.getStatus());
		assertNotNull(response.getBody().getId());
		assertEquals(customerCreateRequestResource.getIdentificationDocument(), response.getBody().getIdentificationDocument());
		assertEquals(customerCreateRequestResource.getFirstName(), response.getBody().getFirstName());
		assertEquals(customerCreateRequestResource.getLastName(), response.getBody().getLastName());
		assertEquals(customerCreateRequestResource.getAddress(), response.getBody().getAddress());
		assertEquals(customerCreateRequestResource.getCity(), response.getBody().getCity());
		assertEquals(customerCreateRequestResource.getTelephone(), response.getBody().getTelephone());
	}
	
	@Test
	public void testUpdateCustomerEndpoint() throws Exception {
		//given
		customerSpringDataRepository.deleteAll();
		CustomerEntity customerEntity = customerSpringDataRepository.save(CustomerObjectMother.generateCustomerEntity());
		CustomerUpdateRequestResource customerUpdateRequestResource = CustomerObjectMother.generateCustomerUpdateRequestResourceFromCustomerEntity(customerEntity);
		
		//when
		ResponseCall<Void> response = makePutCall(RequestCall.builder()
				.endpoint("/customers/{id}")
				.pathParam(customerEntity.getId())
				.body(customerUpdateRequestResource)
				.build(), 
				Void.class);
		
		//then
		assertEquals(HttpStatus.NO_CONTENT, response.getStatus());
		CustomerEntity customerEntityInDataBase = customerSpringDataRepository.findById(customerEntity.getId()).orElseThrow(() -> new Exception("Error in Test"));
		assertEquals(customerEntity.getId(), customerEntityInDataBase.getId());
		assertEquals(customerEntity.getFirstName() + "Modify", customerEntityInDataBase.getFirstName());
		assertEquals(customerEntity.getLastName() + "Modify", customerEntityInDataBase.getLastName());
		assertEquals(customerEntity.getAddress() + "Modify", customerEntityInDataBase.getAddress());
		assertEquals(customerEntity.getCity() + "Modify", customerEntityInDataBase.getCity());
		assertEquals(customerEntity.getTelephone(), customerEntityInDataBase.getTelephone());
	}
	
	@Test
	public void testDeleteCustomerEndpoint() throws Exception {
		//given
		customerSpringDataRepository.deleteAll();
		CustomerEntity customerEntity = customerSpringDataRepository.save(CustomerObjectMother.generateCustomerEntity());
		
		//when
		ResponseCall<Void> response = makeDeleteCall(RequestCall.builder()
				.endpoint("/customers/{id}")
				.pathParam(customerEntity.getId())
				.build(), 
				Void.class);
		
		//then
		Optional<CustomerEntity> customerEntityInDataBase = customerSpringDataRepository.findById(customerEntity.getId());
		assertEquals(HttpStatus.NO_CONTENT, response.getStatus());
		assertEquals(false, customerEntityInDataBase.isPresent());
	}
	
	@Test
	public void testFindCustomerByIdEndpoint() throws Exception {
		//given
		customerSpringDataRepository.deleteAll();
		CustomerEntity customerEntity = customerSpringDataRepository.save(CustomerObjectMother.generateCustomerEntity());
		
		//when
		ResponseCall<CustomerResponseResource> response = makeGetCall(RequestCall.builder()
				.endpoint("/customers/{id}")
				.pathParam(customerEntity.getId())
				.build(), 
				CustomerResponseResource.class);
		
		//then
		assertEquals(HttpStatus.OK, response.getStatus());
		assertEquals(customerEntity.getId(), response.getBody().getId());
		assertEquals(customerEntity.getFirstName(), response.getBody().getFirstName());
		assertEquals(customerEntity.getLastName(), response.getBody().getLastName());
		assertEquals(customerEntity.getAddress(), response.getBody().getAddress());
		assertEquals(customerEntity.getCity(), response.getBody().getCity());
		assertEquals(customerEntity.getTelephone(), response.getBody().getTelephone());
	}
	
	@Test
	public void testFindCustomerByIdEndpointNotFound() throws Exception {
		//given
		customerSpringDataRepository.deleteAll();
		Integer id = 1;
		
		//when
		ResponseCall<ErrorResponseResource> response = makeGetCall(RequestCall.builder()
				.endpoint("/customers/{id}")
				.pathParam(id)
				.build(), 
				ErrorResponseResource.class);
		
		//then
		String expectedErrorMessage = messageFormatter.format(CustomerConstants.CUSTOMER_WITH_IDENTIFICATION_DOCUMENT_NOT_FOUND_ERROR_MESSAGE, Arrays.asList(id));
		assertEquals(HttpStatus.NOT_FOUND, response.getStatus());
		assertEquals(CustomerConstants.CUSTOMER_WITH_IDENTIFICATION_DOCUMENT_NOT_FOUND_ERROR_CODE, response.getBody().getCode());
		assertEquals(expectedErrorMessage, response.getBody().getMessage());
	}
	
	@Test
	public void testFindAllCustomersEndpoint() throws Exception {
		//given
		customerSpringDataRepository.deleteAll();
		customerSpringDataRepository.save(CustomerObjectMother.generateCustomerEntity());
		
		//when
		ResponseCall<CustomerPaginationResponseResource> response = makeGetCall(RequestCall.builder()
				.endpoint("/customers")
				.build(), 
				CustomerPaginationResponseResource.class);
		
		//then
		assertEquals(HttpStatus.OK, response.getStatus());
		assertEquals(1, response.getBody().getCustomers().size());
	}
	
	@Test
	public void testFindAllCustomersEndpointWithPagination() throws Exception {
		//given
		customerSpringDataRepository.deleteAll();
		customerSpringDataRepository.save(CustomerObjectMother.generateCustomerEntity());
		
		//when
		ResponseCall<CustomerPaginationResponseResource> response = makeGetCall(RequestCall.builder()
				.endpoint("/customers")
				.requestParam("page", "1")
				.requestParam("size", "5")
				.requestParam("first-name", "a")
				.build(), 
				CustomerPaginationResponseResource.class);
		
		//then
		assertEquals(HttpStatus.OK, response.getStatus());
		assertEquals(1, response.getBody().getCustomers().size());
	}
	
}
