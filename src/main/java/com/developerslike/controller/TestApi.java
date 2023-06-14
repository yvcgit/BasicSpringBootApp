package com.developerslike.controller;

import java.io.Serializable;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
@RestController

public class TestApi implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@GetMapping(path="/getMessage", produces="application/json")
	public String getMessage() {
		return "welcome to React app";
	}

	@GetMapping(path="/")
	public String getTest() {
		return "welcome";
	}

}
