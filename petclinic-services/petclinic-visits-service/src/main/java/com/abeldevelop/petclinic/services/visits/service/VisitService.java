package com.abeldevelop.petclinic.services.visits.service;

import com.abeldevelop.petclinic.services.visits.generated.resource.VisitPaginationResponseResource;
import com.abeldevelop.petclinic.services.visits.generated.resource.VisitRequestResource;
import com.abeldevelop.petclinic.services.visits.generated.resource.VisitResponseResource;

public interface VisitService {

	public VisitResponseResource create(String identificationDocument, Integer petId, VisitRequestResource visitRequestResource);
	
	public VisitPaginationResponseResource findAll(String identificationDocument, Integer petId);
	
}
