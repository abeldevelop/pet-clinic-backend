package com.abeldevelop.petclinic.services.customers.validation;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.abeldevelop.petclinic.library.common.exception.BadRequestException;
import com.abeldevelop.petclinic.services.customers.generated.resource.pet.PetRequestResource;
import com.abeldevelop.petclinic.services.customers.objectmother.PetObjectMother;

public class PetValidatorTest {

	private PetValidator petValidator;
	
	@BeforeEach
	public void setUp() {
		petValidator = new PetValidator();
	}
	
	@Test
	public void validatePetRequestResourceOk() {
		petValidator.validate(PetObjectMother.generatePetRequestResource());
	}
	
	@Test
	public void validatePetRequestResourceNameNullOrEmpty() {
		PetRequestResource petRequestResource = PetObjectMother.generatePetRequestResource();
		petRequestResource.setName(null);
		assertThrows(BadRequestException.class, () -> petValidator.validate(petRequestResource));
	}
	
	@Test
	public void validatePetRequestResourceTypeIdNullOrEmpty() {
		PetRequestResource petRequestResource = PetObjectMother.generatePetRequestResource();
		petRequestResource.setPetTypeId(null);
		assertThrows(BadRequestException.class, () -> petValidator.validate(petRequestResource));
	}
	
}
