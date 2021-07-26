package com.vignesh.starter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vignesh.starter.beans.dto.Joke;
import com.vignesh.starter.domain.IStarterDomain;
import com.vignesh.starter.exception.BusinessException;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class StarterController {
	
	@Autowired
	IStarterDomain starterDomain;
	
	@GetMapping("/{id}")
	public boolean getEmployeeDetails(@PathVariable String id) {
		log.info("Received Id : {}", id);
		
		if(id.equalsIgnoreCase("123")) {
			throw new BusinessException("TEST_ERROR", HttpStatus.INTERNAL_SERVER_ERROR, "Internal Error");
		}
		
		return true;
	}
	
	@GetMapping("/joke")
	public Joke getRandomJoke() {
		return starterDomain.getRandomJoke();
	}

	
}
