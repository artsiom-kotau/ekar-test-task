package com.itechart.ekar.service.worker;

import com.itechart.ekar.service.countmanager.ChangeResult;
import com.itechart.ekar.service.countmanager.ChangeResultInterpreter;
import com.itechart.ekar.service.countmanager.CounterManager;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

@Slf4j
public class Consumer extends Worker {

    public Consumer(String identifier, CounterManager counterManager, ChangeResultInterpreter resultInterpreter, WorkerPool pool) {
        super(identifier, counterManager, resultInterpreter, pool);
    }

    @Override
    protected ChangeResult doWork() {
        return getCounterManager().decrement();
    }

    @Override
    protected Logger logger() {
        return log;
    }

    @Override
    protected void doRemove() {
        getWorkerPool().removeConsumerWorker(this);
    }
}
