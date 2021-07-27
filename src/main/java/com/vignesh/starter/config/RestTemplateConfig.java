package com.vignesh.starter.config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

	@Autowired
	StarterConfig starterConfig;

	@Bean("sampleTemplate")
	RestTemplate sampleRestTemplate() {

		HttpComponentsClientHttpRequestFactory requestFactory = restFactoryInit(starterConfig.getSampleMaxConnection(),
				starterConfig.getSampleMaxPerRoute(), starterConfig.getSampleConnectionTimeOutInSeconds(),
				starterConfig.getSampleReadTimeOutInSeconds());

		RestTemplate restTemplate = new RestTemplate(requestFactory);

		return restTemplate;
	}

	@Bean("randomJokeTemplate")
	RestTemplate randomJokeTemplate() {

		HttpComponentsClientHttpRequestFactory requestFactory = restFactoryInit(
				starterConfig.getRandomJokeMaxConnection(), starterConfig.getRandomJokeMaxPerRoute(),
				starterConfig.getRandomJokeConnectionTimeOutInSeconds(),
				starterConfig.getRandomJokeReadTimeOutInSeconds());

		RestTemplate restTemplate = new RestTemplate(requestFactory);

		return restTemplate;
	}
	
	@Bean("randomTenJokesTemplate")
	RestTemplate randomTenJokesTemplate() {

		HttpComponentsClientHttpRequestFactory requestFactory = restFactoryInit(
				starterConfig.getRandomTenJokesMaxConnection(), starterConfig.getRandomTenJokesMaxPerRoute(),
				starterConfig.getRandomTenJokesConnectionTimeOutInSeconds(),
				starterConfig.getRandomTenJokesReadTimeOutInSeconds());

		RestTemplate restTemplate = new RestTemplate(requestFactory);

		return restTemplate;
	}

	protected HttpComponentsClientHttpRequestFactory restFactoryInit(int maxConnections, int maxPerRoute,
			int connectTimeOut, int readTimeout) {

		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(maxConnections);
		cm.setDefaultMaxPerRoute(maxPerRoute);

		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();

		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
		requestFactory.setConnectTimeout(connectTimeOut * 1000);
		requestFactory.setReadTimeout(readTimeout * 1000);

		return requestFactory;
	}

}
