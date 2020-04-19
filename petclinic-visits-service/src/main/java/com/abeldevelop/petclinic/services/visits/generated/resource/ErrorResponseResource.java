package com.abeldevelop.petclinic.services.visits.generated.resource;

import java.time.LocalDateTime;

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
public class ErrorResponseResource {

	private LocalDateTime timestamp;
	private String id;
	private String code;
	private String message;
	
}
