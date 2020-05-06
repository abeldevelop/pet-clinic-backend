package com.abeldevelop.petclinic.services.customers.service;

import com.abeldevelop.petclinic.services.customers.generated.entity.CustomerEntity;

public interface CustomerDomainService {

    public CustomerEntity findById(Integer id);
	
}
