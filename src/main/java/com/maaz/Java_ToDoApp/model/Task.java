package com.maaz.Java_ToDoApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int task_id;
    @Column(name = "user_id")
    private int userId;
    private String title;
    private String description;
    private LocalDateTime dueDate;

    @Enumerated(EnumType.ORDINAL)
    private Priority priority;
    private boolean isCompleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "task",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Task_Categories> taskCategories;


}



