package com.example.service.sample;

import static java.util.Objects.isNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Repository;

@Repository
public class SampleRepository {

  private final Map<String, Sample> samples = new HashMap<>();

  public Sample save(final Sample sample) {
    if (isNull(sample.getId())) {
      sample.setId(RandomStringUtils.randomAlphanumeric(6));
    }
    samples.put(sample.getId(), sample);
    return sample;
  }

  public Optional<Sample> findById(final String id) {
    return Optional.ofNullable(samples.get(id));
  }

  public List<Sample> findAll() {
    return new ArrayList<>(samples.values());
  }

  public void delete(final String id) {
    samples.remove(id);
  }

  public void deleteAll() {
    samples.clear();
  }
}
