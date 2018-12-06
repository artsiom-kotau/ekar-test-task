package com.itechart.ekar.service.logging;

import com.itechart.ekar.dto.ClientConfiguration;
import com.itechart.ekar.repository.RequestEventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class RequestLoggingService {

    private RequestEventRepository eventRepository;

    private RequestEventFactory requestEventFactory;

    @Transactional
    public void logConfigurationEvent(ClientConfiguration configuration) {
        if (configuration == null || configuration.isEmpty()) {
            log.warn("Nothing to save. Configuration is empty");
            return;
        }
        eventRepository.save(requestEventFactory.createConfigurationEvent(configuration));
    }

    @Transactional
    public void logCounter(Integer counter) {
        if (counter == null) {
            log.warn("Nothing to save. New counter value is null");
            return;
        }
        eventRepository.save(requestEventFactory.createChangeCounter(counter));
    }

    @Autowired
    public void setEventRepository(RequestEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Autowired
    public void setRequestEventFactory(RequestEventFactory requestEventFactory) {
        this.requestEventFactory = requestEventFactory;
    }
}
