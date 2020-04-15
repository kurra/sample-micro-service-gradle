package com.example.client;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "sample")
public class SampleClientProperties {

  @NotEmpty
  private String apiUrl;

}
