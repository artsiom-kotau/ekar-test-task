package com.itechart.ekar.service.countmanager;

import com.itechart.ekar.repository.StopEventRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CounterManagerTest {

    @InjectMocks
    private CounterManager counterManager;

    @Mock
    private StopResolver stopResolver;

    @Mock
    private StopEventRepository stopEventRepository;

    @Test
    public void setValue() {
        int newValue = 23;
        int currentValue = 50;
        counterManager.setValue(currentValue);
        ChangeResult result = counterManager.setNewValue(newValue);

        assertTrue(result.isChanged());
        assertEquals(currentValue, result.getPreviousValue());
        assertEquals(newValue, result.getCurrentValue());
    }

    @Test
    public void setNullValue() {
        int currentValue = 50;
        counterManager.setValue(currentValue);
        ChangeResult result = counterManager.setNewValue(null);

        assertFalse(result.isChanged());
        assertEquals(currentValue, result.getPreviousValue());
        assertEquals(currentValue, result.getCurrentValue());
    }


    @Test
    public void cannotBeIncrement() {
        int prevValue = 100;
        counterManager.setValue(prevValue);
        couldntChanged(prevValue);
        ChangeResult result = counterManager.increment();

        assertFalse(result.isChanged());
        assertEquals(prevValue, result.getPreviousValue());
        assertEquals(prevValue, result.getCurrentValue());
    }

    @Test
    public void canIncrement() {
        int prevValue = 90;
        counterManager.setValue(prevValue);
        couldChanged(prevValue);
        ChangeResult result = counterManager.increment();

        assertTrue(result.isChanged());
        assertEquals(prevValue, result.getPreviousValue());
        assertEquals(prevValue+1, result.getCurrentValue());
    }


    @Test
    public void cannotBeDecrement() {
        int prevValue = 0;
        counterManager.setValue(prevValue);
        couldntChanged(prevValue);
        ChangeResult result = counterManager.decrement();

        assertFalse(result.isChanged());
        assertEquals(prevValue, result.getPreviousValue());
        assertEquals(prevValue, result.getCurrentValue());
    }

    @Test
    public void canDecrement() {
        int prevValue = 2;
        counterManager.setValue(prevValue);
        couldChanged(prevValue);
        ChangeResult result = counterManager.decrement();

        assertTrue(result.isChanged());
        assertEquals(prevValue, result.getPreviousValue());
        assertEquals(prevValue-1, result.getCurrentValue());
    }

    private void couldChanged(int value) {
        when(stopResolver.couldBeChanged(value)).thenReturn(true);
    }

    private void couldntChanged(int value) {
        when(stopResolver.couldBeChanged(value)).thenReturn(false);
    }

}