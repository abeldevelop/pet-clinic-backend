package com.abeldevelop.petclinic.services.vets.controller;

import org.springframework.web.bind.annotation.RestController;

import com.abeldevelop.petclinic.library.common.util.LoggerUtils;
import com.abeldevelop.petclinic.services.vets.generated.api.VetApi;
import com.abeldevelop.petclinic.services.vets.generated.resource.VetPaginationResponseResult;
import com.abeldevelop.petclinic.services.vets.service.VetService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class VetController implements VetApi {

	private final VetService vetService;
	
	@Override
    public VetPaginationResponseResult findAll() {
		LoggerUtils.info(log, "VetController.findAll Data IN => ");
		return vetService.findAll();
    }
	
}
