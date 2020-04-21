package com.abeldevelop.petclinic.services.vets.controller;

import org.springframework.web.bind.annotation.RestController;

import com.abeldevelop.petclinic.library.common.util.LoggerUtils;
import com.abeldevelop.petclinic.services.vets.generated.api.SpecialtyApi;
import com.abeldevelop.petclinic.services.vets.generated.resource.SpecialtyPaginationResponseResult;
import com.abeldevelop.petclinic.services.vets.service.SpecialtyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class SpecialtyController implements SpecialtyApi {

	private final SpecialtyService specialtyService;
	
	@Override
    public SpecialtyPaginationResponseResult findAll(Integer vetId) {
		LoggerUtils.info(log, "SpecialtyController.findAll Data IN => vetId: {}", vetId);
		return specialtyService.findAll(vetId);
    }
	
}
