package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@NotNull
public class User {
    private int id;
    @EqualsAndHashCode.Include
    @NotBlank
    @Email
    private String email;
    @EqualsAndHashCode.Include
    @NotBlank
    private String login;
    private String name;
    private LocalDate birthday;
}
