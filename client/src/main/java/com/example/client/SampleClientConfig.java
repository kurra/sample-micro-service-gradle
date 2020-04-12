package com.example.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan
@AllArgsConstructor
public class SampleClientConfig {

  private ObjectMapper objectMapper;

  @Bean
  @Qualifier("sampleRestTemplate")
  public RestTemplate sampleRestTemplate() {
    return new RestTemplateBuilder()
        .additionalMessageConverters(createMappingJackson2HttpMessageConverter())
        .build();
  }

  private MappingJackson2HttpMessageConverter createMappingJackson2HttpMessageConverter() {
    final MappingJackson2HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter();
    jacksonConverter.setObjectMapper(objectMapper);
    return jacksonConverter;
  }
}
