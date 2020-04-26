package com.abeldevelop.petclinic.services.customers.validation;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.abeldevelop.petclinic.library.common.exception.BadRequestException;
import com.abeldevelop.petclinic.services.customers.generated.resource.customer.CustomerCreateRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.customer.CustomerUpdateRequestResource;
import com.abeldevelop.petclinic.services.customers.objectmother.CustomerObjectMother;

public class CustomerValidatorTest {

	private CustomerValidator customerValidator;
	
	@BeforeEach
	public void setUp() {
		customerValidator = new CustomerValidator();
	}
	
	@Test
	public void testValidateCustomerCreateRequestResourceOk() {
		customerValidator.validate(CustomerObjectMother.generateCustomerCreateRequestResource());
	}
	
	@Test
	public void testValidateCustomerCreateRequestResourceIdentificationDocumentNullOrEmpty() {
		CustomerCreateRequestResource customerCreateRequestResource = CustomerObjectMother.generateCustomerCreateRequestResource();
		customerCreateRequestResource.setIdentificationDocument(null);
		assertThrows(BadRequestException.class, () -> customerValidator.validate(customerCreateRequestResource));
	}
	
	@Test
	public void testValidateCustomerCreateRequestResourceFirstNameNullOrEmpty() {
		CustomerCreateRequestResource customerCreateRequestResource = CustomerObjectMother.generateCustomerCreateRequestResource();
		customerCreateRequestResource.setFirstName(null);
		assertThrows(BadRequestException.class, () -> customerValidator.validate(customerCreateRequestResource));
	}
	
	@Test
	public void testValidateCustomerUpdateRequestResourceOk() {
		customerValidator.validate(CustomerObjectMother.generateCustomerUpdateRequestResource());
	}
	
	@Test
	public void testValidateCustomerUpdateRequestResourceFirstNameNullOrEmpty() {
		CustomerUpdateRequestResource customerUpdateRequestResource = CustomerObjectMother.generateCustomerUpdateRequestResource();
		customerUpdateRequestResource.setFirstName(null);
		assertThrows(BadRequestException.class, () -> customerValidator.validate(customerUpdateRequestResource));
	}
	
}
