package com.itechart.ekar.service.countmanager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class StopResolver {

    private Integer minValue;

    private Integer maxValue;

    @PostConstruct
    public void init() {
        log.warn("Min value is '{}', Max value is '{}'", minValue, maxValue);
    }

    public boolean couldBeChanged(int value) {
        return minValue < value && value < maxValue;
    }

    @Value("${min.value}")
    public void setMinValue(Integer minValue) {
        this.minValue = minValue;
    }

    @Value("${max.value}")
    public void setMaxValue(Integer maxValue) {
        this.maxValue = maxValue;
    }
}
