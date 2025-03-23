package com.lending.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.lending.exceptions.ScoringEngineException;
import com.lending.dto.CustomerScoreDto;
import com.lending.dto.STClientDto;
import com.lending.enums.Format;
import com.lending.http.HttpClient;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class ScoringEngineService {

	@Value("${app.constants.scoring-test.client.url}")
	private String scoringTestClientUrl;
	@Value("${app.constants.scoring-engine.initiate-url}")
	private String scoringEngineInitiateUrl;
	@Value("${app.constants.scoring-engine.query-url}")
	private String scoringEngineQueryUrl;
	@Value("${app.constants.scoring-engine.token}")
	private String scoringEngineToken;
	private HttpClient httpClient;
	private RestTemplate restTemplate;

	public STClientDto createClient(String url, String name) {
		STClientDto client = new STClientDto();
		client.setName("Tran API");
		client.setUrl("https://");
		client.setUsername("");
		client.setPassword("password");
		try {
			ObjectMapper mapper = new ObjectMapper();
			String payload = mapper.writeValueAsString(client);
			String response = httpClient.sendPostRequest(scoringTestClientUrl, payload, Format.JSON, null);
			//System.out.println(response);
			client = mapper.readValue(response, STClientDto.class);
			return client;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	public String queryScore(String customerNumber) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("client-token", scoringEngineToken);
		ResponseEntity<String> response
		= restTemplate.getForEntity(scoringEngineInitiateUrl + "/" + customerNumber, String.class);
		return response.getBody();
	}

	/**
	 * 
	 * @param customerNumber
	 * @return
	 * 
	 * @TODO implement caching so we store token and avoid fetch it during retries
	 */
	@Retryable(value = ScoringEngineException.class, maxAttempts = 3)
	public CustomerScoreDto getScore(String customerNumber) {
		try {
			String token = queryScore(customerNumber);
			ResponseEntity<CustomerScoreDto> response
			= restTemplate.getForEntity(scoringEngineQueryUrl + "/" + token, CustomerScoreDto.class);
			return response.getBody();
		} catch(Exception e) {
			log.error("Scoring engine service error: {}", e.getMessage());
			throw new ScoringEngineException("Scoring engine service error");
		}
	}
}
