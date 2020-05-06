package com.abeldevelop.petclinic.services.customers.controller;

import org.springframework.web.bind.annotation.RestController;

import com.abeldevelop.petclinic.library.common.util.LoggerUtils;
import com.abeldevelop.petclinic.services.customers.generated.api.PetApi;
import com.abeldevelop.petclinic.services.customers.generated.resource.pet.PetPaginationResponseResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.pet.PetRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.pet.PetResponseResource;
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
	public PetResponseResource executeCreatePet(Integer customerId, PetRequestResource petRequestResource) {
		LoggerUtils.info(log, "PetController.executeCreatePet Data IN => customerId: {}, petRequestResource: {}", customerId, petRequestResource);
		petValidator.validate(petRequestResource);
		return petService.executeCreate(customerId, petRequestResource);
    }
	
	@Override
	public void executeUpdatePet(Integer customerId, Integer petId, PetRequestResource petRequestResource) {
		LoggerUtils.info(log, "PetController.executeUpdatePet Data IN => customerId: {}, petId: {}, petRequestResource: {}", customerId, petId, petRequestResource);
		petValidator.validate(petRequestResource);
		petService.executeUpdate(customerId, petId, petRequestResource);
    }

	@Override
	public void executeDeletePetById(Integer customerId, Integer petId) {
		LoggerUtils.info(log, "PetController.executeDeletePetById Data IN => customerId: {}, petId: {}", customerId, petId);
		petService.executeDeleteById(customerId, petId);
	}
	
	@Override
	public PetResponseResource executeFindPetById(Integer customerId, Integer petId) {
		LoggerUtils.info(log, "PetController.executeFindPetById Data IN => customerId: {}, petId: {}", customerId, petId);
        return petService.executeFindByIdAndCustomer(customerId, petId);
    }

	@Override
	public PetPaginationResponseResource executeFindAllPets(Integer customerId, Integer page, Integer size, String name) {
		LoggerUtils.info(log, "PetController.executeFindAllPets Data IN => customerId: {}, page: {}, size: {}, name: {}", customerId, page, size, name);
        return petService.executeFindAll(customerId, page, size, name);
    }
}
