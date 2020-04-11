package com.example.service.sample;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class Sample {

  @Setter
  private String id;
  private String name;
}
