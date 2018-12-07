package com.itechart.ekar.service.countmanager;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class ChangeResult {

    private final int previousValue;

    private final int currentValue;

    private final boolean changed;

    private ChangeResult() {
        previousValue = 0;
        currentValue = 0;
        changed = false;
    }

    private ChangeResult(int previousValue, int currentValue, boolean changed) {
        this.previousValue = previousValue;
        this.currentValue = currentValue;
        this.changed = changed;
    }

    public static ChangeResult changed(int previousValue, int currentValue) {
        return new ChangeResult(previousValue, currentValue, true);
    }

    public static ChangeResult notChanged(int currentValue) {
        return new ChangeResult(currentValue, currentValue,false);
    }
}
