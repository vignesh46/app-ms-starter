package com.vignesh.starter.beans;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HttpRequestEntity {
	
	private RestTemplate restTemplate;
	private HttpMethod httpMethod;
	private Map<String, String> uriVariables;
	private HttpHeaders httpHeaders;
	private String url;
	private Object body;
	private String apiCode;

}
