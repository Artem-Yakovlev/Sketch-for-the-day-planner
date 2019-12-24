package com.badger.diary;

import com.badger.scheduleitem.MarkableItem;
import com.badger.scheduleitem.ScheduleItem;

import java.util.ArrayList;

/**
 * @author Artem Yakovlev
 * @version 0.3
 * <p>
 * DiaryDay - является страницой дневника, является неким буфером между Diary и ScheduleItem
 */

public class DiaryDay {
    // Дата - уникальный идентификатор в HashMap
    private String date;
    // Здесь хранятся все дела на день
    private ArrayList<MarkableItem> dayPlan = new ArrayList<>();

    public DiaryDay(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public ArrayList<MarkableItem> getDayPlan() {
        return dayPlan;
    }

    // Отметить задачу выполненной по index
    public boolean acceptScheduleItem(int index) {
        if (index >= 0 && index < dayPlan.size()) {
            return dayPlan.get(index).accept();
        } else {
            return false;
        }
    }

    // Отметить задачу проваленной по index
    public boolean cancelScheduleItem(int index) {
        if (index >= 0 && index < dayPlan.size()) {
            return dayPlan.get(index).cancel();
        } else {
            return false;
        }
    }

    // Добавить задачу в план
    public boolean addScheduleItem(String text) {

        for (ScheduleItem scheduleItem : dayPlan) {

            if (scheduleItem.getText().toLowerCase().equals(text.toLowerCase())) {
                return false;
            }
        }
        dayPlan.add(new MarkableItem(text));
        return true;
    }

    // Удалить задачу по index
    public boolean deleteScheduleItem(int index) {
        if (index >= 0 && index < dayPlan.size()) {
            dayPlan.remove(index);
            return true;
        }
        return false;
    }
}
