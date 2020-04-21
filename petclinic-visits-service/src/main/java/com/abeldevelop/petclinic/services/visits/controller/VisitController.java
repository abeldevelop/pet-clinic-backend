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
	public VisitResponseResource create(Integer ownerId, Integer petId, VisitRequestResource visitRequestResource) {
		LoggerUtils.info(log, "VisitController.create Data IN => ownerId: {}, petId: {}, visitRequestResource: {}", ownerId, petId, visitRequestResource);
		return visitService.create(ownerId, petId, visitRequestResource);
	}

	@Override
	public VisitPaginationResponseResource findAll(Integer ownerId, Integer petId) {
		LoggerUtils.info(log, "VisitController.findAll Data IN => ownerId: {}, petId: {}", ownerId, petId);
		return visitService.findAll(ownerId, petId);
	}

}
