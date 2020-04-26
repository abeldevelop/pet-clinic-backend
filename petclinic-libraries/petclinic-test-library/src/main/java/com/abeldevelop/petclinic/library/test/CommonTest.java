package com.abeldevelop.petclinic.library.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.util.CollectionUtils;

import com.abeldevelop.petclinic.library.test.domain.RequestCall;
import com.abeldevelop.petclinic.library.test.domain.ResponseCall;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonTest {

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	protected MockMvc mvc;

	protected <T> T convertJsonAsStringToObject(String jsonAsString, Class<T> valueType) throws Exception {
		return objectMapper.readValue(jsonAsString, valueType);
	}
	
	protected String convertObjectToJsonAsString(Object content) throws Exception {
		return objectMapper.writeValueAsString(content);
	}
	
	public <T> ResponseCall<T> makePostCall(RequestCall makeCall, Class<T> responseClass) throws Exception {
		
		MockHttpServletRequestBuilder requestBuilder = post(makeCall.getEndpoint(), makeCall.getPathParams().toArray())
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.content(convertObjectToJsonAsString(makeCall.getBody()));
		
		return makeCall(requestBuilder, responseClass);
	}
	
	public <T> ResponseCall<T> makeGetCall(RequestCall makeCall, Class<T> responseClass) throws Exception {
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = get(makeCall.getEndpoint(), makeCall.getPathParams().toArray());
		if(!CollectionUtils.isEmpty(makeCall.getRequestParams())) {
			makeCall.getRequestParams().forEach((key, value) -> mockHttpServletRequestBuilder.param(key, value));
		}
		return makeCall(mockHttpServletRequestBuilder, responseClass);
	}
	
	public <T> ResponseCall<T> makePutCall(RequestCall makeCall, Class<T> responseClass) throws Exception {
		MockHttpServletRequestBuilder requestBuilder = put(makeCall.getEndpoint(), makeCall.getPathParams().toArray())
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.content(convertObjectToJsonAsString(makeCall.getBody()));
		
		return makeCall(requestBuilder, responseClass);
	}
	
	public <T> ResponseCall<T> makeDeleteCall(RequestCall makeCall, Class<T> responseClass) throws Exception {
		return makeCall(delete(makeCall.getEndpoint(), makeCall.getPathParams().toArray()), responseClass);
	}
	
	private <T> ResponseCall<T> makeCall(MockHttpServletRequestBuilder requestBuilder, Class<T> responseClass) throws Exception {
		MockHttpServletResponse response = mvc.perform(requestBuilder).andReturn().getResponse();
		response.setCharacterEncoding("UTF-8");
		
		if(Void.class.getCanonicalName().equals(responseClass.getCanonicalName())) {
			return new ResponseCall<>(HttpStatus.valueOf(response.getStatus()), null);
		}
		
		return new ResponseCall<>(HttpStatus.valueOf(response.getStatus()), convertJsonAsStringToObject(response.getContentAsString(), responseClass));
	}
}
