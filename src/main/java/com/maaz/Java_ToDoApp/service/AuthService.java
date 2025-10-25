package com.maaz.Java_ToDoApp.service;

import com.maaz.Java_ToDoApp.dto.SignResponse;
import com.maaz.Java_ToDoApp.dto.UserSignUpRequest;
import com.maaz.Java_ToDoApp.model.User;
import com.maaz.Java_ToDoApp.repo.Authrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuthService {

   @Autowired
    Authrepo repo;

    public SignResponse saveUser(UserSignUpRequest userInfo) {



        if (repo.findByEmail(userInfo.email()) != null) {
            return new SignResponse(false, "Email already registered!");
        }

        User user = new User();
        user.setFName(userInfo.fName());
        user.setLName(userInfo.lName());
        user.setEmail(userInfo.email());
        user.setPassword(userInfo.password());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        repo.save(user);

        SignResponse signUpResponse = new SignResponse(
                true,"SignUp Successfull"
        );

     return  signUpResponse;
    }
}
