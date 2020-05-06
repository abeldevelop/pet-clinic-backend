package com.abeldevelop.petclinic.services.customers.validation;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.abeldevelop.petclinic.library.common.exception.BadRequestException;
import com.abeldevelop.petclinic.services.customers.generated.resource.pet.PetRequestResource;
import com.abeldevelop.petclinic.services.customers.util.constants.PetConstants;

@Component
public class PetValidator {

	public void validate(PetRequestResource petRequestResource) {
		validateName(petRequestResource.getName());
		validatePetType(petRequestResource.getPetTypeId());
	}
	
	private void validateName(String name) {
		if(StringUtils.isEmpty(name)) {
			throw new BadRequestException(PetConstants.NAME_VALIDATION_NOT_NULL_ERROR_CODE, PetConstants.NAME_VALIDATION_NOT_NULL_ERROR_MESSAGE);
		}
		if(name.length() > PetConstants.NAME_VALIDATION_MAX_LENGTH) {
			throw new BadRequestException(PetConstants.NAME_VALIDATION_MAX_LENGTH_ERROR_CODE, PetConstants.NAME_VALIDATION_MAX_LENGTH_ERROR_MESSAGE);
		}
	}
	
	private void validatePetType(Integer petTypeId) {
		if(petTypeId == null) {
			throw new BadRequestException(PetConstants.PET_TYPE_VALIDATION_NOT_NULL_ERROR_CODE, PetConstants.PET_TYPE_VALIDATION_NOT_NULL_ERROR_MESSAGE);
		}
	}
}
