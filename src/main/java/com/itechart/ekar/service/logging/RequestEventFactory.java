package com.itechart.ekar.service.logging;

import com.itechart.ekar.dto.ClientConfiguration;
import com.itechart.ekar.entity.RequestEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RequestEventFactory {

    public RequestEvent createConfigurationEvent(ClientConfiguration configuration) {
        String payload = String.format("producer = %s, consumer = %s", configuration.getProducer(), configuration.getConsumer());
        return createEvent(payload);
    }

    public RequestEvent createChangeCounter(Integer counter) {
        String payload = String.format("counter value = %s", counter);
        return createEvent(payload);
    }

    private RequestEvent createEvent(String payload) {
        RequestEvent requestEvent = new RequestEvent();
        requestEvent.setPayload(payload);
        requestEvent.setEventTime(LocalDateTime.now());
        return requestEvent;
    }
}
