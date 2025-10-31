package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Duration;
import java.time.LocalDate;

@Data
@NotNull
public class Film {
    private int id;
    @EqualsAndHashCode.Include
    @NotBlank
    private String name;
    private String description;
    private LocalDate releaseDate;
    private Duration duration;
}
