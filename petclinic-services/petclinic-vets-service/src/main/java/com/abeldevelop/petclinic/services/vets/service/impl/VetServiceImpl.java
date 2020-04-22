package com.abeldevelop.petclinic.services.vets.service.impl;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abeldevelop.petclinic.services.vets.generated.resource.VetPaginationResponseResult;
import com.abeldevelop.petclinic.services.vets.mapper.VetMapper;
import com.abeldevelop.petclinic.services.vets.repository.VetRepository;
import com.abeldevelop.petclinic.services.vets.service.VetService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class VetServiceImpl implements VetService {

	private final VetRepository vetRepository;
	private final VetMapper vetMapper;
	
	@Override
	@Transactional(readOnly = true)
	public VetPaginationResponseResult findAll() {
		return VetPaginationResponseResult.builder()
				.vets(
						vetRepository.findAll().stream()
						.map(vetMapper::map).collect(Collectors.toList()))
				.build();
	}

}
