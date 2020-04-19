package com.abeldevelop.petclinic.services.visits.generated.resource;

import java.util.List;

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
public class VisitPaginationResponseResource {

	private List<VisitResponseResource> visits;
	
}
