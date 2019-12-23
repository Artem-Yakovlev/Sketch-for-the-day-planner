package com.badger.scheduleitem;

/**
 * @author Artem Yakovlev
 * @version 0.3
 * Данное перечисление представляет собой набор состояний для MarkableItem.
 * Поле title нужно для более удобного вывода на MarkableItem на экран
 */
public enum MarkableItemState {

    WAIT("Ожидание"), PERFORMED("Выполнено"), FAILED("Провалено");

    private String title;

    MarkableItemState(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
