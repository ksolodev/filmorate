package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;
import java.util.Optional;

public interface FilmStorage {
    Film add(Film film);

    boolean delete(Film film);

    List<Film> getFilms();

    Optional<Film> find(String name);
}
