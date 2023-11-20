package com.github.victortedesco.todolist.model;

import lombok.Data;

@Data
public class TodoDTO {

    private String name;
    private boolean taskCompleted;

    public Todo transformToTodo() {
        return new Todo(name, taskCompleted);
    }
}
