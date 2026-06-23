package com.Icy.Todo_list.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "todos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private boolean completed;
    private LocalDate dueDate;

    public Todo(String title, String description, LocalDate dueDate){
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        completed = false;
    }
}
