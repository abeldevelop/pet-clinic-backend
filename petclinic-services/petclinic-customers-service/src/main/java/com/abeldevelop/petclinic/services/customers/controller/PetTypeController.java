package com.abeldevelop.petclinic.services.customers.controller;

import org.springframework.web.bind.annotation.RestController;

import com.abeldevelop.petclinic.library.common.util.LoggerUtils;
import com.abeldevelop.petclinic.services.customers.generated.api.PetTypeApi;
import com.abeldevelop.petclinic.services.customers.generated.resource.PetTypePaginationResponseResource;
import com.abeldevelop.petclinic.services.customers.service.PetTypeService;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Timed("petclinic.petType")
@RequiredArgsConstructor
@RestController
public class PetTypeController implements PetTypeApi {

	private final PetTypeService petTypeService;
	
	@Override
    public PetTypePaginationResponseResource findAll() {
		LoggerUtils.info(log, "PetTypeController.findAllPetTypes Data IN => ");
        return petTypeService.findAll();
    }
}
