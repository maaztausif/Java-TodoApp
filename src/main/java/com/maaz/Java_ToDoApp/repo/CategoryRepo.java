package com.maaz.Java_ToDoApp.repo;

import com.maaz.Java_ToDoApp.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Categories,Integer> {
}
