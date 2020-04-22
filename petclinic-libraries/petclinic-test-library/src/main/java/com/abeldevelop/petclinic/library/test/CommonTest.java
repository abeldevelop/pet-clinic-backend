package com.abeldevelop.petclinic.library.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonTest {

	@Autowired
	protected MockMvc mvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	protected <T> T convertJsonAsStringToObject(String jsonAsString, Class<T> valueType) throws Exception {
		return objectMapper.readValue(jsonAsString, valueType);
	}
	
	protected String convertObjectToJsonAsString(Object content) throws Exception {
		return objectMapper.writeValueAsString(content);
	}
}
