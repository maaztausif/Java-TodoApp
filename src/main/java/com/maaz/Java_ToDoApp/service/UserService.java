package com.maaz.Java_ToDoApp.service;

import com.maaz.Java_ToDoApp.model.User;
import com.maaz.Java_ToDoApp.repo.Authrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private Authrepo repo;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User userSave(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }
}
