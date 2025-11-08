package com.maaz.Java_ToDoApp.service;

import com.maaz.Java_ToDoApp.dto.todolist.*;
import com.maaz.Java_ToDoApp.model.*;
import com.maaz.Java_ToDoApp.repo.Authrepo;
import com.maaz.Java_ToDoApp.repo.CategoryRepo;
import com.maaz.Java_ToDoApp.repo.TaskCategoriesRepo;
import com.maaz.Java_ToDoApp.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    TaskRepo repoTask;

    @Autowired
    Authrepo authrepo;

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    TaskCategoriesRepo taskCategoriesRepo;


    public SavingTaskResponse addingTask(SavingTaskRequest request) {
        System.out.println("Request received: " + request);

        System.out.println("USer Id from req");
        System.out.println(request.userId());

        User user = authrepo.getById(request.userId());

        Task userTask = new Task();

        userTask.setDescription(request.description());
        userTask.setCompleted(request.isCompleted());
        userTask.setCreatedAt(LocalDateTime.now());
        userTask.setDueDate(request.dueDate());
        userTask.setCreatedAt(LocalDateTime.now());
        userTask.setUpdatedAt(LocalDateTime.now());
        userTask.setUserId(request.userId());
        userTask.setTitle(request.title());

        userTask.setPriority(Priority.values()[request.priority()]);

        Task savedTask = repoTask.save(userTask);

        System.out.println("saved task response = " + savedTask);
        List<Task_Categories> taskCategories = new ArrayList<>();

        for (Integer categoryId : request.categoryIds()){
            Categories selectedCategories = categoryRepo.getById(categoryId);
            Task_Categories taskCategory = new Task_Categories();
            taskCategory.setCategory(selectedCategories);
            taskCategory.setTask(savedTask);
            taskCategories.add(taskCategory);

        }

        taskCategoriesRepo.saveAll(taskCategories);

//        System.out.println(savedTaskCategories);

        System.out.println("=====================0000===================");
        System.out.println("user task = " + userTask);

        return new SavingTaskResponse(true,"Task Save");


    }

    public List <GetAllTaskResponse> getAllTasks(Integer userId) {
        List<Task> allTasks = repoTask.findByUserId(userId);

        return allTasks.stream().map(task -> {
            List<String> categoryNames = task.getTaskCategories().stream()
                    .map(tc -> tc.getCategory().getName())
                    .toList();

            return new GetAllTaskResponse(
                    task.getTask_id(),
                    task.getTitle(),
                    task.getDescription(),
                    task.getDueDate(),
                    task.getPriority().ordinal(),
                    task.isCompleted(),
                    categoryNames
            );
        }).toList();
    }

    public GetSingleTaskResposne getTask(Integer taskId) {
        Task userSelectedTask = repoTask.getById(taskId);

        List<String> categoryNames = userSelectedTask.getTaskCategories().stream()
                .map(tc -> tc.getCategory().getName())
                .toList();

        return new GetSingleTaskResposne(
                userSelectedTask.getTask_id(),
                userSelectedTask.getTitle(),
                userSelectedTask.getDescription(),
                userSelectedTask.getDueDate(),
                userSelectedTask.getPriority().ordinal(),
                userSelectedTask.isCompleted(),
                categoryNames
        );
    }

    public DeleteTaskResponse deleteTaskById(Integer taskId) {
        repoTask.deleteById(taskId);
        return new DeleteTaskResponse(true,"Task Deleted Successfully");
    }

    public UpdateTaskResponse updateTask(UpdateTaskRequest request) {
        Task getTask = repoTask.getById(request.taskId());
        getTask.setTitle(request.title());
        getTask.setDescription(request.description());
        getTask.setDueDate(request.dueDate());
        getTask.setUpdatedAt(LocalDateTime.now());
        getTask.setCompleted(request.isCompleted());

        getTask.setPriority(Priority.values()[request.priority()]);

        List<Task_Categories> taskCategories = new ArrayList<>();

        for (Integer categoryId : request.categoryIds()){
            Categories selectedCategories = categoryRepo.getById(categoryId);
            Task_Categories taskCategory = new Task_Categories();
            taskCategory.setCategory(selectedCategories);

            taskCategory.setTask(getTask);
            taskCategories.add(taskCategory);

        }
        getTask.setTaskCategories(taskCategories);
        repoTask.save(getTask);

        return new UpdateTaskResponse(true,"Updated Successfully!");



    }
}
