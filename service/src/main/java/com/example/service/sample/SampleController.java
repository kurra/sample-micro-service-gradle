package com.example.service.sample;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.of;
import static org.springframework.web.util.UriComponentsBuilder.fromPath;

import com.example.client.model.SampleRequest;
import com.example.client.model.SampleResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sample")
@AllArgsConstructor
public class SampleController {

  private SampleService sampleService;

  @PostMapping
  public ResponseEntity<?> create(@RequestBody final SampleRequest sampleRequest) {
    return created(fromPath("/sample/{id}").buildAndExpand(sampleService.create(sampleRequest)).toUri()).build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<SampleResponse> get(@PathVariable final String id) {
    return of(sampleService.get(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable final String id) {
    sampleService.delete(id);
    return noContent().build();
  }

}
