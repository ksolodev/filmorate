package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;
import java.util.Optional;

public interface FilmStorage {
    Film add(Film film);

    boolean delete(String name);

    Collection<Film> getFilms();

    Optional<Film> find(String name);
}
