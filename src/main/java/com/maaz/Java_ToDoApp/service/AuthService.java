package com.maaz.Java_ToDoApp.service;

import com.maaz.Java_ToDoApp.dto.auth.SignInRequest;
import com.maaz.Java_ToDoApp.dto.auth.SignInResponse;
import com.maaz.Java_ToDoApp.dto.auth.SignResponse;
import com.maaz.Java_ToDoApp.dto.auth.UserSignUpRequest;
import com.maaz.Java_ToDoApp.model.User;
import com.maaz.Java_ToDoApp.repo.Authrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

    public SignInResponse SignIn(SignInRequest userLoginRequest) {

        User user = repo.findByEmail(userLoginRequest.email());
        if(user == null){
            return SignInResponse.failure(false,"Email not registered!");
        }else {
            if(user.getEmail().equals(userLoginRequest.email())){
                if(user.getPassword().equals(userLoginRequest.password()) ){
                    return SignInResponse.success(user,"Success");
                }else{
                    return SignInResponse.failure(false,"Wrong Password!");
                }
            }else{
                return SignInResponse.failure(false,"Wrong Email!");

            }
        }
    }
}
