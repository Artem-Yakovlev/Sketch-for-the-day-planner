package com.badger.diary;

import java.util.HashMap;

/**
 * @author Artem Yakovlev
 * @version 0.3
 *
 * Diary - синглтон словаря. Синглтон был использован, так как в данной программе
 * не будет многопоточности.
 * */
public class Diary {


    private static Diary instance;
    // HashMap, где ключ это дата
    HashMap<String, DiaryDay> diaryDayHashMap;
    // План дня, открытый в данный момент
    DiaryDay selectedDay;

    // Приватный конструктр
    private Diary() {
        diaryDayHashMap = new HashMap<>();
    }

    // Синглтон
    public static synchronized Diary getInstance() {
        if (instance == null) {
            instance = new Diary();
        }
        return instance;
    }

    // Добавить план на новую дату
    public boolean createDay(String date) {
        if (!diaryDayHashMap.containsKey(date)) {
            selectedDay = new DiaryDay(date);
            diaryDayHashMap.put(date, selectedDay);
            return true;
        }
        return false;
    }

    // Открыть план на другой день по дате
    public boolean selectDay(String date) {
        if (diaryDayHashMap.containsKey(date)) {
            selectedDay = diaryDayHashMap.get(date);
            return true;
        }
        return false;
    }

    // Удалить дневной план по дате
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
