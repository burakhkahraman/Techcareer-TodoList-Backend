package com.burak.todolistbackend.business.service.impl;

import com.burak.todolistbackend.business.service.ITodoServices;
import com.burak.todolistbackend.data.entity.Todo;
import com.burak.todolistbackend.data.repository.TodoRepository;
import com.burak.todolistbackend.exception.BurakKahramanException;
import com.burak.todolistbackend.exception.Resource404NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TodoServicesImpl implements ITodoServices<Todo> {
    private TodoRepository todoRepository;

    @Autowired
    public TodoServicesImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    @Transactional
    public Todo todoServiceCreate(Todo todo) {
        if (todo != null) {
            todoRepository.save(todo);
            return todo;
        }
        return null;
    }

    @Override
    public Todo todoServiceFindById(Long id) {
        Todo todo = null;
        if (id != null) {
            todo = todoRepository.findById(id).orElseThrow(() -> new Resource404NotFoundException("There is not any todo which has id of " + id));
        } else if (id == null) {
            throw new BurakKahramanException("id is null!");
        }
        return todo;
    }

    @Override
    public List<Todo> todoServiceFindAll() {
        return (List<Todo>) todoRepository.findAll();
    }

    @Override
    @Transactional
    public Todo todoServiceUpdate(Long id, Todo todo) {
        Todo updatingTodo = todoServiceFindById(id);
        if (updatingTodo != null) {
            updatingTodo.setDone(todo.isDone());
            updatingTodo.setTask(todo.getTask());
            todoRepository.save(updatingTodo);
            return updatingTodo;
        }
        else
            throw new Resource404NotFoundException("There is not any todo which has id of " + id);
    }

    @Override
    @Transactional
    public Todo todoDeleteById(Long id) {
        Todo todo = todoServiceFindById(id);
        if (todo != null) {
            todoRepository.deleteById(id);
            return todo;
        }
        throw new Resource404NotFoundException("There is not any todo which has id of " + id);
    }

}
