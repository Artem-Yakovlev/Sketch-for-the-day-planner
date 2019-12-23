package com.badger.scheduleitem;

public interface ScheduleItemBehaviour {

    boolean accept();

    boolean cancel();

    String getTextOfSchedule();
}
