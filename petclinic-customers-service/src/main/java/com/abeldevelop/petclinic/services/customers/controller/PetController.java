package com.abeldevelop.petclinic.services.customers.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.abeldevelop.petclinic.services.customers.generated.api.PetApi;
import com.abeldevelop.petclinic.services.customers.generated.resource.PetRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.PetResponseResource;
import com.abeldevelop.petclinic.services.customers.service.PetService;
import com.abeldevelop.petclinic.services.customers.util.LoggerUtils;
import com.abeldevelop.petclinic.services.customers.validation.PetValidator;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Timed("petclinic.pet")
@RequiredArgsConstructor
@RestController
public class PetController implements PetApi {

	private final PetService petService;
	private final PetValidator petValidator;
	
	@Override
    public PetResponseResource create(Integer ownerId, PetRequestResource petRequestResource) {
		LoggerUtils.info(log, "PetController.create Data IN => ownerId: {}, petRequestResource: {}", ownerId, petRequestResource);
		petValidator.validate(petRequestResource);
		return petService.create(ownerId, petRequestResource);
    }
	
	@Override
    public void update(Integer ownerId, Integer petId, PetRequestResource petRequestResource) {
		LoggerUtils.info(log, "PetController.update Data IN => ownerId: {}, petId: {}, petRequestResource: {}", ownerId, petId, petRequestResource);
		petValidator.validate(petRequestResource);
		petService.update(ownerId, petId, petRequestResource);
    }

	@Override
    public PetResponseResource findById(Integer ownerId, Integer petId) {
    	LoggerUtils.info(log, "PetController.findById Data IN => ownerId: {}, petId: {}", ownerId, petId);
        return petService.findById(ownerId, petId);
    }

	@Override
    public List<PetResponseResource> findAll(Integer ownerId) {
    	LoggerUtils.info(log, "PetController.findAll Data IN => ownerId: {}", ownerId);
        return petService.findAll(ownerId);
    }
}
