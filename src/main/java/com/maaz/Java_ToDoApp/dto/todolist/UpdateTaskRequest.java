package com.maaz.Java_ToDoApp.dto.todolist;

import java.time.LocalDateTime;
import java.util.List;

public record UpdateTaskRequest(
        Integer taskId ,String description, LocalDateTime dueDate, Boolean isCompleted , Integer priority, String title, Integer userId,
        List<Integer> categoryIds
) {
}
