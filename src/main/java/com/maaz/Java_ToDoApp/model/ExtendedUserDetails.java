package com.maaz.Java_ToDoApp.model;

import org.springframework.security.core.userdetails.UserDetails;

public interface ExtendedUserDetails extends UserDetails {
    String getEmail();
}
