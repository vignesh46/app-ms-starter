package com.vignesh.starter.beans.dto;

import lombok.Data;

@Data
public class Joke {

	private int id;
	private String type;
	private String setup;
	private String punchline;
	
}
