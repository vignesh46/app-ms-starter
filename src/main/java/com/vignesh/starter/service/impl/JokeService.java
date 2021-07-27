package com.vignesh.starter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vignesh.starter.beans.dto.Joke;
import com.vignesh.starter.client.IJokeClient;
import com.vignesh.starter.service.IJokeService;

@Service
public class JokeService implements IJokeService {

	@Autowired
	IJokeClient jokeClient;

	@Override
	public Joke getRandomJoke() {

		return jokeClient.getRandomJoke();

	}

	@Override
	public List<Joke> getRandomTenJokes() {

		return jokeClient.getRandomTenJokes();

	}

}
