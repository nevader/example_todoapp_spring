package com.nevader.todo.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserDto {

    private Long id;

    @Min(value = 3, message = "Name must be at least 3 characters long")
    @Max(value = 50, message = "Name must be less than 50 characters long")
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Min(value = 6, message = "Username must be at least 6 characters long")
    @Max(value = 20, message = "Username must be less than 20 characters long")
    @NotNull(message = "Username cannot be null")
    @NotBlank(message = "Username cannot be blank")
    private String username;

    @Min(value = 6, message = "Password must be at least 6 characters long")
    @Max(value = 20, message = "Password must be less than 20 characters long")
    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password cannot be blank")
    private String password;

    @Email
    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email cannot be blank")
    private String email;
}
