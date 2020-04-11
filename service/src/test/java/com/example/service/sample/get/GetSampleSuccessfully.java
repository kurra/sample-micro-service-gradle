package com.example.service.sample.get;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.OK;

import com.example.service.BaseSampleTest;
import com.example.service.sample.Sample;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class GetSampleSuccessfully extends BaseSampleTest {

  //Need to source from file in case big body
  private static final String SAMPLE_RESPONSE = "{id: '%s', name: '%s'}";

  private Sample persistedSample;

  @BeforeAll
  void setup() {
    persistedSample = sampleRepository.save(Sample.builder().name(randomAlphabetic(9)).build());
    response = getRequestSpecification()
        .pathParam("id", persistedSample.getId())
        .get(SAMPLE_ENDPOINT_PATH + "/{id}")
        .then()
        .extract()
        .response();
  }

  @Test
  void should_return_http_status_200() {
    assertThat(response.statusCode()).isEqualTo(OK.value());
  }

  @Test
  void should_return_sample_response() {
    assertThatJson(response.body().asString())
        .isEqualTo(String.format(SAMPLE_RESPONSE, persistedSample.getId(), persistedSample.getName()));
  }

}
