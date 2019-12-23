package com.badger.scheduleitem;

import java.util.Objects;

public class MarkableItem extends ScheduleItem {

    private MarkableItemState state = MarkableItemState.WAIT;

    public MarkableItem(String text) {
        super(text);
    }

    @Override
    public boolean accept() {
        if (state == MarkableItemState.WAIT) {
            state = MarkableItemState.PERFORMED;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean cancel() {
        if (state == MarkableItemState.WAIT) {
            state = MarkableItemState.FAILED;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getTextOfSchedule() {
        return text.substring(0, 1).toUpperCase() + text.substring(1) + " (" + state.getTitle() + ")";
    }
}
