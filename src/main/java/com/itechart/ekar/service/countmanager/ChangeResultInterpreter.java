package com.itechart.ekar.service.countmanager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ChangeResultInterpreter {

    public void interpret(String who, ChangeResult result) {
        if (result.isChanged()) {
            log.info("{} has changed value from '{}' to '{}'", who, result.getPreviousValue(), result.getCurrentValue());
        }
    }
}
