package com.stg.model;

import java.security.SecureRandom;

public class Token {
	private static final String symbols = "abcdefghijklmnopqrstuvwxyz0123456789";
	private final char[] buf;

	public Token(int length) {
		if (length < 1) {
			throw new IllegalArgumentException("length < 1: " + length);
		}

		SecureRandom random = new SecureRandom();
		this.buf = new char[length];

		for (int idx = 0; idx < buf.length; ++idx) {
			buf[idx] = symbols.charAt(random.nextInt(symbols.length()));
		}

	}

	public String getToken() {
		return new String(buf);
	}
}
