package com.maaz.Java_ToDoApp.dto.auth;

public record ResetPasswordRequest(String email,String password,Boolean isSuccess,String message,String otp) {
    public static ResetPasswordRequest body(boolean isSuccess,String message){
        return new ResetPasswordRequest(null,null,isSuccess,message,null);
    }
}
