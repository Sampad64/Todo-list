package com.Icy.Todo_list.service;

import com.Icy.Todo_list.model.Todo;
import org.springframework.stereotype.Service;
import com.Icy.Todo_list.repository.TodoRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class TodoService {
    private final TodoRepository repo;

    public TodoService(TodoRepository repo) {
        this.repo = repo;
    }

    public List<Todo> getAllTodos() { return repo.findAll(); }


    public Todo getTodoById(Long id){
        return repo.findById(id).
                orElseThrow(() -> new RuntimeException());
    }


    public Todo createTodo(Todo todo){
        return repo.save(todo);
    }

    public Todo updateTodo(Long id, Todo updated){
        Todo existing = getTodoById(id);
        existing.setTitle(updated.getTitle());
        existing.setDescription(updated.getDescription());
        existing.setCompleted(updated.isCompleted());
        existing.setDueDate(updated.getDueDate());
        return repo.save(existing);
    }

    public void deleteTodo(Long id){
        repo.delete(getTodoById(id));
    }

    public List<Todo> getCompletedTodos(){
        return repo.findByCompleted(true);
    }

    public List<Todo> getTodosByDueDate(LocalDate date){
        return repo.findByDueDate(date);
    }

    public List<Todo> getByPriority(String priority){ return repo.findByPriority(priority); }
}
