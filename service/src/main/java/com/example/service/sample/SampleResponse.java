package com.example.service.sample;

import lombok.Getter;

@Getter
public class SampleResponse {

  private final String id;
  private final String name;

  public SampleResponse(final Sample sample) {
    this.id = sample.getId();
    this.name = sample.getName();
  }
}
