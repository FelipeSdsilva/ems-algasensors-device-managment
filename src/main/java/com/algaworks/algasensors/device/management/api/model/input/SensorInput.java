package com.algaworks.algasensors.device.management.api.model.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensorInput {

  private String name;
  private String ip;
  private String location;
  private String protocol;
  private String model;

}
