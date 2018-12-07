package com.itechart.ekar.service.logging;

import com.itechart.ekar.dto.ClientConfiguration;
import com.itechart.ekar.entity.RequestEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class RequestEventFactoryTest {

    @InjectMocks
    private RequestEventFactory requestEventFactory;

    @Test
    public void createClientConfigurationEvent() {
        Integer producer = 10;
        Integer consumer = 33;
        RequestEvent requestEvent = requestEventFactory.createConfigurationEvent(ClientConfiguration.builder().producer(producer).consumer(consumer).build());
        assertNotNull(requestEvent.getEventTime());
        assertThat(requestEvent.getPayload(), containsString(producer.toString()));
        assertThat(requestEvent.getPayload(), containsString(consumer.toString()));
    }

    @Test
    public void createClientConfigurationEventWithConsumerOnly() {
        Integer consumer = 33;
        RequestEvent requestEvent = requestEventFactory.createConfigurationEvent(ClientConfiguration.builder().consumer(consumer).build());
        assertNotNull(requestEvent.getEventTime());
        assertThat(requestEvent.getPayload(), containsString("null"));
        assertThat(requestEvent.getPayload(), containsString(consumer.toString()));
    }

    @Test
    public void createClientConfigurationEventWithProducerOnly() {
        Integer producer = 33;
        RequestEvent requestEvent = requestEventFactory.createConfigurationEvent(ClientConfiguration.builder().producer(producer).build());
        assertNotNull(requestEvent.getEventTime());
        assertThat(requestEvent.getPayload(), containsString("null"));
        assertThat(requestEvent.getPayload(), containsString(producer.toString()));
    }

    @Test
    public void createNewCounterEvent() {
        Integer newCounter = 10;
        RequestEvent requestEvent = requestEventFactory.createChangeCounter(newCounter);
        assertNotNull(requestEvent.getEventTime());
        assertThat(requestEvent.getPayload(), containsString(newCounter.toString()));
    }


}