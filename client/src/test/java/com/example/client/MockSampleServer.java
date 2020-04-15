package com.example.client;

import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.status;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static java.nio.charset.Charset.defaultCharset;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class MockSampleServer {

  @Value("classpath:sample/sampleRequest.json")
  Resource sampleRequest;

  @Value("classpath:sample/sampleResponse.json")
  Resource sampleResponse;

  public void getSample(final String id) throws IOException {
    stubFor(get(urlEqualTo("/sample-micro-service/sample/" + id))
        .willReturn(status(OK.value())
            .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
            .withBody(IOUtils.toString(sampleResponse.getInputStream(), defaultCharset())
                .replace("RANDOM_ID", id))));
  }

  public void deleteSample(final String id) throws IOException {
    stubFor(delete(urlEqualTo("/sample-micro-service/sample/" + id))
        .willReturn(status(NO_CONTENT.value())));
  }

  public void create(final String id) throws IOException {
    stubFor(post(urlEqualTo("/sample-micro-service/sample"))
        .withRequestBody(
            equalToJson(IOUtils.toString(sampleRequest.getInputStream(), defaultCharset())))
        .willReturn(status(CREATED.value())
            .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
            .withHeader("Location", "/sample/" + id)));
  }

}
