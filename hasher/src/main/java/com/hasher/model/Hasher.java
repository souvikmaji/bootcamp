package com.hasher.model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {
	private static final String ALGORITHM = "SHA-256";
	private String hash;
	private String token;

//	public Hasher(String token) {
//		this.token = token;
//	}

	public String computeHash() {
		try {

			// Static getInstance method is called with hashing SHA
			MessageDigest md = MessageDigest.getInstance(ALGORITHM);

			// digest() method called
			// to calculate message digest of an input
			// and return array of byte
			byte[] messageDigest = md.digest(this.token.getBytes());

			// Convert byte array into signum representation
			BigInteger no = new BigInteger(1, messageDigest);

			// Convert message digest into hex value
			String hashtext = no.toString(16);

			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}

			this.hash = hashtext;
			return this.hash;
		}

		// For specifying wrong message digest algorithms
		catch (NoSuchAlgorithmException e) {
			System.out.println("Exception thrown" + e + " for incorrect algorithm: " + ALGORITHM);

			return null;
		}
	}

	public String getHash() {
		return hash;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return String.format("{hash: %s}", this.getHash());
	}
}
