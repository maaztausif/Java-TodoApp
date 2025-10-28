package com.maaz.Java_ToDoApp.controller;

import com.maaz.Java_ToDoApp.dto.auth.*;
import com.maaz.Java_ToDoApp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
        SignResponse SignUpResponse = service.saveUser(userInfoRequest);
        return new ResponseEntity<>(SignUpResponse, HttpStatus.CREATED);
    }

    @PostMapping("Signin")
    public ResponseEntity<SignInResponse> useSignIn(@RequestBody SignInRequest userLoginRequest){
        SignInResponse userSignInResponse = service.SignIn(userLoginRequest);
        return new ResponseEntity<>(userSignInResponse,HttpStatus.CREATED);
    }

    @PostMapping("validEmail")
    public  ResponseEntity<ValidEmailResponse> validEmail(@RequestBody ValidEmailRequest validEmailRequest){
        ValidEmailResponse validEmailResponse = service.validEmail(validEmailRequest);
        return  new ResponseEntity<>(validEmailResponse,HttpStatus.CREATED);
    }

    @PostMapping("resetPassword")
    public ResponseEntity<ResetPasswordResponse> resetPassword(@RequestBody ResetPasswordRequest resetPassReq){
        ResetPasswordResponse resetPasswordResponse = service.resetPassword(resetPassReq);
        return new ResponseEntity<>(resetPasswordResponse,HttpStatus.CREATED);
    }

    @PostMapping("changePassword")
    public ResponseEntity<ChangePasswordResponse> changePassword(@RequestBody ChangePasswordRequest changePassReq){
        ChangePasswordResponse changePasswordResponse = service.changePassword(changePassReq);
        return new ResponseEntity<>(changePasswordResponse,HttpStatus.CREATED);
    }


}
