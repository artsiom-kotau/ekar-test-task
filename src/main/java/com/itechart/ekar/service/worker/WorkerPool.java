package com.itechart.ekar.service.worker;

import com.itechart.ekar.dto.ClientConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class WorkerPool {

    private final Object producerMonitor = new Object();
    private final Object consumerMonitor = new Object();
    private final Queue<Worker> producers = new LinkedList<>();
    private final Queue<Worker> consumers = new LinkedList<>();
    private ExecutorService executorService = Executors.newCachedThreadPool();
    private WorkerFactory workerFactory;

    public void updateWorkers(ClientConfiguration configuration) {
        if (configuration == null) {
            return;
        }

        updateProducer(configuration);
        updateConsumers(configuration);
    }

    public void removeProducerWorker(Worker worker) {
        synchronized (producerMonitor) {
            producers.remove(worker);
        }
    }

    public void removeConsumerWorker(Worker worker) {
        synchronized (consumerMonitor) {
            consumers.remove(worker);
        }
    }

    private void updateConsumers(ClientConfiguration configuration) {
        Integer consumerNewNumber = normalizeNumber(configuration.getConsumer());
        if (consumerNewNumber != null) {
            synchronized (consumerMonitor) {
                updateQueue(consumerNewNumber, consumers, identity -> workerFactory.createConsumer(identity, this));
            }
        }
    }

    private Integer updateProducer(ClientConfiguration configuration) {
        Integer producerNewNumber = normalizeNumber(configuration.getProducer());
        if (producerNewNumber != null) {
            synchronized (producerMonitor) {
                updateQueue(producerNewNumber, producers, identity -> workerFactory.createProducer(identity, this));
            }
        }
        return producerNewNumber;
    }

    private Integer normalizeNumber(Integer newNumber) {
        if (newNumber == null) {
            return null;
        }
        return newNumber < 0 ? 0 : newNumber;
    }

    private void updateQueue(int newNumber, Queue<Worker> workers, WhatProduce whatProduce) {
        int currentSize = workers.size();
        if (currentSize > newNumber) {
            int diff = currentSize - newNumber;
            while (diff > 0) {
                Worker worker = workers.poll();
                if (worker != null) {
                    worker.stop();
                }
                diff--;
            }
        } else if (currentSize < newNumber) {
            int diff = newNumber - currentSize;
            int identifier = currentSize;
            while (diff > 0) {
                Worker worker = whatProduce.produce(identifier);
                workers.add(worker);
                executorService.submit(worker);
                diff--;
                identifier++;
            }
        }
    }

    @Autowired
    public void setWorkerFactory(WorkerFactory workerFactory) {
        this.workerFactory = workerFactory;
    }

    private interface WhatProduce {
        Worker produce(Integer identity);
    }
}
