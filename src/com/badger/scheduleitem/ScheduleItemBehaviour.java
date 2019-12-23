package com.badger.scheduleitem;

/**
 * @author Artem Yakovlev
 * @version 0.3
 * Данный интерфейс описывает поведение пункта в плане дня.
 */
public interface ScheduleItemBehaviour {

    // Пункт выполнен
    boolean accept();

    // Пункт провален
    boolean cancel();

    // Получить текст и состояние пункта меню для вывода
    String getTextOfSchedule();
}
