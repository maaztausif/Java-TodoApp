package com.maaz.Java_ToDoApp.dto.auth;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ValidEmailResponse(Boolean isSuccess, String message) {

    public static ValidEmailResponse success(Boolean isSuccess, String message) {
        return new ValidEmailResponse(isSuccess,message);
    }

}



