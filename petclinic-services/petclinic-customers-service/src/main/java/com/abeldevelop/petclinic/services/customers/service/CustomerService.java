package com.abeldevelop.petclinic.services.customers.service;

import com.abeldevelop.petclinic.services.customers.generated.resource.customer.CustomerCreateRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.customer.CustomerPaginationResponseResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.customer.CustomerResponseResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.customer.CustomerUpdateRequestResource;

public interface CustomerService {

    public CustomerResponseResource executeCreate(CustomerCreateRequestResource customerCreateRequestResource);
	
    public void executeUpdate(String identificationDocument, CustomerUpdateRequestResource customerUpdateRequestResource);
	
    public void executeDelete(String identificationDocument);
	
    public CustomerResponseResource executeFindByIdentificationDocument(String identificationDocument);
    
    public CustomerPaginationResponseResource executeFindAll();
	
}
