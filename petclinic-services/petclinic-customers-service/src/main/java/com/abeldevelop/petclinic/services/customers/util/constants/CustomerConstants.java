package com.abeldevelop.petclinic.services.customers.util.constants;

public class CustomerConstants {

	private CustomerConstants() {
		
	}

	public static final int IDENTIFICATION_DOCUMENT_VALIDATION_NOT_NULL_ERROR_CODE = 0;
	public static final String IDENTIFICATION_DOCUMENT_VALIDATION_NOT_NULL_ERROR_MESSAGE = "The Identification Document is mandatory";
	
	public static final int IDENTIFICATION_DOCUMENT_VALIDATION_MAX_LENGTH = 15;
	public static final int IDENTIFICATION_DOCUMENT_VALIDATION_MAX_LENGTH_ERROR_CODE = 0;
	public static final String IDENTIFICATION_DOCUMENT_VALIDATION_MAX_LENGTH_ERROR_MESSAGE = "The Identification Document cannot have more than {} characters";
	
	
	public static final int FIRST_NAME_VALIDATION_NOT_NULL_ERROR_CODE = 0;
	public static final String FIRST_NAME_VALIDATION_NOT_NULL_ERROR_MESSAGE = "The First Name is mandatory";
	
	public static final int FIRST_NAME_VALIDATION_MAX_LENGTH = 30;
	public static final int FIRST_NAME_VALIDATION_MAX_LENGTH_ERROR_CODE = 0;
	public static final String FIRST_NAME_VALIDATION_MAX_LENGTH_ERROR_MESSAGE = "The First Name cannot have more than {} characters";
	

	public static final int LAST_NAME_VALIDATION_MAX_LENGTH = 30;
	public static final int LAST_NAME_VALIDATION_MAX_LENGTH_ERROR_CODE = 0;
	public static final String LAST_NAME_VALIDATION_MAX_LENGTH_ERROR_MESSAGE = "The Last Name cannot have more than {} characters";
	
	public static final int ADDRESS_VALIDATION_MAX_LENGTH = 255;
	public static final int ADDRESS_VALIDATION_MAX_LENGTH_ERROR_CODE = 0;
	public static final String ADDRESS_VALIDATION_MAX_LENGTH_ERROR_MESSAGE = "The Address cannot have more than {} characters";

	public static final int CITY_VALIDATION_MAX_LENGTH = 50;
	public static final int CITY_VALIDATION_MAX_LENGTH_ERROR_CODE = 0;
	public static final String CITY_VALIDATION_MAX_LENGTH_ERROR_MESSAGE = "The City cannot have more than {} characters";
	
	public static final int TELEPHONE_VALIDATION_MAX_LENGTH = 20;
	public static final int TELEPHONE_VALIDATION_MAX_LENGTH_ERROR_CODE = 0;
	public static final String TELEPHONE_VALIDATION_MAX_LENGTH_ERROR_MESSAGE = "The Telephone cannot have more than {} characters";
	
	
	public static final int CUSTOMER_WITH_IDENTIFICATION_DOCUMENT_ALREADY_EXIST_ERROR_CODE = 0;
	public static final String CUSTOMER_WITH_IDENTIFICATION_DOCUMENT_ALREADY_EXIST_ERROR_MESSAGE = "Already exist a customer with the Identification Document: '{}'";

	public static final int CUSTOMER_WITH_IDENTIFICATION_DOCUMENT_NOT_FOUND_ERROR_CODE = 0;
	public static final String CUSTOMER_WITH_IDENTIFICATION_DOCUMENT_NOT_FOUND_ERROR_MESSAGE = "Not found customer with Identification Document: '{}'";
	
}
