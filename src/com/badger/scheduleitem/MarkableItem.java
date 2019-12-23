package com.badger.scheduleitem;

/**
 * @author Artem Yakovlev
 * @version 0.3
 * <p>
 * MarkableItem - пункт плана в который можно быть в трех состояниях:
 * Выполненно (галочка)
 * Ожидание (пустое поле)
 * Провалено (крестик)
 * <p>
 * Большинство методов возвращает true/false для того, чтобы в Main можно было
 * вывести правильную подсказку
 */
public class MarkableItem extends ScheduleItem {

    private MarkableItemState state = MarkableItemState.WAIT;

    public MarkableItem(String text) {
        super(text);
    }

    // Отметить как выполненное
    @Override
    public boolean accept() {
        if (state == MarkableItemState.WAIT) {
            state = MarkableItemState.PERFORMED;
            return true;
        } else {
            return false;
        }
    }

    // Отметить как проваленное
    @Override
    public boolean cancel() {
        if (state == MarkableItemState.WAIT) {
            state = MarkableItemState.FAILED;
            return true;
        } else {
            return false;
        }
    }

    // Вернуть текст для вывода
    @Override
    public String getTextOfSchedule() {
        return text.substring(0, 1).toUpperCase() + text.substring(1) + " (" + state.getTitle() + ")";
    }
}
