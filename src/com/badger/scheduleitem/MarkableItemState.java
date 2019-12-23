package com.badger.scheduleitem;

public enum MarkableItemState {

    WAIT("Ожидание"), PERFORMED("Выполнено"), FAILED("Провалено");

    private String title;

    MarkableItemState(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
