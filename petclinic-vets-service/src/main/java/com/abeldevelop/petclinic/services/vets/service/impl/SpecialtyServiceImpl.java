package com.abeldevelop.petclinic.services.vets.service.impl;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.abeldevelop.petclinic.services.vets.exception.NotFoundException;
import com.abeldevelop.petclinic.services.vets.generated.resource.SpecialtyPaginationResponseResult;
import com.abeldevelop.petclinic.services.vets.mapper.SpecialtyMapper;
import com.abeldevelop.petclinic.services.vets.repository.VetRepository;
import com.abeldevelop.petclinic.services.vets.service.SpecialtyService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SpecialtyServiceImpl implements SpecialtyService {
	
	private final VetRepository vetRepository;
	private final SpecialtyMapper specialtyMapper;
	
	@Override
	public SpecialtyPaginationResponseResult findAll(Integer vetId) {
		return SpecialtyPaginationResponseResult.builder()
				.specialties(
						vetRepository.findById(vetId)
						.orElseThrow(() -> new NotFoundException("No exist vet with ID: '" + vetId + "'"))
						.getSpecialties().stream()
						.map(specialtyMapper::map).collect(Collectors.toList()))
				.build();
	}

}
