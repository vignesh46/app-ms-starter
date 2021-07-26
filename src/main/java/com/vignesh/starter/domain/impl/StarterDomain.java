package com.vignesh.starter.domain.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vignesh.starter.beans.dto.Joke;
import com.vignesh.starter.domain.IStarterDomain;
import com.vignesh.starter.service.IJokeService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class StarterDomain implements IStarterDomain {
	
	@Autowired
	IJokeService jokeService;

	@Override
	public Joke getRandomJoke() {
		return jokeService.getRandomJoke();
	}
}
