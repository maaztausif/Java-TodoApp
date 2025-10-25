package com.maaz.Java_ToDoApp.controller;

import com.maaz.Java_ToDoApp.dto.SignResponse;
import com.maaz.Java_ToDoApp.dto.UserSignUpRequest;
import com.maaz.Java_ToDoApp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/api")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("Signup")
    public ResponseEntity<SignResponse> placeOrder(@RequestBody UserSignUpRequest userInfoRequest){
        SignResponse orderResponse = service.saveUser(userInfoRequest);
        return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
    }


}
