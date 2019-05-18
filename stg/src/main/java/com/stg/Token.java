package com.stg;

import java.security.SecureRandom;

public class Token {
	private static final String symbols = "abcdefghijklmnopqrstuvwxyz0123456789";
	private final char[] buf;
	private final SecureRandom random;

	public Token(int length) {
		if (length < 1) {
			throw new IllegalArgumentException("length < 1: " + length);
		}

		this.random = new SecureRandom();
		this.buf = new char[length];
	}

	public String generate() {
		for (int idx = 0; idx < buf.length; ++idx) {
			buf[idx] = symbols.charAt(random.nextInt(symbols.length()));
		}
			
		return new String(buf);
	}
}
