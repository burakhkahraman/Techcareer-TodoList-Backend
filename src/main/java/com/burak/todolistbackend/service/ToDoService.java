package com.burak.todolistbackend.service;

import com.burak.todolistbackend.data.entity.ToDo;
import com.burak.todolistbackend.data.repository.ToDoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoService {
    private final ToDoRepository toDoRepository;

    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public List<ToDo> getAllToDos(){
        return toDoRepository.findAll();
    }

    public ToDo getToDoById(Long id) {
        return toDoRepository.findById(id).orElse(null);
    }

    public ToDo createToDo(ToDo toDo) {
        return toDoRepository.save(toDo);
    }

    public ToDo updateToDo(Long id, ToDo updatedToDo) {
        ToDo existingToDo = toDoRepository.findById(id).orElse(null);
        if (existingToDo == null) {
            return null;
        }

        existingToDo.setTask(updatedToDo.getTask());
        existingToDo.setCompleted(updatedToDo.isCompleted());

        return toDoRepository.save(existingToDo);
    }

    public void deleteToDo(Long id) {
        toDoRepository.deleteById(id);
    }


    public List<ToDo> getCompletedTodos(boolean completed){
        return toDoRepository.findAllByCompleted(completed);
    }
}
