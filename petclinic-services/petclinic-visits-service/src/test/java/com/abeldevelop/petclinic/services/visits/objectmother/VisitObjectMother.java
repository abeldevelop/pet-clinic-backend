package com.abeldevelop.petclinic.services.visits.objectmother;

import java.time.LocalDate;

import com.abeldevelop.petclinic.services.visits.generated.entity.VisitEntity;
import com.abeldevelop.petclinic.services.visits.generated.resource.VisitRequestResource;
import com.abeldevelop.petclinic.services.visits.generated.resource.VisitResponseResource;

public class VisitObjectMother {

	private VisitObjectMother() {
		
	}
	
	public static VisitEntity generateVisitEntity() {
	    return VisitEntity.builder()
	    		.id(1)
	    		.date(LocalDate.of(2020,04,19))
	    		.description("descriptionEntity")
	    		.petId(1)
	    		.build();
	}

	public static VisitRequestResource generateVisitRequestResource() {
		return VisitRequestResource.builder()
				.date(LocalDate.of(2020,04,18))
	    		.description("descriptionRequestResource")
				.build();
	}
	
	public static VisitResponseResource generateVisitResponseResource() {
		return VisitResponseResource.builder()
				.id(2)
				.date(LocalDate.of(2020,04,17))
	    		.description("descriptionResponseResource")
	    		.petId(2)
				.build();
	}
}
