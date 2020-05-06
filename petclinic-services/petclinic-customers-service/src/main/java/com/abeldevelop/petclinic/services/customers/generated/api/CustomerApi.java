package com.abeldevelop.petclinic.services.customers.generated.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
	public void executeUpdateCustomer(@PathVariable("id") Integer id, @RequestBody CustomerUpdateRequestResource customerUpdateRequestResource);
	
	@DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
	public void executeDeleteCustomerById(@PathVariable("id") Integer id);
	
	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
    public CustomerResponseResource executeFindCustomerById(@PathVariable("id") Integer id);
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
    public CustomerPaginationResponseResource findAllCustomers(
    		@RequestParam(name = "page", required = false) Integer page, 
    		@RequestParam(name = "size", required = false) Integer size,
    		@RequestParam(name = "identification-document", required = false) String identificationDocument,
    		@RequestParam(name = "first-name", required = false) String firstName);
	
}
