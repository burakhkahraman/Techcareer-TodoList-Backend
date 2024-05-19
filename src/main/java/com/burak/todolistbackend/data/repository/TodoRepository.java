package com.burak.todolistbackend.data.repository;

import com.burak.todolistbackend.data.entity.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface TodoRepository extends CrudRepository<Todo,Long> {
}
