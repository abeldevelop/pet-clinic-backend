package com.abeldevelop.petclinic.services.customers.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.abeldevelop.petclinic.services.customers.generated.entity.CustomerEntity;

@Component
public class CustomerSpecification {

	public Specification<CustomerEntity> firstNameLike(String firstName) {
		return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get("firstName")), "%" + firstName.toUpperCase() + "%");
	}
	
	public Specification<CustomerEntity> identificationDocumentLike(String identificationDocument) {
		return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get("identificationDocument")), "%" + identificationDocument.toUpperCase() + "%");
	}
}
