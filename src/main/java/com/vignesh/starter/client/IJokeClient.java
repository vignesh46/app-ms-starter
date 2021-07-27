package com.vignesh.starter.client;

import java.util.List;

import com.vignesh.starter.beans.dto.Joke;

public interface IJokeClient {

	List<Joke> getRandomTenJokes();

	Joke getRandomJoke();

}
