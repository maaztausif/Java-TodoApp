package com.maaz.Java_ToDoApp.service;

import com.maaz.Java_ToDoApp.dto.todolist.SavingTaskRequest;
import com.maaz.Java_ToDoApp.dto.todolist.SavingTaskResponse;
import com.maaz.Java_ToDoApp.model.Priority;
import com.maaz.Java_ToDoApp.model.Task;
import com.maaz.Java_ToDoApp.model.User;
import com.maaz.Java_ToDoApp.repo.Authrepo;
import com.maaz.Java_ToDoApp.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class TaskService {
    @Autowired
    TaskRepo repoTask;

    @Autowired
    Authrepo authrepo;

    @Autowired
    public SavingTaskResponse addingTask(SavingTaskRequest request) {
        User user = authrepo.getById(request.userId());

        Task userTask = new Task();

        userTask.setDescription(userTask.getDescription());
        userTask.setCompleted(request.isCompleted());
        userTask.setCreatedAt(LocalDateTime.now());
        userTask.setDueDate(request.dueDate());
        userTask.setTitle(request.title());

        userTask.setPriority(Priority.values()[request.priority()]);
//        userTask.setTaskCategories();

 return null;
    }
}
