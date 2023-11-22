package com.github.victortedesco.todolist.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractAuditable;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class Todo extends AbstractAuditable<String, UUID> {

    private String name;
    private boolean taskCompleted;

    public Todo(String name, boolean taskCompleted) {
        this.name = name;
        this.taskCompleted = taskCompleted;
    }
}
