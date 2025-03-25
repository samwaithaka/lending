package com.lending.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.lending.dto.STClientDto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("score-client/api")
public class ScoreClientController {
	
	@Value("${app.constants.scoring-engine.client.url}")
	private String scoringClientApi;
	private RestTemplate restTemplate;
	
	@PostMapping("/client")
	public ResponseEntity<STClientDto> createClient(@RequestBody STClientDto stClientDto) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		try {
			HttpEntity<STClientDto> entity = new HttpEntity<>(stClientDto, headers);
			ResponseEntity<STClientDto> response = restTemplate.exchange(scoringClientApi, HttpMethod.POST,entity, STClientDto.class);
			return response;
		} catch(Exception e) {
			return ResponseEntity.ok(new STClientDto());
		}
	}
}
