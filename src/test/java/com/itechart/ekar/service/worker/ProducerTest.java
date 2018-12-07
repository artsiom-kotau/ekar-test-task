package com.itechart.ekar.service.worker;

import com.itechart.ekar.service.countmanager.ChangeResult;
import com.itechart.ekar.service.countmanager.ChangeResultInterpreter;
import com.itechart.ekar.service.countmanager.CounterManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProducerTest {

    private static final String identity = "producer_identity";

    @Mock
    private CounterManager counterManager;

    @Mock
    private ChangeResultInterpreter resultInterpreter;

    private Producer producer;

    @Before
    public void init() {
        producer = new Producer(identity, counterManager, resultInterpreter);
    }

    @Test
    public void changeResultChangedStopCalled() {
        ChangeResult changeResult = ChangeResult.changed(23,22);
        when(counterManager.increment()).thenReturn(changeResult);
        producer.stop();
        producer.run();

        verify(resultInterpreter, times(1)).interpret(identity, changeResult);
    }

    @Test
    public void changeResultNotChanged() {
        ChangeResult changeResult = ChangeResult.notChanged(22);
        when(counterManager.increment()).thenReturn(changeResult);
        producer.run();
        verify(resultInterpreter, times(1)).interpret(identity, changeResult);
    }

}