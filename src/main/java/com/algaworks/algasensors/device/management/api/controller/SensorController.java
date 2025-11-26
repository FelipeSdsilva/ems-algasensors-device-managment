package com.algaworks.algasensors.device.management.api.controller;

import com.algaworks.algasensors.device.management.api.model.input.SensorInput;
import com.algaworks.algasensors.device.management.api.model.output.SensorOutput;
import com.algaworks.algasensors.device.management.domain.services.SensorService;
import io.hypersistence.tsid.TSID;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping(value = "/api/sensors")
public class SensorController {

  private final SensorService sensorService;

  @GetMapping
  public ResponseEntity<Page<SensorOutput>> search(@PageableDefault Pageable pageable){
    return ResponseEntity.ok(sensorService.search(pageable));
  }

  @GetMapping(value = "/{sensorId}")
  public ResponseEntity<SensorOutput> getOne(@PathVariable TSID sensorId) {
    return ResponseEntity.ok(sensorService.getOne(sensorId));
  }

  @PostMapping
  public ResponseEntity<SensorOutput> create(@RequestBody SensorInput input) {
    SensorOutput sensor = sensorService.create(input);
    URI uri = fromCurrentRequest().path("/{id}").buildAndExpand(sensor.getId()).toUri();
    return ResponseEntity.created(uri).body(sensor);
  }

  @PutMapping(value = "/{sensorId}")
  public ResponseEntity<SensorOutput> update(@PathVariable TSID sensorId, @Valid @RequestBody SensorInput input) {
    return ResponseEntity.ok(sensorService.update(sensorId,input));
  }

  @PutMapping(value = "/{sensorId}/enable")
  public ResponseEntity<SensorOutput> enable(@PathVariable TSID sensorId) {
    return ResponseEntity.ok(sensorService.enable(sensorId));
  }

  @DeleteMapping(value = "/{sensorId}")
  public ResponseEntity<Void> delete(@PathVariable TSID sensorId) {
    sensorService.delete(sensorId);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping(value = "/{sensorId}/enable")
  public ResponseEntity<Void> disable(@PathVariable TSID sensorId) {
    sensorService.disable(sensorId);
    return ResponseEntity.noContent().build();
  }

}
