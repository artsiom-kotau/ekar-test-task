package com.itechart.ekar.controller;

import com.itechart.ekar.dto.ClientConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdjustController {

    @PostMapping("/adjust/client")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void adjustClient(@RequestBody  ClientConfiguration configuration) {

    }

    @PostMapping("/adjust/counter/{newCounter}")
    @ResponseStatus(value = HttpStatus.OK)
    public void adjustCounter(@PathVariable("newCounter") Integer newCounter) {

    }
}
