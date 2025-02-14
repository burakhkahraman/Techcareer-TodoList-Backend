package com.burak.todolistbackend.controller.api.impl;

import com.burak.todolistbackend.business.service.ITodoServices;
import com.burak.todolistbackend.controller.api.ITodoApi;
import com.burak.todolistbackend.data.entity.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class TodoApiImpl implements ITodoApi<Todo> {

    private final ITodoServices iTodoServices;

    @Override
    @PostMapping("/todos")
    public ResponseEntity<?> todoApiCreate(@RequestBody Todo todo) {
        return ResponseEntity.ok(iTodoServices.todoServiceCreate(todo));
    }

    @Override
    @GetMapping("/todos/{id}")
    public ResponseEntity<?> todoApiFindById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(iTodoServices.todoServiceFindById(id));
    }

    @Override
    @GetMapping("/todos")
    public ResponseEntity<?> todoApiFindAll() {
        return ResponseEntity.ok().body(iTodoServices.todoServiceFindAll());
    }

    @Override
    @PutMapping("/todos/{id}")
    public ResponseEntity<?> todoApiUpdate(@PathVariable(name = "id") Long id, @RequestBody Todo todo) {
        return ResponseEntity.status(200).body(iTodoServices.todoServiceUpdate(id,todo));
    }

    @Override
    @DeleteMapping("/todos/{id}")
    public ResponseEntity<?> todoApiDeleteById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(200).body(iTodoServices.todoDeleteById(id));
    }
}
