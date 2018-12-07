package com.itechart.ekar.service.worker;

import com.itechart.ekar.service.countmanager.ChangeResultInterpreter;
import com.itechart.ekar.service.countmanager.CounterManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkerFactory {

    private static final String PRODUCER_IDENTITY_TEMPLATE = "Producer#%s";
    private static final String CONSUMER_IDENTITY_TEMPLATE = "Consumer#%s";

    private CounterManager counterManager;

    private ChangeResultInterpreter resultInterpreter;

    public Worker createProducer(Integer number) {
        return new Producer(String.format(PRODUCER_IDENTITY_TEMPLATE, number), counterManager, resultInterpreter);
    }

    public Worker createConsumer(Integer number) {
        return new Consumer(String.format(CONSUMER_IDENTITY_TEMPLATE, number), counterManager, resultInterpreter);
    }

    @Autowired
    public void setCounterManager(CounterManager counterManager) {
        this.counterManager = counterManager;
    }

    @Autowired
    public void setResultInterpreter(ChangeResultInterpreter resultInterpreter) {
        this.resultInterpreter = resultInterpreter;
    }
}
