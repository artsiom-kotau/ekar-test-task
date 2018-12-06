package com.itechart.ekar.service.logging;

import com.itechart.ekar.dto.ClientConfiguration;
import com.itechart.ekar.entity.RequestEvent;
import com.itechart.ekar.repository.RequestEventRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class RequestLoggingServiceTest {

    @InjectMocks
    private RequestLoggingService requestLoggingService;

    @Mock
    private RequestEventRepository eventRepository;

    @Mock
    private RequestEventFactory requestEventFactory;

    @Test
    public void passNullConfiguration() {
        requestLoggingService.logConfigurationEvent(null);
        verify(requestEventFactory, times(0)).createConfigurationEvent(any(ClientConfiguration.class));
        verify(eventRepository, times(0)).save(any(RequestEvent.class));
    }

    @Test
    public void passEmptyConfiguration() {
        requestLoggingService.logConfigurationEvent(new ClientConfiguration());
        verify(requestEventFactory, times(0)).createConfigurationEvent(any(ClientConfiguration.class));
        verify(eventRepository, times(0)).save(any(RequestEvent.class));
    }


    @Test
    public void passClientConfigurationWithConsumerOnly() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder().consumer(2).build();
        RequestEvent requestEvent = mock(RequestEvent.class);
        when(requestEventFactory.createConfigurationEvent(clientConfiguration)).thenReturn(requestEvent);
        requestLoggingService.logConfigurationEvent(clientConfiguration);
        verify(eventRepository, times(1)).save(requestEvent);
    }

    @Test
    public void passClientConfigurationWithProducerOnly() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder().producer(3).build();
        RequestEvent requestEvent = mock(RequestEvent.class);
        when(requestEventFactory.createConfigurationEvent(clientConfiguration)).thenReturn(requestEvent);
        requestLoggingService.logConfigurationEvent(clientConfiguration);
        verify(eventRepository, times(1)).save(requestEvent);
    }

    @Test
    public void passNotEmptyClientConfiguration() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder().producer(3).consumer(4).build();
        RequestEvent requestEvent = mock(RequestEvent.class);
        when(requestEventFactory.createConfigurationEvent(clientConfiguration)).thenReturn(requestEvent);
        requestLoggingService.logConfigurationEvent(clientConfiguration);
        verify(eventRepository, times(1)).save(requestEvent);
    }

    @Test
    public void passNullCounter() {
        requestLoggingService.logCounter(null);
        verify(requestEventFactory, times(0)).createChangeCounter(any(Integer.class));
        verify(eventRepository, times(0)).save(any(RequestEvent.class));
    }

    @Test
    public void passNotNullCounter() {
        Integer newValue = 34;
        RequestEvent requestEvent = mock(RequestEvent.class);
        when(requestEventFactory.createChangeCounter(newValue)).thenReturn(requestEvent);
        requestLoggingService.logCounter(newValue);
        verify(eventRepository, times(1)).save(requestEvent);
    }


}