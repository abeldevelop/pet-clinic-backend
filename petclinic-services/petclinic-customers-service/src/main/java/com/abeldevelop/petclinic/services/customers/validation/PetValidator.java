package com.abeldevelop.petclinic.services.customers.validation;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.abeldevelop.petclinic.library.common.exception.BadRequestException;
import com.abeldevelop.petclinic.services.customers.generated.resource.pet.PetRequestResource;

@Component
public class PetValidator {

	public void validate(PetRequestResource petRequestResource) {
		if(StringUtils.isEmpty(petRequestResource.getName())) {
			throw new BadRequestException(0, "The name is mandatory");
		}
		if(petRequestResource.getTypeId() == null) {
			throw new BadRequestException(0, "The typeId is mandatory");
		}
	}
}
