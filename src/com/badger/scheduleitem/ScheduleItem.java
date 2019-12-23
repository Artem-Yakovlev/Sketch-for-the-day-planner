package com.badger.scheduleitem;

public abstract class ScheduleItem implements ScheduleItemBehaviour {

    protected String text;

    public ScheduleItem(String text) {
        setText(text);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text.trim();
    }

}
