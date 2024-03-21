package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatFactController {

  private final CatFactService catFactService;

  public CatFactController(CatFactService catFactService) {
    this.catFactService = catFactService;
  }

  @GetMapping(path = "/fact")
  public CatFact getFact() {
    return catFactService.getFact();
  }
}

