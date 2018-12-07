package com.itechart.ekar.service.countmanager;

import com.itechart.ekar.entity.StopEvent;
import com.itechart.ekar.repository.StopEventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Slf4j
public class CounterManager {

    private final Object monitor = new Object();

    private StopResolver stopResolver;

    private StopEventRepository stopEventRepository;

    private int value = 0;

    private AtomicBoolean stopEventSaved = new AtomicBoolean(false);

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
        stopEventSaved.set(false);
        return ChangeResult.changed(previous, newValue);

    }

    public ChangeResult increment() {
        synchronized (monitor) {
            if (stopResolver.couldBeChanged(value)) {
                int previous = value;
                value++;
                return ChangeResult.changed(previous, value);
            } else {
                ChangeResult result = ChangeResult.notChanged(value);
                publishStopEvent(result);
                return result;
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
                ChangeResult result = ChangeResult.notChanged(value);
                publishStopEvent(result);
                return result;
            }
        }
    }

    private void publishStopEvent(ChangeResult changeResult) {
        if (!stopEventSaved.getAndSet(true)) {
            StopEvent stopEvent = new StopEvent();
            stopEvent.setEventTime(LocalDateTime.now());
            stopEvent.setReached(String.valueOf(changeResult.getCurrentValue()));
            stopEventRepository.save(stopEvent);
        }
    }

    @Autowired
    public void setStopResolver(StopResolver stopResolver) {
        this.stopResolver = stopResolver;
    }

    @Autowired
    public void setStopEventRepository(StopEventRepository stopEventRepository) {
        this.stopEventRepository = stopEventRepository;
    }

    @Value("${init.value}")
    public void setValue(Integer value) {
        this.value = value;
    }
}
