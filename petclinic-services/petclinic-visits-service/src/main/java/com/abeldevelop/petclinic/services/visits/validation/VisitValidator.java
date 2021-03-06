package com.abeldevelop.petclinic.services.visits.validation;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.abeldevelop.petclinic.library.common.exception.BadRequestException;
import com.abeldevelop.petclinic.services.visits.generated.resource.VisitRequestResource;

@Component
public class VisitValidator {

	public void validate(VisitRequestResource visitRequestResource) {
		if(!StringUtils.isEmpty(visitRequestResource.getDescription()) && visitRequestResource.getDescription().length() > 8192) {
			throw new BadRequestException("The description cannot have more than 8192 characters");
		}
	}
	
}
