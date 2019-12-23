package com.badger.diary;

import com.badger.scheduleitem.ScheduleItem;

import java.util.HashMap;

public class Diary {

    private static Diary instance;
    HashMap<String, DiaryDay> diaryDayHashMap;
    DiaryDay selectedDay;

    private Diary() {
        diaryDayHashMap = new HashMap<>();
        // Хватаемся за данные
    }

    public static synchronized Diary getInstance() {
        if (instance == null) {
            instance = new Diary();
        }
        return instance;
    }

    public boolean createDay(String date) {
        if (!diaryDayHashMap.containsKey(date)) {
            selectedDay = new DiaryDay(date);
            diaryDayHashMap.put(date, selectedDay);
            return true;
        }
        return false;
    }

    public boolean selectDay(String date) {
        if (diaryDayHashMap.containsKey(date)) {
            selectedDay = diaryDayHashMap.get(date);
            return true;
        }
        return false;
    }

    public boolean removeDay(String date) {
        if (diaryDayHashMap.containsKey(date)) {
            if (selectedDay.equals(diaryDayHashMap.get(date)))
                selectedDay = null;
            diaryDayHashMap.remove(date);
            return true;
        }
        return false;
    }

    public DiaryDay getSelectedDay() {
        return selectedDay;
    }
}
