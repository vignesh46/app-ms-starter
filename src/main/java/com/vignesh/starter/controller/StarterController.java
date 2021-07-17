package com.vignesh.starter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vignesh.starter.exception.BusinessException;

@RestController
public class StarterController {
	
	private static final Logger LOG = LoggerFactory.getLogger(StarterController.class);
	
	@GetMapping("/{id}")
	public boolean getEmployeeDetails(@PathVariable String id) {
		LOG.info("Received Id : {}", id);
		
		if(id.equalsIgnoreCase("123")) {
			throw new BusinessException("TEST_ERROR", HttpStatus.INTERNAL_SERVER_ERROR, "Internal Error");
		}
		
		return true;
	}

	
}
