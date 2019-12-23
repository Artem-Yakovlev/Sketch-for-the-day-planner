package com.badger.diary;

import com.badger.scheduleitem.MarkableItem;
import com.badger.scheduleitem.ScheduleItem;

import java.util.ArrayList;

public class DiaryDay {

    private String date;
    private ArrayList<ScheduleItem> dayPlan = new ArrayList<>();

    public DiaryDay(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void showDayPlan() {
        System.out.println("План дня на " + date + ":");
        for (int i = 0; i < dayPlan.size(); i++) {
            System.out.println(i + ") " + dayPlan.get(i).getTextOfSchedule());
        }

        if (dayPlan.size() == 0) {
            System.out.println("Пока пуст, но Вы можете это исправить");
        }
    }

    public boolean acceptScheduleItem(int index) {
        if (index >= 0 && index < dayPlan.size()) {
            return dayPlan.get(index).accept();
        } else {
            return false;
        }
    }

    public boolean cancelScheduleItem(int index) {
        if (index >= 0 && index < dayPlan.size()) {
            return dayPlan.get(index).cancel();
        } else {
            return false;
        }
    }

    public boolean addScheduleItem(String text) {

        for (ScheduleItem scheduleItem : dayPlan) {

            if (scheduleItem.getText().toLowerCase().equals(text.toLowerCase())) {
                return false;
            }
        }
        dayPlan.add(new MarkableItem(text));
        return true;
    }

    public boolean deleteScheduleItem(int index) {
        if (index >= 0 && index < dayPlan.size()) {
            dayPlan.remove(index);
            return true;
        }
        return false;
    }
}
