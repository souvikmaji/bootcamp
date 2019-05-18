package com.stg;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/stg")
public class StgController {

	@GetMapping("/tokens/{size}")
	public String token(@PathVariable int size) {
		Token token = new Token(size);
		return token.generate();
	}

}