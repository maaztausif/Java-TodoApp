package com.maaz.Java_ToDoApp.controller;

import com.maaz.Java_ToDoApp.dto.todolist.*;
import com.maaz.Java_ToDoApp.service.JwtService;
import com.maaz.Java_ToDoApp.service.TaskService;
import com.maaz.Java_ToDoApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ItemsController {

    @Autowired
    private TaskService service;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("addTask")
    public ResponseEntity<SavingTaskResponse> addingTask(@RequestBody SavingTaskRequest request){
        System.out.println("this is the req = " + request);
        SavingTaskResponse response = service.addingTask(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("tasks/{userId}")
    public ResponseEntity<List<GetAllTaskResponse>> getAllTaskList(@PathVariable Integer userId){
        List<GetAllTaskResponse> response = service.getAllTasks(userId);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping("task/{taskId}")
    public ResponseEntity<GetSingleTaskResposne> getTaskById(@PathVariable Integer taskId){
        GetSingleTaskResposne response = service.getTask(taskId);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @PostMapping("updateTask")
    public ResponseEntity<UpdateTaskResponse> updateTask(@RequestBody UpdateTaskRequest request){
        UpdateTaskResponse response = service.updateTask(request);
        return new ResponseEntity<>(response,HttpStatus.CREATED);

    }

    @DeleteMapping("deleteTask/{taskId}")
    public ResponseEntity<DeleteTaskResponse> deleteTask(@PathVariable Integer taskId){
        DeleteTaskResponse response = service.deleteTaskById(taskId);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @PostMapping("taskComplete")
    public ResponseEntity<TaskCompletedResponse> taskCompleted(@RequestBody TaskCompletedRequest request){
        TaskCompletedResponse response = service.taskCompleted(request);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
}
