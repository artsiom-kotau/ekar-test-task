package com.itechart.ekar.service.countmanager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CounterManager {

    private final Object monitor = new Object();

    private StopResolver stopResolver;

    private int value = 0;

    public ChangeResult setNewValue(Integer newValue) {
        if (newValue == null) {
            log.warn("Null is not valid value");
            return ChangeResult.notChanged(value);
        }
        int previous;
        synchronized (monitor) {
            previous = value;
            value = newValue;
        }
        return ChangeResult.changed(previous, newValue);

    }

    public ChangeResult increment() {
        synchronized (monitor) {
            if (stopResolver.couldBeChanged(value)) {
                int previous = value;
                value++;
                return ChangeResult.changed(previous, value);
            } else {
                return ChangeResult.notChanged(value);
            }
        }
    }

    public ChangeResult decrement() {
        synchronized (monitor) {
            if (stopResolver.couldBeChanged(value)) {
                int previous = value;
                value--;
                return ChangeResult.changed(previous, value);
            } else {
                return ChangeResult.notChanged(value);
            }
        }
    }

    @Autowired
    public void setStopResolver(StopResolver stopResolver) {
        this.stopResolver = stopResolver;
    }

    @Value("${init.value}")
    public void setValue(Integer value) {
        this.value = value;
    }
}
