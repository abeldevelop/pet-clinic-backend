package com.abeldevelop.petclinic.services.customers.controller;

import org.springframework.web.bind.annotation.RestController;

import com.abeldevelop.petclinic.library.common.extend.CommonController;
import com.abeldevelop.petclinic.library.common.util.LoggerUtils;
import com.abeldevelop.petclinic.services.customers.generated.api.CustomerApi;
import com.abeldevelop.petclinic.services.customers.generated.resource.customer.CustomerCreateRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.customer.CustomerPaginationResponseResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.customer.CustomerResponseResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.customer.CustomerUpdateRequestResource;
import com.abeldevelop.petclinic.services.customers.service.CustomerService;
import com.abeldevelop.petclinic.services.customers.validation.CustomerValidator;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Timed("petclinic.customer")
@RequiredArgsConstructor
@RestController
public class CustomerController extends CommonController implements CustomerApi {

	private final CustomerService customerService;
	private final CustomerValidator customerValidator;

	@Override
	public CustomerResponseResource executeCreateCustomer(CustomerCreateRequestResource customerCreateRequestResource) {
		LoggerUtils.info(log, "CustomerController.executeCreateCustomer Data IN => customerCreateRequestResource: {}", customerCreateRequestResource);
		customerValidator.validate(customerCreateRequestResource);
        return customerService.executeCreate(customerCreateRequestResource);
    }
	
	@Override
	public void executeUpdateCustomer(Integer id, CustomerUpdateRequestResource customerUpdateRequestResource) {
		LoggerUtils.info(log, "CustomerController.executeUpdateCustomer Data IN => id: {}, customerUpdateRequestResource: {}", id, customerUpdateRequestResource);
		customerValidator.validate(customerUpdateRequestResource);
		customerService.executeUpdate(id, customerUpdateRequestResource);
	}
	
	@Override
	public void executeDeleteCustomerById(Integer id) {
		LoggerUtils.info(log, "CustomerController.executeDeleteCustomer Data IN => id: {}", id);
		customerService.executeDeleteById(id);
	}

	@Override
	public CustomerResponseResource executeFindCustomerById(Integer id) {
		LoggerUtils.info(log, "CustomerController.executeFindCustomerByIdentificationDocument Data IN => id: {}", id);
        return customerService.executeFindById(id);
    }

	@Override
	public CustomerPaginationResponseResource findAllCustomers(Integer page, Integer size, String identificationDocument, String firstName) {
		LoggerUtils.info(log, "CustomerController.findAllCustomers Data IN page: {}, size: {}, identificationDocument: {}, firstName: {} => ", page, size, identificationDocument, firstName);
		paginationValidator.validate(page, size);
		return customerService.executeFindAll(page, size, identificationDocument, firstName);
    }
}
