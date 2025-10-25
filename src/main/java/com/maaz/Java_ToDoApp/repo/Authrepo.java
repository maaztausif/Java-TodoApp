package com.maaz.Java_ToDoApp.repo;

import com.maaz.Java_ToDoApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Authrepo extends JpaRepository<User,Integer> {
    User findByEmail(String email);

}
