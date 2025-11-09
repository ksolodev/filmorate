package ru.yandex.practicum.filmorate.storage.inMemoryStorage;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import java.util.*;

@Component
public class InMemoryFilmStorage implements FilmStorage {

    private final Map<String, Film> films = new HashMap<>();

    @Override
    public Film add(Film film) {
        films.put(film.getName(), film);
        return film;
    }

    @Override
    public boolean delete(String name) {
        return films.remove(name, films.get(name));
    }

    @Override
    public Collection<Film> getFilms() {
        return films.values();
    }

    @Override
    public Optional<Film> find(String name) {
        return Optional.ofNullable(films.get(name));
    }
}
