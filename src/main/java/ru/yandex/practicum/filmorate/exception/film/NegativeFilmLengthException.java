package ru.yandex.practicum.filmorate.exception.film;

public class NegativeFilmLengthException extends RuntimeException {
    public NegativeFilmLengthException(String message) {
        super(message);
    }
}
