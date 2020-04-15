package com.example.client;

import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

import com.example.client.model.SampleRequest;
import com.example.client.model.SampleResponse;
import java.net.URI;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SampleClient {

  private final SampleClientProperties sampleClientProperties;
  private final RestTemplate restTemplate;

  public SampleClient(final SampleClientProperties sampleClientProperties,
      @Qualifier("sampleRestTemplate") final RestTemplate restTemplate) {
    this.sampleClientProperties = sampleClientProperties;
    this.restTemplate = restTemplate;
  }

  public SampleResponse get(final String id) {
    return restTemplate
        .getForEntity(fromUriString(sampleClientProperties.getApiUrl()).path("/sample/").path(id).toUriString(),
            SampleResponse.class)
        .getBody();
  }

  public URI create(final SampleRequest sampleRequest) {
    return restTemplate
        .postForLocation(fromUriString(sampleClientProperties.getApiUrl()).path("/sample").toUriString(),
            sampleRequest);
  }

  public void delete(final String id) {
    restTemplate
        .delete(fromUriString(sampleClientProperties.getApiUrl()).path("/sample/").path(id).toUriString());
  }

}
