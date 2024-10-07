package com.nevader.todo.controller;

import com.nevader.todo.dto.TodoDto;
import com.nevader.todo.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")

@RequiredArgsConstructor
@Tag(
        name = "CRUD Todo",
        description = "CRUD Todo API"
)

public class TodoController {

    private final TodoService todoService;


    @Operation(
            summary = "Add Todo",
            description = "Add a new todo"
    )
    @ApiResponse(responseCode = "201", description = "Todo added successfully")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @ApiResponse(responseCode = "404", description = "Resource not found")

    @PostMapping
    public ResponseEntity<?> addTodo(@Valid @RequestBody TodoDto todoDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            StringBuilder errorMessage = new StringBuilder();
            bindingResult.getAllErrors().forEach(error -> errorMessage.append(error.getDefaultMessage()).append("\n"));
            return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);

        }

        TodoDto savedTodo = todoService.addTodo(todoDto);
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);

    }


    @Operation(
            summary = "Get Todo by ID",
            description = "Get a todo by ID"
    )
    @ApiResponse(responseCode = "200", description = "Todo retrieved successfully")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @ApiResponse(responseCode = "404", description = "Resource not found")

    @GetMapping("/{id}")
    public ResponseEntity<TodoDto> getTodoById(@PathVariable("id") Long id) {

        TodoDto todo = todoService.getTodoById(id);

        return new ResponseEntity<>(todo, HttpStatus.OK);

    }


    @Operation(
            summary = "Get all Todos",
            description = "Get all todos"
    )
    @ApiResponse(responseCode = "200", description = "Todos retrieved successfully")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @ApiResponse(responseCode = "404", description = "Resource not found")

    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos() {

        List<TodoDto> allTodos = todoService.getAllTodos();

        return new ResponseEntity<>(allTodos, HttpStatus.OK);
    }


    @Operation(
            summary = "Update Todo by ID",
            description = "Update a todo by ID"
    )
    @ApiResponse(responseCode = "200", description = "Todo updated successfully")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @ApiResponse(responseCode = "404", description = "Resource not found")

    @PutMapping("/{id}")
    public ResponseEntity<TodoDto> updateTodoById(@PathVariable("id") Long id, @RequestBody TodoDto todoDto) {

        TodoDto updatedTodo = todoService.updateTodoById(id, todoDto);

        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }


    @Operation(
            summary = "Delete Todo by ID",
            description = "Delete a todo by ID"
    )
    @ApiResponse(responseCode = "200", description = "Todo deleted successfully")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @ApiResponse(responseCode = "404", description = "Resource not found")

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodoById(@PathVariable("id") Long id) {

        todoService.deleteTodoById(id);

        return new ResponseEntity<>("Todo deleted successfully", HttpStatus.OK);
    }


    @Operation(
            summary = "Complete Todo by ID",
            description = "Complete a todo by ID"
    )
    @ApiResponse(responseCode = "200", description = "Todo completed successfully")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @ApiResponse(responseCode = "404", description = "Resource not found")

    @PatchMapping("/{id}/complete")
    public ResponseEntity<TodoDto> completeTodoById(@PathVariable("id") Long id) {

        TodoDto completedTodo = todoService.completeTodoById(id);

        return new ResponseEntity<>(completedTodo, HttpStatus.OK);
    }


    @Operation(
            summary = "Uncomplete Todo by ID",
            description = "Uncomplete a todo by ID"
    )
    @ApiResponse(responseCode = "200", description = "Todo uncompleted successfully")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @ApiResponse(responseCode = "404", description = "Resource not found")

    @PatchMapping("/{id}/uncomplete")
    public ResponseEntity<TodoDto> uncompleteTodoById(@PathVariable("id") Long id) {

        TodoDto uncompletedTodo = todoService.uncompleteTodoById(id);

        return new ResponseEntity<>(uncompletedTodo, HttpStatus.OK);
    }
}
