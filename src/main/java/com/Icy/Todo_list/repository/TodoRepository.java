package com.Icy.Todo_list.repository;

import com.Icy.Todo_list.model.Todo;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByCompleted(boolean completed);
    List<Todo> findByDueDate(LocalDate dueDate);
    List<Todo> findByPriority(String priority);
}
