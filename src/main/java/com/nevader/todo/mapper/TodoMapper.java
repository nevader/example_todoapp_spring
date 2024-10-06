package com.nevader.todo.mapper;

import com.nevader.todo.dto.TodoDto;
import com.nevader.todo.entity.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoMapper {

    TodoMapper INSTANCE = Mappers.getMapper(TodoMapper.class);

    TodoDto toDto(Todo entity);

    Todo toEntity(TodoDto dto);

    List<Todo> toEntityList(List<TodoDto> dtoList);

    List<TodoDto> toDtoList(List<Todo> entityList);
}
