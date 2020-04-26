package com.abeldevelop.petclinic.services.customers.validation;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.abeldevelop.petclinic.library.common.exception.BadRequestException;
import com.abeldevelop.petclinic.services.customers.generated.resource.customer.CustomerCreateRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.customer.CustomerUpdateRequestResource;

@Component
public class CustomerValidator {

	public void validate(CustomerCreateRequestResource customerCreateRequestResource) {
		if(StringUtils.isEmpty(customerCreateRequestResource.getIdentificationDocument())) {
			throw new BadRequestException(0, "The identificationDocument is mandatory");
		}
		if(StringUtils.isEmpty(customerCreateRequestResource.getFirstName())) {
			throw new BadRequestException(0, "The firstName is mandatory");
		}
	}
	
	public void validate(CustomerUpdateRequestResource customerUpdateRequestResource) {
		if(StringUtils.isEmpty(customerUpdateRequestResource.getFirstName())) {
			throw new BadRequestException(0, "The firstName is mandatory");
		}
	}
	
}
