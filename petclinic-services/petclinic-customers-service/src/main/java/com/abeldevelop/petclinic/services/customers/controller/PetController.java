package com.abeldevelop.petclinic.services.customers.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.abeldevelop.petclinic.library.common.util.LoggerUtils;
import com.abeldevelop.petclinic.services.customers.generated.api.PetApi;
import com.abeldevelop.petclinic.services.customers.generated.resource.PetPaginationResponseResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.PetRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.PetResponseResource;
import com.abeldevelop.petclinic.services.customers.service.PetService;
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
	@ResponseStatus(HttpStatus.CREATED)
	public PetResponseResource executeCreatePet(String identificationDocument, PetRequestResource petRequestResource) {
		LoggerUtils.info(log, "PetController.executeCreatePet Data IN => identificationDocument: {}, petRequestResource: {}", identificationDocument, petRequestResource);
		petValidator.validate(petRequestResource);
		return petService.executeCreate(identificationDocument, petRequestResource);
    }
	
	@Override
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void executeUpdatePet(String identificationDocument, Integer petId, PetRequestResource petRequestResource) {
		LoggerUtils.info(log, "PetController.executeUpdatePet Data IN => identificationDocument: {}, petId: {}, petRequestResource: {}", identificationDocument, petId, petRequestResource);
		petValidator.validate(petRequestResource);
		petService.executeUpdate(identificationDocument, petId, petRequestResource);
    }

	@Override
	public PetResponseResource executeFindPetById(String identificationDocument, Integer petId) {
    	LoggerUtils.info(log, "PetController.executeFindPetById Data IN => identificationDocument: {}, petId: {}", identificationDocument, petId);
        return petService.executeFindById(identificationDocument, petId);
    }

	@Override
	public PetPaginationResponseResource executeFindAllPets(String identificationDocument) {
    	LoggerUtils.info(log, "PetController.executeFindAllPets Data IN => identificationDocument: {}", identificationDocument);
        return petService.executeFindAll(identificationDocument);
    }
}
