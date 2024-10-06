package com.nevader.todo.service;

import com.nevader.todo.dto.TodoDto;

import java.util.List;

public interface TodoService {

    TodoDto addTodo(TodoDto todoDto);

    TodoDto getTodoById(Long id);

    TodoDto updateTodoById(Long id, TodoDto todoDto);

    List<TodoDto> getAllTodos();

    void deleteTodoById(Long id);

    TodoDto completeTodoById(Long id);

    TodoDto uncompleteTodoById(Long id);

}
