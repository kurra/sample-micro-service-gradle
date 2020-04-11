package com.example.service.sample.delete;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import com.example.service.BaseSampleTest;
import com.example.service.sample.Sample;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DeleteSampleSuccessfully extends BaseSampleTest {

  private Sample persistedSample;

  @BeforeAll
  void setup() {
    persistedSample = sampleRepository.save(Sample.builder().name(randomAlphabetic(9)).build());
    response = getRequestSpecification()
        .pathParam("id", persistedSample.getId())
        .delete(SAMPLE_ENDPOINT_PATH + "/{id}")
        .then()
        .extract()
        .response();
  }

  @Test
  void should_return_http_status_204() {
    assertThat(response.statusCode()).isEqualTo(NO_CONTENT.value());
  }

  @Test
  void should_delete_from_db() {
    assertThat(sampleRepository.findById(persistedSample.getId())).isEmpty();
  }
}
