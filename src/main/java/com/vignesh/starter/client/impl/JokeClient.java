package com.vignesh.starter.client.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.vignesh.starter.beans.HttpRequestEntity;
import com.vignesh.starter.beans.dto.Joke;
import com.vignesh.starter.client.IJokeClient;
import com.vignesh.starter.client.IRestClient;

@Component
public class JokeClient implements IJokeClient {
	
	@Autowired
	IRestClient restClient;

	@Autowired
	@Qualifier("randomJokeTemplate")
	RestTemplate randomJokeTemplate;
	
	@Autowired
	@Qualifier("randomTenJokesTemplate")
	RestTemplate randomTenJokesTemplate;

	@Override
	public Joke getRandomJoke() {

		HttpRequestEntity httpRequestEntity = new HttpRequestEntity();
		httpRequestEntity.setRestTemplate(randomJokeTemplate);
		httpRequestEntity.setApiCode("RANDOM_JOKE");
		httpRequestEntity.setHttpMethod(HttpMethod.GET);
		httpRequestEntity.setUrl("https://official-joke-api.appspot.com/random_joke");
		httpRequestEntity.setUriVariables(new HashMap<>());
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		httpRequestEntity.setHttpHeaders(httpHeaders);

		return restClient.invokeRequest(httpRequestEntity, Joke.class).getBody();
	}

	@Override
	public List<Joke> getRandomTenJokes() {

		HttpRequestEntity httpRequestEntity = new HttpRequestEntity();
		httpRequestEntity.setRestTemplate(randomTenJokesTemplate);
		httpRequestEntity.setApiCode("RANDOM_TEN_JOKES");
		httpRequestEntity.setHttpMethod(HttpMethod.GET);
		httpRequestEntity.setUrl("https://official-joke-api.appspot.com/random_ten");
		httpRequestEntity.setUriVariables(new HashMap<>());
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		httpRequestEntity.setHttpHeaders(httpHeaders);

		return restClient.invokeRequestForList(httpRequestEntity, new ParameterizedTypeReference<List<Joke>>() {
		}).getBody();

	}

}
