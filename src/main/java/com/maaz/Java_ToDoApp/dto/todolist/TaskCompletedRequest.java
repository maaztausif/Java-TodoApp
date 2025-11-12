package com.maaz.Java_ToDoApp.dto.todolist;

public record TaskCompletedRequest(Integer taskId, Boolean isCompleted) {
}
