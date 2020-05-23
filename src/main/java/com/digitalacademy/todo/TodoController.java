package com.digitalacademy.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TodoController {

    private TodoRepository todoRepository;

    @Autowired
    public void Todo(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping("/todos")
    public List<Todo> getAll() {
        return todoRepository.findAll();
    }

    @GetMapping("/todos/{id}")
    public Todo get(@PathVariable Long id) {
        return todoRepository.findById(id).orElseThrow(() -> new TodoNotFoundException(id));
    }

    @PostMapping("/todos")
    public Todo post(@RequestBody Todo todo) {
        return todoRepository.save(todo);
    }

    @PatchMapping("/todos/{id}")
    public Todo patch(@PathVariable Long id, @RequestBody TodoRequest todoRequest) {
        return todoRepository.findById(id).map(todo -> {
            todo.setText(Optional.ofNullable(todoRequest.getText()).orElse(todo.getText()));
            todo.setCompleted(Optional.ofNullable(todoRequest.getCompleted()).orElse(todo.isCompleted()));
            return todoRepository.save(todo);
        }).orElseThrow(() -> new TodoNotFoundException(id));
    }

    @DeleteMapping("/todos/{id}")
    public void delete(@PathVariable Long id) {
        todoRepository.deleteById(id);
    }
}
