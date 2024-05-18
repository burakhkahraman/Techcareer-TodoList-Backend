package com.burak.todolistbackend.data.repository;

import com.burak.todolistbackend.data.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo,Long> {

    List<ToDo> findAllByCompleted(boolean completed);

}
