package com.abeldevelop.petclinic.library.common.mapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.abeldevelop.petclinic.library.common.resources.PaginationResponseResource;

@Component
public class PaginationMapper {

	public Pageable map(Integer page, Integer size) {
		return PageRequest.of(page, size);
	}
	
	public Pageable map(Integer page, Integer size, String order) {
		Integer pageAux = 0;
		Integer sizeAux = size;
		if(page != null) {
			pageAux = page -1;
		}
		if(size == null) {
			sizeAux = 10;	//TODO Move to property defult size of pagination
		}
		return PageRequest.of(pageAux, sizeAux, Sort.by(order).ascending());
	}
	
	public PaginationResponseResource map(Page<?> page) {
		return PaginationResponseResource.builder()
				.page(page.getNumber() + 1)
				.size(page.getSize())
				.numberOfElements(page.getNumberOfElements())
				.totalPages(page.getTotalPages())
				.totalElements(page.getTotalElements())
				.first(page.isFirst())
				.last(page.isLast())
				.build();
	}
}
