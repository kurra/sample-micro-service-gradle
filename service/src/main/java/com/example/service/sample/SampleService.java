package com.example.service.sample;

import com.example.client.model.SampleRequest;
import com.example.client.model.SampleResponse;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SampleService {

  private SampleRepository sampleRepository;

  public String create(final SampleRequest sampleRequest) {

    final Sample sample = Sample.builder()
        .name(sampleRequest.getName())
        .build();
    return sampleRepository.save(sample).getId();
  }

  public Optional<SampleResponse> get(final String id) {

    return sampleRepository.findById(id).map(sample -> SampleResponse.builder()
        .id(sample.getId())
        .name(sample.getName())
        .build()
    );
  }

  public void delete(final String id) {

    sampleRepository.delete(id);
  }

  public void deleteAll() {
    sampleRepository.deleteAll();
  }
}
