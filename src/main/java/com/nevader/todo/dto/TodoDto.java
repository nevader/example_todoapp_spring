package com.nevader.todo.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Schema(name = "TodoDto", description = "Todo Data Transfer Object")

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class TodoDto {

    private Long id;

    @NotNull(message = "Title cannot be null")
    @NotBlank(message = "Title cannot be blank")
    @Schema(description = "Title of the todo", example = "Buy milk")
    private String title;

    @NotNull(message = "Description cannot be null")
    @NotBlank(message = "Description cannot be blank")
    @Schema(description = "Description of the todo", example = "Buy milk at the store")
    private String description;

    @NotNull(message = "Completed cannot be null")
    @Schema(description = "Status of the todo", example = "false")
    private boolean completed;

}
