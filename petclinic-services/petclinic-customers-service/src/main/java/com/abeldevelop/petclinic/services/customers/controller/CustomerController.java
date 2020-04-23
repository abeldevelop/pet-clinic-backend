package com.abeldevelop.petclinic.services.customers.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.abeldevelop.petclinic.library.common.util.LoggerUtils;
import com.abeldevelop.petclinic.services.customers.generated.api.CustomerApi;
import com.abeldevelop.petclinic.services.customers.generated.resource.CustomerCreateRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.CustomerPaginationResponseResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.CustomerResponseResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.CustomerUpdateRequestResource;
import com.abeldevelop.petclinic.services.customers.service.CustomerService;
import com.abeldevelop.petclinic.services.customers.validation.CustomerValidator;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Timed("petclinic.customer")
@RequiredArgsConstructor
@RestController
public class CustomerController implements CustomerApi {

	private final CustomerService ownerService;
	private final CustomerValidator customerValidator;

	@Override
	@ResponseStatus(HttpStatus.CREATED)
	public CustomerResponseResource executeCreateCustomer(CustomerCreateRequestResource customerCreateRequestResource) {
		LoggerUtils.info(log, "CustomerController.executeCreateCustomer Data IN => customerCreateRequestResource: {}", customerCreateRequestResource);
		customerValidator.validate(customerCreateRequestResource);
        return ownerService.executeCreate(customerCreateRequestResource);
    }
	
	@Override
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void executeUpdateCustomer(String identificationDocument, CustomerUpdateRequestResource customerUpdateRequestResource) {
		LoggerUtils.info(log, "CustomerController.executeUpdateCustomer Data IN => identificationDocument: {}, customerUpdateRequestResource: {}", identificationDocument, customerUpdateRequestResource);
		customerValidator.validate(customerUpdateRequestResource);
		ownerService.executeUpdate(identificationDocument, customerUpdateRequestResource);
	}
	
	@Override
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void executeDeleteCustomer(String identificationDocument) {
		LoggerUtils.info(log, "CustomerController.executeDeleteCustomer Data IN => identificationDocument: {}", identificationDocument);
		ownerService.executeDelete(identificationDocument);
	}

	@Override
	public CustomerResponseResource executeFindCustomerByIdentificationDocument(String identificationDocument) {
		LoggerUtils.info(log, "CustomerController.executeFindCustomerByIdentificationDocument Data IN => identificationDocument: {}", identificationDocument);
        return ownerService.executeFindByIdentificationDocument(identificationDocument);
    }

	@Override
	public CustomerPaginationResponseResource findAllCustomers() {
		LoggerUtils.info(log, "CustomerController.findAllCustomers Data IN => ");
        return ownerService.executeFindAll();
    }
}
