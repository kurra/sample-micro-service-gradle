package com.example.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableSampleClient
public class TestApplication {

  public static void main(final String[] args) {
    SpringApplication.run(TestApplication.class, args);
  }

}
