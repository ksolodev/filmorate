package ru.yandex.practicum.filmorate.exception;

public class NegativeFilmLengthException extends RuntimeException {
    public NegativeFilmLengthException(String message) {
        super(message);
    }
}
