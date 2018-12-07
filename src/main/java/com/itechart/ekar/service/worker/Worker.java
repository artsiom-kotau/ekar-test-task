package com.itechart.ekar.service.worker;

import com.itechart.ekar.service.countmanager.ChangeResult;
import com.itechart.ekar.service.countmanager.ChangeResultInterpreter;
import com.itechart.ekar.service.countmanager.CounterManager;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

@Slf4j
public abstract class Worker implements Runnable {

    private final String identifier;

    private final CounterManager counterManager;

    private final ChangeResultInterpreter resultInterpreter;

    private volatile boolean continueWork;

    private WorkerPool workerPool;

    public Worker(String identifier, CounterManager counterManager, ChangeResultInterpreter resultInterpreter, WorkerPool pool) {
        this.identifier = identifier;
        this.counterManager = counterManager;
        this.resultInterpreter = resultInterpreter;
        this.continueWork = true;
        this.workerPool = pool;
    }

    @Override
    public void run() {
        ChangeResult changeResult;
        do {
            changeResult = doWork();
            resultInterpreter.interpret(identifier, changeResult);
        }while (changeResult.isChanged() && continueWork);
        doRemove();
        logger().info("{} has been stopped", identifier);
    }

    protected abstract void doRemove();

    public void stop() {
        continueWork = false;
    }

    protected CounterManager getCounterManager() {
        return counterManager;
    }

    protected abstract ChangeResult doWork();

    protected abstract Logger logger();

    protected String getIdentifier() {
        return identifier;
    }

    public WorkerPool getWorkerPool() {
        return workerPool;
    }
}
