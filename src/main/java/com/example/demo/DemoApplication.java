package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@SpringBootApplication
public class DemoApplication {

  @Value("${base-url}")
  private String baseUrl;

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Bean
  public RestClient restClient(RestClient.Builder builder) {
    return builder
        .baseUrl(baseUrl)
        .build();
  }

  @Bean
  public RestClient.Builder restClientBuilder() {
    return RestClient.builder();
  }

}
