package com.example.service.swagger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.OK;

import com.example.service.BaseSampleTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CallingSwaggerEndpoint extends BaseSampleTest {

  @BeforeAll
  void setUp() {
    response = getRequestSpecification()
        .get(BASE_PATH + "/api-docs")
        .then()
        .extract()
        .response();
  }

  @Test
  void should_return_http_200_for_api_docs() {
    response = getRequestSpecification()
        .get(BASE_PATH + "/api-docs")
        .then()
        .extract()
        .response();
    assertThat(response.statusCode()).isEqualTo(OK.value());
  }

  @Test
  void should_return_http_200_for_ui() {
    response = getRequestSpecification()
        .get(BASE_PATH + "/swagger-ui.html")
        .then()
        .extract()
        .response();
    assertThat(response.statusCode()).isEqualTo(OK.value());
  }

}
