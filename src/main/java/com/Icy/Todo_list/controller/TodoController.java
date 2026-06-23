package com.Icy.Todo_list.controller;

import com.Icy.Todo_list.service.TodoService;
import com.Icy.Todo_list.model.Todo;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> getAllTodos(){
        return todoService.getAllTodos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Todo createTodo(@RequestBody Todo todo) {
        return todoService.createTodo(todo);
    }

    @GetMapping("/{id}")
    public Todo getTodoWithId(@PathVariable Long id){
        return todoService.getTodoById(id);
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable Long id, @RequestBody Todo updateTodo){
        return todoService.updateTodo(id, updateTodo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable Long id){
        todoService.deleteTodo(id);
    }

    @GetMapping("/completed")
    public List<Todo> getCompletedTodos(){
        return todoService.getCompletedTodos();
    }

    @GetMapping("/due")
    public List<Todo> getTodosByDueDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        return todoService.getTodosByDueDate(date);
    }
}
