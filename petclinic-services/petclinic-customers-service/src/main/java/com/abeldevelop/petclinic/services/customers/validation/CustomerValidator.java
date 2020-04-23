package com.abeldevelop.petclinic.services.customers.validation;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.abeldevelop.petclinic.library.common.exception.RequestValidationException;
import com.abeldevelop.petclinic.services.customers.generated.resource.CustomerCreateRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.CustomerUpdateRequestResource;

@Component
public class CustomerValidator {

	public void validate(CustomerCreateRequestResource customerCreateRequestResource) {
		if(StringUtils.isEmpty(customerCreateRequestResource.getIdentificationDocument())) {
			throw new RequestValidationException("The identificationDocument is mandatory");
		}
		if(StringUtils.isEmpty(customerCreateRequestResource.getFirstName())) {
			throw new RequestValidationException("The firstName is mandatory");
		}
	}
	
	public void validate(CustomerUpdateRequestResource customerUpdateRequestResource) {
		if(StringUtils.isEmpty(customerUpdateRequestResource.getFirstName())) {
			throw new RequestValidationException("The firstName is mandatory");
		}
	}
	
}
