package com.burak.todolistbackend.service;

import com.burak.todolistbackend.model.Todo;
import com.burak.todolistbackend.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public List<Todo> getCompletedTodos() {
        return todoRepository.findByCompleted(true);
    }

    public List<Todo> getIncompleteTodos() {
        return todoRepository.findByCompleted(false);
    }

    public Todo getTodoById(Long id) {
        return todoRepository.findById(id).orElse(null);
    }

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo updateTodo(Long id, Todo todo) {
        Todo existingTodo = todoRepository.findById(id).orElse(null);
        if (existingTodo != null) {
            existingTodo.setTitle(todo.getTitle());
            existingTodo.setCompleted(todo.isCompleted());
            return todoRepository.save(existingTodo);
        }
        return null;
    }

    public void deleteTodoById(Long id) {
        todoRepository.deleteById(id);
    }
}
