package com.abeldevelop.petclinic.services.customers.generated.resource.pet;

import java.util.List;

import com.abeldevelop.petclinic.library.common.resources.PaginationResponseResource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PetPaginationResponseResource {

	private PaginationResponseResource pagination;
    private List<PetResponseResource> pets;
    
}
