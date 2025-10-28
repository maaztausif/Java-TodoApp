package com.maaz.Java_ToDoApp.service;

import com.maaz.Java_ToDoApp.dto.auth.*;
import com.maaz.Java_ToDoApp.model.User;
import com.maaz.Java_ToDoApp.repo.Authrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

   @Autowired
    Authrepo repo;

    public ValidEmailResponse validEmail(ValidEmailRequest validEmailRequest) {
        if(repo.findByEmail(validEmailRequest.email()) != null){
            return new ValidEmailResponse(true,"Valid email");
        }
        return new ValidEmailResponse(false,"Email not found!");
    }

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

    public ResetPasswordResponse resetPassword(ResetPasswordRequest resetPassReq) {
        User user = repo.findByEmail(resetPassReq.email());
        if(user != null){
            if (!isValidPassword(resetPassReq.password())) {
                return new ResetPasswordResponse(false, "Password must be at least 8 characters long, contain a number and a special character.");
            }else{
                user.setPassword(resetPassReq.password());
                user.setUpdatedAt(LocalDateTime.now());
                repo.save(user);
                return  new ResetPasswordResponse(true,"Password Updated");
            }
        }
        return  new ResetPasswordResponse(true,"Something went wrong!");
    }

    public ChangePasswordResponse changePassword(ChangePasswordRequest changePassReq) {
        User user = repo.findByEmail(changePassReq.email());
        if(user.getEmail().equals(changePassReq.email())){

            if (!isValidPassword(changePassReq.password())) {
                return new ChangePasswordResponse(false, "Password must be at least 8 characters long, contain a number and a special character.");
            }else{
                user.setPassword(changePassReq.password());
                user.setUpdatedAt(LocalDateTime.now());
                repo.save(user);
                return new ChangePasswordResponse(true,"Password Changed");
            }
        }
        return new ChangePasswordResponse(false,"Password Not Updated");
    }

    private boolean isValidPassword(String password) {
        // Minimum 8 characters, at least one letter, one number, and one special character
        String passwordRegex = "^(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-={}:;\"'<>,.?/]).{8,}$";
        return password != null && password.matches(passwordRegex);
    }


}
