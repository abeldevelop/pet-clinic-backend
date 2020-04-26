package com.abeldevelop.petclinic.library.common.extend;

import org.springframework.beans.factory.annotation.Autowired;

import com.abeldevelop.petclinic.library.common.validation.PaginationValidator;

public abstract class CommonController {

	@Autowired
	protected PaginationValidator paginationValidator;
	
}
