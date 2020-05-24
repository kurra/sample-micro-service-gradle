package com.example.service.management;

import static io.prometheus.client.exporter.common.TextFormat.CONTENT_TYPE_004;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.OK;

import com.example.service.BaseSampleTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

class CallingPrometheusEndpoint extends BaseSampleTest {

  private static final String MANAGEMENT_PROMETHEUS_PATH = MANAGEMENT_PATH + "/prometheus";

  @Value("${local.management.port}")
  private int adminPort;

  @BeforeAll
  void setUp() {
    response = given().accept(CONTENT_TYPE_004)
        .when()
        .port(adminPort)
        .get(MANAGEMENT_PROMETHEUS_PATH)
        .then().extract().response();
  }

  @Test
  void should_return_http_200() {
    assertThat(response.statusCode()).isEqualTo(OK.value());
  }

}
