package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.film.*;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {
    private final Map<String, Film> films = new HashMap<>();
    private static final LocalDate FIRST_FILM_DATE = LocalDate.of(1895, 12, 28);

    @GetMapping
    public Collection<Film> findAll() {
        return films.values();
    }

    @PostMapping
    public Film create(@Valid @RequestBody Film film) {
        checkFilmOnExceptions(film);
        if (films.containsValue(film)) {
            log.error("Фильм уже есть в списке для: {}", film);
            throw new FilmAlreadyExistException("Фильма уже есть в списке");
        }
        films.put(film.getName(), film);

        return film;
    }

    @PutMapping
    public Film put(@Valid @RequestBody Film film) {
        checkFilmOnExceptions(film);
        films.put(film.getName(), film);
        return film;
    }

    private void checkFilmOnExceptions(Film film) {
        if (film.getName().trim().isBlank()) {
            log.error("Название не может быть пустым для: {}", film);
            throw new TheNameCannotBeEmptyException("Название не может быть пустым");
        }
        if (film.getDescription().length() > 200) {
            log.error("Превышен лимит длины описания в 200 символов для: {}", film);
            throw new DescriptionLengthExceededException("Превышен лимит длины описания в 200 символов");
        }
        if (film.getReleaseDate().isBefore(FIRST_FILM_DATE)) {
            log.error("Указана дата релиза ранее 28 декабря 1895 для: {}", film);
            throw new TooEarlyReleaseDateException("Дата релиза - не раньше 28 декабря 1895 года");
        }
        if (film.getDuration().isNegative()) {
            log.error("Указана отрицательная продолжительность фильма для: {}", film);
            throw new NegativeFilmLengthException("Продолжительность фильма должна быть положительной");
        }
    }
}
