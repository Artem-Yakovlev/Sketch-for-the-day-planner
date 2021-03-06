package com.badger.correctdata;

import java.text.SimpleDateFormat;

/**
 * @author Artem Yakovlev
 * @version 0.3
 *
 *  С помощью данного класса производится проверка входных данных на валидность
 * */

public class ValidCheck {

    //Формат даты, используется при проверке на валидность
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    //Проверка даты на валидность
    public static boolean isDate(String date) {
        try {
            dateFormat.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Проверка числа на валидность
    public static boolean isNumber(String number){
        return number.matches("\\d+");
    }
}
