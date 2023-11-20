package com.github.victortedesco.todolist.service;

import com.github.victortedesco.todolist.model.Todo;
import com.github.victortedesco.todolist.model.TodoDTO;
import com.github.victortedesco.todolist.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    private static boolean isMatch(String fullName, String searchTerm) {
        String normalizedFullName = removeAccents(fullName.toLowerCase());
        String normalizedSearchTerm = removeAccents(searchTerm.toLowerCase());

        String pattern = String.join(".*", normalizedSearchTerm.split(" "));
        Pattern regex = Pattern.compile(pattern);

        Matcher matcher = regex.matcher(normalizedFullName);

        return matcher.find();
    }

    private static String removeAccents(String input) {
        String normalizedString = Normalizer.normalize(input, Normalizer.Form.NFD);
        return normalizedString.replaceAll("\\p{M}", "");
    }

    public List<Todo> getAll() {
        return this.todoRepository.findAll();
    }

    public List<Todo> getByName(String name) {
        return this.todoRepository.findAll().stream().filter(todo -> isMatch(todo.getName(), name)).collect(Collectors.toList());
    }

    public List<Todo> getByStatus(boolean completed) {
        return this.todoRepository.findAll().stream().filter(todo -> todo.isTaskCompleted() == completed).collect(Collectors.toList());
    }

    public Optional<Todo> getById(UUID uuid) {
        return this.todoRepository.findById(uuid);
    }

    public boolean addTodo(TodoDTO todo) {
        Todo newTodo = new Todo();
        newTodo.setName(todo.getName());
        newTodo.setTaskCompleted(todo.isTaskCompleted());
        todoRepository.save(newTodo);
        return true;
    }

    public boolean removeTodo(UUID uuid) {
        if (getById(uuid).isPresent()) {
            todoRepository.deleteById(uuid);
            return true;
        }
        return false;
    }

    public boolean updateTodo(UUID uuid, TodoDTO todo) {
        Optional<Todo> currentTodo = getById(uuid);
        if (currentTodo.isPresent()) {
            currentTodo.get().setName(todo.getName());
            currentTodo.get().setTaskCompleted(todo.isTaskCompleted());
            this.todoRepository.save(currentTodo.get());
            return true;
        } else {
            return false;
        }
    }
}
