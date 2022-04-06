package me.marcocortes.todo.service;

import me.marcocortes.todo.domain.Todo;

import java.util.List;

public interface TodoService {
    Todo saveTodo(Todo todo);
    Boolean deleteTodo(Long todo);
    List<Todo> getTodos();
    Boolean deleteChecked();
}
