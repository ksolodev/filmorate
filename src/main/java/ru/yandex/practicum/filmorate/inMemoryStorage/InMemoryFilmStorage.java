package ru.yandex.practicum.filmorate.inMemoryStorage;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class InMemoryFilmStorage implements FilmStorage {
    private final List<Film> films = new ArrayList<>();

    @Override
    public Film add(Film film) {
        films.add(film);
        return film;
    }

    @Override
    public boolean delete(Film film) {
        return films.remove(film);
    }

    @Override
    public List<Film> getFilms() {
        return films;
    }

    @Override
    public Optional<Film> find(String name) {
        for (Film f: films) {
            if (f.getName().equals(name)) {
                return Optional.of(f);
            }
        }
        return Optional.empty();
    }
}
