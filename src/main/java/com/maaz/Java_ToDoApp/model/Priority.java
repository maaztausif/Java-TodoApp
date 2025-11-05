package com.maaz.Java_ToDoApp.model;

public enum Priority {
    LOW("low"),
    MEDIUM("medium"),
    HIGH("high");

    private String description;

    Priority(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
