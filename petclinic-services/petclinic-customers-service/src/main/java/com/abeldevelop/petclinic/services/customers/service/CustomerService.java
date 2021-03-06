package com.abeldevelop.petclinic.services.customers.service;

import com.abeldevelop.petclinic.services.customers.generated.resource.customer.CustomerCreateRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.customer.CustomerPaginationResponseResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.customer.CustomerResponseResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.customer.CustomerUpdateRequestResource;

public interface CustomerService {

    public CustomerResponseResource executeCreate(CustomerCreateRequestResource customerCreateRequestResource);
	
    public void executeUpdate(Integer id, CustomerUpdateRequestResource customerUpdateRequestResource);
	
    public void executeDeleteById(Integer id);
	
    public CustomerResponseResource executeFindById(Integer id);
    
    public CustomerPaginationResponseResource executeFindAll(Integer page, Integer size, String identificationDocument, String firstName);
	
}
