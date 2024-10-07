package com.nevader.todo.service.impl;

import com.nevader.todo.dto.TodoDto;
import com.nevader.todo.entity.Todo;
import com.nevader.todo.exceptions.customExceptions.ResourceNotFoundException;
import com.nevader.todo.mapper.TodoMapper;
import com.nevader.todo.repository.TodoRepository;
import com.nevader.todo.service.TodoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;


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


    @Override
    public TodoDto getTodoById(Long id) {

        Todo todo = todoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Todo", "id", id));

        return todoMapper.toDto(todo);
    }

    @Override
    public TodoDto updateTodoById(Long id, TodoDto todoDto) {

        Todo todo = todoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Todo", "id", id));

        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());

        todoRepository.save(todo);

        return todoMapper.toDto(todo);
    }

    @Override
    public List<TodoDto> getAllTodos() {

        List<Todo> allTodos = todoRepository.findAll();

        return todoMapper.toDtoList(allTodos);

    }

    @Override
    @Transactional
    public void deleteTodoById(Long id) {

        Todo todo = todoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Todo", "id", id));

        todoRepository.delete(todo);

    }

    @Override
    public TodoDto completeTodoById(Long id) {

            Todo todo = todoRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("Todo", "id", id));

            todo.setCompleted(true);

            todoRepository.save(todo);

            return todoMapper.toDto(todo);
    }

    @Override
    public TodoDto uncompleteTodoById(Long id) {

            Todo todo = todoRepository.findById(id).orElseThrow(
                    () -> new ResolutionException("Todo not found with id: " + id));

            todo.setCompleted(false);

            todoRepository.save(todo);

            return todoMapper.toDto(todo);
    }


}
