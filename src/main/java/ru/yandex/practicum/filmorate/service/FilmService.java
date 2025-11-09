package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import java.time.LocalDate;
import java.util.Collection;

@Slf4j
@Service
public class FilmService {
    private static final LocalDate FIRST_FILM_DATE = LocalDate.of(1895, 12, 28);

    private final FilmStorage filmStorage;

    public FilmService(@Qualifier("filmDbStorage") FilmStorage filmStorage) {
        this.filmStorage = filmStorage;
    }

    public Collection<Film> findAll() {
        return filmStorage.getFilms();
    }

    public Film create(Film film) {
        checkFilmOnExceptions(film);
        if (filmStorage.find(film.getName()).isPresent()) {
            log.error("Фильм уже есть в списке для: {}", film);
            throw new FilmAlreadyExistException("Фильма уже есть в списке");
        }
        return filmStorage.add(film);
    }

    public Film put(Film film) {
        checkFilmOnExceptions(film);
        return filmStorage.add(film);
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
        if (film.getDuration() < 0) {
            log.error("Указана отрицательная продолжительность фильма для: {}", film);
            throw new NegativeFilmLengthException("Продолжительность фильма должна быть положительной");
        }
    }
}
