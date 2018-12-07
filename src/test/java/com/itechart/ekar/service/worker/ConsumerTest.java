package com.itechart.ekar.service.worker;

import com.itechart.ekar.service.countmanager.ChangeResult;
import com.itechart.ekar.service.countmanager.ChangeResultInterpreter;
import com.itechart.ekar.service.countmanager.CounterManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ConsumerTest {

    private static final String identity = "consumer_identity";

    @Mock
    private CounterManager counterManager;

    @Mock
    private ChangeResultInterpreter resultInterpreter;

    private Consumer consumer;

    @Before
    public void init() {
        consumer = new Consumer(identity, counterManager, resultInterpreter);
    }

    @Test
    public void changeResultChangedStopCalled() {
        ChangeResult changeResult = ChangeResult.changed(23,22);
        when(counterManager.decrement()).thenReturn(changeResult);
        consumer.stop();
        consumer.run();

        verify(resultInterpreter, times(1)).interpret(identity, changeResult);
    }

    @Test
    public void changeResultNotChanged() {
        ChangeResult changeResult = ChangeResult.notChanged(22);
        when(counterManager.decrement()).thenReturn(changeResult);
        consumer.run();
        verify(resultInterpreter, times(1)).interpret(identity, changeResult);
    }

}