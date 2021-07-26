package com.vignesh.starter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Configuration
@Getter
public class StarterConfig {
	
	@Value("${resttemplateconfig.sample.poolMax}")
	private int sampleMaxConnection;
	
	@Value("${resttemplateconfig.sample.poolMaxPerRoute}")
	private int sampleMaxPerRoute;
	
	@Value("${resttemplateconfig.sample.httpConnTimeOutInSeconds}")
	private int sampleConnectionTimeOutInSeconds;
	
	@Value("${resttemplateconfig.sample.httpReadTimeOutInSeconds}")
	private int sampleReadTimeOutInSeconds;
	
	@Value("${resttemplateconfig.randomjoke.poolMax}")
	private int randomJokeMaxConnection;
	
	@Value("${resttemplateconfig.randomjoke.poolMaxPerRoute}")
	private int randomJokeMaxPerRoute;
	
	@Value("${resttemplateconfig.randomjoke.httpConnTimeOutInSeconds}")
	private int randomJokeConnectionTimeOutInSeconds;
	
	@Value("${resttemplateconfig.randomjoke.httpReadTimeOutInSeconds}")
	private int randomJokeReadTimeOutInSeconds;

}
