package com.itechart.ekar.service.countmanager;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChangeResultTest {

    @Test
    public void createChanged() {
        int prevValue = 23423;
        int currentValue = 23423;
        ChangeResult changeResult = ChangeResult.changed(prevValue, currentValue);
        assertEquals(prevValue, changeResult.getPreviousValue());
        assertEquals(currentValue, changeResult.getCurrentValue());
        assertTrue(changeResult.isChanged());
    }

    @Test
    public void createNotChanged() {
        int value = 23423;
        ChangeResult changeResult = ChangeResult.notChanged(value);
        assertEquals(value, changeResult.getPreviousValue());
        assertEquals(value, changeResult.getCurrentValue());
        assertFalse(changeResult.isChanged());
    }

}