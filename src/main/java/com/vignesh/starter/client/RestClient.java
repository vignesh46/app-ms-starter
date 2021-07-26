package com.vignesh.starter.client;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.vignesh.starter.beans.HttpRequestEntity;
import com.vignesh.starter.constant.ErrorCode;
import com.vignesh.starter.exception.BusinessException;

@Component
public class RestClient {

	private static final Logger LOG = LoggerFactory.getLogger(RestClient.class);

	public <T> ResponseEntity<T> invokeRequest(HttpRequestEntity httpRequestEntity, Class<T> responseType) {

		HttpEntity<Object> httpEntity = new HttpEntity<>(httpRequestEntity.getBody(),
				httpRequestEntity.getHttpHeaders());
		
		return performHttpRequest(httpRequestEntity.getRestTemplate(), responseType, httpRequestEntity.getHttpMethod(),
				httpEntity, httpRequestEntity.getUriVariables(), httpRequestEntity.getUrl(),
				httpRequestEntity.getApiCode());

	}

	private <T> ResponseEntity<T> performHttpRequest(RestTemplate restTemplate, Class<T> responseType,
			HttpMethod httpMethod, HttpEntity<Object> httpEntity, Map<String, String> uriVariables, String url,
			String apiCode) {

		ResponseEntity<T> responseEntity = null;

		try {
			responseEntity = restTemplate.exchange(url, httpMethod, httpEntity, responseType, uriVariables);
		} catch (HttpClientErrorException ex) {
			LOG.error("Exception During HTTP Call for API={} with status={} message={} URL{}", apiCode,
					ex.getRawStatusCode(), ex.getMessage(), url);
			String errorMessage = generateErrorMessage(apiCode);
			throw new BusinessException(ErrorCode.HTTP_CLIENT_ERROR.name(), HttpStatus.INTERNAL_SERVER_ERROR,
					errorMessage, ex);
		} catch (HttpServerErrorException ex) {
			LOG.error("Exception During HTTP Call for API={} with status={} message={} URL{}", apiCode,
					ex.getRawStatusCode(), ex.getMessage(), url);
			String errorMessage = generateErrorMessage(apiCode);
			throw new BusinessException(ErrorCode.HTTP_SERVER_ERROR.name(), HttpStatus.INTERNAL_SERVER_ERROR,
					errorMessage, ex);
		} catch (ResourceAccessException ex) {
			LOG.error("ResourceAccessException Exception During HTTP Call for API={} with message={} URL{}", apiCode,
					ex.getMessage(), url);
			String errorMessage = generateErrorMessage(apiCode);
			throw new BusinessException(ErrorCode.HTTP_TIMEOUT_ISSUE.name(), HttpStatus.INTERNAL_SERVER_ERROR,
					errorMessage, ex);
		}

		return responseEntity;
	}

	private String generateErrorMessage(String apiCode) {
		StringBuilder errorMessageBuilder = new StringBuilder("Problem Calling API=");
		return errorMessageBuilder.append(apiCode).append(" Internally").toString();
	}

}
