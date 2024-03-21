package com.example.demo;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class CatFactService {
  private final RestClient restClient;

  public CatFactService(RestClient restClient) {
    this.restClient = restClient;
  }

  public CatFact getFact() {
    return restClient.get()
        .uri("/fact")
        .retrieve()
        .body(CatFact.class);
  }
}
