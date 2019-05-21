package com.worker.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class WorkerJobService {
	public void executeWorkersJob() {
		try {
			System.out.println("Starting job");
			computeHash(getToken(10));
		} finally {
			System.out.println("Worker job has finished...");
		}
	}

	private String getToken(int size) throws RestClientException {
		MultiValueMap<String, String> headers = getHeader();
		Map<String, String> req_payload = new HashMap<String, String>();

		HttpEntity<?> request = new HttpEntity<>(req_payload, headers);
		String url = "http://localhost:8080/stg/tokens/" + size;

		// Create a new RestTemplate instance
		RestTemplate restTemplate = new RestTemplate();

		// Add the String message converter
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

		Map<String, String> response = restTemplate.getForObject(url, Map.class);

		System.out.println(response.get("token"));
		return response.get("token");
	}

	private void computeHash(String token) throws RestClientException {
		MultiValueMap<String, String> headers = getHeader();
		Map<String, String> req_payload = new HashMap<String, String>();
		req_payload.put("token", token);

		HttpEntity<?> request = new HttpEntity<>(req_payload, headers);
		String url = "http://localhost:8090/hasher";

		// Create a new RestTemplate instance
		RestTemplate restTemplate = new RestTemplate();

		// Add the String message converter
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

		ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

		System.out.println(response);
	}

	private MultiValueMap<String, String> getHeader() {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("Content-Type", "application/json");
		headers.setAll(map);
		return headers;
	}

}
