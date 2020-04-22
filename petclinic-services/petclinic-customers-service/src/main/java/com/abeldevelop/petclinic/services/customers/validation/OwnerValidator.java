package com.abeldevelop.petclinic.services.customers.validation;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.abeldevelop.petclinic.library.common.exception.RequestValidationException;
import com.abeldevelop.petclinic.services.customers.generated.resource.OwnerRequestResource;

@Component
public class OwnerValidator {

	public void validate(OwnerRequestResource ownerRequestResource) {
		if(ownerRequestResource == null) {
			throw new RequestValidationException("The body is mandatory");
		}
		if(StringUtils.isEmpty(ownerRequestResource.getFirstName())) {
			throw new RequestValidationException("The name is mandatory");
		}
	}
}
