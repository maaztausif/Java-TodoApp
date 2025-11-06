package com.maaz.Java_ToDoApp.controller;

import com.maaz.Java_ToDoApp.dto.todolist.SavingTaskRequest;
import com.maaz.Java_ToDoApp.dto.todolist.SavingTaskResponse;
import com.maaz.Java_ToDoApp.service.JwtService;
import com.maaz.Java_ToDoApp.service.TaskService;
import com.maaz.Java_ToDoApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<SavingTaskResponse> addingTask(SavingTaskRequest request){
        SavingTaskResponse response = service.addingTask(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
