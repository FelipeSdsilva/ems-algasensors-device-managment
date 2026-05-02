package com.algaworks.algasensors.device.management.api.client.impl;

import com.algaworks.algasensors.device.management.api.client.SensorMonitoringClient;
import com.algaworks.algasensors.device.management.api.controller.exceptions.SensorMonitoringClientBadGatewayException;
import io.hypersistence.tsid.TSID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.Duration;

@Component
public class SensorMonitoringClientImpl implements SensorMonitoringClient {

  private final RestClient restClient;

  public SensorMonitoringClientImpl(RestClient.Builder restClient,
                                    @Value("${external-apis.monitoring-base-url}") String baseUrl) {
    this.restClient = restClient.baseUrl(baseUrl)
            .requestFactory(genereteClientHttpRequestFactory())
            .defaultStatusHandler(HttpStatusCode::isError, ((request, response) -> {
              throw new SensorMonitoringClientBadGatewayException(
                      "Error while calling Sensor Monitoring service. Status: " + response.getStatusCode()
              );
            }))
            .build();
  }

  private ClientHttpRequestFactory genereteClientHttpRequestFactory() {
    SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
    factory.setConnectTimeout(Duration.ofSeconds(3));
    factory.setReadTimeout(Duration.ofSeconds(5));
    return factory;
  }

  @Override
  public void enableMonitoring(TSID tsid) {
    restClient.put().uri("/api/sensors/{sensorId}/monitoring/enable", tsid)
        .retrieve()
        .toBodilessEntity();
  }

  @Override
  public void disableMonitoring(TSID tsid) {
    restClient.delete().uri("/api/sensors/{sensorId}/monitoring/enable", tsid)
        .retrieve()
        .toBodilessEntity();
  }
}
