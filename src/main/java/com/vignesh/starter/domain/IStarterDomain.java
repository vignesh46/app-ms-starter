package com.vignesh.starter.domain;

import java.util.List;

import com.vignesh.starter.beans.dto.Joke;

public interface IStarterDomain {

	Joke getRandomJoke();

	List<Joke> getRandomTenJokes();

}
