package ru.yandex.practicum.filmorate.exception.film;

public class TooEarlyReleaseDateException extends RuntimeException {
    public TooEarlyReleaseDateException(String message) {
        super(message);
    }
}
