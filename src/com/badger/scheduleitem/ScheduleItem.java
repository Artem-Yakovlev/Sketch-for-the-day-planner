package com.badger.scheduleitem;
/**
 * @author Artem Yakovlev
 * @version 0.3
 * Абстрактный класс описывает пункт меню. Этот класс создан, так как
 * для расширения функционала может понадобиться добавить новые типы
 * пунктов меню.
 * */
public abstract class ScheduleItem implements ScheduleItemBehaviour {

    protected String text;

    public ScheduleItem(String text) {
        setText(text);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text.trim();
    }

}
