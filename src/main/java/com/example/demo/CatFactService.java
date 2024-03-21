package com.example.demo;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CatFactService {
    private final RestTemplate restTemplate;

    public CatFactService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CatFact getFact() {
        return restTemplate.getForObject("/fact", CatFact.class);

    }
}
