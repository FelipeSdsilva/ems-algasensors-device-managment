package com.algaworks.algasensors.device.management.api.controller;

import com.algaworks.algasensors.device.management.api.model.SensorInput;
import com.algaworks.algasensors.device.management.common.IdGenerator;
import com.algaworks.algasensors.device.management.domain.model.Sensor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping(value = "/api/sensors")
public class SensorController {

  @PostMapping
  public ResponseEntity<Sensor> create(@RequestBody SensorInput input) {
    Sensor sensor = Sensor.builder()
        .id(IdGenerator.generateTSID())
        .name(input.getName())
        .ip(input.getIp())
        .location(input.getLocation())
        .protocol(input.getProtocol())
        .model(input.getModel())
        .enable(false)
        .build();
    URI uri = fromCurrentRequest().path("/{id}").buildAndExpand(sensor.getId()).toUri();
    return ResponseEntity.created(uri).body(sensor);
  }
}
