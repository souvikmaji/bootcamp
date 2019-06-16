package com.hasher.model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Hasher {
	private static final Logger logger = LogManager.getLogger(Hasher.class);
	private static final String ALGORITHM = "SHA-256";

	private String hash;
	private String token;

	public Hasher(String token) {
		this.token = token;
	}

	public String getHash() {
		if (this.hash == null) {
			return this.computeHash();
		} else {
			return this.hash;
		}
	}

	public String computeHash() {
		if (this.hash == null) {
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
				logger.error("Exception thrown" + e + " for incorrect algorithm: " + ALGORITHM);
				return null;
			}
		} else {
			return this.hash;
		}
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
