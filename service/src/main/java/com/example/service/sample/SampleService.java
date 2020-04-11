package com.example.service.sample;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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

    return sampleRepository.findById(id).map(SampleResponse::new);
  }

  public List<SampleResponse> getAll() {

    return sampleRepository.findAll()
        .stream()
        .map(SampleResponse::new)
        .collect(Collectors.toList());
  }

  public void delete(final String id) {

    sampleRepository.delete(id);
  }

  public void deleteAll() {
    sampleRepository.deleteAll();
  }
}
