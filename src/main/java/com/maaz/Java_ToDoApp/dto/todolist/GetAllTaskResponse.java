package com.maaz.Java_ToDoApp.dto.todolist;

import java.time.LocalDateTime;
import java.util.List;

public record GetAllTaskResponse(
        int taskId,
        String title,
        String description,
        LocalDateTime dueDate,
        int priority,
        boolean isCompleted,
        List<String> categories // category names
) {
}
