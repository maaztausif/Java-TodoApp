package com.maaz.Java_ToDoApp.repo;

import com.maaz.Java_ToDoApp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task,Integer> {
    List<Task> findByUserId(int userId); // query by user_id
}
