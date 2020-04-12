package com.example.client;

import static java.nio.charset.Charset.defaultCharset;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.springframework.web.util.UriComponentsBuilder.fromPath;

import com.example.client.model.SampleRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.UUID;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, properties = {
    "serverHost=http://localhost:${wiremock.server.port}",
})
@AutoConfigureWireMock(port = 0)
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
public class SampleClientTest {

  protected String id;

  @Value("classpath:sample/sampleRequest.json")
  Resource sampleRequestResource;

  @Value("classpath:sample/sampleResponse.json")
  Resource sampleResponseResource;

  @Autowired
  private SampleClient sampleClient;

  @Autowired
  private MockSampleServer mockSampleServer;

  @Autowired
  private ObjectMapper objectMapper;

  @BeforeAll
  public void setup() {
    id = UUID.randomUUID().toString();
  }

  @Test
  void get() throws IOException {
    mockSampleServer.getSample(id);
    assertThatJson(sampleClient.get(id))
        .isEqualTo(IOUtils.toString(sampleResponseResource.getInputStream(), defaultCharset())
            .replace("RANDOM_ID", id));
  }

  @Test
  void createDocument() throws IOException {
    mockSampleServer.create(id);
    final SampleRequest sampleRequest = objectMapper
        .readValue(sampleRequestResource.getInputStream(), SampleRequest.class);
    assertThat(sampleClient.create(sampleRequest)).isEqualTo(fromPath("/sample/{id}").buildAndExpand(id).toUri());
  }

  @Test
  void deleteDocument() throws IOException {
    mockSampleServer.deleteSample(id);
    assertThatCode(() -> sampleClient.delete(id)).doesNotThrowAnyException();
  }

}
