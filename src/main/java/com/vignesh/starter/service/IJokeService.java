package com.vignesh.starter.service;

import java.util.List;

import com.vignesh.starter.beans.dto.Joke;

public interface IJokeService {

	Joke getRandomJoke();

	List<Joke> getRandomTenJokes();

}
