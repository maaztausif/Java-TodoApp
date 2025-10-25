package com.maaz.Java_ToDoApp.dto;

public record UserSignUpRequest(
        String email,
        String fName,
        String lName,
        String password
) {

}
