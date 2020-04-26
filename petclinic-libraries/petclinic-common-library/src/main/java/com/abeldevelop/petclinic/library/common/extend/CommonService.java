package com.abeldevelop.petclinic.library.common.extend;

import org.springframework.beans.factory.annotation.Autowired;

import com.abeldevelop.petclinic.library.common.mapper.PaginationMapper;

public abstract class CommonService {

	@Autowired
	protected PaginationMapper paginationMapper;
	
}
