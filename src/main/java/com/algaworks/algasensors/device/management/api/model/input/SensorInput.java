package com.algaworks.algasensors.device.management.api.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensorInput {

  @NotBlank(message = "Name cannot be blank")
  private String name;

  @NotBlank(message = "IP cannot be blank")
  private String ip;

  @NotBlank(message = "Location cannot be blank")
  private String location;

  @NotBlank(message = "Protocol cannot be blank")
  private String protocol;

  @NotBlank(message = "Model cannot be blank")
  private String model;

}
