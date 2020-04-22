package com.abeldevelop.petclinic.services.customers.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.abeldevelop.petclinic.library.common.util.LoggerUtils;
import com.abeldevelop.petclinic.services.customers.generated.api.OwnerApi;
import com.abeldevelop.petclinic.services.customers.generated.resource.OwnerPaginationResponseResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.OwnerRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.OwnerResponseResource;
import com.abeldevelop.petclinic.services.customers.service.OwnerService;
import com.abeldevelop.petclinic.services.customers.validation.OwnerValidator;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Timed("petclinic.owner")
@RequiredArgsConstructor
@RestController
public class OwnerController implements OwnerApi {

	private final OwnerService ownerService;
	private final OwnerValidator ownerValidator;

	@Override
	@ResponseStatus(HttpStatus.CREATED)
    public OwnerResponseResource create(OwnerRequestResource ownerRequestResource) {
		LoggerUtils.info(log, "OwnerController.create Data IN => ownerResource: {}", ownerRequestResource);
		ownerValidator.validate(ownerRequestResource);
        return ownerService.create(ownerRequestResource);
    }
	
	@Override
	@ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(Integer ownerId, OwnerRequestResource ownerRequestResource) {
		LoggerUtils.info(log, "OwnerController.update Data IN => ownerId: {}, ownerResource: {}", ownerId, ownerRequestResource);
		ownerValidator.validate(ownerRequestResource);
		ownerService.update(ownerId, ownerRequestResource);
	}
	
	@Override
	@ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Integer ownerId) {
		LoggerUtils.info(log, "OwnerController.delete Data IN => ownerId: {}", ownerId);
		ownerService.delete(ownerId);
	}

	@Override
    public OwnerResponseResource findById(Integer ownerId) {
		LoggerUtils.info(log, "OwnerController.findById Data IN => ownerId: {}", ownerId);
        return ownerService.findById(ownerId);
    }

	@Override
    public OwnerPaginationResponseResource findAll() {
		LoggerUtils.info(log, "OwnerController.findAll Data IN => ");
        return ownerService.findAll();
    }
}
