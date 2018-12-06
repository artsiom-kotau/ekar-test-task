package com.itechart.ekar.service.logging;

import com.itechart.ekar.dto.ClientConfiguration;
import com.itechart.ekar.entity.RequestEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RequestEventFactory {

    public RequestEvent createConfigurationEvent(ClientConfiguration configuration) {
        String payload = String.format("producer = %s, consumer = %s", configuration.getProducer(), configuration.getConsumer());
        RequestEvent requestEvent = new RequestEvent();
        requestEvent.setPayload(payload);
        requestEvent.setEventTime(LocalDateTime.now());
        return requestEvent;
    }
}
