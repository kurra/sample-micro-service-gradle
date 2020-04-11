package com.example.service.spring;

import static org.springframework.boot.actuate.health.Health.up;

import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AppHealthIndicator implements HealthIndicator {

  private final InfoEndpoint infoEndpoint;

  @Override
  public Health health() {
    return up().withDetails(infoEndpoint.info()).build();
  }
}
