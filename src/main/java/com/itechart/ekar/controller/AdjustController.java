package com.itechart.ekar.controller;

import com.itechart.ekar.dto.ClientConfiguration;
import com.itechart.ekar.service.countmanager.ChangeResult;
import com.itechart.ekar.service.countmanager.ChangeResultInterpreter;
import com.itechart.ekar.service.countmanager.CounterManager;
import com.itechart.ekar.service.logging.RequestLoggingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class AdjustController {

    @Autowired
    private RequestLoggingService requestLoggingService;

    @Autowired
    private CounterManager counterManager;

    @Autowired
    private ChangeResultInterpreter resultInterpreter;

    @PostMapping("/adjust/client")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void adjustClient(@RequestBody ClientConfiguration configuration) {
        requestLoggingService.logConfigurationEvent(configuration);

    }

    @PostMapping("/adjust/counter/{newCounter}")
    @ResponseStatus(value = HttpStatus.OK)
    public void adjustCounter(@PathVariable("newCounter") Integer newCounter) {
        requestLoggingService.logCounter(newCounter);
        ChangeResult changeResult = counterManager.setNewValue(newCounter);
        resultInterpreter.interpret("External request", changeResult);
    }
}
