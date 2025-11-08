package com.maaz.Java_ToDoApp.repo;

import com.maaz.Java_ToDoApp.model.Task_Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskCategoriesRepo extends JpaRepository<Task_Categories,Integer> {
}
