package com.vignesh.starter.client;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

import com.vignesh.starter.beans.HttpRequestEntity;

public interface IRestClient {

	<T> ResponseEntity<T> invokeRequest(HttpRequestEntity httpRequestEntity, Class<T> responseType);

	<T> ResponseEntity<List<T>> invokeRequestForList(HttpRequestEntity httpRequestEntity,
			ParameterizedTypeReference<List<T>> responseType);

}
