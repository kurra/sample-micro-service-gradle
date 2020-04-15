package com.example.client.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SampleResponse {

  private final String id;
  private final String name;

}
