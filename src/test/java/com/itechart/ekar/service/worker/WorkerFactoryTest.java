package com.itechart.ekar.service.worker;

import com.itechart.ekar.service.countmanager.ChangeResultInterpreter;
import com.itechart.ekar.service.countmanager.CounterManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class WorkerFactoryTest {

    @Mock
    private CounterManager counterManager;

    @Mock
    private ChangeResultInterpreter resultInterpreter;

    @Mock
    private WorkerPool workerPool;

    @InjectMocks
    private WorkerFactory workerFactory;

    @Test
    public void createProducer() {
        int number = 23;
        Worker worker = workerFactory.createProducer(number, workerPool);
        assertTrue(worker instanceof Producer);
        assertThat(worker.getIdentifier(), containsString(Integer.toString(number)));
    }

    @Test
    public void createConsumer() {
        int number = 25;
        Worker worker = workerFactory.createConsumer(number, workerPool);
        assertTrue(worker instanceof Consumer);
        assertThat(worker.getIdentifier(), containsString(Integer.toString(number)));
    }

}