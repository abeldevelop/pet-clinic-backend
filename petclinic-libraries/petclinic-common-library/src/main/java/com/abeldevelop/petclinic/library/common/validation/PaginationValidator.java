package com.abeldevelop.petclinic.library.common.validation;

import org.springframework.stereotype.Component;

import com.abeldevelop.petclinic.library.common.exception.BadRequestException;
import com.abeldevelop.petclinic.library.common.util.constants.PaginationConstants;

@Component
public class PaginationValidator {

	public void validate(Integer page, Integer size) {
		validatePage(page);
		validateSize(size);
	}
	
	private void validatePage(Integer page) {
		if(page != null && page < 0) {
			throw new BadRequestException(0, PaginationConstants.PAGE_NUMBER_ERROR_MESSAGE);
		}
	}
	
	private void validateSize(Integer size) {
		if(size != null && size < 1) {
			throw new BadRequestException(0, PaginationConstants.SIZE_NUMBER_ERROR_MESSAGE);
		}
	}
	
}
