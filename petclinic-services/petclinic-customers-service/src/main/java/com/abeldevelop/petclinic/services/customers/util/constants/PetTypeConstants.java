package com.abeldevelop.petclinic.services.customers.util.constants;

public class PetTypeConstants {

	private PetTypeConstants() {
		
	}

	public static final int NAME_VALIDATION_NOT_NULL_ERROR_CODE = 0;
	public static final String NAME_VALIDATION_NOT_NULL_ERROR_MESSAGE = "The name is mandatory";
	
	public static final int NAME_VALIDATION_MAX_LENGTH = 80;
	public static final String NAME_VALIDATION_MAX_LENGTH_ERROR_MESSAGE = "The name cannot have more than {} characters";
	public static final int NAME_VALIDATION_MAX_LENGTH_ERROR_CODE = 0;
	
	
	public static final String PET_TYPE_WITH_ID_NOT_FOUND_ERROR_MESSAGE = "Not found Pet Type with ID: '{}'";
	public static final int PET_TYPE_WITH_ID_NOT_FOUND_ERROR_CODE = 0;
	
	public static final String PET_TYPE_WITH_NAME_ALREADY_EXIST_ERROR_MESSAGE = "Already exist a type of pet with the name: '{}'";
	public static final int PET_TYPE_WITH_NAME_ALREADY_EXIST_ERROR_CODE = 0;
}
