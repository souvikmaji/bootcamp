package com.worker.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	private static final Logger logger = LogManager.getLogger(WorkerJobService.class);
	private RestTemplate restTemplate;

	private MultiValueMap<String, String> headers;

	public WorkerJobService() {
		restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

		headers = getHeader();
	}

	private MultiValueMap<String, String> getHeader() {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("Content-Type", "application/json");
		headers.setAll(map);
		return headers;
	}

	public void executeWorkersJob() {
		logger.trace("Starting job");

		computeHash(getToken(10));

		logger.trace("Worker job has finished...");
	}

	private String getToken(int size) throws RestClientException {

		String url = "http://localhost:8080/stg/tokens/" + size;

		Map<String, String> response = restTemplate.getForObject(url, Map.class);

		logger.trace("{}", response);
		return response.get("token");
	}

	private void computeHash(String token) throws RestClientException {
		Map<String, String> payload = new HashMap<String, String>();
		payload.put("token", token);

		HttpEntity<?> request = new HttpEntity<>(payload, headers);
		String url = "http://localhost:8090/hasher";

		ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

		logger.trace("response: {}", response);
	}

}
