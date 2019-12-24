package com.badger.diary;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * @author Artem Yakovlev
 * @version 0.3
 * <p>
 * Diary - синглтон словаря. Синглтон был использован, так как в данной программе
 * не будет многопоточности.
 *
 * Сама база для этого словаря хранится в JSON файле
 */
public class Diary {


    private static Diary instance;
    // HashMap, где ключ это дата
    private HashMap<String, DiaryDay> diaryDayHashMap;
    // План дня, открытый в данный момент
    private DiaryDay selectedDay;

    // Это нужно для JSON
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    // Тип для десериализации
    private static final Type DIARY_MAP_TYPE = new TypeToken<HashMap<String, DiaryDay>>() {}.getType();
    // Имя json файла, где хранится HashMap
    private static final String FILE_STORE_NAME = "DiaryStore.json";

    // Приватный конструктр
    private Diary() {

        // Эсли файл нормально открылся, то читаем из него, иначе создаем HashMap с нуля
        try {
            String jsonStore = new String(Files.readAllBytes(Paths.get(FILE_STORE_NAME)));
            diaryDayHashMap = GSON.fromJson(jsonStore, DIARY_MAP_TYPE);
        } catch (IOException e) {
            diaryDayHashMap = new HashMap<>();
        }

    }

    public DiaryDay getSelectedDay() {
        return selectedDay;
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

    // Сохраняем JSON
    public boolean saveDiary() {
        try {
            FileWriter fileWriter = new FileWriter(FILE_STORE_NAME, false);
            fileWriter.write(GSON.toJson(diaryDayHashMap));
            fileWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
