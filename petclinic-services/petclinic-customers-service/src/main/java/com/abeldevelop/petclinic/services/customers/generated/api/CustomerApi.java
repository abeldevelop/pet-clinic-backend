package com.abeldevelop.petclinic.services.customers.generated.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.abeldevelop.petclinic.services.customers.generated.resource.customer.CustomerCreateRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.customer.CustomerPaginationResponseResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.customer.CustomerResponseResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.customer.CustomerUpdateRequestResource;

@RequestMapping("/customers")
public interface CustomerApi {

	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
	public CustomerResponseResource executeCreateCustomer(@RequestBody CustomerCreateRequestResource customerCreateRequestResource);
	
	@PutMapping("/{identificationDocument}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
	public void executeUpdateCustomer(@PathVariable("identificationDocument") String identificationDocument, @RequestBody CustomerUpdateRequestResource customerUpdateRequestResource);
	
	@DeleteMapping("/{identificationDocument}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
	public void executeDeleteCustomer(@PathVariable("identificationDocument") String identificationDocument);
	
	@GetMapping(value = "/{identificationDocument}")
    public CustomerResponseResource executeFindCustomerByIdentificationDocument(@PathVariable("identificationDocument") String identificationDocument);
	
	@GetMapping
    public CustomerPaginationResponseResource findAllCustomers();
	
}
