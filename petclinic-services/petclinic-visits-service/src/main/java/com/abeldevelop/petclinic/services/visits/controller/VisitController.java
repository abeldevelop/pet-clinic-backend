package com.abeldevelop.petclinic.services.visits.controller;

import org.springframework.web.bind.annotation.RestController;

import com.abeldevelop.petclinic.library.common.util.LoggerUtils;
import com.abeldevelop.petclinic.services.visits.generated.api.VisitApi;
import com.abeldevelop.petclinic.services.visits.generated.resource.VisitPaginationResponseResource;
import com.abeldevelop.petclinic.services.visits.generated.resource.VisitRequestResource;
import com.abeldevelop.petclinic.services.visits.generated.resource.VisitResponseResource;
import com.abeldevelop.petclinic.services.visits.service.VisitService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class VisitController implements VisitApi {
	
	private final VisitService visitService;
	
	@Override
	public VisitResponseResource create(String identificationDocument, Integer petId, VisitRequestResource visitRequestResource) {
		LoggerUtils.info(log, "VisitController.create Data IN => identificationDocument: {}, petId: {}, visitRequestResource: {}", identificationDocument, petId, visitRequestResource);
		return visitService.create(identificationDocument, petId, visitRequestResource);
	}

	@Override
	public VisitPaginationResponseResource findAll(String identificationDocument, Integer petId) {
		LoggerUtils.info(log, "VisitController.findAll Data IN => identificationDocument: {}, petId: {}", identificationDocument, petId);
		return visitService.findAll(identificationDocument, petId);
	}

}
