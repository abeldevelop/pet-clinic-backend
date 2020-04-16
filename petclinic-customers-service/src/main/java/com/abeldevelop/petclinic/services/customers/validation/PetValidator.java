package com.abeldevelop.petclinic.services.customers.validation;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.abeldevelop.petclinic.services.customers.exception.RequestValidationException;
import com.abeldevelop.petclinic.services.customers.generated.resource.PetRequestResource;

@Component
public class PetValidator {

	public void validate(PetRequestResource petRequestResource) {
		if(petRequestResource == null) {
			throw new RequestValidationException("The body is mandatory");
		}
		if(StringUtils.isEmpty(petRequestResource.getName())) {
			throw new RequestValidationException("The name is mandatory");
		}
		if(StringUtils.isEmpty(petRequestResource.getTypeId())) {
			throw new RequestValidationException("The typeId is mandatory");
		}
	}
}
