package com.example.service.sample.create;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.CREATED;

import com.example.service.BaseSampleTest;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CreateSampleSuccessfully extends BaseSampleTest {

  private String randomName;

  @BeforeAll
  void setup() {
    randomName = RandomStringUtils.randomAlphabetic(9);
    response = getRequestSpecification()
        .body("{\"name\": \"" + randomName + "\"}")
        .post(SAMPLE_ENDPOINT_PATH)
        .then()
        .extract()
        .response();
  }

  @Test
  void should_return_http_status_201() {
    assertThat(response.statusCode()).isEqualTo(CREATED.value());
  }

  @Test
  void should_return_resource_path() {
    assertThat(response.getHeader("Location")).startsWith("/sample/");
  }

  @Test
  void should_persist_in_db() {
    final String id = response.getHeader("Location").substring(response.getHeader("Location").lastIndexOf("/") + 1);
    assertThat(sampleRepository.findById(id).get().getName()).isEqualTo(randomName);
  }

}
