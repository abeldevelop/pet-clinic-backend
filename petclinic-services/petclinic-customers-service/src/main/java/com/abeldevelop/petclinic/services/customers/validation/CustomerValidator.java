package com.abeldevelop.petclinic.services.customers.validation;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.abeldevelop.petclinic.library.common.exception.BadRequestException;
import com.abeldevelop.petclinic.services.customers.generated.resource.customer.CustomerCreateRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.customer.CustomerUpdateRequestResource;
import com.abeldevelop.petclinic.services.customers.util.constants.CustomerConstants;

@Component
public class CustomerValidator {

	public void validate(CustomerCreateRequestResource customerCreateRequestResource) {
		validateIdentificationDocument(customerCreateRequestResource.getIdentificationDocument());
		validateFirstName(customerCreateRequestResource.getFirstName());
		validateLastName(customerCreateRequestResource.getLastName());
		validateAddress(customerCreateRequestResource.getAddress());
		validateCity(customerCreateRequestResource.getCity());
		validateTelephone(customerCreateRequestResource.getTelephone());
	}
	
	public void validate(CustomerUpdateRequestResource customerUpdateRequestResource) {
		validateFirstName(customerUpdateRequestResource.getFirstName());
		validateLastName(customerUpdateRequestResource.getLastName());
		validateAddress(customerUpdateRequestResource.getAddress());
		validateCity(customerUpdateRequestResource.getCity());
		validateTelephone(customerUpdateRequestResource.getTelephone());
	}
	
	
	private void validateIdentificationDocument(String identificationDocument) {
		if(StringUtils.isEmpty(identificationDocument)) {
			throw new BadRequestException(CustomerConstants.IDENTIFICATION_DOCUMENT_VALIDATION_NOT_NULL_ERROR_CODE, CustomerConstants.IDENTIFICATION_DOCUMENT_VALIDATION_NOT_NULL_ERROR_MESSAGE);
		}
		if(identificationDocument.length() > CustomerConstants.IDENTIFICATION_DOCUMENT_VALIDATION_MAX_LENGTH) {
			throw new BadRequestException(CustomerConstants.IDENTIFICATION_DOCUMENT_VALIDATION_MAX_LENGTH_ERROR_CODE, CustomerConstants.IDENTIFICATION_DOCUMENT_VALIDATION_MAX_LENGTH_ERROR_MESSAGE);
		}
	}
	
	private void validateFirstName(String firstName) {
		if(StringUtils.isEmpty(firstName)) {
			throw new BadRequestException(CustomerConstants.FIRST_NAME_VALIDATION_NOT_NULL_ERROR_CODE, CustomerConstants.FIRST_NAME_VALIDATION_NOT_NULL_ERROR_MESSAGE);
		}
		if(firstName.length() > CustomerConstants.FIRST_NAME_VALIDATION_MAX_LENGTH) {
			throw new BadRequestException(CustomerConstants.FIRST_NAME_VALIDATION_MAX_LENGTH_ERROR_CODE, CustomerConstants.FIRST_NAME_VALIDATION_MAX_LENGTH_ERROR_MESSAGE);
		}
	}
	
	private void validateLastName(String lastName) {
		if(!StringUtils.isEmpty(lastName) && lastName.length() > CustomerConstants.LAST_NAME_VALIDATION_MAX_LENGTH) {
			throw new BadRequestException(CustomerConstants.LAST_NAME_VALIDATION_MAX_LENGTH_ERROR_CODE, CustomerConstants.LAST_NAME_VALIDATION_MAX_LENGTH_ERROR_MESSAGE);
		}
	}
	
	private void validateAddress(String address) {
		if(!StringUtils.isEmpty(address) && address.length() > CustomerConstants.ADDRESS_VALIDATION_MAX_LENGTH) {
			throw new BadRequestException(CustomerConstants.ADDRESS_VALIDATION_MAX_LENGTH_ERROR_CODE, CustomerConstants.ADDRESS_VALIDATION_MAX_LENGTH_ERROR_MESSAGE);
		}
	}
	
	private void validateCity(String city) {
		if(!StringUtils.isEmpty(city) && city.length() > CustomerConstants.CITY_VALIDATION_MAX_LENGTH) {
			throw new BadRequestException(CustomerConstants.CITY_VALIDATION_MAX_LENGTH_ERROR_CODE, CustomerConstants.CITY_VALIDATION_MAX_LENGTH_ERROR_MESSAGE);
		}
	}
	
	private void validateTelephone(String telephone) {
		if(!StringUtils.isEmpty(telephone) && telephone.length() > CustomerConstants.TELEPHONE_VALIDATION_MAX_LENGTH) {
			throw new BadRequestException(CustomerConstants.TELEPHONE_VALIDATION_MAX_LENGTH_ERROR_CODE, CustomerConstants.TELEPHONE_VALIDATION_MAX_LENGTH_ERROR_MESSAGE);
		}
	}
}
