package com.hasher.model;

public class HasherRequest {
	private Hasher hasher;

	public void setToken(String token) {
		hasher = new Hasher(token);
	}

	public String getHash() {
		return hasher.computeHash();
	}

	@Override
	public String toString() {
		return String.format("{hash: %s}", hasher.getHash());
	}
}
