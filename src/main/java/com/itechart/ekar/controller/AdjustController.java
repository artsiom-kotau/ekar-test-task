package com.itechart.ekar.controller;

import com.itechart.ekar.dto.ClientConfiguration;
import com.itechart.ekar.service.logging.RequestLoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdjustController {

    @Autowired
    private RequestLoggingService requestLoggingService;

    @PostMapping("/adjust/client")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void adjustClient(@RequestBody ClientConfiguration configuration) {
        requestLoggingService.logConfigurationEvent(configuration);

    }

    @PostMapping("/adjust/counter/{newCounter}")
    @ResponseStatus(value = HttpStatus.OK)
    public void adjustCounter(@PathVariable("newCounter") Integer newCounter) {

    }
}
