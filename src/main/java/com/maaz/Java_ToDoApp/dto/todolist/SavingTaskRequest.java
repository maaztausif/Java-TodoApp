package com.maaz.Java_ToDoApp.dto.todolist;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public record SavingTaskRequest(String des, LocalDateTime dueDate, Boolean isCompleted , int priority, String title, int userId,
                                List<Integer> categoryIds) {
}
