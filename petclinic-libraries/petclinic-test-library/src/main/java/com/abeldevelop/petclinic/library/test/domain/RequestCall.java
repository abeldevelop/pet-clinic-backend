package com.abeldevelop.petclinic.library.test.domain;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RequestCall {

	private String endpoint;
	
	@Singular
	private List<Object> pathParams;
	
	@Singular
	private Map<String, String> requestParams;
	
	private Object body;
}
