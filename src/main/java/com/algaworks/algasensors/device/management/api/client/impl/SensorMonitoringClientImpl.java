package com.algaworks.algasensors.device.management.api.client.impl;

import com.algaworks.algasensors.device.management.api.client.RestClientFactory;
import com.algaworks.algasensors.device.management.api.client.SensorMonitoringClient;
import io.hypersistence.tsid.TSID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class SensorMonitoringClientImpl implements SensorMonitoringClient {

    private final RestClient restClient;

    public SensorMonitoringClientImpl(RestClientFactory restClientFactory,
                                      @Value("${external-apis.monitoring-base-url}") String baseUrl) {
        this.restClient = restClientFactory.temperatureMonitoringRestClient(baseUrl);
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
