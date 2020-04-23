package com.abeldevelop.petclinic.services.visits.mapper;

import org.springframework.stereotype.Component;

import com.abeldevelop.petclinic.services.visits.generated.entity.VisitEntity;
import com.abeldevelop.petclinic.services.visits.generated.resource.VisitRequestResource;
import com.abeldevelop.petclinic.services.visits.generated.resource.VisitResponseResource;

@Component
public class VisitMapper {

	public VisitEntity map(VisitRequestResource visitRequestResource) {
		return VisitEntity.builder()
	    		.date(visitRequestResource.getDate())
	    		.time(visitRequestResource.getTime())
	    		.description(visitRequestResource.getDescription())
				.build();
	}
	
	public VisitResponseResource map(VisitEntity visitEntity) {
		return VisitResponseResource.builder()
				.id(visitEntity.getId())
				.customerIdentificationDocument(visitEntity.getCustomerIdentificationDocument())
	    		.petId(visitEntity.getPetId())
	    		.date(visitEntity.getDate())
	    		.time(visitEntity.getTime())
	    		.description(visitEntity.getDescription())
				.build();
	}
	
	public VisitEntity map(VisitEntity visitEntity, VisitRequestResource visitRequestResource) {
		visitEntity.setDescription(visitRequestResource.getDescription());
		return visitEntity;
	}
}
