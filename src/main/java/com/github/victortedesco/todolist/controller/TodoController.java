package com.github.victortedesco.todolist.controller;

import com.github.victortedesco.todolist.model.Todo;
import com.github.victortedesco.todolist.model.TodoDTO;
import com.github.victortedesco.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/todo")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("getall")
    @ResponseBody
    public List<Todo> getAll() {
        return todoService.getAll();
    }

    @GetMapping("get/name/{name}")
    @ResponseBody
    public List<Todo> getByName(@PathVariable String name) {
        return todoService.getByName(name);
    }

    @GetMapping("get/id/{uuid}")
    @ResponseBody
    public ResponseEntity<Todo> getById(@PathVariable UUID uuid) {
        return todoService.getById(uuid).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("get/status/{completed}")
    @ResponseBody
    public List<Todo> getByStatus(@PathVariable boolean completed) {
        return todoService.getByStatus(completed);
    }

    @PostMapping
    public ResponseEntity<String> addTodo(@RequestBody TodoDTO todo) {
        if (todoService.addTodo(todo)) {
            return ResponseEntity.ok("Tarefa adicionada com sucesso.");
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("update/{uuid}")
    public ResponseEntity<String> updateTodo(@PathVariable UUID uuid, @RequestBody TodoDTO todo) {
        if (todoService.updateTodo(uuid, todo)) {
            return ResponseEntity.ok("Tarefa atualizada com sucesso.");
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("delete/{uuid}")
    public ResponseEntity<String> deleteTodo(@PathVariable UUID uuid) {
        if (todoService.removeTodo(uuid)) {
            return ResponseEntity.ok("Tarefa removida com sucesso.");
        }
        return ResponseEntity.badRequest().build();
    }
}
