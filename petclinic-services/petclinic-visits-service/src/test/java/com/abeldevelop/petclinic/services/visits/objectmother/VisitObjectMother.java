package com.abeldevelop.petclinic.services.visits.objectmother;

import java.time.LocalDate;
import java.time.LocalTime;

import com.abeldevelop.petclinic.services.visits.generated.entity.VisitEntity;
import com.abeldevelop.petclinic.services.visits.generated.resource.VisitRequestResource;
import com.abeldevelop.petclinic.services.visits.generated.resource.VisitResponseResource;

public class VisitObjectMother {

	private VisitObjectMother() {
		
	}
	
	public static VisitEntity generateVisitEntity() {
	    return VisitEntity.builder()
	    		.id(1)
	    	    .customerIdentificationDocument("1")
	    	    .petId(1)
	    	    .date(LocalDate.of(2020,04,19))
	    	    .time(LocalTime.of(14, 30))
	    		.description("descriptionEntity")
	    		.build();
	}

	public static VisitRequestResource generateVisitRequestResource() {
		return VisitRequestResource.builder()
				.vetId(1)
				.date(LocalDate.of(2020,04,18))
				.time(LocalTime.of(15, 00))
	    		.description("descriptionRequestResource")
				.build();
	}
	
	public static VisitResponseResource generateVisitResponseResource() {
		return VisitResponseResource.builder()
				.id(2)
				.date(LocalDate.of(2020,04,17))
	    		.description("descriptionResponseResource")
	    		.petId(2)
	    	    .customerIdentificationDocument("1")
	    	    .vetId(1)
	    	    .time(LocalTime.of(11, 25))
				.build();
	}
}
