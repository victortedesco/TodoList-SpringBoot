package com.github.victortedesco.todolist.repository;

import com.github.victortedesco.todolist.model.Todo;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TodoRepository extends CrudRepository<Todo, UUID> {

    @NonNull
    List<Todo> findAll();
}
