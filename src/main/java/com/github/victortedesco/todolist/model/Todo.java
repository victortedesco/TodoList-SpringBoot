package com.github.victortedesco.todolist.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.PRIVATE)
    private UUID id;

    private String name;

    private boolean taskCompleted;

    @Setter(AccessLevel.PRIVATE)
    private Date dateAdded = new Date();

    public Todo(String name, boolean taskCompleted) {
        this.name = name;
        this.taskCompleted = taskCompleted;
    }
}
