package com.nevader.todo.service.impl;

import com.nevader.todo.dto.TodoDto;
import com.nevader.todo.entity.Todo;
import com.nevader.todo.mapper.TodoMapper;
import com.nevader.todo.repository.TodoRepository;
import com.nevader.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor

public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {

        Todo todo = todoMapper.toEntity(todoDto);

        todoRepository.save(todo);

        return todoMapper.toDto(todo);
    }
}
