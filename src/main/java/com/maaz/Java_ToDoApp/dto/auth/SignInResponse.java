package com.maaz.Java_ToDoApp.dto.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.maaz.Java_ToDoApp.model.User;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record SignInResponse(boolean success,
                             String message,
                             String fName,
                             String lName,
                             String email,
                             String token
) {

    // Static factory methods for clarity
    public static SignInResponse success(User user,String message,String token) {
        return new SignInResponse(true, message, user.getFName(), user.getLName(), user.getEmail(),token);
    }

    public static SignInResponse failure(Boolean success,String message) {
        return new SignInResponse(success, message, null, null, null,null);
    }
}
