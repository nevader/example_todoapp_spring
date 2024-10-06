package com.nevader.todo.dto;


import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "Title of the todo", example = "Buy milk")
    private String title;

    @Schema(description = "Description of the todo", example = "Buy milk at the store")
    private String description;

    @Schema(description = "Status of the todo", example = "false")
    private boolean completed;

}
