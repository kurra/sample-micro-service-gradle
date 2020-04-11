package com.example.service.management;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.OK;

import com.example.service.BaseSampleTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

class CallingInfoEndpoint extends BaseSampleTest {

  private static final String MANAGEMENT_INFO_PATH = MANAGEMENT_PATH + "/info";

  @Value("${local.management.port}")
  private int adminPort;

  @BeforeAll
  void setUp() {
    response = given().accept(JSON).contentType(JSON)
        .when()
        .port(adminPort)
        .get(MANAGEMENT_INFO_PATH)
        .then().extract().response();
  }

  @Test
  void should_return_http_200() {
    assertThat(response.statusCode()).isEqualTo(OK.value());
  }

  @Test
  void should_return_application_name() {
    assertThat(response.path("name").toString()).isEqualTo("sample-service");
  }

  @Test
  void should_return_application_version() {
    assertThat(response.path("version").toString()).isNotBlank();
  }

  @Test
  void should_return_application_description() {
    assertThat(response.path("description").toString()).isEqualTo("description");
  }

}
