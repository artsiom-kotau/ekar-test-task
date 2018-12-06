package com.itechart.ekar.service.logging;

import com.itechart.ekar.dto.ClientConfiguration;
import com.itechart.ekar.repository.RequestEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RequestLoggingService {

    @Autowired
    private RequestEventRepository eventRepository;

    @Autowired
    private RequestEventFactory requestEventFactory;

    @Transactional
    public void logConfigurationEvent(ClientConfiguration configuration) {
        eventRepository.save(requestEventFactory.createConfigurationEvent(configuration));
    }




}
