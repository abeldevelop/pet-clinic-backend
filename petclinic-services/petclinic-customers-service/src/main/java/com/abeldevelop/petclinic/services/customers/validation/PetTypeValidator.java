package com.abeldevelop.petclinic.services.customers.validation;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.abeldevelop.petclinic.library.common.exception.BadRequestException;
import com.abeldevelop.petclinic.services.customers.generated.resource.pettype.PetTypeRequestResource;
import com.abeldevelop.petclinic.services.customers.util.constants.PetTypeConstants;

@Component
public class PetTypeValidator {

	public void validate(PetTypeRequestResource petTypeRequestResource) {
		if(StringUtils.isEmpty(petTypeRequestResource.getName())) {
			throw new BadRequestException(PetTypeConstants.NAME_VALIDATION_NOT_NULL_ERROR_CODE, PetTypeConstants.NAME_VALIDATION_NOT_NULL_ERROR_MESSAGE);
		}
		if(petTypeRequestResource.getName().length() > PetTypeConstants.NAME_VALIDATION_MAX_LENGTH) {
			throw new BadRequestException(PetTypeConstants.NAME_VALIDATION_MAX_LENGTH_ERROR_CODE, PetTypeConstants.NAME_VALIDATION_MAX_LENGTH_ERROR_MESSAGE, PetTypeConstants.NAME_VALIDATION_MAX_LENGTH);
		}
	}
}
