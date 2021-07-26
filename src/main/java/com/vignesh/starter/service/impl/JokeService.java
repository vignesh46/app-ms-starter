package com.vignesh.starter.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vignesh.starter.beans.HttpRequestEntity;
import com.vignesh.starter.beans.dto.Joke;
import com.vignesh.starter.client.RestClient;
import com.vignesh.starter.service.IJokeService;

@Service
public class JokeService implements IJokeService {
	
	@Autowired
	RestClient restClient;
	
	@Autowired
	@Qualifier("randomJokeTemplate")
	RestTemplate randomJokeTemplate;

	@Override
	public Joke getRandomJoke() {
		
		HttpRequestEntity httpRequestEntity = new HttpRequestEntity();
		httpRequestEntity.setRestTemplate(randomJokeTemplate);
		httpRequestEntity.setApiCode("RANDOM_JOKE");
		httpRequestEntity.setHttpMethod(HttpMethod.GET);
		httpRequestEntity.setUrl("https://official-joke-api.appspot.com/random_joke");
		httpRequestEntity.setUriVariables(new HashMap<>());
		
		return restClient.invokeRequest(httpRequestEntity, Joke.class).getBody();
	}
}
