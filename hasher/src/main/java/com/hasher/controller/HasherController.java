package com.hasher.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hasher.model.Hasher;

@RestController
public class HasherController {

	@PostMapping(value = "/hasher", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Hasher> hash(@RequestBody Hasher hasher) {
		
		hasher.computeHash();
		return new ResponseEntity<Hasher>(hasher, HttpStatus.OK);
	}

}