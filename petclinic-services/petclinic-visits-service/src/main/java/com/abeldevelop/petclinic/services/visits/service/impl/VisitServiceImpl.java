package com.abeldevelop.petclinic.services.visits.service.impl;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abeldevelop.petclinic.services.visits.generated.entity.VisitEntity;
import com.abeldevelop.petclinic.services.visits.generated.resource.VisitPaginationResponseResource;
import com.abeldevelop.petclinic.services.visits.generated.resource.VisitRequestResource;
import com.abeldevelop.petclinic.services.visits.generated.resource.VisitResponseResource;
import com.abeldevelop.petclinic.services.visits.mapper.VisitMapper;
import com.abeldevelop.petclinic.services.visits.repository.VisitRepository;
import com.abeldevelop.petclinic.services.visits.service.VisitService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class VisitServiceImpl implements VisitService {
	
	private final VisitRepository visitRepository;
	private final VisitMapper visitMapper;
	
	@Override
	@Transactional
	public VisitResponseResource create(String identificationDocument, Integer petId, VisitRequestResource visitRequestResource) {
		validateExistCustomerAndPet(identificationDocument, petId);
		VisitEntity visitEntity = visitMapper.map(visitRequestResource);
		visitEntity.setCustomerIdentificationDocument(identificationDocument);
		visitEntity.setPetId(petId);
		return visitMapper.map(visitRepository.save(visitEntity));
	}

	@Override
	@Transactional(readOnly = true)
	public VisitPaginationResponseResource findAll(String identificationDocument, Integer petId) {
		validateExistCustomerAndPet(identificationDocument, petId);
		return VisitPaginationResponseResource.builder()
				.visits(visitRepository.findByPetId(petId).stream().map(visitMapper::map).collect(Collectors.toList()))
				.build();
	}

	private void validateExistCustomerAndPet(String identificationDocument, Integer petId) {
		//TODO Call to petclinic-customers-service to validate exist identificationDocument and petId
	}
	
}
