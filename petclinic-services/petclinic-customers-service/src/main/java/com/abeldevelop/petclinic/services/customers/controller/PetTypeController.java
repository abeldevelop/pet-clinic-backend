package com.abeldevelop.petclinic.services.customers.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.abeldevelop.petclinic.library.common.extend.CommonController;
import com.abeldevelop.petclinic.library.common.util.LoggerUtils;
import com.abeldevelop.petclinic.services.customers.generated.api.PetTypeApi;
import com.abeldevelop.petclinic.services.customers.generated.resource.pettype.PetTypePaginationResponseResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.pettype.PetTypeRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.pettype.PetTypeResponseResource;
import com.abeldevelop.petclinic.services.customers.service.PetTypeService;
import com.abeldevelop.petclinic.services.customers.validation.PetTypeValidator;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Timed("petclinic.petType")
@RequiredArgsConstructor
@RestController
public class PetTypeController extends CommonController implements PetTypeApi {

	private final PetTypeService petTypeService;
	private final PetTypeValidator petTypeValidator;
	
	@Override
	@ResponseStatus(HttpStatus.CREATED)
	public PetTypeResponseResource executeCreatePetType(PetTypeRequestResource petTypeRequestResource) {
		LoggerUtils.info(log, "PetTypeController.executeCreatePetType Data IN petTypeRequestResource: {}=> ", petTypeRequestResource);
		petTypeValidator.validate(petTypeRequestResource);
		return petTypeService.executeCreate(petTypeRequestResource);
	}

	@Override
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void executeUpdatePetType(Integer petTypeId, PetTypeRequestResource petTypeRequestResource) {
		LoggerUtils.info(log, "PetTypeController.executeUpdatePetType Data IN petTypeId: {}, petTypeRequestResource: {} => ", petTypeId, petTypeRequestResource);
		petTypeValidator.validate(petTypeRequestResource);
		petTypeService.executeUpdate(petTypeId, petTypeRequestResource);
	}

	@Override
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void executeDeletePetTypeById(Integer petTypeId) {
		LoggerUtils.info(log, "PetTypeController.executeDeletePetTypeById Data IN petTypeId: {} => ", petTypeId);
		petTypeService.executeDeleteById(petTypeId);
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	public PetTypeResponseResource executeFindPetTypeById(Integer petTypeId) {
		LoggerUtils.info(log, "PetTypeController.executeFindPetTypeById Data IN petTypeId: {} => ", petTypeId);
		return petTypeService.executeFindById(petTypeId);
	}
	
	@Override
	@ResponseStatus(HttpStatus.OK)
	public PetTypePaginationResponseResource executeFindAllPetTypes(Integer page, Integer size) {
		LoggerUtils.info(log, "PetTypeController.executeFindAllPetTypes Data IN page: {}, size: {} => ", page, size);
		paginationValidator.validate(page, size);
        return petTypeService.executeFindAll(page, size);
    }
	
}
