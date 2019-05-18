package com.stg.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stg.model.Token;

@RestController
@RequestMapping("/stg")
public class StgController {

	@GetMapping(value = "/tokens/{size}", produces = "application/json")
	public ResponseEntity<Token> token(@PathVariable int size) {
		Token token = new Token(size);

		return new ResponseEntity<Token>(token, HttpStatus.OK);
	}

}