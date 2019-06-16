package com.hasher.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hasher.model.HasherRequest;

@RestController
public class HasherController {

	@PostMapping(value = "/hasher", consumes = "application/json", produces = "application/json")
	public HasherRequest hash(@RequestBody HasherRequest hasher) {
		return hasher;
	}

}