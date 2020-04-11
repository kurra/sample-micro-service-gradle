package com.example.service.sample.get;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.example.service.BaseSampleTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class GetSampleNotFound extends BaseSampleTest {

  @BeforeAll
  void setup() {
    response = getRequestSpecification()
        .pathParam("id", randomAlphanumeric(6))
        .get(SAMPLE_ENDPOINT_PATH + "/{id}")
        .then()
        .extract()
        .response();
  }

  @Test
  void should_return_http_status_404() {
    assertThat(response.statusCode()).isEqualTo(NOT_FOUND.value());
  }

}
