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
	public void testValidateCustomerCreateRequestResourceIdentificationDocumentLength() {
		CustomerCreateRequestResource customerCreateRequestResource = CustomerObjectMother.generateCustomerCreateRequestResource();
		customerCreateRequestResource.setIdentificationDocument("01234567890123456789");
		assertThrows(BadRequestException.class, () -> customerValidator.validate(customerCreateRequestResource));
	}
	
	@Test
	public void testValidateCustomerCreateRequestResourceFirstNameNullOrEmpty() {
		CustomerCreateRequestResource customerCreateRequestResource = CustomerObjectMother.generateCustomerCreateRequestResource();
		customerCreateRequestResource.setFirstName(null);
		assertThrows(BadRequestException.class, () -> customerValidator.validate(customerCreateRequestResource));
	}
	
	@Test
	public void testValidateCustomerCreateRequestResourceFirstNameLength() {
		CustomerCreateRequestResource customerCreateRequestResource = CustomerObjectMother.generateCustomerCreateRequestResource();
		customerCreateRequestResource.setFirstName("0123456789012345678901234567890");
		assertThrows(BadRequestException.class, () -> customerValidator.validate(customerCreateRequestResource));
	}
	
	@Test
	public void testValidateCustomerCreateRequestResourceLastNameLength() {
		CustomerCreateRequestResource customerCreateRequestResource = CustomerObjectMother.generateCustomerCreateRequestResource();
		customerCreateRequestResource.setLastName("0123456789012345678901234567890");
		assertThrows(BadRequestException.class, () -> customerValidator.validate(customerCreateRequestResource));
	}
	
	@Test
	public void testValidateCustomerCreateRequestResourceAddressLength() {
		CustomerCreateRequestResource customerCreateRequestResource = CustomerObjectMother.generateCustomerCreateRequestResource();
		customerCreateRequestResource.setAddress(
				"0123456789001234567890012345678900123456789001234567890" +
				"0123456789001234567890012345678900123456789001234567890" + 
				"0123456789001234567890012345678900123456789001234567890" +
				"0123456789001234567890012345678900123456789001234567890" +
				"0123456789001234567890012345678900123456789001234567890" +
				"0"
				);
		assertThrows(BadRequestException.class, () -> customerValidator.validate(customerCreateRequestResource));
	}
	
	@Test
	public void testValidateCustomerCreateRequestResourceCityLength() {
		CustomerCreateRequestResource customerCreateRequestResource = CustomerObjectMother.generateCustomerCreateRequestResource();
		customerCreateRequestResource.setCity("012345678901234567890123456789012345678901234567890123456789");
		assertThrows(BadRequestException.class, () -> customerValidator.validate(customerCreateRequestResource));
	}
	
	@Test
	public void testValidateCustomerCreateRequestResourceTelephoneLength() {
		CustomerCreateRequestResource customerCreateRequestResource = CustomerObjectMother.generateCustomerCreateRequestResource();
		customerCreateRequestResource.setTelephone("012345678901234567890");
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
