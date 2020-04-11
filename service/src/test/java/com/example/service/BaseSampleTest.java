package com.example.service;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.example.service.sample.SampleRepository;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = RANDOM_PORT, properties = {"management.server.port=0"})
@TestInstance(PER_CLASS)
public class BaseSampleTest {

  private static final String BASE_PATH = "sample-micro-service";
  protected static final String MANAGEMENT_PATH = BASE_PATH + "/management";
  protected static final String SAMPLE_ENDPOINT_PATH = BASE_PATH + "/sample";

  @LocalServerPort
  private int port;
  
  @Autowired
  protected SampleRepository sampleRepository;

  protected Response response;

  protected RequestSpecification getRequestSpecification() {
    return given()
        .accept(JSON)
        .contentType(JSON)
        .when()
        .port(port);
  }

}
