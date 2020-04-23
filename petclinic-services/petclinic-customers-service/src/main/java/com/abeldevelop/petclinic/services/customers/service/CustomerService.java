package com.abeldevelop.petclinic.services.customers.service;

import com.abeldevelop.petclinic.services.customers.generated.resource.CustomerCreateRequestResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.CustomerPaginationResponseResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.CustomerResponseResource;
import com.abeldevelop.petclinic.services.customers.generated.resource.CustomerUpdateRequestResource;

public interface CustomerService {

    public CustomerResponseResource executeCreate(CustomerCreateRequestResource customerCreateRequestResource);
	
    public void executeUpdate(String identificationDocument, CustomerUpdateRequestResource customerUpdateRequestResource);
	
    public void executeDelete(String identificationDocument);
	
    public CustomerResponseResource executeFindByIdentificationDocument(String identificationDocument);
    
    public CustomerPaginationResponseResource executeFindAll();
	
}
